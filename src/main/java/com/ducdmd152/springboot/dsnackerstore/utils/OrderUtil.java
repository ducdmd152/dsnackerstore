package com.ducdmd152.springboot.dsnackerstore.utils;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ducdmd152.springboot.dsnackerstore.cart.CartModel;
import com.ducdmd152.springboot.dsnackerstore.order.Order;
import com.ducdmd152.springboot.dsnackerstore.order.OrderDetail;
import com.ducdmd152.springboot.dsnackerstore.product.ProductModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class OrderUtil {
	@Autowired
	private ProductUtil productUtil;

	public Order genOrderBy(CartModel cart, String[] checkedItems) {
		if (cart == null) {
			return null;
		}
		Order order = new Order();
		List<ProductModel> productsInCart = cart.getSelectedProductsInCart(checkedItems);
		for (ProductModel product : productsInCart) {

			OrderDetail orderDetail = genOrderDetailBy(product);
			order.addOrderDetail(orderDetail);
		}
		return order;
	}

	public OrderDetail genOrderDetailBy(ProductModel productModel) {
		if (productModel == null) {
			return null;
		}
		OrderDetail orderDetail = new OrderDetail();
		orderDetail.setSku(productModel.getSku());
		orderDetail.setPrice(productModel.getPrice());
		orderDetail.setQuantity(productModel.getQuantity());
		orderDetail.setTotal(productModel.getTotal());

		return orderDetail;
	}

//	public String orderDetailsToJSON(List<OrderDetail> orderDetails) {
//		if (orderDetails == null) {
//			return null;
//		}
//
//		String result = "[";
//		ObjectMapper mapper = new ObjectMapper();
//
//		boolean bef = false;
//		for (OrderDetail orderDetail : orderDetails) {
//			if (bef)
//				result += ",";
//			try {
//				result += mapper.writeValueAsString(orderDetail);
//			} catch (JsonProcessingException e) {
//				// mention to log
//				e.printStackTrace();
//			}
//			bef = true;
//		}
//
//		result = result + "]";
//		return result;
//	}
//
//	public List<OrderDetail> getOrderDetailsFromJSONString(String orderDetailsJSON) {
//		ObjectMapper mapper = new ObjectMapper();
//		List<OrderDetail> orderDetails = null;
//		try {
//			orderDetails = mapper.readValue(orderDetailsJSON, new TypeReference<List<OrderDetail>>() {
//			});
//			System.out.println("Mapping...");
//		} catch (JsonMappingException e) {
//			// mention to log
//			e.printStackTrace();
//		} catch (JsonProcessingException e) {
//			// mention to log
//			e.printStackTrace();
//		}
//
//		return orderDetails;
//	}

	public boolean checkValidOrder(Order order) {
		List<OrderDetail> orderDetails = order.getOrderDetails();
		if(orderDetails == null) {
			return false;
		}

		for (OrderDetail orderDetail : orderDetails) {
			int requestQuantity = orderDetail.getQuantity();
			int availableQuantity = 0;

			ProductModel product = productUtil.getProductSyncOrderedQuantity(orderDetail.getSku());
			if (product != null) {
				availableQuantity = product.getQuantity();
			}

			if (requestQuantity > availableQuantity) {
				return false;
			}
		}
		return true;
	}
}
