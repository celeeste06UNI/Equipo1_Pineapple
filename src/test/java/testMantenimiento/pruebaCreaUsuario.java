package testMantenimiento;


import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.or;
import static org.junit.Assert.*;

import java.util.List;

import org.bson.BsonString;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pineapple.intime.dao.MongoBroker;
import com.uclm.equipo02.modelo.Usuario;

public class pruebaCreaUsuario {
	
	private static ChromeDriver driver;
	 WebElement element;
	 

		//Obtener todos los usuarios
	private MongoCollection<Document> obtenerUsuarios() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> usuarios = broker.getCollection("Empleado");
		return usuarios;
	}

		//Devuelve true si el usuario existe en la bd
	public Boolean existeUserDNI(String dni) {
		Document documento = new Document();
		MongoCursor<Document> elementos = obtenerUsuarios().find().iterator();
		Boolean esta = false;
		while(elementos.hasNext()) {
			documento = elementos.next();
			if(documento.get("dni").toString().equalsIgnoreCase(dni)) {
				esta = true;
					
			}
		}
		return esta;
	}
	
	public void delete (String dni){
		Object db;
		MongoCollection<Document> collection = db.getcollectiion("Empleado");
		

	}

	
	 @BeforeClass
     public static void openBrowser(){

         System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
 		 driver = new ChromeDriver();
		 driver.get("https://equipo01mantenimiento.herokuapp.com"); 

	     driver.findElement(By.name("email")).sendKeys("alejandro.verod@gmail.com");
	     driver.findElement(By.name("password")).sendKeys("alex");
	     driver.findElement(By.id("botonEntrar")).click();
 }
 

	@Test
	public void crearNuevoUsuarioNormal() {
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/button")).click();
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/ul/li[1]/a")).click();
		 
		try {
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[1]")).sendKeys("55555555P");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[2]")).sendKeys("Probador");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[3]")).sendKeys("Prueba Probadorez");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[4]")).sendKeys("pruebacreauser@prueba.com");
			driver.findElement (By.xpath("//*[@id=\"usuario\"]")).click();
			driver.findElement (By.xpath("/html/body/form/input")).click();
			Alert alert = driver.switchTo().alert();
	        alert.accept();
	        
	        Boolean resultado = existeUserDNI("55555555P");
			System.out.println(resultado);

	        assertTrue(resultado);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	

	@Test
	public void crearNuevoGestor() {
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/button")).click();
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/ul/li[1]/a")).click();
		 
		try {
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[1]")).sendKeys("22222222G");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[2]")).sendKeys("Gestor");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[3]")).sendKeys("Prueba Perez");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[4]")).sendKeys("pruebacreagestor@prueba.com");
			driver.findElement (By.xpath("//*[@id=\"gestor\"]")).click();
			driver.findElement (By.xpath("/html/body/form/input")).click();
			Alert alert = driver.switchTo().alert();
	        alert.accept();
	        Boolean resultado = existeUserDNI("22222222G");
			System.out.println(resultado);
	        assertTrue(resultado);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
  
	@Test
	public void crearNuevoAdmin() {
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/button")).click();
		 driver.findElement (By.xpath("/html/body/div/div/div[1]/div[1]/ul/li[1]/a")).click();
		 
		try {
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[1]")).sendKeys("77777777A");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[2]")).sendKeys("Administrador");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[3]")).sendKeys("Prueba Garcia");
			driver.findElement (By.xpath("/html/body/form/fieldset[1]/input[4]")).sendKeys("pruebacreaadmin@prueba.com");
			driver.findElement (By.xpath("//*[@id=\"admin\"]")).click();
			driver.findElement (By.xpath("/html/body/form/input")).click();
			Alert alert = driver.switchTo().alert();
	        alert.accept();
	        Boolean resultado = existeUserDNI("77777777A");
			System.out.println(resultado);

	        assertTrue(resultado);

		}catch(Exception e){
			System.out.println(e.getMessage());
		}

	}
	
}
