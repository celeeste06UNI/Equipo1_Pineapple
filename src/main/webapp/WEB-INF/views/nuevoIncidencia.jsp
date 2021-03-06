<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Nueva Incidencia</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

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
	<div class="row">
		<div class="col-sm-12"></div>
	</div>

	<div class="row">
		<div class="col-sm-12"></div>
	</div>

	<div class="row">
		<div align="left" class="col-sm-2">
			&nbsp&nbsp<a style="color: #cc0000" href="/intime">atrás</a>
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-8"></div>
	</div>
	
	<div class="container">
		<div class="page-header">
			<h1>Nueva Incidencia</h1>
		</div>
	</div>

	<form name='IncidenciaForm' action='/saveIncidencia' method='POST'>
		<fieldset>
			<legend>
				<span class="number">1</span>Información básica
			</legend>
			<label for="name">Asunto:</label> <input type="text" required
				autocomplete="off" name="asunto"> <label for="name">Descripción:</label><input
				type="text" required autocomplete="off" name="descripcion">
		</fieldset>

		<fieldset>
			<legend>
				<span class="number">2</span>Seleccionar el tipo
			</legend>
			<label>Tipos:</label> <input type="radio" id="incidFich"
				value="incidFich" required autocomplete="off" name="tipo"> <label
				class="light" for="incidFich">Incidencia de Fichaje</label><br>
			<input type="radio" id="vacaciones" value="vacaciones" required
				autocomplete="off" name="tipo"> <label class="light"
				for="vacaciones">Vacaciones</label><br> <input type="radio"
				id="permisos" value="permisos" required autocomplete="off"
				name="tipo"> <label class="light" for="permisos">Permisos</label>
		</fieldset>

		<button type="submit">Crear</button>

	</form>


</body>
</html>