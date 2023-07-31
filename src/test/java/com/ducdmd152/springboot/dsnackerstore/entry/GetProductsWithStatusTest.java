package com.ducdmd152.springboot.dsnackerstore.entry;

import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.testng.MockitoTestNGListener;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.ducdmd152.springboot.dsnackerstore.product.Product;
import com.ducdmd152.springboot.dsnackerstore.product.ProductJpaRepository;
import com.ducdmd152.springboot.dsnackerstore.product.ProductServiceJpaRepositoryImpl;

@SpringBootTest
@Listeners(MockitoTestNGListener.class)
public class GetProductsWithStatusTest {
	@Mock
	private ProductJpaRepository productRepository;
	@InjectMocks
    private ProductServiceJpaRepositoryImpl productService;
	
	@BeforeClass
	public void setup() {
	}
	
	// TestNG test for init/inject mock resources
	@Test(priority = 0)
	public void injectedComponentsAreNotNull() {
		assertTrue(productRepository != null);
		assertTrue(productService != null);
		System.out.println("Init Mock resources successfully!");
	}

	// TestNG test for save(user) method for both Repository & Service
	@Test(priority = 1)
	public void testGetProductWithStatus_thenReturnProductListAllRightStatus() {
		// getProductsByStatus(false) => expected AllFalse
		// 1. Return mock List<Product>
		List<Product> productList = new ArrayList<>();
		for(int i = 0; i < 10; i++) {
			Product product = new Product();
			product.setSku("sku" + System.currentTimeMillis());
			product.setStatus(false);
			productList.add(product);
		}
		
		 Mockito.lenient().when(productRepository.findAllByStatus(false))
         .thenReturn(productList);
		// 2. Check/Test with mock data => service's method => OK?
		 List<Product> result = productService.getProductsByStatus(false);
		 boolean allFalse = true;
		 for(Product product : result) {
				if (product.isStatus() == true) {
					allFalse = false;
					break;
				}
			}
		 
		 assertTrue(allFalse == true);
	}
	
	@AfterClass
	public void finish() {
	}
}
