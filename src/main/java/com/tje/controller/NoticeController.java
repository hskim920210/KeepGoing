package com.tje.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import com.tje.model.*;
import com.tje.page.*;
import com.tje.service.*;

@Controller
public class NoticeController {

	@Autowired
	private Board_NoticeSelectAllByBoardIdDescService b_nsabdService;
	@Autowired
	private Board_NoticeWriteService b_nwService;

	
	@GetMapping("/notice")
	public String Notice(Model model) {
		List<Board_Notice> board_noticeList = (List<Board_Notice>)b_nsabdService.service();
		System.out.println(board_noticeList);
		model.addAttribute("board_noticeList", board_noticeList);
		return "notice";
	}
	
	@GetMapping("/notice/write")
	public String NoticeWrite() {

		return "add_notice";
	}
	
	@PostMapping("/notice/write")
	public String NoticeWrite(Board_Notice board_Notice) {
		
		int r=(int) b_nwService.service(board_Notice);
		if(r==1) {
			return "free_view";
		}
		
		return "글 등록에 실패하였습니다";
		
	}
	
}
