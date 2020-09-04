package com.dxctraining.wishlistmgt.service;

import java.util.List;

import com.dxctraining.wishlistmgt.entities.WishedItem;

public interface IWishedItemService {

    WishedItem save(WishedItem wishedItem);
    
    List<WishedItem> findAllById(int customerId);
    
    List<WishedItem> displayAllWishedItems();

}