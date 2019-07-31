package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Free;
import com.tje.model.DetailBoardFreeView;
import com.tje.repo.Board_freeDAO;
import com.tje.repo.DetailBoardFreeViewDAO;

//게시판 수정 서비스
@Service

public class DetailBoardFreeView_UpdateService {
	@Autowired
	private DetailBoardFreeViewDAO detailBoardFreeViewDAO;
	
	public Object service(DetailBoardFreeView detailBoardFreeView) {
		int result=0;
		try {
			result=detailBoardFreeViewDAO.update(detailBoardFreeView);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}
