package com.pineapple.cucumberJava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;

import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.junit.Cucumber;
import cucumber.runtime.PendingException;


@RunWith(Cucumber.class)
public class annotation {

	@Given("^nosotros navegamos hacia el sitio \"([^\"]*)\"$")
	public void nosotros_navegamos_hacia_el_sitio(String arg1) {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Given("^nosotros introducimos el usuario como \"([^\"]*)\" y el password como \"([^\"]*)\"$")
	public void nosotros_introducimos_el_usuario_como_y_el_password_como(String arg1, String arg2) {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Given("^nosotros hacemos click en el boton del login$")
	public void nosotros_hacemos_click_en_el_boton_del_login() {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Then("^nosotros deberiamos de realizar un login incorrecto y salta un \"([^\"]*)\"$")
	public void nosotros_deberiamos_de_realizar_un_login_incorrecto_y_salta_un(String arg1) {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}

	@Then("^nosotros deberiamos de realizar un login correcto$")
	public void nosotros_deberiamos_de_realizar_un_login_correcto() {
	    // Express the Regexp above with the code you wish you had
	    throw new PendingException();
	}
}
