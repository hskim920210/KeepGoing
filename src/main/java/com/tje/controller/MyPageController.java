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
import com.tje.model.Member_Auth;
import com.tje.service.member.AllMemberService;
import com.tje.service.mypage.AllMemberAuthService;
import com.tje.service.mypage.MembersAuthDeleteService;
import com.tje.service.mypage.MembersAuthUpdateAndDeleteService;
import com.tje.service.mypage.MembersDeleteService;

@Controller
public class MyPageController {
	
	@Autowired
	private AllMemberService amService;
	@Autowired
	private MembersDeleteService mdsService;
	@Autowired
	private MembersAuthUpdateAndDeleteService mauadService;
	@Autowired
	private AllMemberAuthService amaService;
	@Autowired
	private MembersAuthDeleteService madService;
	
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
	public String permission_setting(Model model) {
		
		model.addAttribute("member_auth", amaService.service());
		
		return "/permission_setting";
	}
	
	@PostMapping(value = "/mypage/members_auth_update", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String members_auth_update(
			@RequestBody List<Member_Auth> list) {
		
		List<Member> memberList=new ArrayList<Member>();
		
		for (Member_Auth model : list) {
			Member member=new Member();
			member.setNickname(model.getNickname());
			member.setAuth(model.getReq_auth());
			
			memberList.add(member);
		}
		
		try {
			mauadService.service(memberList, list);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@PostMapping(value = "/mypage/members_auth_delete", produces = "application/text;charset=utf-8")
	@ResponseBody
	public String members_auth_delete(
			@RequestBody List<Member_Auth> list) {
		
		try {
			madService.service(list);
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
	
	@GetMapping(value = "/mypage/board_management")
	public String board_management(Model model) {
		
		model.addAttribute("member_auth", amaService.service());
		
		return "/board_management";
	}
}
