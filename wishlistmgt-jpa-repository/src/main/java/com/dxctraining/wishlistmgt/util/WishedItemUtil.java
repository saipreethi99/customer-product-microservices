package com.dxctraining.wishlistmgt.util;

import org.springframework.stereotype.Component;

import com.dxctraining.wishlistmgt.dto.WishedItemDto;
import com.dxctraining.wishlistmgt.entities.WishedItem;

@Component
public class WishedItemUtil {

    public WishedItemDto wishedItemDto(WishedItem wishedItem, int customerId, String customerName, String productId, String productName){
        WishedItemDto dto=new WishedItemDto(wishedItem.getId());
        dto.setCustomerId(customerId);
        dto.setCustomerName(customerName);
        dto.setProductId(productId);
        dto.setProductName(productName);
        return dto;
    }

}