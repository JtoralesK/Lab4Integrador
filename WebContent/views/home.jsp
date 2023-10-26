<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Home</title>
</head>
<body class="h-screen  ">
	<jsp:include page="navbar.jsp" />
	<div class="flex flex-row w-full h-full">
		<div
			class="w-2/12 h-screen bg-white border-r border-gray-300 flex flex-col p-6">
			<div class="w-full h-1/6 border-b border-gray-300">
				<p>Empresa :</p>
			</div>
			<div class="w-full h-5/6 flex flex-col gap-6 pt-5 ">
				<a href="#">Mensajes</a> <a href="#">Administar Empresas</a> <a
					href="#">Solicitudes</a> <a href="#">Consulta De Operaciones</a> <a
					href="#">Autorizar Operaciones</a> <a href="#">Descargar De
					Archivos</a> <a href="#">Autorizacion de plazo fijo </a> <a href="#">Ayuda</a>
			</div>
		</div>
		<div class="w-10/12 h-screen p-7 flex flex-col gap-8">
			<div class="h-16">
				<h2 class="text-xl">
					<b>Hola,Javier Torales</b>
				</h2>
			</div>
			<div class="h-32 border rounded-lg border-gray-300 px-3 py-2">
				<h3 class="text-lg">Accesos Directos</h3>
				<div class="flex flex-row gap-5 py-3">
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
				</div>
			</div>
			<div class="h-44 border rounded-lg border-gray-300 p-3">
				<h3 class="text-lg">Todas las cuentas</h3>
				<div class="flex flex-row gap-5 py-5">
					<div class="w-1/5 h-24  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-24  border rounded-lg border-gray-300"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>