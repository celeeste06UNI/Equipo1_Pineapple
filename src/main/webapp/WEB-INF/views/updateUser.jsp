<!DOCTYPE html>
<html lang="en">
<head>
<title>Editar Usuario</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
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
						<li><a href="#">Eliminar</a></li>
						<li><a href="#">Modificar</a></li>
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

	<form name='searchForm' action='/searchUser' method='POST'>
		<div class= "container">
			<h1>Busqueda de un usuario</h1>
			<label for="name">Introduzca el email:</label> <input type="email" name="email">
			<button type="submit">Buscar</button>
		</div>


	</form>

</body>