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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pineapple.intime.dao.DAOFichaje;
import com.pineapple.intime.model.Fichaje;

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
	public String abrirFichaje(ModelAndView model,HttpServletRequest request) {
		boolean fichado;
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		fichado = DAOFichaje.abrirFichaje(email);
		model.addObject("fichado", fichado);
		return "fichajeUser";
	}

	@RequestMapping(value = "/cerrarFichaje", method = RequestMethod.GET)
	public String cerrarFichaje(ModelAndView model, HttpServletRequest request) {
		boolean fichado;
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		fichado = DAOFichaje.cerrarFichaje(email);
		model.addObject("fichado", fichado);
		return "fichajeUser";
	}
	
	@RequestMapping(value = "/searchFichaje", method = RequestMethod.GET)
	public String searchFichaje(@ModelAttribute("fecha") String fecha,Model model, HttpServletRequest request) {
		ArrayList<String> listDate= new ArrayList<String>();
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		Document Document = DAOFichaje.consultarFichajes(email, fecha);
		for(Integer i = 1; i<Document.keySet().size();i++) {
			listDate.add((String) Document.get(""+i+""));
		}
		model.addAttribute("listDate",listDate);
		return "fichajeUser";
	}

}
