<!DOCTYPE html>
<html lang="en">
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Modificar Usuario</title>

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
					data-toggle="dropdown" href="#">Gestión Incidencias <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="#">Crear</a></li>
						<li><a href="#">Eliminar</a></li>
						<li><a href="#">Modificar</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/cerrarSesion"><span
						class="glyphicon glyphicon-log-in"></span> Cerrar Sesión</a></li>
			</ul>
		</div>
	</nav>
	<!-- 	<div class="container">
		<form name='searchForm' action='/searchUser' method='POST'>

			<h1>Busqueda de un usuario</h1>
			<label for="name">Introduzca el email:</label> <input type="email"
				name="email">
			<button type="submit">Buscar</button>
		</form>
		
		<form name='updateForm' action='/editUser' method='POST'>
			
			<label for="email">Email</label> <input type="email" name="emailAntiguo"
				readonly value=${email} >
			
			<label for="nombre">Nombre</label> <input type="text" name="nombre"
				value=${nombre} >
				
			<label for="apellidos">Apellidos</label> <input type="text" name="apellidos"
				value=${apellidos} >
				
			<label for="email">Email</label> <input type="email" name="emailNuevo"
				value=${email} >
				
			<label for="rol">Rol</label> <input type="text" name="rol"
				value=${rol} >
				
			<button type="submit">Actualizar</button>
			
		</form>

	</div> -->

	<div class="container">
		<div class="page-header">
			<h1>Modificar de Usuario</h1>
		</div>
		<div class="row">
			<div class="col-sm-6" style="background-color: white;">


				<form name='searchForm' action='/searchUser' method='POST'>

					<fieldset>
						<legend>
							<span class="number">1</span>Introduzca el e-mail del usuario
						</legend>
						<label for="name">Introduzca el email:</label> <input type="email"
							name="email">
						<button type="submit">Buscar</button>
					</fieldset>
				</form>


			</div>
			<div class="col-sm-6" style="background-color: white;">

				<form name='updateForm' action='/editUser' method='POST'>

					<fieldset>
						<legend>
							<span class="number">2</span>Modificar campos
						</legend>

						<label for="email">Email Actual</label> <input type="email"
							name="emailAntiguo" readonly value=${email} > <label
							for="nombre">Nombre</label> <input type="text" name="nombre"
							value=${nombre} > <label for="apellidos">Apellidos</label>
						<input type="text" name="apellidos" value=${apellidos} > <label
							for="email">Nuevo Email</label> <input type="email"
							name="emailNuevo" value=${email} > <label>Roles:</label>
						<input type="radio" id="admin" value="admin" name="rol">
						<label class="light" for="Administrador">Administrador</label><br>
						<input type="radio" id="usuario" value="user" name="rol">
						<label class="light" for="Usuario">Usuario</label><br> <input
							type="radio" id="gestor" value="incid" name="rol"> <label
							class="light" for="Gestor">Gestor</label>
					</fieldset>

					<button type="submit">Actualizar</button>

				</form>
			</div>
		</div>
	</div>
</body>
