package com.ducdmd152.springboot.dsnackerstore.order;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
//@Table(name="order")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="date_buy")
    private Timestamp dateBuy;
	
	@Column(name="total")
    private float total;
	
	@Column(name="name")
    private String name;
	
	@Column(name="address")
    private String address;
	
	@Column(name="phone")
    private String phone;

	@Column(name="username")
    private String username;

	private List<OrderDetail> orderDetails;
	public void addOrderDetail(OrderDetail orderDetail) {
		if(orderDetail==null) {
			return;
		}
		
		if(orderDetails==null) {
			orderDetails = new ArrayList<>();
		}
		
		orderDetails.add(orderDetail);
		orderDetail.setOrderId(id);
	}
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	

	public Order() {
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getDateBuy() {
		return dateBuy;
	}

	public void setDateBuy(Timestamp dateBuy) {
		this.dateBuy = dateBuy;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", dateBuy=" + dateBuy + ", total=" + total + ", name=" + name + ", address="
				+ address + ", phone=" + phone + ", username=" + username + ", orderDetails=" + orderDetails + "]";
	}

	

	

    
}
