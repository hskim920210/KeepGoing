package com.tje.controller;

import java.util.HashMap;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.google.connect.GoogleOAuth2Template;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tje.api.AuthInfo;
import com.tje.api.NaverLoginBO;
import com.tje.model.Member;
import com.tje.service.member.MemberIDCheckService;
import com.tje.service.member.MemberInsertService;
import com.tje.service.member.MemberNickNameCheckService;

@Controller
public class AndroidController {
	
	// google 로그인
	@Inject
	private AuthInfo authInfo;
	@Autowired
	private GoogleOAuth2Template googleOAuth2Template;
	@Autowired
	private OAuth2Parameters googleOAuth2Parameters;
	@Autowired
	private GoogleConnectionFactory googleConnectionFactory;

	// naver 로그인
	private NaverLoginBO naverLoginBO;
	private String apiResult = null;

	@Autowired
	private void setNaverLoginBO(NaverLoginBO naverLoginBO) {
		this.naverLoginBO = naverLoginBO;
	}
	
	@Autowired
	private MemberInsertService miService;
	@Autowired
	private MemberIDCheckService mIDcService;
	@Autowired
	private MemberNickNameCheckService mnncService;
	
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
	
	
	
	
	
	
	
	
	
}
