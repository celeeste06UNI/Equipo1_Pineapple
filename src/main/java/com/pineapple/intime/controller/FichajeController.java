package com.pineapple.intime.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FichajeController {
	
	@RequestMapping(value = "/fichajeUser", method = RequestMethod.GET)
	public ModelAndView fichajeUser(ModelAndView model) {
		model.setViewName("fichajeUser");
		return model;
	}
	
	@RequestMapping(value = "/consultaFichaje", method = RequestMethod.GET)
	public ModelAndView consultaFichaje(ModelAndView model) {
		model.setViewName("/consultaFichaje");
		return model;
	}
	
	@RequestMapping(value = "/abrirFichaje", method = RequestMethod.GET)
	public String abrirFichaje(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		//DAOEmpleado.abrirFichaje(email); Que me devuelva true o false. False
		//en el caso de que no haya cerrado el fichaje anterior y true en el caso
		//en el que se pueda abrir el fichaje
		return "fichajeUser";
	}
	
	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.GET)
	public String cerrarFichaje(HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		//DAOEmpleado.cerrarFichaje(email);
		return "fichajeUser";
	}
	

}
