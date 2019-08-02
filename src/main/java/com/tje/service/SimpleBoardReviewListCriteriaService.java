package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.page.Criteria;
import com.tje.repo.*;

@Service
public class SimpleBoardReviewListCriteriaService {
	@Autowired
	private SimpleBoardReviewViewDAO simpleBoardReviewViewDAO;
	
	public Object service(Criteria criteria) {
		Object result=null;
		result=simpleBoardReviewViewDAO.listCriteria(criteria.getPageStart(), criteria.getPerPageNum());
		return result;
	}
}
