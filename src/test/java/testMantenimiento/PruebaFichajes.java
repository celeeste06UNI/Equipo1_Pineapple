package testMantenimiento;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;
import static org.junit.Assert.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

import org.bson.Document;
import org.bson.conversions.Bson;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.pineapple.intime.dao.DAOFichaje;
import com.pineapple.intime.dao.MongoBroker;
import com.steadystate.css.parser.ParseException;

import junit.framework.Assert;

public class PruebaFichajes {

	private static ChromeDriver driver;
	WebElement element;
	private static DAOFichaje daofichaje = new DAOFichaje();
	private static String email = "alejandro.verod@gmail.com";
	private static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	private static DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
	
	@BeforeClass
    public static void openBrowser() throws ParseException, java.text.ParseException{
        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
 	    driver = new ChromeDriver();
 	    driver.get("https://equipo01mantenimiento.herokuapp.com");
	    driver.findElement(By.name("email")).sendKeys(email);
	    driver.findElement(By.name("password")).sendKeys("alex");
	    driver.findElement(By.id("botonEntrar")).click();
	    dateFormat.setTimeZone(TimeZone.getTimeZone("UTC+1"));
		hourFormat.setTimeZone(TimeZone.getTimeZone("UTC+1"));
	}
	 
	@Test
	public void abrir_y_cerrar_fichaje() {
		
		Document fichajePrueba = new Document();
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/ul/li[1]/a")).click();
	
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/fieldset/a[1]/button")).click();
		Alert alert = driver.switchTo().alert();
        alert.accept();
		hourFormat.setTimeZone(TimeZone.getTimeZone("UTC+1"));
		String fechaInicio = (String) dateFormat.format(new Date());
		String horaInicio =  (String) hourFormat.format(new Date());
		System.out.println(fechaInicio);
		
		try {
			fichajePrueba.append("email", email);
			fichajePrueba.append("estado", "abierto");
			fichajePrueba.append("fechaInicio", fechaInicio);
			fichajePrueba.append("horaInicio", horaInicio);
			Thread.sleep(2000);
		}catch(Exception e) {
			
		}
		
		//Volver atrás
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/a")).click();
		
		
		Assert.assertTrue(buscarFichajeAbierto(fichajePrueba));
		
		//Entrar otra vez a la  interfaz de abrir/cerrar fichaje
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/ul/li[1]/a")).click();
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[1]/fieldset/a[2]/button")).click();
		alert.accept();
		
		String fechaFin = (String) dateFormat.format(new Date());
		String horaFin =  (String) hourFormat.format(new Date());
		fichajePrueba.append("fechaFin", fechaFin);
		fichajePrueba.append("horaFin", horaFin);

		//Volver atrás
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/a")).click();
		
		Assert.assertTrue(buscarFichajeCerrado(fichajePrueba));
	}
	
	@Test
	public void buscar_fichaje_inexistente () {
		Document fichajePrueba = new Document();
		try {
			fichajePrueba.append("email", email);
			fichajePrueba.append("estado", "abierto");
			fichajePrueba.append("fechaInicio", "12-07-2019");
			fichajePrueba.append("horaInicio", "12:59:23");
			fichajePrueba.append("fechaFin", "13-07-2019");
			fichajePrueba.append("horaFin", "14:40:24");
		}catch(Exception e) {
			
		}
		buscarFichajeAbierto(fichajePrueba);
		Assert.assertFalse(buscarFichajeAbierto(fichajePrueba));
		fichajePrueba.put("estado", "cerrado");
		Assert.assertFalse(buscarFichajeCerrado(fichajePrueba));
	}
	
	@Test
	public void prueba_listar_fichajes() {
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/ul/li[1]/a")).click();
		
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/form/fieldset/input[1]")).sendKeys("2018/07/12");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/form/fieldset/input[2]")).sendKeys("2018/12/30");
		driver.findElement(By.xpath("/html/body/div[4]/div[2]/div[2]/form/fieldset/button")).click();
		String datos = driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr[2]/td")).getText();
		Assert.assertTrue(datos.contains(email));
		//Volver atrás
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/a")).click();
	}

	@Test
	public void prueba_ver_fichajes_otros () {
		String emailConsulta = "joseja127@gmail.com";
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/button")).click();
		driver.findElement(By.xpath("/html/body/div/div/div[1]/div[2]/ul/li[2]/a")).click();
		driver.findElement(By.xpath("/html/body/div[4]/form/fieldset/input[1]")).sendKeys(emailConsulta);
		driver.findElement(By.xpath("/html/body/div[4]/form/fieldset/input[2]")).sendKeys("2018/07/01");
		driver.findElement(By.xpath("/html/body/div[4]/form/fieldset/input[3]")).sendKeys("2018/12/20");
		driver.findElement(By.xpath("/html/body/div[4]/form/fieldset/button")).click();
		String datos = driver.findElement(By.xpath("/html/body/div[5]/table/tbody/tr[2]/td")).getText();
		Assert.assertTrue(datos.contains(emailConsulta));
		//Volver atrás
		driver.findElement(By.xpath("/html/body/div[3]/div[1]/a")).click();
	}
	
	@Test
	public void prueba_consultar_fichajes_fecha_codigo () {
		hourFormat.setTimeZone(TimeZone.getTimeZone("UTC+1"));
		String fechaInicio = (String) dateFormat.format(new Date());
		String fechaFin =  (String) hourFormat.format(new Date());
		
		fechaInicio = "20/07/2017";
		fechaFin = "20/12/2019";
		Assert.assertNotNull(daofichaje.consultarFichajes(email, fechaInicio, fechaFin, ""));
	}

	private MongoCollection<Document> obtenerFichajes() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> fichajes = broker.getCollection("Fichaje");
		return fichajes;
	}
	
	private MongoCollection<Document> obtenerEstadoFichajes() {
		MongoBroker broker = MongoBroker.get();
		MongoCollection<Document> estados = broker.getCollection("EstadoFichaje");
		return estados;
	}
	
	public boolean buscarFichajeAbierto(Document fichaje) {
		Document documento = new Document();
		MongoCursor<Document> listaEstados = obtenerEstadoFichajes().find().iterator();

		while(listaEstados.hasNext()) {
			documento = listaEstados.next();

			if(documento.get("email").toString().equalsIgnoreCase(email)){
				if (documento.get("estado").toString().equalsIgnoreCase("abierto")) {
					if (documento.get("fechaInicio").toString().equals(fichaje.get("fechaInicio").toString())){
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean buscarFichajeCerrado(Document fichaje) {
		Document documento = new Document();
		MongoCursor<Document> listaFichajes = obtenerFichajes().find().iterator();
		while(listaFichajes.hasNext()) {
			documento = listaFichajes.next();
			if(documento.get("email").toString().equalsIgnoreCase(email)){
				if(documento.get("fechaFin").toString().equalsIgnoreCase(fichaje.get("fechaFin").toString())) {
					return true;
				}
			}
		}
		return false;
	}
	
	@AfterClass
	public static void closeBrowser(){
		driver.quit();
	}
	
}
