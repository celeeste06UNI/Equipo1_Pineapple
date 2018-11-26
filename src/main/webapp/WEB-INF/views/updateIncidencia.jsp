<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Modificar Incidencia</title>
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


	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/intime">InTime</a>
		</div>
		<ul class="nav navbar-nav">
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Usuarios <span class="caret"></span></a>
				<ul class="dropdown-menu">

					<li><a href="/newUser">Crear</a></li>
					<li><a href="/deleteUser">Eliminar</a></li>
					<li><a href="/updateUser">Modificar</a></li>
				</ul></li>

			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Fichajes<span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/fichajeUser">Fichar</a></li>
					<li><a href="/consultaFichaje">Consultar</a></li>

				</ul></li>
			<li class="dropdown"><a class="dropdown-toggle"
				data-toggle="dropdown" href="#">Gestión Incidencias <span
					class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="#">Crear</a></li>
					<li><a href="#">Eliminar</a></li>
					<li><a href="#">Modificar</a></li>
				</ul></li>
		</ul>
		<ul class="nav navbar-nav navbar-right">
			<li><a href="/viewUpdatePassword"><span
					class="glyphicon glyphicon-pencil"></span> Modificar Contraseña</a></li>
			<li><a href="/cerrarSesion"><span
					class="glyphicon glyphicon-log-in"></span> Cerrar Sesión</a></li>

		</ul>
	</div>
	</nav>


	<form name='IncidenciaForm' action='/ModifyIncidencia' method='POST'>
		<h1>Modificar Incidencia</h1>
		<fieldset>
			<legend>
				<span class="number">1</span>Modificar Incidencia
			</legend>
			<label for="asunto">Asunto:</label> 
			<textarea class="form-control" rows="3" id="asunto" name="asunto">${asunto}</textarea>
			<!-- <input type="text" required
				autocomplete="off" name="asunto" value=${asunto}> --> 
			<label for="descripcion">Descripción:</label>
			<textarea class="form-control" rows="5" id="descripcion" name="descripcion">${descripcion}</textarea>
			<!-- <input
				type="textarea" required autocomplete="off" name="descripcion" value=${descripcion}> -->
		</fieldset>
		<fieldset>
			<label for="estado">Estado</label> 
			<textarea readonly class="form-control" rows="2" id="estado" name="estado">${estado}</textarea>
			
			<label for="tipoModi">Tipo</label> 
			<textarea readonly class="form-control" rows="2" id="tipo" name="tipo">${tipo}</textarea>
			<!-- <input type="text" readonly
  				name="EstadoActual" required autocomplete="off" value=${estado} > -->
			<label>Tipos Disponibles:</label> <input type="radio" id="incidencia"
				value="incident" required autocomplete="off" name="tipo"> <label
				class="light" for="Incidencia">Incidencia por Fichaje</label><br>
			<input type="radio" id="permisos" value="per" required
				autocomplete="off" name="tipo"> <label class="light"
				for="Permisos">Permisos</label><br> <input type="radio"
				id="vacaciones" value="vac" required autocomplete="off" name="tipo">
			<label class="light" for="Vacaciones">Vacaciones</label>
		</fieldset>

		<button type="submit">Actualizar</button>


	</form>

</body>
</html>