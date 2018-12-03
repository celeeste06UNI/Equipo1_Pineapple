package com.pineapple.intime.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.pineapple.intime.dao.DAOIncidencia;
import com.pineapple.intime.model.Incidencia;

@Controller
public class IncidenciaController {
	
	@RequestMapping(value = "/nuevoIncidencia", method = RequestMethod.GET)
	public ModelAndView nuevoIncidencia(ModelAndView model) {
		model.setViewName("nuevoIncidencia");
		return model;
	}
	
	@RequestMapping(value = "/consultarIncidenciaUser", method = RequestMethod.GET)
	public ModelAndView consultarIncidenciaUser(ModelAndView model) {
		model.setViewName("consultarIncidenciaUser");
		return model;
	}
	
	@RequestMapping(value = "/resolverIncidencia", method = RequestMethod.GET)
	public ModelAndView resolverIncidencia(ModelAndView model) {
		model.setViewName("resolverIncidencia");
		return model;
	}
	
	@RequestMapping(value = "/saveIncidencia", method = RequestMethod.POST)
	public String saveIncidencia(HttpServletRequest request,@ModelAttribute("asunto") String asunto, @ModelAttribute("descripcion") String descripcion,
			@ModelAttribute("tipo") String tipo) throws Exception {
		Document incidencia=new Document();
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		dateFormat.setTimeZone(TimeZone.getTimeZone("Europe/Madrid"));
		String fechaInicio = (String) dateFormat.format(new Date());
		incidencia.put("email", email);
		incidencia.put("estado", "En espera");
		incidencia.put("asunto", asunto);
		incidencia.put("descripcion", descripcion);
		incidencia.put("tipo", tipo);
		incidencia.put("fecha", fechaInicio);
		Boolean insertado = DAOIncidencia.insert(incidencia);
		
		return "nuevoIncidencia";
	}
	
	@RequestMapping(value = "/buscarIncidenciaTipo", method = RequestMethod.GET)
	public ModelAndView buscarIncidenciaTipo(HttpServletRequest request, ModelAndView model, @ModelAttribute("tipo") String tipo) {	
		ArrayList<Incidencia> listIncidencia=new ArrayList<Incidencia>();
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		String rol = (String) session.getAttribute("rolSession");
		listIncidencia = DAOIncidencia.consultar(email, tipo,rol);
		model.addObject("listIncidencia", listIncidencia);
		model.setViewName("consultarIncidenciaUser");
		return model;
	}
	
	@RequestMapping(value = "/buscarIncidenciaTipoE", method = RequestMethod.GET)
	public ModelAndView buscarIncidenciaTipoE(HttpServletRequest request, ModelAndView model, @ModelAttribute("tipo") String tipoE) {	
		ArrayList<Incidencia> listIncidencia=new ArrayList<Incidencia>();
		HttpSession session = request.getSession(true);
		String emailE = (String) session.getAttribute("emailSession");
		String rolE = (String) session.getAttribute("rolSession");
		listIncidencia = DAOIncidencia.consultarTodo(emailE, tipoE,rolE);
		model.addObject("listIncidencia", listIncidencia);
		model.setViewName("resolverIncidencia");
		return model;
	}
	
	@RequestMapping(value = "/deleteIncidencias", method = RequestMethod.GET)
	public ModelAndView deleteIncidencias(HttpServletRequest request, ModelAndView model) {	
		String email = request.getParameter("email");
		String estado = request.getParameter("estado");
		String asunto = request.getParameter("asunto");
		String descripcion = request.getParameter("descripcion");
		String tipo = request.getParameter("tipo");
		String fecha = request.getParameter("fecha");
		DAOIncidencia.delete(email, estado, asunto, descripcion, tipo, fecha);
		model.setViewName("consultarIncidenciaUser");
		return model;
	}
	
	@RequestMapping(value = "/editIncidencias", method = RequestMethod.GET)
	public ModelAndView editIncidencias(HttpServletRequest request, ModelAndView model) {	
		String emailI = request.getParameter("email");
		String estadoI = request.getParameter("estado");
		String asuntoI = request.getParameter("asunto");
		String descripcionI = request.getParameter("descripcion");
		String tipoI = request.getParameter("tipo");
		String fechaI = request.getParameter("fecha");
		model.setViewName("updateIncidencia");
		model.addObject("email",emailI);
		model.addObject("estado",estadoI);
		model.addObject("asunto",asuntoI);
		model.addObject("descripcion",descripcionI);
		model.addObject("tipo",tipoI);
		model.addObject("fecha",fechaI);
		
		return model;
	}
	@RequestMapping(value = "/editIncidenciasE", method = RequestMethod.GET)
	public ModelAndView editIncidenciasE(HttpServletRequest request, ModelAndView model) {	
		String email1 = request.getParameter("email");
		String estado1 = request.getParameter("estado");
		String asunto1 = request.getParameter("asunto");
		String descripcion1 = request.getParameter("descripcion");
		String tipo1 = request.getParameter("tipo");
		String fecha1 = request.getParameter("fecha");
		model.setViewName("updateEstadoIncidencia");
		model.addObject("email",email1);
		model.addObject("estado",estado1);
		model.addObject("asunto",asunto1);
		model.addObject("descripcion",descripcion1);
		model.addObject("tipo",tipo1);
		model.addObject("fecha",fecha1);
		
		return model;
	}
	
	@RequestMapping(value = "/updateIn", method = RequestMethod.GET)
	public ModelAndView updateIn(HttpServletRequest request, ModelAndView model) {	
		HttpSession session = request.getSession(true);
		String emailIn = (String) session.getAttribute("emailSession");
		String rolIn = (String) session.getAttribute("rolSession");
		String estadoIn = request.getParameter("estado");
		String asuntoIn = request.getParameter("asunto");
		String descripcionIn = request.getParameter("descripcion");
		String tipoIn = request.getParameter("tipo");
		String fechaIn = request.getParameter("fecha");
		DAOIncidencia.updateIncidencia(emailIn, estadoIn, asuntoIn, descripcionIn, tipoIn, fechaIn, rolIn);
		model.setViewName("consultarIncidenciaUser");
	
		
		return model;
	}
	
	@RequestMapping(value = "/updateEIn", method = RequestMethod.GET)
	public ModelAndView updateEIn(HttpServletRequest request, ModelAndView model) {	
		HttpSession session1 = request.getSession(true);
		String emailEIn = (String) session1.getAttribute("emailSession");
		String rolEIn = (String) session1.getAttribute("rolSession");
		String estadoEIn = request.getParameter("estado");
		String asuntoEIn = request.getParameter("asunto");
		String descripcionEIn = request.getParameter("descripcion");
		String tipoEIn = request.getParameter("tipo");
		String fechaEIn = request.getParameter("fecha");
		DAOIncidencia.updateEstado(emailEIn, estadoEIn, asuntoEIn, descripcionEIn, tipoEIn, fechaEIn, rolEIn);
		model.setViewName("resolverIncidencia");
		return model;
	}

}
