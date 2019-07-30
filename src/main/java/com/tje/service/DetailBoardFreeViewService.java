package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.DetailBoardFreeView;
import com.tje.repo.DetailBoardFreeViewDAO;

//정보수정 서비스
@Service
public class DetailBoardFreeViewService {
	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;
	
	public Object service(DetailBoardFreeView detailBoardFreeView) {
		Object result=null;
		result=detailBoardFreeViewDAO.selectOne(detailBoardFreeView);
		return result;
	}
}
