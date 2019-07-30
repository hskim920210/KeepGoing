package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.repo.*;
import com.tje.model.*;

@Service
public class Board_ReviewInsertService {
	@Autowired
	private Board_ReviewDAO board_ReviewDAO;
	
	public Object service(Board_Review b_r) {
		Object result=null;
		result=board_ReviewDAO.insert(b_r);
		return result;
	}
}
