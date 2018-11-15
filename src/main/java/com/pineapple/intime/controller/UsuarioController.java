package com.pineapple.intime.controller;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.bson.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import com.pineapple.intime.dao.DAOEmpleado;
import com.pineapple.intime.dominio.EmpleadoHelper;

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
		
	@RequestMapping(value = "/actionDeleteUser", method = RequestMethod.POST)
	public String actionDeleteUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("email") String email, @ModelAttribute("rol") String rol) {
		Document empleado=new Document();
		String emailLowerCase=email.toLowerCase();
		empleado.put("email", emailLowerCase);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		DAOEmpleado.delete(empleado);
		return "admin";
	}
	
	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView updateUser(ModelAndView model) {
		model.setViewName("updateUser");
		return model;
	}
	
	@RequestMapping(value = "/viewUpdatePassword", method = RequestMethod.GET)
	public ModelAndView viewUpdatePassword(ModelAndView model) {
		model.setViewName("updatePassword");
		return model;
	}
	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute("passwordVieja") String contraseñaVieja,
			@ModelAttribute("passwordNueva") String contraseñaNueva, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		DAOEmpleado.updatePassword(email,contraseñaVieja,contraseñaNueva);
		return "user";
	}
	
	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("email") String email, @ModelAttribute("rol") String rol) {
		Document empleado=new Document();
		String contrasenna = EmpleadoHelper.generarContraseña();
		String emailLowerCase=email.toLowerCase();
		empleado.put("email", emailLowerCase);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		empleado.put("contrasenna", contrasenna);
		if(DAOEmpleado.insert(empleado)) {
			EmpleadoHelper.sesionEmail(email, contrasenna);
		}
		
		return "admin";
	}
	
	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("emailAntiguo") String emailAntiguo, @ModelAttribute("emailNuevo") String emailNuevo, @ModelAttribute("rol") String rol) {
		Document empleado=new Document();
		empleado.put("email", emailNuevo);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		DAOEmpleado.update(emailAntiguo, empleado);
		return "admin";
	}
	
	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public ModelAndView searchUser(ModelAndView model, @ModelAttribute("email") String email) {
		try {
			Document empleado = DAOEmpleado.cargarEmpleado(email);
			model.addObject("nombre", empleado.get("nombre"));
			model.addObject("apellidos", empleado.get("apellidos"));
			model.addObject("email", empleado.get("email"));
			model.addObject("rol", empleado.get("rol"));
			model.setViewName("updateUser");
		}catch(Exception e){
			model.addObject("nombre","");
			model.addObject("apellidos", "");
			model.addObject("email", "");
			model.addObject("rol", "");
			model.setViewName("updateUser");
		}
		return model;
	}
	
	@RequestMapping(value = "/deleteSearchUser", method = RequestMethod.POST)
	public ModelAndView deleteSearchUser(ModelAndView model, @ModelAttribute("email") String email) {
		Document empleado = new Document();
		try {
			empleado = DAOEmpleado.cargarEmpleado(email);
			model.addObject("nombre", empleado.get("nombre"));
			model.addObject("apellidos", empleado.get("apellidos"));
			model.addObject("email", empleado.get("email"));
			model.addObject("rol", empleado.get("rol"));
			model.setViewName("deleteUser");
		}catch(Exception e){
			model.addObject("nombre", "");
			model.addObject("apellidos", "");
			model.addObject("email", "");
			model.addObject("rol", "");
			model.setViewName("deleteUser");
		}
		return model;
	}
	
	
	

}
