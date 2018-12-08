package testMantenimiento;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import junit.framework.Assert;

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
	      String mensajeError = "";
		  driver.get("https://equipo01mantenimiento.herokuapp.com");
	      driver.findElement(By.name("email")).sendKeys("usuarioPrueba");
	      driver.findElement(By.name("password")).sendKeys("password1234");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  element = driver.findElement (By.xpath("/html/body/section/div/div/div[1]/form/div[3]/a/span"));
	    	  mensajeError = element.getText();
	      }catch (Exception e){

	      }

	      Assert.assertTrue(!mensajeError.equals(""));
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	 @Test
	 public void login_campos_vacios(){
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      String mensajeError = "";
		  driver.get("https://equipo01mantenimiento.herokuapp.com");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  element = driver.findElement (By.xpath("/html/body/section/div/div/div[1]/form/div[3]/a/span"));
	    	  mensajeError = element.getText();
	      }catch (Exception e){

	      }

		  Assert.assertTrue(!mensajeError.equals(""));
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	 @Test
	 public void login_valido_administrador(){
		 
		  String funcion = "";
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      driver.get("https://equipo01mantenimiento.herokuapp.com"); 
	      driver.findElement(By.name("email")).sendKeys("alejandro.verod@gmail.com");
	      driver.findElement(By.name("password")).sendKeys("alex");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  element = driver.findElement(By.xpath("/html/body/a/section/div/div/div[1]/h2"));
	    	  funcion = element.getText();
	      }catch (Exception e){
	    	  
	      }
	      
	      Assert.assertEquals(funcion, "Administrador");
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	 @Test
	 public void login_valido_gestor(){
		 
		  String funcion = "";
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      driver.get("https://equipo01mantenimiento.herokuapp.com"); 
	      driver.findElement(By.name("email")).sendKeys("pavlo.urgot@gmail.com");
	      driver.findElement(By.name("password")).sendKeys("pavlo1");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  element = driver.findElement(By.xpath("/html/body/a/section/div/div/div[1]/h2"));
	    	  funcion = element.getText();
	      }catch (Exception e){
	    	  
	      }
	      
	      Assert.assertEquals(funcion, "Gestor de Incidencias");
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	 @Test
	 public void login_valido_usuario(){
		 
		  String funcion = "";
		  System.out.println("Starting test " + new Object(){}.getClass().getEnclosingMethod().getName());
	      driver.get("https://equipo01mantenimiento.herokuapp.com"); 
	      driver.findElement(By.name("email")).sendKeys("joseja127@gmail.com");
	      driver.findElement(By.name("password")).sendKeys("joseja1234");
	      driver.findElement(By.id("botonEntrar")).click();
	      
	      try{
	    	  element = driver.findElement(By.xpath("/html/body/a/section/div/div/div[1]/h2"));
	    	  funcion = element.getText();
	      }catch (Exception e){
	    	  
	      }
	      
	      Assert.assertEquals(funcion, "Usuario");
	      System.out.println("Ending test " + new Object(){}.getClass().getEnclosingMethod().getName());
	 }
	 
	 @AfterClass
	 public static void closeBrowser(){
		 driver.quit();
	 }
}