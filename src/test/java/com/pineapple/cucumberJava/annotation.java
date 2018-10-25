package com.pineapple.cucumberJava;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import com.google.common.collect.Table;

import cucumber.annotation.Before;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.junit.Cucumber;
import cucumber.runtime.PendingException;
import cucumber.table.DataTable;


import org.openqa.selenium.By; 
import org.openqa.selenium.WebDriver; 
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.*; 
import org.openqa.selenium.firefox.FirefoxDriver; 
import org.openqa.selenium.support.ui.Select;



import java.util.List;

@RunWith(Cucumber.class)
public class annotation {

	WebDriver driver = new FirefoxDriver(); 
	
	@Given("^Yo navego hacia el sitio web$")
	public void Yo_navego_hacia_el_sitio_web() {
	      driver.navigate().to("https://equipo1pineapple.herokuapp.com/");
	}

	@When("^Yo ingreso el email \"([^\"]*)\" y \"([^\"]*)\" entonces$")
	public void Yo_ingreso_el_email_y_entonces(String arg1, String arg2, DataTable arg3) {
		driver.findElement(By.name("email")).sendKeys(arg1);
		driver.findElement(By.name("password")).sendKeys(arg2);
		driver.findElement(By.id("botonEntrar")).click();
		
	}
	
	
	@Then("^Yo deberia acceder al sistema del login$")
	public void Yo_deberia_acceder_al_sistema_del_login() {
		  if(driver.getCurrentUrl().equalsIgnoreCase(
			      "https://equipo1pineapple.herokuapp.com/login")){ 
			         System.out.println("Test Pass");
			   } else { 
			      System.out.println("Test Failed"); 
			   } 
			   driver.close(); 
	}
	
	@Then("^Yo no deberia acceder al sistema de login$")
	public void Yo_no_deberia_acceder_al_sistema_de_login() {
		  if(driver.getCurrentUrl().equalsIgnoreCase(
			      "https://equipo1pineapple.herokuapp.com/login")){ 
			         System.out.println("Test Pass");
			   } else { 
			      System.out.println("Test Failed"); 
			   } 
			   driver.close(); 
	}
	
	
}
