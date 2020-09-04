package com.dxctraining.mongodb.productmgt.entities;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
public class Product {

	@Id
	private String id;

	private String name;

	public Product() {

	}

	public Product(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object arg) {
		if (this == arg) {
			return true;
		}
		if (arg == null || getClass() != arg.getClass()) {
			return false;
		}
		Product product = (Product) arg;
		return Objects.equals(id, product.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
