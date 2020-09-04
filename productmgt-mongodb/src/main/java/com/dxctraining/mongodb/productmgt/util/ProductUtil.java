package com.dxctraining.mongodb.productmgt.util;

import org.springframework.stereotype.Component;

import com.dxctraining.mongodb.productmgt.dto.ProductDto;
import com.dxctraining.mongodb.productmgt.entities.Product;

@Component
public class ProductUtil{
	public ProductDto productDto(Product product) {
		ProductDto dto=new ProductDto(product.getId(),product.getName());
		return dto;
	}
}