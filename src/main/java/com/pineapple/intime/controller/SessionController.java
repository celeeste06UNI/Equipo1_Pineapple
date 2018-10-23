package com.pineapple.intime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SessionController {
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) {
		model.setViewName("newUser");
		return model;
	}

}
