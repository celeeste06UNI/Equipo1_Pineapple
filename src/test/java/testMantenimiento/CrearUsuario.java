package testMantenimiento;

import static org.junit.Assert.*;

import org.bson.Document;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.pineapple.intime.dao.DAOEmpleado;

public class CrearUsuario {
	
	private static ChromeDriver driver;
	 WebElement element;
	 DAOEmpleado empleadodao=new DAOEmpleado();
	 @Test
	 public void T1() throws Exception {
		 boolean resultado;
		 Document result = new Document();
		 result=empleadodao.autenticar("joseja127@gmail.com", "joseja1234");
		 
		 if(result!=null)
			 resultado=true;
		 else
			 resultado=false;
		 
		 assertTrue(resultado);
		 
	 }
}
