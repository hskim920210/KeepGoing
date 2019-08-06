package com.tje.service.faq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Free;
import com.tje.repo.Board_qnaDAO;

@Service
public class Board_QnaReadDownService {

	@Autowired
	private Board_qnaDAO board_qnaDAO;

	public Object service(Object args) {
		Object result = null;

		result = board_qnaDAO.downQna((Board_Free) args);

		return result;
	}
}
