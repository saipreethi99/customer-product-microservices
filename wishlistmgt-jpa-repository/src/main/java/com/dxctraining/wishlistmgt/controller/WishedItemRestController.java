package com.dxctraining.wishlistmgt.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.wishlistmgt.dto.CreateWishedItemRequest;
import com.dxctraining.wishlistmgt.dto.CustomerDto;
import com.dxctraining.wishlistmgt.dto.ProductDto;
import com.dxctraining.wishlistmgt.dto.WishedItemDto;
import com.dxctraining.wishlistmgt.entities.WishedItem;
import com.dxctraining.wishlistmgt.service.IWishedItemService;
import com.dxctraining.wishlistmgt.util.WishedItemUtil;

@RestController
@RequestMapping("/wishlist")
public class WishedItemRestController {

    @Autowired
    private IWishedItemService wisheditemservice;

    @Autowired
    private WishedItemUtil wishedItemUtil;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public WishedItemDto addWishlist(@RequestBody CreateWishedItemRequest requestData) {
		int customerId=requestData.getCustomerId();
		String productId = requestData.getProductId();
		WishedItem wishedItem = new WishedItem(customerId,productId);
		wishedItem = wisheditemservice.save(wishedItem);
		CustomerDto customerDto = fetchCustomerDetailsByCustomerId(customerId);
		customerDto.setCustomerId(customerId);
		ProductDto productDto = fetchProductDetailsByProductId(productId);
		WishedItemDto response = wishedItemUtil.wishedItemDto(wishedItem,customerId,customerDto.getName(),productId,productDto.getName());
		return response;
	}
    
    public CustomerDto fetchCustomerDetailsByCustomerId(int customerId) {
    	String url = "http://customermgt/customers/get/"+customerId;
    	CustomerDto dto = restTemplate.getForObject(url, CustomerDto.class);
		return dto;
    }
    
    public ProductDto fetchProductDetailsByProductId(String productId) {
    	String url = "http://productmgt/products/get/"+productId;
    	ProductDto dto = restTemplate.getForObject(url, ProductDto.class);
		return dto;
    }

    
    @GetMapping("/get/{id}")
	public List<WishedItemDto> findAllWishedItemsById(@PathVariable("id")int customerId) {
		List<WishedItem>list = wisheditemservice.findAllById(customerId);
		List<WishedItemDto>response = new ArrayList<>();
		for(WishedItem wishedItem:list) {
			String productId = wishedItem.getProductId();
        	ProductDto productDto = fetchProductDetailsByProductId(productId);
        	int custId = wishedItem.getCustomerId();
        	CustomerDto customerDto = fetchCustomerDetailsByCustomerId(custId);
			WishedItemDto dto = wishedItemUtil.wishedItemDto(wishedItem,custId,customerDto.getName(),productId, productDto.getName());
			response.add(dto);
		}
		return response;
	}

    @GetMapping
    public List<WishedItemDto> fetchAll() {
        List<WishedItem> list = wisheditemservice.displayAllWishedItems();
        List<WishedItemDto>response=new ArrayList<>();
        for (WishedItem wishedItem:list){
        	String productId = wishedItem.getProductId();
        	ProductDto productDto = fetchProductDetailsByProductId(productId);
        	int customerId = wishedItem.getCustomerId();
        	CustomerDto customerDto = fetchCustomerDetailsByCustomerId(customerId);
            WishedItemDto dto=wishedItemUtil.wishedItemDto(wishedItem,customerId,customerDto.getName(),productId,productDto.getName());
            response.add(dto);
        }
        return response;
    }
   
}