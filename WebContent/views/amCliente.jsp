<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%
	request.setAttribute("titulo", "Registrar Cliente");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />

	<div class="bg-blue-200 border-b container mx-auto p-4 w-8/12 rounded mt-4">
		<h1 class="text-2xl font-semibold mb-4 text-center">Registro de Cliente</h1>
		<form action="procesar_registro.jsp" method="post">
			<div class="grid grid-cols-2 gap-4">
				<div>
					<label for="dni">DNI:</label> 
					<input type="text" id="dni" name="dni" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cuil">CUIL:</label> 
					<input type="text" id="cuil" name="cuil" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="nombre">Nombre:</label> 
					<input type="text" id="nombre" name="nombre" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="apellido">Apellido:</label> 
					<input type="text" id="apellido" name="apellido" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="sexo">Sexo:</label> 
					<select id="sexo" name="sexo" class="w-full p-2 rounded" required>
						<option value="masculino">Masculino</option>
						<option value="femenino">Femenino</option>
						<option value="otro">Otro</option>
					</select>
				</div>
				<div>
					<label for="nacionalidad">Nacionalidad:</label> 
					<input type="text" id="nacionalidad" name="nacionalidad" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="fechaNacimiento">Fecha de Nacimiento:</label> 
					<input type="date" id="fechaNacimiento" name="fechaNacimiento" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="direccion">Dirección:</label> <input type="text"
						id="direccion" name="direccion" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="localidad">Localidad:</label> 
					<input type="text" id="localidad" name="localidad" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="provincia">Provincia:</label> 
					<input type="text" id="provincia" name="provincia" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="email">Correo Electrónico:</label> 
					<input type="email" id="email" name="email" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="telefonos">Teléfonos:</label> 
					<input type="text" id="telefonos" name="telefonos" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="usuario">Usuario:</label> 
					<input type="text" id="usuario" name="usuario" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="password">Contraseña:</label> 
					<input type="password" id="password" name="password" class="w-full p-2 rounded" required>
				</div>
			</div>
			<div class="flex">
				<button type="submit" class="bg-blue-500 text-white p-3 mt-5 rounded hover:bg-blue-700 ml-auto">Crear Cliente</button>
			</div>
		</form>
	</div>
	<%request.setAttribute("texto", "Usuario creado con éxito!");%>
	<%request.setAttribute("modal", true);%>

	<jsp:include page="modal.jsp" />
</body>
</html>
