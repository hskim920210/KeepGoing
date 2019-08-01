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
	private DetailBoardReviewViewSelectOneService dbrvsoService;
	@Autowired
	private Review_MapInsertService r_miService;
	@Autowired
	private ReviewWriteTransactionService rwtService;
	

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
	public String ReviewWritePost(Board_Review b_r, Review_Map r_m, Model model) {
		System.out.println(r_m.getSelectedAddress());
		if( r_m.getSelectedAddress().equals("0") ) { // 주소 입력 안한 경우
			int b_rInsert = (Integer)b_riService.service(b_r);
			if (b_rInsert == 1) {
				model.addAttribute("resultMsg", "글 등록 완료 (주소 관련 정보 미 기재 등록)");
			} else {
				model.addAttribute("resultMsg", "글 등록 실패 (ReviewWritePost메소드 부분)");
			}
			return "reviewWriteResult";
		} else { // 주소 입력 한경우
			System.out.println(r_m.getSelectedAddress());
			System.out.println(r_m.getSelectedLat());
			System.out.println(r_m.getSelectedLng());
			System.out.println("-------------------");
			try {
				boolean transacResult = rwtService.service(b_r, r_m);
				if ( transacResult ) {
					model.addAttribute("resultMsg", "글 등록 완료 (주소 관련 정보 포함 등록)");
					return "reviewWriteResult";
				}
			} catch (Exception e) {
				model.addAttribute("resultMsg", "글 등록 실패 (ReviewWritePost메소드 부분)");
				e.printStackTrace();
			}
			return "reviewWriteResult";
		}

	}
	
	@GetMapping("/review/detail/{board_id}")
	public String detatilReview(Model model, @PathVariable(value="board_id") Integer board_id) {
		System.out.println("detail 진입");
		
		DetailBoardReviewView dbrv = new DetailBoardReviewView();
		dbrv.setBoard_id(board_id);
		DetailBoardReviewView result = (DetailBoardReviewView)dbrvsoService.service(dbrv);
		model.addAttribute("detailReview", result);
		return "reviewDetail";
	}
	
	
	
}
