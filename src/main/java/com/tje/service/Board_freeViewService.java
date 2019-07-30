package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.SimpleBoardFreeView;
import com.tje.repo.SimpleBoardFreeViewDAO;

@Service
public class Board_freeViewService {

	@Autowired
	private SimpleBoardFreeViewDAO simpleBoardFreeViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = simpleBoardFreeViewDAO.selectOne((SimpleBoardFreeView) args);

		return result;
	}
}
