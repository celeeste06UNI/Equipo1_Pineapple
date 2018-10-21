package com.pineapple.intime.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.pineapple.intime.dao.DAOEmpleado;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView newContact(ModelAndView model) {
		model.setViewName("home");
		//Document obj=DAOEmpleado.load();
		return model;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("email") String email, @ModelAttribute("password") String password) {
		ModelAndView mav = null;
		
		//User user = userService.validateUser(login);
		/*if (null != user) {
			mav = new ModelAndView("welcome");
			mav.addObject("firstname", user.getFirstname());
		} else {
			mav = new ModelAndView("login");
			mav.addObject("message", "Username or Password is wrong!!");
		}*/
		return mav;
	}
	public boolean autenticar(String email,String password) {
		Boolean autenticado = false;
		ConcurrentHashMap<Integer,String> empleados=DAOEmpleado.load();
		return true;
	}

}
