package com.pineapple.intime.controller;

import java.util.List;
import java.util.Locale;
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
	public ModelAndView newUser(ModelAndView model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String rolSession = (String) session.getAttribute("rolSession");
		if (rolSession.equals("admin")) {
			model.setViewName("newUser");
		} else {
			model.setViewName(rolSession);
		}
		return model;
	}

	@RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
	public ModelAndView deleteUser(ModelAndView model, HttpServletRequest request) {
		HttpSession session = request.getSession(true);
		String rolSession = (String) session.getAttribute("rolSession");
		if (rolSession.equals("admin")) {
			model.setViewName("deleteUser");
		}
		return model;
	}

	@RequestMapping(value = "/updateUser", method = RequestMethod.GET)
	public ModelAndView updateUser(HttpServletRequest request, ModelAndView model) {
		HttpSession session = request.getSession(true);
		String rolSession = (String) session.getAttribute("rolSession");
		if (rolSession.equals("admin")) {
			model.setViewName("updateUser");
		}
		return model;
	}

	@RequestMapping(value = "/viewUpdatePassword", method = RequestMethod.GET)
	public ModelAndView viewUpdatePassword(HttpServletRequest request, ModelAndView model) {
		HttpSession session = request.getSession(true);
		String rolSession = (String) session.getAttribute("rolSession");

		model.setViewName("updatePassword");

		return model;
	}

	@RequestMapping(value = "/saveUser", method = RequestMethod.POST)
	public ModelAndView saveUser(HttpServletRequest request, ModelAndView model, @ModelAttribute("dni") String dni,
			@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("email") String email, @ModelAttribute("rol") String rol) throws Exception {
		HttpSession session = request.getSession(true);
		String rolSession = (String) session.getAttribute("rolSession");
		if (rolSession.equals("admin")) {
			Document empleado = new Document();
			String contrasenna = EmpleadoHelper.generarContrasenna();
			String emailLowerCase = email.toLowerCase();
			empleado.put("email", emailLowerCase);
			empleado.put("dni", dni);
			empleado.put("rol", rol);
			empleado.put("nombre", nombre);
			empleado.put("apellidos", apellidos);
			empleado.put("contrasenna", contrasenna);
			if (DAOEmpleado.insert(empleado)) {
				EmpleadoHelper.sesionEmail(email, contrasenna);
			}
			model.setViewName("admin");
		}

		return model;
	}

	@RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
	public String updatePassword(@ModelAttribute("passwordVieja") String contrasennaVieja,
			@ModelAttribute("passwordNueva") String contrasennaNueva, HttpServletRequest request) throws Exception {
		String pagina = "";
		String passAntCifrada = EmpleadoHelper.cifra(contrasennaVieja);
		String passAntHex = EmpleadoHelper.ConvertirHexadecimal(passAntCifrada);
		String passNuevaCifrada = EmpleadoHelper.cifra(contrasennaNueva);
		String passNuevaHex = EmpleadoHelper.ConvertirHexadecimal(passNuevaCifrada);
		HttpSession session = request.getSession(true);
		String email = (String) session.getAttribute("emailSession");
		String rolSession = (String) session.getAttribute("rolSession");
		DAOEmpleado.updatePassword(email, passAntHex, passNuevaHex);

		if ("admin".equals(rolSession)) {
			pagina = "admin";
		}

		if ("user".equals(rolSession)) {
			pagina = "user";
		}
		if ("incid".equals(rolSession)) {
			pagina = "incid";
		}
		return pagina;
	}

	public String saveUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("email") String email, @ModelAttribute("rol") String rol) throws Exception {
		Document empleado = new Document();
		String contrasenna = EmpleadoHelper.generarContrasenna();
		String emailLowerCase = email.toLowerCase(new Locale("en", "EN"));
		empleado.put("email", emailLowerCase);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		empleado.put("contrasenna", contrasenna);
		if (DAOEmpleado.insert(empleado)) {
			EmpleadoHelper.sesionEmail(email, contrasenna);
		}

		return "admin";
	}

	@RequestMapping(value = "/editUser", method = RequestMethod.POST)
	public String editUser(@ModelAttribute("nombre") String nombre, @ModelAttribute("apellidos") String apellidos,
			@ModelAttribute("emailAntiguo") String emailAntiguo, @ModelAttribute("emailNuevo") String emailNuevo,
			@ModelAttribute("rol") String rol) {
		Document empleado = new Document();
		String emailLowerCase = emailNuevo.toLowerCase(new Locale("en", "EN"));
		empleado.put("email", emailLowerCase);
		empleado.put("rol", rol);
		empleado.put("nombre", nombre);
		empleado.put("apellidos", apellidos);
		DAOEmpleado.update(emailAntiguo, empleado);
		return "admin";
	}

	@RequestMapping(value = "/searchUser", method = RequestMethod.POST)
	public ModelAndView searchUser(ModelAndView model, @ModelAttribute("email") String email) {
		String emailLowerCase = email.toLowerCase(new Locale("en", "EN"));
		try {
			Document empleado = DAOEmpleado.cargarEmpleado(emailLowerCase);
			model.addObject("nombre", empleado.get("nombre"));
			model.addObject("apellidos", empleado.get("apellidos"));
			model.addObject("email", empleado.get("email"));
			model.addObject("rol", empleado.get("rol"));
			model.setViewName("updateUser");
		} catch (Exception e) {
			model.addObject("nombre", "");
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
		String emailLowerCase = email.toLowerCase(new Locale("en", "EN"));
		try {
			empleado = DAOEmpleado.cargarEmpleado(email);
			model.addObject("dni", empleado.get("dni"));
			model.addObject("nombre", empleado.get("nombre"));
			model.addObject("apellidos", empleado.get("apellidos"));
			model.addObject("email", empleado.get("email"));
			model.addObject("rol", empleado.get("rol"));
			model.setViewName("deleteUser");
		} catch (Exception e) {
			model.addObject("nombre", "");
			model.addObject("apellidos", "");
			model.addObject("email", "error");
			model.addObject("rol", "");
			model.setViewName("deleteUser");
		}
		return model;
	}

	@RequestMapping(value = "/actionDeleteUser", method = RequestMethod.POST)
	public ModelAndView actionDeleteUser(ModelAndView model, HttpServletRequest request,
			@ModelAttribute("dni") String dni, @ModelAttribute("nombre") String nombre,
			@ModelAttribute("apellidos") String apellidos, @ModelAttribute("email") String email,
			@ModelAttribute("rol") String rol) {
			HttpSession session = request.getSession(true);
			String rolSession = (String) session.getAttribute("rol");
			//if (rolSession.equals("admin")) {
			Document empleado = new Document();
			String emailLowerCase = email.toLowerCase();
			empleado.put("dni", dni);
			empleado.put("email", emailLowerCase);
			empleado.put("rol", rol);
			empleado.put("nombre", nombre);
			empleado.put("apellidos", apellidos);
			if(DAOEmpleado.delete(empleado)) {
				model.setViewName("admin");
			}else {
				model.setViewName("error");
			}
			
		//}else {
			
		//}

		return model;
	}
}
