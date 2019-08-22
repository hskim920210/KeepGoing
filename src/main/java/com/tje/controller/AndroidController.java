package com.tje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AndroidController {
	
	@GetMapping("/android/address_search")
	public String address_search() {
		
		return "address_search";
	}
}
