package com.dxctraining.mongodb.productmgt.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.mongodb.productmgt.entities.Product;
import com.dxctraining.mongodb.productmgt.exceptions.InvalidArgumentException;

@ExtendWith(SpringExtension.class)
@DataMongoTest
@Import(ProductServiceImpl.class)
class ProductServiceImplTest {
	
	@Autowired
	private IProductService productservice;

	@Test
	public void testAdd_1() {
		Executable executable=()->productservice.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, executable);
	}
	
	@Test
	public void testAdd_2() {
		String name = "Lappy";
		Product product = new Product(name);
		product = productservice.save(product);
		Product fetched = productservice.findProductById(product.getId());
		Assertions.assertEquals(product.getId(),fetched.getId());
		Assertions.assertEquals(name,fetched.getName());
	}
}