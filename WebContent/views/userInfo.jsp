<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Usuario</title>
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Perfil de Usuario</h1>
        <form class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
            <div class="grid grid-cols-2 gap-4">
                <div class="mb-4">
                    <label for="dni" class="text-sm font-medium">DNI:</label>
                    <input type="text" id="dni" name="dni" value="12345678" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="cuil" class="text-sm font-medium">CUIL:</label>
                    <input type="text" id="cuil" name="cuil" value="20-12345678-0" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="nombre" class="text-sm font-medium">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="Juan" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="apellido" class="text-sm font-medium">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="Pérez" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
             	<div class="mb-4">
				    <label for="sexo" class="text-sm font-medium">Sexo:</label>
				    <input type="text" id="sexo" name="sexo" value="Masculino" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="nacionalidad" class="text-sm font-medium">Nacionalidad:</label>
				    <input type="text" id="nacionalidad" name="nacionalidad" value="Argentina" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="fechaNacimiento" class="text-sm font-medium">Fecha de Nacimiento:</label>
				    <input type="text" id="fechaNacimiento" name="fechaNacimiento" value="01/01/1990" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="direccion" class="text-sm font-medium">Dirección:</label>
				    <input type="text" id="direccion" name="direccion" value="Calle Principal 123" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="localidad" class="text-sm font-medium">Localidad:</label>
				    <input type="text" id="localidad" name="localidad" value="Ciudad Capital" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="provincia" class="text-sm font-medium">Provincia:</label>
				    <input type="text" id="provincia" name="provincia" value="Buenos Aires" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="correoElectronico" class="text-sm font-medium">Correo Electrónico:</label>
				    <input type="text" id="correoElectronico" name="correoElectronico" value="usuario@example.com" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="telefonos" class="text-sm font-medium">Teléfonos:</label>
				    <input type="text" id="telefonos" name="telefonos" value="+1 123-456-7890" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="usuario" class="text-sm font-medium">Usuario:</label>
				    <input type="text" id="usuario" name="usuario" value="miusuario" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
                <div class="mb-4">
                    <label for="password" class="text-sm font-medium">Contraseña:</label>
                    <div class="flex">
                        <input type="password" id="password" name="password" value="pepito" class="w-full border border-gray-300 rounded-md p-2" readonly>
                        <button id="togglePassword" type="button" class="bg-gray-300 text-gray-600 px-4 py-2 rounded-md ml-2">Mostrar</button>
                    </div>
                </div>
            </div>
        </form>
    </div>

    <script>
        const togglePasswordButton = document.getElementById('togglePassword');
        const passwordField = document.getElementById('password');

        togglePasswordButton.addEventListener('click', () => {
            if (passwordField.type === 'password') {
                passwordField.type = 'text';
                togglePasswordButton.textContent = 'Ocultar';
            } else {
                passwordField.type = 'password';
                togglePasswordButton.textContent = 'Mostrar';
            }
        });
    </script>
</body>
</html>