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

import com.pineapple.intime.dao.DAOEmpleado;
import com.pineapple.intime.dao.DAOFichaje;
import com.pineapple.intime.dao.DAOIncidencia;
import com.pineapple.intime.dominio.EmpleadoHelper;
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
		ConcurrentHashMap<Integer, Document> result=new ConcurrentHashMap<Integer, Document>();
		ArrayList<Incidencia> listIncidencia=new ArrayList<Incidencia>();
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		result = DAOIncidencia.consultar(email, tipo);
		for(Integer i = 0; i<result.keySet().size();i++) {
			Document doc = result.get(i);
			Incidencia indencia = new Incidencia((String) doc.get("email"), (String) doc.get("estado"),(String) doc.get("asunto"),
					(String) doc.get("descripcion"),(String) doc.get("tipo"),(String) doc.get("fecha"));
			listIncidencia.add(indencia);
		}
		model.addObject("listIncidencia", listIncidencia);
		model.setViewName("consultarIncidenciaUser");
		return model;
	}

}
