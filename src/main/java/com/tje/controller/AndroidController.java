package com.tje.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tje.model.Member;
import com.tje.model.SimpleBoardItemView;
import com.tje.service.board_item.AllItemListService;
import com.tje.service.member.MemberIDCheckService;
import com.tje.service.member.MemberInsertService;
import com.tje.service.member.MemberNickNameCheckService;

@Controller
public class AndroidController {
	
	@Autowired
	private MemberInsertService miService;
	@Autowired
	private MemberIDCheckService mIDcService;
	@Autowired
	private MemberNickNameCheckService mnncService;
	@Autowired
	private AllItemListService ailService;
	
	@GetMapping("/android/address_search")
	public String address_search() {
		
		return "address_search";
	}
	
	@PostMapping(value = "/android/regist", produces = "application/text; charset=utf8")
	@ResponseBody
	public String regist(Member member) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		
		Member nickCheckMemeber=(Member) mnncService.service(member);
		if(nickCheckMemeber!=null) {
			map.put("msg", "중복된 닉네임 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		Member idCheckMemeber=(Member) mIDcService.service(member);
		if(idCheckMemeber!=null) {
			map.put("msg", "중복된 아이디 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		
		int r=(int) miService.service(member);
		
		if(r==1) {
			map.put("msg", "회원가입을 축하합니다.");
		}else {
			map.put("msg", "회원가입에 실패하였습니다.");
		}
		
		String json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/sns_regist", produces = "application/text; charset=utf8")
	@ResponseBody
	public String sns_regist(Member member) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		
		Member nickCheckMemeber=(Member) mnncService.service(member);
		if(nickCheckMemeber!=null) {
			map.put("msg", "중복된 닉네임 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		Member idCheckMemeber=(Member) mIDcService.service(member);
		if(idCheckMemeber!=null) {
			map.put("msg", "중복된 아이디 입니다.");
			String json=gson.toJson(map);
			return json;
		}
		
		int r=(int) miService.service(member);
		
		if(r==1) {
			map.put("msg", "회원가입을 축하합니다.");
		}else {
			map.put("msg", "회원가입에 실패하였습니다.");
		}
		
		String json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String login(Member member, HttpSession session) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "존재하지 않는 ID 입니다.");
			json=gson.toJson(map);
			return json;
		}
		
		if(result.getPassword().equals(member.getPassword())) {
			login_result=true;
			session.setAttribute("login_member", result);
			map.put("login_result", login_result.toString());
			map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
			map.put("login_nickname", member.getNickname());
			json=gson.toJson(map);
			return json;
		}
		
		map.put("login_result", login_result.toString());
		map.put("login_msg", "정보가 일치하지 않습니다.");
		json=gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/naver_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String naver_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/kakao_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String kakao_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@PostMapping(value = "/android/google_login", produces = "application/text; charset=utf8")
	@ResponseBody
	public String google_login(Member member, HttpSession session) {
		
		System.out.println(member.getMember_id());
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean login_result=false;
		String json="";
		
		Member result=(Member) mIDcService.service(member);
		if(result==null) {
			map.put("login_result", login_result.toString());
			map.put("login_msg", "");
			json=gson.toJson(map);
			return json;
		}
		
		login_result = true;
		session.setAttribute("login_member", result);
		map.put("login_result", login_result.toString());
		map.put("login_msg", String.format("%s 님 환영합니다.", result.getNickname()));
		map.put("login_nickname", member.getNickname());
		json = gson.toJson(map);
		
		return json;
	}
	
	@GetMapping(value = "android/logout", produces = "application/text; charset=utf8")
	@ResponseBody
	public String logout(HttpSession session) {
		
		Gson gson=new Gson();
		HashMap<String, String> map=new HashMap<String, String>();
		Boolean logout_result=false;
		String json="";
		
		Member login_member=(Member) session.getAttribute("login_member");
		
		if(login_member!=null) {
			logout_result=true;
			
			map.put("logout_result", logout_result.toString());
			map.put("logout_msg", "로그아웃을 성공했습니다.");
			
			session.removeAttribute("login_member");
			
			json=gson.toJson(map);
			return json;
		}
		
		map.put("logout_result", logout_result.toString());
		map.put("logout_msg", "로그아웃을 실했습니다.");
		
		json=gson.toJson(map);
		
		return json;
	}
	
	@GetMapping(value = "android/simpleItem_selectAll", produces = "application/text; charset=utf8")
	@ResponseBody
	public String simpleItem_selectAll(HttpServletRequest request) {
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
				
		List<SimpleBoardItemView> itemList=(List<SimpleBoardItemView>) ailService.service();
		ArrayList<SimpleBoardItemView> convertList=new ArrayList<SimpleBoardItemView>();
		
		for (SimpleBoardItemView item : itemList) {
			convertList.add(item);
		}
		
		String json="";
		
		json=gson.toJson(convertList);
		
		System.out.println(json);
		
		return json;
	}
	
	
	
	
	
	
	
	
}
