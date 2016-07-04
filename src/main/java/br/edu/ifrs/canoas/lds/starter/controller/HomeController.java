package br.edu.ifrs.canoas.lds.starter.controller;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/")
	public String index(Model model){
		model.addAttribute("auth",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "redirect:/login";
	}
	
	@RequestMapping("/home")
	public String home(Model model){
		model.addAttribute("auth",SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return "/index";
	}
	// Error page
	  @RequestMapping("/error.html")
	  public String error(HttpServletRequest request, Model model) {
	    model.addAttribute("errorCode", request.getAttribute("javax.servlet.error.status_code"));
	    Throwable throwable = (Throwable) request.getAttribute("javax.servlet.error.exception");
	    String errorMessage = null;
	    if (throwable != null) {
	      errorMessage = throwable.getMessage();
	    }
	    model.addAttribute("errorMessage", errorMessage);
	    return "/error";
	  }
}
