package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.*;
import com.tje.repo.*;

@Service
public class FreeViewCnt_UpdateService {

	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;

	public Object service(Object args) {
		Object result = null;

		result = detailBoardFreeViewDAO.update_view_cnt((DetailBoardFreeView)args);

		return result;
	}
}
