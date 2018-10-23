package com.pineapple.intime.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;
import org.bson.Document;
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
		return model;
	}
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("index");
		return model;
	}
	

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginProcess(@ModelAttribute("email") String email, @ModelAttribute("password") String password) throws IOException {
		String pagina = null;
		Document doc = DAOEmpleado.autenticar(email,password);
		if(doc.get("email").equals("error")) {
			pagina = "error";
		}
		if(doc.get("email").equals(email)) {
			if(doc.get("rol").equals("admin")){
				pagina = "admin";
			}
			if(doc.get("rol").equals("user")){
				pagina = "user";
			}
			if(doc.get("rol").equals("incid")){
				pagina = "incid";
			}
		}
		return pagina;
	
	}
	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public ModelAndView newUser(ModelAndView model) {
		model.setViewName("newUser");
		return model;
	}
}
