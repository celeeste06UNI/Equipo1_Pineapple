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
<title>Insert title here</title>
<style type="text/css">
body {
	background-color: #525252;
}

.centered-form {
	margin-top: 60px;
}

.centered-form .panel {
	background: rgba(255, 255, 255, 0.8);
	box-shadow: rgba(0, 0, 0, 0.3) 20px 20px 20px;
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

	<div class="container">
		<div class="row centered-form">
			<div
				class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">
							Bienvenido Por Favor Registrese <small>It's free!</small>
						</h3>
					</div>
					<div class="panel-body">
						<form name='loginForm' action='/saveUser' method='POST'>
							<div class="row">
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<input type="text" name="nombre" class="form-control input-sm"
											placeholder="Nombre">
									</div>
								</div>
								<div class="col-xs-6 col-sm-6 col-md-6">
									<div class="form-group">
										<input type="text" name="apellido"
											class="form-control input-sm" placeholder="Apellido">
									</div>
								</div>
							</div>

							<div class="form-group">
								<input type="text" name="rol" class="form-control input-sm"
									placeholder="rol">
							</div>

							<div class="form-group">
								<input type="email" name="email" class="form-control input-sm"
									placeholder="Email">
							</div>

							<input type="submit" value="Registrar"
								class="btn btn-info btn-block">

						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>