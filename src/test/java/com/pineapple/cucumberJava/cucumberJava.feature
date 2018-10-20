@FeatureTest
Feature: Comprobacion del login en el sistema a traves
		de una serie de pruebas para asegurarse que el login se 
		realiza de manera adecuada. 

	@Login
	Scenario: Credenciales incorrectas, no accedemos al sistema
		Given nosotros navegamos hacia el sitio "web"
		And nosotros introducimos el usuario como "admin" y el password como "admin"
		And nosotros hacemos click en el boton del login
		Then nosotros deberiamos de realizar un login incorrecto y salta un "mensaje"

	@Login
	Scenario: Credenciales correctas, accedemos al sistema
		Given nosotros navegamos hacia el sitio "web"
		And nosotros introducimos el usuario como "admin" y el password como "admin"
		And nosotros hacemos click en el boton del login
		Then nosotros deberiamos de realizar un login correcto
		
