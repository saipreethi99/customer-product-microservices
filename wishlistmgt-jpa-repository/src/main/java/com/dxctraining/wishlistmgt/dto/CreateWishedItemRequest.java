package com.dxctraining.wishlistmgt.dto;

public class CreateWishedItemRequest {
	
	private int customerId;

    private String productId;
    
    public CreateWishedItemRequest() {
    	
    }
    
    public CreateWishedItemRequest(int customerId, String productId) {
    	this.customerId = customerId;
    	this.productId = productId;
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
}