package com.dxctraining.wishlistmgt.service;

import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.wishlistmgt.dao.IWishedItemDao;
import com.dxctraining.wishlistmgt.entities.WishedItem;
import com.dxctraining.wishlistmgt.exceptions.InvalidArgumentException;

@Transactional
@Service
public class WishedItemServiceImpl implements IWishedItemService{

    @Autowired
    private IWishedItemDao dao;
    
    @Override
    public WishedItem save(WishedItem wishedItem) {
    	validate(wishedItem);
    	String id = generateId();
    	wishedItem.setId(id);
        wishedItem=dao.save(wishedItem);
        return wishedItem;
    }

    private String generateId() {
    	StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            int randNum = random.nextInt(100);
            builder.append(randNum);
        }
        String id = builder.toString();
		return id;
	}

	private void validate(WishedItem wishedItem) {
		if(wishedItem == null) {
			throw new InvalidArgumentException("wishedItem should not be null");
		}
		
	}

    @Override
    public List<WishedItem> displayAllWishedItems(){
        List<WishedItem>list = dao.findAll();
        return list;
    }

	@Override
	public List<WishedItem> findAllById(int customerId) {
		validateId(customerId);
		List<WishedItem>list = dao.findAllById(customerId);
		return list;
	}
	
	private void validateId(int customerId) {
		if(customerId == 0) {
			throw new InvalidArgumentException("id should not be null");
		}
		
	}

}