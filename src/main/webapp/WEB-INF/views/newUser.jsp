<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="css/normalize.css">
<link href='https://fonts.googleapis.com/css?family=Nunito:400,300'
	rel='stylesheet' type='text/css'>
<link rel="stylesheet" href="css/main.css">

<title>Insert title here</title>

<style type="text/css">
*, *:before, *:after {
	-moz-box-sizing: border-box;
	-webkit-box-sizing: border-box;
	box-sizing: border-box;
}

body {
	font-family: 'Nunito', sans-serif;
	color: #384047;
}

form {
	max-width: 300px;
	margin: 10px auto;
	padding: 10px 20px;
	background: #f4f7f8;
	border-radius: 8px;
}

h1 {
	margin: 0 0 30px 0;
	text-align: center;
}

input[type="text"], input[type="password"], input[type="date"], input[type="datetime"],
	input[type="email"], input[type="number"], input[type="search"], input[type="tel"],
	input[type="time"], input[type="url"], textarea, select {
	background: rgba(255, 255, 255, 0.1);
	border: none;
	font-size: 16px;
	height: auto;
	margin: 0;
	outline: 0;
	padding: 15px;
	width: 100%;
	background-color: #E7C1B9;
	color: #8a97a0;
	box-shadow: 0 1px 0 rgba(0, 0, 0, 0.03) inset;
	margin-bottom: 30px;
}

input[type="radio"], input[type="checkbox"] {
	margin: 0 4px 8px 0;
}

select {
	padding: 6px;
	height: 32px;
	border-radius: 2px;
}

button {
	padding: 19px 39px 18px 39px;
	color: #FFF;
	background-color: #FE2E2E;
	font-size: 18px;
	text-align: center;
	font-style: normal;
	border-radius: 5px;
	width: 100%;
	border: 2px solid #8A0808;
	border-width: 1px 1px 3px;
	box-shadow: 0 -1px 0 rgba(255, 255, 255, 0.1) inset;
	margin-bottom: 10px;
}

fieldset {
	margin-bottom: 10px;
	border: none;
}

legend {
	font-size: 1.4em;
	margin-bottom: 10px;
}

label {
	display: block;
	margin-bottom: 8px;
}

label.light {
	font-weight: 300;
	display: inline;
}

.number {
	background-color: #FE2E2E;
	color: #fff;
	height: 30px;
	width: 30px;
	display: inline-block;
	font-size: 0.8em;
	margin-right: 4px;
	line-height: 30px;
	text-align: center;
	text-shadow: 0 1px 0 rgba(255, 255, 255, 0.2);
	border-radius: 100%;
}

@media screen and (min-width: 480px) {
	form {
		max-width: 480px;
	}
}
</style>
</head>
<body>


	<nav class="navbar navbar-inverse">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">InTime</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="active"><a href="#">Home</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Usuarios <span class="caret"></span></a>
				<ul class="dropdown-menu">

					<li><a href="/newUser">Crear</a></li>
					<li><a href="/deleteUser">Eliminar</a></li>
					<li><a href="/updateUser">Modificar</a></li>
				</ul></li>
			<li><a href="#">Fichajes</a></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Gestion Incidencias <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">Crear</a></li>
					<li><a href="#">Eliminar</a></li>
					<li><a href="#">Modificar</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="#"><span class="glyphicon glyphicon-log-in"></span>
					Cerrar Sesión</a></li>
		</ul>
	</div>
	</nav>

	<form name='loginForm' action='/saveUser' method='POST'>
		<h1>Registro</h1>
		<fieldset>
			<legend>
				<span class="number">1</span>Información básica
			</legend>
			<label for="name">Nombre:</label> <input type="text" required autocomplete="off" name="nombre"> 
			<label for="name">Apellido:</label><input type="text" required autocomplete="off" name="apellidos"> 
			<label for="mail">Email:</label> <input type="email" required autocomplete="off" name="email">
		</fieldset>

		<fieldset>
			<legend>
				<span class="number">2</span>Seleccionar Rol
			</legend>
			<label>Roles:</label> 
			<input type="radio" id="admin" value="rol_admin" name="rol">
			<label class="light" for="Administrador">Administrador</label><br> 
			<input type="radio" id="usuario" value="rol_user" name="rol">
			<label class="light" for="Usuario">Usuario</label><br> 
			<input type="radio" id="gestor" value="rol_gestor" name="rol">
			<label class="light" for="Gestor">Gestor</label>
		</fieldset>
		
		<button type="submit">Registrar</button>
		
	</form>


</body>
</html>