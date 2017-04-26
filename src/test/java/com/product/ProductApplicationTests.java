package com.product;

import com.product.dao.Product;
import com.product.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductApplicationTests {

	@Autowired
	ProductService service;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testProduct(){

		Product added = addProduct();
		assertNotNull(added);
		assertTrue(added.getSku() == 999);
		assertTrue(added.getName().equals("First Product"));

		getProduct(999);

		getProducts();

		service.deleteProduct(999);
	}

	private void getProducts(){

		List<Product> productList = service.getProducts();
		assertNotNull(productList);
		assertTrue(productList.size() == 1);

	}

	private void getProduct(Integer sku){
		Product fetched = service.getProduct(sku);
		assertNotNull(fetched);
		assertTrue(fetched.getSku() == 999);
		assertEquals("First Product", fetched.getName());
	}

	private Product addProduct(){

		Product product = new Product();
		product.setSku(999);
		product.setName("First Product");
		return service.addProduct(product);
	}

}
