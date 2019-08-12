package com.tje.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tje.model.Member;
import com.tje.service.member.AllMemberService;
import com.tje.service.mypage.MembersDeleteService;

@Controller
public class MyPageController {
	
	@Autowired
	private AllMemberService amService;
	@Autowired
	private MembersDeleteService mdsService;
	
	@RequestMapping(value = "/notAdminLogin")
	public ModelAndView notAdminLogin() {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("notAdminLogin");
		mav.addObject("msg", "권한이 필요한 기능입니다.");
		
		return mav;
	}
	
	@GetMapping(value = "/mypage")
	public String mypage() {
		return "/mypage";
	}
	
	@GetMapping(value = "/mypage/member_management")
	public String member_management(Model model) {
		
		model.addAttribute("members", amService.service());
		
		return "/member_management";
	}
	
	@PostMapping(value = "/mypage/members_delete", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String members_delete(
			@RequestBody List<Member> list) {
		
		List<Member> memberList=new ArrayList<Member>();
		
		for (Member model : list) {
			Member member=new Member();
			member.setNickname(model.getNickname());
			
			memberList.add(member);
		}
		
		try {
			mdsService.service(memberList);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@GetMapping(value = "/mypage/permission_setting")
	public String permission_setting() {
		
		return "/permission_setting";
	}
}
