package com.tje.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.tje.model.*;
import com.tje.service.*;

@Controller
public class NoticeController {

	@Autowired
	private Board_NoticeSelectAllByBoardIdDescService b_nsabdService;
	@Autowired
	private Board_NoticeSelectHeadService b_nshService;
	@Autowired
	private Board_NoticeWriteService b_nwService;

	
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

		return "add_notice";
	}

	
	@PostMapping("/add_notice")
	public String NoticeWrite(Board_Notice board_Notice) {
		int r=(int) b_nwService.service(board_Notice);
		if(r==1) {
			return "redirect:notice";
		}
		
		return "글 등록에 실패하였습니다";
		
	}
	
}
