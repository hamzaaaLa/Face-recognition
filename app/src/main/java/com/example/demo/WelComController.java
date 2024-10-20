package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WelComController {
	
	@GetMapping("/welcome")
	public String welcome(Model model) {
		model.addAttribute("message","Hello World !");
		return "upload";
	}

}
