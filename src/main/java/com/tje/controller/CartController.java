package com.tje.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tje.model.Cart;
import com.tje.model.CartJsonModel;
import com.tje.model.Member;
import com.tje.model.Sold_item;
import com.tje.service.cart.CartListService;
import com.tje.service.cart.SoldItemAddAndCartDeleteService;

@Controller
public class CartController {
	
	@Autowired
	private CartListService clService;
	@Autowired
	private SoldItemAddAndCartDeleteService siaService;
	
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
	public String item_buy(@RequestBody List<CartJsonModel> list) {
		
		int r=0;
		
		List<Sold_item> itemList=new ArrayList<Sold_item>();
		List<Cart> cartList=new ArrayList<Cart>();
		
		for (CartJsonModel model : list) {
			Sold_item item=new Sold_item(0, model.getBoard_id(), model.getCategory(),
					model.getMember_id(), model.getName(), model.getAddress_post(),
					model.getAddress_basic(), model.getAddress_detail(), model.getTitle(),
					model.getNumber(), model.getPrice(), null);
			
			itemList.add(item);
			
			Cart cart=new Cart();
			cart.setCart_id(model.getCart_id());
			
			cartList.add(cart);
		}
		
		try {
			System.out.println(siaService.service(itemList, cartList));
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
		
		return "success";
	}
}
