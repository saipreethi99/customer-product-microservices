package com.dxctraining.wishlistmgt.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.dxctraining.wishlistmgt.entities.WishedItem;
import com.dxctraining.wishlistmgt.exceptions.InvalidArgumentException;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(WishedItemServiceImpl.class)
class WishedItemServiceImplTest {
	
	@Autowired
	private IWishedItemService wishedItemService;

	@Test
	public void testAdd_1() {
		Executable executable =()->wishedItemService.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, executable);
	}
	
	@Test
	public void testAdd_2() {
		int customerId = 1;
		String productId = "g7";
		WishedItem wishedItem = new WishedItem(customerId,productId);
		wishedItem = wishedItemService.save(wishedItem);
		List<WishedItem>list = new ArrayList<>();
		list.add(wishedItem);
		WishedItem fetched = list.get(0);
		Assertions.assertEquals(1, list.size());
		Assertions.assertEquals(customerId, fetched.getCustomerId());
		Assertions.assertEquals(productId, fetched.getProductId());
	}

}
