package com.dxctraining.wishlistmgt.entities;

import java.util.Objects;

import javax.persistence.*;

@Entity
public class WishedItem {

    @Id
    private String id;

    private int customerId;
    
    private String productId;
    
    public WishedItem() {

    }

    public WishedItem(int customerId, String productId) {
         this.customerId = customerId;
         this.productId = productId;
    }

    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	@Override
    public boolean equals(Object arg) {
        if (this == arg) return true;
        if (arg == null || getClass() != arg.getClass()) {
            return false;
        }
        WishedItem wishedItem = (WishedItem) arg;
        return Objects.equals(id, wishedItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}