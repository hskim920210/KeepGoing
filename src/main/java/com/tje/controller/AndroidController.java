package com.tje.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tje.model.Member;
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
}
