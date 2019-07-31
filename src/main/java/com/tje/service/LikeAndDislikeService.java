package com.tje.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tje.model.LikeAndDislike;
import com.tje.repo.LikeAndDislikeDAO;

@Service
public class LikeAndDislikeService {

	@Autowired
	private LikeAndDislikeDAO likeAndDislikeDAO;

	public Object selectOne(Object args) {
		Object result = null;

		result = likeAndDislikeDAO.selectOne((LikeAndDislike)args);

		return result;
	}
	
	public Object insert(Object args) {
		Object result = null;

		result = likeAndDislikeDAO.insert((LikeAndDislike)args);

		return result;
	}
	
	public Object update(Object args) {
		Object result = null;

		result = likeAndDislikeDAO.update_islike((LikeAndDislike)args);

		return result;
	}
	
	public Object delete(Object args) {
		Object result = null;

		result = likeAndDislikeDAO.delete((LikeAndDislike)args);

		return result;
	}
}
