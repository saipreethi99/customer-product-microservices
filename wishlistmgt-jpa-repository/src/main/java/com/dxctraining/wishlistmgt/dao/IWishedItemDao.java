package com.dxctraining.wishlistmgt.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dxctraining.wishlistmgt.entities.WishedItem;

public interface IWishedItemDao extends JpaRepository<WishedItem, String> {
	
	@Query("from WishedItem where customerId=:customerId")
	List<WishedItem> findAllById(@Param("customerId")int customerId);

}