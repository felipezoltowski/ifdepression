package br.edu.ifrs.canoas.lds.starter.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(Model model){
		
		model.addAttribute("auth",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		return "/login";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		
		model.addAttribute("auth",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		
		return "/index";
	}
}
