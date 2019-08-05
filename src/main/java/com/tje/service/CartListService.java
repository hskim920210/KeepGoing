package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Cart;
import com.tje.repo.CartDAO;

@Service
public class CartListService {
	@Autowired
	private CartDAO cartDAO;
	
	public Object service() {
		Object result=null;
		
		result=cartDAO.selectAll();
		
		return result;
	}
}
