package com.tje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tje.model.*;
import com.tje.service.*;

@Controller
public class Board_ReviewController {
	@Autowired
	private SimpleBoardReviewViewSelectByDateDescService sbrvsbddService;
	@Autowired
	private Board_ReviewInsertService b_riService;
	@Autowired
	private SimpleBoardReviewViewSelectOneService sbrvsoService;

	@GetMapping("/review")
	public String Review(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "review";
	}
	
	@GetMapping("/review/write")
	public String ReviewWrite(Model model) {
		List<SimpleBoardReviewView> result = (List<SimpleBoardReviewView>)sbrvsbddService.service();
		model.addAttribute("simpleBoardReviewViewList", result);
		return "reviewWrite";
	}
	
	@PostMapping("/review/write")
	public String ReviewWritePost(Board_Review b_r, Model model) {
		if( (Integer)b_riService.service(b_r) != null ) {
			model.addAttribute("resultMsg", "글 등록이 완료되었습니다.");
		} else {
			model.addAttribute("resultMsg", "글 등록에 실패했습니다.");
		}
		return "reviewWriteResult";
	}
	
	@GetMapping("/review/detail/{board_id}")
	public String detatilReview(Model model, @PathVariable(value="board_id") Integer board_id) {
		System.out.println("detail 진입");
		
		SimpleBoardReviewView sbrv = new SimpleBoardReviewView();
		sbrv.setBoard_id(board_id);
		SimpleBoardReviewView result = (SimpleBoardReviewView)sbrvsoService.service(sbrv);
		model.addAttribute("detailReview", result);
		return "detailReview";
	}
	
	
	
}
