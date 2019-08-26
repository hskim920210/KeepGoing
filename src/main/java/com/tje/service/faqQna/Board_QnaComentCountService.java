package com.tje.service.faqQna;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.Board_Qna;
import com.tje.model.Comment;
import com.tje.repo.*;


@Service
public class Board_QnaComentCountService {

	@Autowired
	private CommentDAO commentDAO;

	public Object service(Object args) {
		Object result = null;

		result = commentDAO.selectCount((Comment) args);

		return result;
	}
}
