package com.dxctraining.mongodb.productmgt.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.dxctraining.mongodb.productmgt.dto.CreateProductRequest;
import com.dxctraining.mongodb.productmgt.dto.ProductDto;
import com.dxctraining.mongodb.productmgt.entities.Product;
import com.dxctraining.mongodb.productmgt.service.IProductService;
import com.dxctraining.mongodb.productmgt.util.ProductUtil;

@RequestMapping("/products")
@RestController
public class ProductRestController {

    @Autowired
    private IProductService productservice;

    @Autowired
    private ProductUtil util;

    @PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto add(@RequestBody CreateProductRequest requestData) {
		Product product = new Product(requestData.getName());
		product = productservice.save(product);
		ProductDto response = util.productDto(product);
		return response;
	}
	
	@GetMapping("/get/{id}")
	public ProductDto findProductById(@PathVariable("id")String id) {
		Product product = productservice.findProductById(id);
		ProductDto response = util.productDto(product);
		return response;
	}
	
	@GetMapping("/get/product/{name}")
	public List<ProductDto> fetchProductByName(@PathVariable("name")String name){
		List<Product>list = productservice.findProductByName(name);
		List<ProductDto> response = new ArrayList<>();
		for(Product product:list) {
			ProductDto productDto = util.productDto(product);
			response.add(productDto);
		}
		return response;
		
	}
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public List<ProductDto> fetchAllProducts(){
		List<Product> list = productservice.displayAllProducts();
		List<ProductDto>response = new ArrayList<>();
		for(Product product:list) {
			ProductDto dto = util.productDto(product);
			response.add(dto);
		}
		return response;
	}
}