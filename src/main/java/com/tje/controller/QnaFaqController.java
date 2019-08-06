package com.tje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.BoardService.SimpleBoardFreeViewSelectByDateDescService;
import com.tje.model.*;
import com.tje.service.notice.*;
import com.tje.service.faq.*;

@Controller
public class QnaFaqController {
	@Autowired
	private SimpleBoardFreeViewSelectByDateDescService sbfvsbddService;
	@Autowired
	private Board_qnaService b_qService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_FaqSelectAllByBoardIdDescService b_faqService;
	@Autowired
	private Board_FaqWriteService b_faqwService;
	@Autowired
	private Board_QnaReadService b_qrService;
	@Autowired
	private Board_QnaReadUpService b_qruService;
	@Autowired
	private Board_QnaReadDownService b_qrdService;
	
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
		
		int r=(int) b_qService.service(board_Free);
		if(r==1) {
			return "qna";
		}
		
		return "글 등록에 실패하였습니다";
	}
	
	@GetMapping("/qna/{board_id}")
	public String NoticeRead(Model model, @PathVariable(value="board_id") Integer board_id) {
		Board_Free qna = new Board_Free();
		qna.setBoard_id(board_id);
		
		model.addAttribute("qnaRead", b_qrService.service(qna));
		model.addAttribute("qnaReadUp", b_qruService.service(qna));
		model.addAttribute("qnaReadDown", b_qrdService.service(qna));
		return "qna_read";
	}
	
	@RequestMapping("/faq")
	public String Faq(Model model) {
		List<Board_Faq> faq = (List<Board_Faq>)b_faqService.service();
		model.addAttribute("faq", faq);
		return "faq";
	}
	
	@GetMapping("/faq/write")
	public String FaqWrite() {
		return "faqWrite";
	}
	
	@RequestMapping("/faq/write")
	public String FaqWrite(Board_Faq board_qna ) {
		int r=(int) b_faqwService.service(board_qna);
		if(r==1) {
			return "redirect:faq";
		}
		
		return "글 등록에 실패하였습니다";		
	}
	
	@GetMapping("/faq/rev")
	public String FaqRevice() {
		
		return "faqWrite";
	}
	
	

}
