package com.pineapple.intime.controller;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pineapple.intime.dao.DAOEmpleado;

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
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("email") String email, @ModelAttribute("rol") String rol) {
		Document empleado=new Document();
		empleado.put("email", email);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		
		DAOEmpleado.insert(empleado);
		return "admin";
	}
	
	

}
