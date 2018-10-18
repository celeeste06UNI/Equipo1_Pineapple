package com.pineapple.intime.controller;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class SessionController {
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("email") String email, @ModelAttribute("password") String password) {
		
		
		//User user = userService.validateUser(login);
		/*if (null != user) {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}*/
		return "home";
	}

}
