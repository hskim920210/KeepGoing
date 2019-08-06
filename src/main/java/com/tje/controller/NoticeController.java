package com.tje.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.tje.model.*;
import com.tje.service.notice.*;

@Controller
public class NoticeController {

	@Autowired
	private Board_NoticeSelectAllByBoardIdDescService b_nsabdService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_NoticeWriteService b_nwService;
	@Autowired
	private Board_NoticeReadService b_nrService;
	@Autowired
	private Board_NoticeReadUpService b_nruService;
	@Autowired
	private Board_NoticeReadDownService b_nrdService;

	
	@GetMapping("/notice")
	public String Notice(Model model) {
		List<Board_Notice> board_noticeList = (List<Board_Notice>)b_nsabdService.service();
		model.addAttribute("board_noticeList", board_noticeList);
		List<Board_Notice> board_noticeheadList = (List<Board_Notice>)b_nshService.service();
		model.addAttribute("board_noticeheadList", board_noticeheadList);
		return "notice";
	}
	
	@GetMapping("/notice/write")
	public String NoticeWrite() {

		return "noticeWrite";
	}

	
	@PostMapping("/add_notice")
	public String NoticeWrite(Board_Notice board_Notice) {
		int r=(int) b_nwService.service(board_Notice);
		if(r==1) {
			return "redirect:notice";
		}
		
		return "글 등록에 실패하였습니다";
	}
	
	@GetMapping("/notice/{board_id}")
	public String NoticeRead(Model model, @PathVariable(value="board_id") Integer board_id) {
		Board_Notice notice = new Board_Notice();
		notice.setBoard_id(board_id);
		
		model.addAttribute("noticeRead", b_nrService.service(notice));
		model.addAttribute("noticeReadUp", b_nruService.service(notice));
		model.addAttribute("noticeReadDown", b_nrdService.service(notice));
		return "notice_read";
	}
	
	
}
