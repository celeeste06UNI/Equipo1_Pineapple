<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<script type="text/javascript">
	function pregunta() {
		if (confirm("¿Desea consultar los fichajes?")) {
			document.searchForm.submit()
		}
	}
</script>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<title>Gestor Fichajes</title>

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
	input[type="time"], input[type="url"], input[type="mes"], textarea,
	select {
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
			&nbsp&nbsp<a style="color: #cc0000" href="intime">atrás</a>
		</div>
		<div class="col-sm-2"></div>
		<div class="col-sm-8"></div>
	</div>
	<div class="container">
		<div class="page-header">
			<h1>Consulta de Fichajes</h1>
		</div>
		<form name='searchFichaje' action='searchFichajeOtro' method='GET'>

			<fieldset>
				<legend>
					<span class="number">1</span>Consultar Fichajes
				</legend>
				<label for="emailF">Email:<br></label> <input
					placeholder="email" type="text" name="emailF"><label
					for="fechaI">Introduzca la fecha inicio:<br></label> <input
					placeholder="yyyy/MM/dd" type="text" name="fechaIn"> <label
					for="fechaF">Introduzca la fecha fin:<br></label> <input
					placeholder="yyyy/MM/dd" type="text" name="fechaFi">

				<button type="submit">Buscar</button>
			</fieldset>
		</form>
	</div>
	<div class="container" align="left">
		<table class="table table-hover">
			<p>Fecha de los fichajes</p>
			<!-- <th>Id</th> -->
			<th>Fecha de apertura / Fecha de cierre / Tiempo trabajado</th>
			<c:forEach var="fechaDelFichaje" items="${listDateOtro}">
				<tr>
					<td>${fechaDelFichaje}</td>
				</tr>
			</c:forEach>
		</table>
	</div>

	<div class="col-sm-6" style="background-color: white;"></div>

</body>