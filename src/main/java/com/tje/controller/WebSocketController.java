package com.tje.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebSocketController {
	
	@GetMapping("/ws/bc")
	public String broadcast() {
		return "broadcast";
	}
}
