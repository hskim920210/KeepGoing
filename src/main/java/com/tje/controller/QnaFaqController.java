package com.tje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.BoardService.Board_freeService;
import com.tje.BoardService.SimpleBoardFreeViewSelectByDateDescService;
import com.tje.model.*;
import com.tje.service.*;

@Controller
public class QnaFaqController {
	@Autowired
	private SimpleBoardFreeViewSelectByDateDescService sbfvsbddService;
	@Autowired
	private Board_freeService b_fService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_FaqSelectAllByBoardIdDescService b_faqService;
	@Autowired
	private Board_FaqWriteService b_faqwService;
	
	@RequestMapping("/qna")
	public String Qna(Model model) {
		List<SimpleBoardFreeView> simpleBoardFreeViewList = (List<SimpleBoardFreeView>)sbfvsbddService.service();
		model.addAttribute("simpleBoardFreeViewList", simpleBoardFreeViewList);
		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
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
	public String Faq(Model model) {
		List<Board_Faq> faq = (List<Board_Faq>)b_faqService.service();
		model.addAttribute("faq", faq);
		return "faq";
	}
	
	@RequestMapping("/faq/write")
	public String FaqWrite(Board_Faq board_qna ) {
		int r=(int) b_faqwService.service(board_qna);
		if(r==1) {
			return "faq";
		}
		
		return "글 등록에 실패하였습니다";		
	}
	
	
	

}
