package com.tje.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.service.cart.CartListService;

@Controller
public class CartController {
	
	@Autowired
	private CartListService clService;
	
	@RequestMapping("/cart")
	public String cart(
			Model model,
			HttpSession session) {
		
		Member login_member=(Member) session.getAttribute("login_member");
		if(login_member!=null)
			model.addAttribute("cartList", clService.service());
		
		return "cart";
	}
	
	@PostMapping(value = "/item_buy", produces = "application/text; charset=utf-8")
	@ResponseBody
	public String item_buy(@RequestBody Sold_item sold_item) {
		
		
		
		return null;
	}
}
