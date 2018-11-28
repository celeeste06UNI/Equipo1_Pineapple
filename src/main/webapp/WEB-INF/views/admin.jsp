<!DOCTYPE html>
<html lang="en">
<head>
<title>Administrador</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">
.login-block {
	background: #FFFFFF; */ /* fallback for old browsers */
	/* background: -webkit-linear-gradient(to bottom, #FF0000, #FE2E2E);
	/* Chrome 10-25, Safari 5.1-6 */
	/* background: linear-gradient(to bottom, #FF0000, #FE2E2E); */
	/* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
	float: left;
	width: 100%;
	padding: 50px 0;
}

.banner-sec {
	background: url(https://static.pexels.com/photos/33972/pexels-photo.jpg)
		no-repeat left bottom;
	background-size: cover;
	min-height: 500px;
	border-radius: 0 10px 10px 0;
	padding: 0;
}

.container {
	background: #fff;
	border-radius: 10px;
	margin-top: 80px;
	/* box-shadow: 15px 20px 0px rgba(0, 0, 0, 0.1); */
}

.carousel-inner {
	border-radius: 0 10px 10px 0;
}

.carousel-caption {
	text-align: left;
	left: 5%;
}

.login-sec {
	padding: 50px 30px;
	position: relative;
}

.login-sec .copy-text {
	position: absolute;
	width: 80%;
	bottom: 20px;
	font-size: 13px;
	text-align: center;
}

.login-sec .copy-text i {
	color: #FEB58A;
}

.login-sec .copy-text a {
	color: #E36262;
}

.login-sec h2 {
	margin-bottom: 30px;
	font-weight: 800;
	font-size: 30px;
	color: #DE6262;
}

.login-sec h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #fff;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
	margin-left: auto;
	margin-right: auto
}

.btn-login {
	background: #DE6262;
	color: #fff;
	font-weight: 600;
}

.banner-text {
	width: 70%;
	position: absolute;
	bottom: 40px;
	padding-left: 20px;
}

.banner-text h2 {
	color: #fff;
	font-weight: 600;
}

.banner-text h2:after {
	content: " ";
	width: 100px;
	height: 5px;
	background: #FFF;
	display: block;
	margin-top: 20px;
	border-radius: 3px;
}

.banner-text p {
	color: #fff;
}
</style>
</head>
<body>

	<!-- <nav class="navbar navbar-inverse">
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
					data-toggle="dropdown" href="#">Gesti�n Incidencias <span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="/nuevoIncidencia">Crear</a></li>
						<li><a href="/consultarIncidenciaUser">Eliminar/Modificar</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="/viewUpdatePassword"><span
						class="glyphicon glyphicon-pencil"></span> Modificar Contrase�a</a></li>
				<li><a href="/cerrarSesion"><span
						class="glyphicon glyphicon-log-in"></span> Cerrar Sesi�n</a></li>

			</ul>
		</div>
	</nav> -->

	<div class="container">
		<div class="btn-group">
			<div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle btn-lg"
					data-toggle="dropdown">
					Usuario <span class="caret"></span>
				</button>

				<ul class="dropdown-menu" role="menu">
					<li><a href="/newUser">Crear</a></li>
					<li><a href="/deleteUser">Eliminar</a></li>
					<li><a href="/updateUser">Modificar</a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle btn-lg"
					data-toggle="dropdown">
					Fichajes <span class="caret"></span>
				</button>

				<ul class="dropdown-menu" role="menu">
					<li><a href="/newUser">Crear</a></li>
					<li><a href="/deleteUser">Eliminar</a></li>
					<li><a href="/updateUser">Modificar</a></li>
				</ul>
			</div>
			<div class="btn-group">
				<button type="button" class="btn btn-default dropdown-toggle btn-lg"
					data-toggle="dropdown">
					Gesti�n Incidencias<span class="caret"></span>
				</button>

				<ul class="dropdown-menu" role="menu">
					<li><a href="/newUser">Crear</a></li>
					<li><a href="/deleteUser">Eliminar</a></li>
					<li><a href="/updateUser">Modificar</a></li>
				</ul>
			</div>

		</div>

		<button type="button" class="btn btn-default dropdown-toggle btn-lg"
			data-toggle="dropdown" align= "right">
			Modificar Contrase�a <span class="caret"></span>
		</button>


		<button type="button" class="btn btn-default dropdown-toggle btn-lg"
			data-toggle="dropdown"  align= "right">
			Cerrar <span class="caret"></span>
		</button>






	</div>

	<section class="login-block">

		<!-- class="login-block" -->
		<div class="container"
			style="background-color: #E36B6C; margin-top: 0px">

			<div class="row">
				<div class="col-md-4 login-sec">
					<h2 class="text-center" style="color: #fff">Administrador</h2>
					<div class="copy-text" style="color: #fff">Pineapple</div>
				</div>
				<div class="col-md-8 banner-sec">

					<ol class="carousel-indicators">
					</ol>
					<div>
						<div class="banner-text">
							<h2>In Time</h2>
							<p>Aplicaci�n web para la gesti�n de los fichajes</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
</body>
</html>