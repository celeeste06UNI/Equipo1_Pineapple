package com.pineapple.intime.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pineapple.intime.dao.DAOFichaje;
import com.steadystate.css.parser.ParseException;

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
	public String abrirFichaje(ModelAndView model, HttpServletRequest request) {
		boolean fichado;
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		fichado = DAOFichaje.abrirFichaje(email);
		model.addObject("fichado", fichado);
		return "fichajeUser";
	}

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.GET)
	public String cerrarFichaje(ModelAndView model, HttpServletRequest request) throws ParseException, java.text.ParseException {
		boolean fichado;
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		fichado = DAOFichaje.cerrarFichaje(email);
		model.addObject("fichado", fichado);
		return "fichajeUser";
	}

	@RequestMapping(value = "/searchFichaje", method = RequestMethod.GET)
	public ModelAndView searchFichaje(@ModelAttribute("fechaI") String fechaI, @ModelAttribute("fechaF") String fechaF, @ModelAttribute("tiempo") String tiempo,
			ModelAndView model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		ArrayList<String> result = DAOFichaje.consultarFichajes(email, fechaI, fechaF, tiempo);
		model.addObject("listDate", result);
		model.setViewName("fichajeUser");
		return model;
	}

	@RequestMapping(value = "/searchFichajeOtro", method = RequestMethod.GET)
	public ModelAndView searchFichajeOtro(@ModelAttribute("fechaIn") String fechaIn,
			@ModelAttribute("emailF") String emailF, @ModelAttribute("fechaFi") String fechaFi, @ModelAttribute("tiempo") String tiempo , ModelAndView model,
			HttpServletRequest request) {
		ArrayList<String> result = DAOFichaje.consultarFichajes(emailF, fechaIn, fechaFi, tiempo);
		model.addObject("listDateOtro", result);
		model.setViewName("consultaFichaje");
		//LAS QUE TIENEN FECHA DE AYER NO ME LAS MUESTRA... Y SOLO ME MUESTRA LAS DE UN DIA 
		return model;
	}

}
