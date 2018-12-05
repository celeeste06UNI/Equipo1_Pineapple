package testMantenimiento;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.google.common.base.Function;

import junit.framework.Assert;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

public class pruebaLogin {

	private static ChromeDriver driver;
	 WebElement element;
	 
	 @BeforeClass
	     public static void openBrowser(){

	         System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
	 		 driver = new ChromeDriver();
	 } 
	 
	 @Test
	 public void login_no_valido(){
	 
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      driver.get("https://equipo01mantenimiento.herokuapp.com"); 
	      //driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	      driver.findElement(By.name("email")).sendKeys("usuarioPrueba");
	      driver.findElement(By.name("password")).sendKeys("password1234");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	 //element = driver.findElement (By.xpath(".//*[@id='account_logout']/a"));
	    
	      }catch (Exception e){

	      }
	    	  
	      String comprobacion = "";
		  if(driver.findElementByClassName("copy-text") != null) {
		   	  comprobacion = driver.findElementByClassName("copy-text").getText();
		  }else {
		  	  comprobacion = "";
		  }
		      
		  Assert.assertEquals(comprobacion, "Pineapple");
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	     }
	 
	 @Test
	 public void login_valido_administrador(){
		 
		  String funcion = "";
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      driver.get("https://equipo01mantenimiento.herokuapp.com"); 
	      //driver.findElement(By.xpath(".//*[@id='account']/a")).click();
	      driver.findElement(By.name("email")).sendKeys("alejandro.verod@gmail.com");
	      driver.findElement(By.name("password")).sendKeys("alex");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  WebElement element = driver.findElement(By.xpath("/html/body/a/section/div/div/div[1]/h2"));
	    	  funcion = element.getText();
	      }catch (Exception e){
	    	  
	      }
	      
	      Assert.assertEquals(funcion, "Administrador");
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	
	 @AfterClass
	 public static void closeBrowser(){
	 driver.quit();
	 }
}