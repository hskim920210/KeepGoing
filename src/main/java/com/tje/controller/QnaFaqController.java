package com.tje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.tje.model.*;
import com.tje.service.*;

@Controller
public class QnaFaqController {
	@Autowired
	private SimpleBoardFreeViewSelectByDateDescService sbfvsbddService;
	@Autowired
	private Board_freeService b_fService;
	
	@RequestMapping("/qna")
	public String Qna(Model model) {
		List<SimpleBoardFreeView> simpleBoardFreeViewList = (List<SimpleBoardFreeView>)sbfvsbddService.service();
		model.addAttribute("simpleBoardFreeViewList", simpleBoardFreeViewList);
		return "qna";
	}
	
	@GetMapping("/qna/write")
	public String QnaWrite() {
		return "add_qna";
	}
	
	@PostMapping("/qna/write")
	public String QnaWrite(Board_Free board_Free) {
		
		int r=(int) b_fService.service(board_Free);
		if(r==1) {
			return "qna";
		}
		
		return "글 등록에 실패하였습니다";
		
	}
	
	@RequestMapping("/faq")
	public String Faq() {
		return "faq";
	}
	
	
	

}
