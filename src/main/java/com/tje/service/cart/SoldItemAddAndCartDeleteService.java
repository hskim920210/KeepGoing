package com.tje.service.cart;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tje.model.Cart;
import com.tje.model.Sold_item;
import com.tje.repo.CartDAO;
import com.tje.repo.Sold_itemDAO;

@Service
public class SoldItemAddAndCartDeleteService {
	@Autowired
	private Sold_itemDAO sold_itemDAO;
	@Autowired
	private CartDAO cartDAO;
	
	@Transactional
	public Object service(List<Sold_item> args1, List<Cart> args2) throws Exception {
		Object result=null;
		
		result=sold_itemDAO.batchInsert(args1);
		
		if(true)
			throw new Exception();
		
		result=cartDAO.batchDelete(args2);
		
		return result;
	}
}
