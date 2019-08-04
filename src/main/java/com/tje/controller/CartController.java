package com.tje.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tje.model.Member;
import com.tje.service.CartListService;

@Controller
public class CartController {
	
	@Autowired
	private CartListService clService;
	
	@RequestMapping("/cart")
	public String Cart(
			Model model,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member!=null)
			model.addAttribute("cartList", clService.service());
		
		return "cart";
	}
}
