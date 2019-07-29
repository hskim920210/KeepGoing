package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.SimpleBoardItemView;
import com.tje.repo.SimpleBoardItemViewDAO;

@Service
public class ItemViewService {

	@Autowired
	private SimpleBoardItemViewDAO simpleBoardItemViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = simpleBoardItemViewDAO.selectOne((SimpleBoardItemView) args);

		return result;
	}
}
