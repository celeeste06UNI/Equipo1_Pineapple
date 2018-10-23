package com.pineapple.intime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UsuarioController {
	
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) {
		model.setViewName("newUser");
		return model;
	}
	
	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(ModelAndView model) {
		model.setViewName("deleteUser");
		return model;
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView updateUser(ModelAndView model) {
		model.setViewName("updateUser");
		return model;
	}

}
