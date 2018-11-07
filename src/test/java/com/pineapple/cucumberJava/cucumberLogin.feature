@FeatureTest
Feature: Comprobacion del login para el administrador en el sistema a traves
		de una serie de pruebas para asegurarse que el login se 
		realiza de manera adecuada. 
	
	@Login
	Scenario: Login correcto del administrador en el sistema
		Given Yo navego hacia el sitio web 
		When Yo ingreso el email "admin@admin" y "admin" entonces				 
		|		email		|	password	|
		| admin@admin.com 	| 	  admin	 	|
		Then Yo deberia acceder al sistema del login

	@Login
	Scenario: Login incorrecto del administrador en el sistema
		Given Yo navego hacia el sitio web 
		When Yo ingreso el email "admin@admin" y "1234" entonces				 
		|		email		|	password	|
		| admin@admin.com 	| 	  1234	 	|
		Then Yo no deberia acceder al sistema de login
		