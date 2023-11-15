<%@page import="entidad.eTipoUsuario"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Usuario");
	cliente cliente = new cliente();
	Usuario usuario = (Usuario)request.getSession().getAttribute("loggedUser");
	boolean admin = false;
	if(usuario.getTipoUsuario() == eTipoUsuario.Administrador) admin=true;
	else{
		cliente=(cliente)request.getSession().getAttribute("loggedCliente");
	}
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Perfil de Usuario</h1>
        <form class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
            <div class="grid grid-cols-2 gap-4">
            <%if(!admin){%>
            	<div class="mb-4">
                    <label for="dni" class="text-sm font-medium">DNI:</label>
                    <input type="text" id="dni" name="dni" value="<%= cliente.getDni() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="cuil" class="text-sm font-medium">CUIL:</label>
                    <input type="text" id="cuil" name="cuil" value="<%= cliente.getCuil() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="nombre" class="text-sm font-medium">Nombre:</label>
                    <input type="text" id="nombre" name="nombre" value="<%= cliente.getNombre()%>" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
                <div class="mb-4">
                    <label for="apellido" class="text-sm font-medium">Apellido:</label>
                    <input type="text" id="apellido" name="apellido" value="<%= cliente.getApellido() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
                </div>
             	<div class="mb-4">
				    <label for="sexo" class="text-sm font-medium">Sexo:</label>
				    <input type="text" id="sexo" name="sexo" value="<%=cliente.getSexo().toString() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="nacionalidad" class="text-sm font-medium">Nacionalidad:</label>
				    <input type="text" id="nacionalidad" name="nacionalidad" value="<%= cliente.getNacionalidad().getNombre() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="fechaNacimiento" class="text-sm font-medium">Fecha de Nacimiento:</label>
				    <input type="text" id="fechaNacimiento" name="fechaNacimiento" value="<%= cliente.getFechaNacimiento().toString() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="direccion" class="text-sm font-medium">Dirección:</label>
				    <input type="text" id="direccion" name="direccion" value="<%= cliente.getDireccion().getDireccion() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="localidad" class="text-sm font-medium">Localidad:</label>
				    <input type="text" id="localidad" name="localidad" value="<%=cliente.getDireccion().getLocalidad().getNombre() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="provincia" class="text-sm font-medium">Provincia:</label>
				    <input type="text" id="provincia" name="provincia" value="<%= cliente.getDireccion().getLocalidad().getProvincia().getNombre() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="correoElectronico" class="text-sm font-medium">Correo Electrónico:</label>
				    <input type="text" id="correoElectronico" name="correoElectronico" value="<%=cliente.getEmail() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
				<div class="mb-4">
				    <label for="telefonos" class="text-sm font-medium">Teléfonos:</label>
				    <input type="text" id="telefonos" name="telefonos" value="<%= cliente.getTelefono() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
            <%}%>
				<div class="mb-4">
				    <label for="usuario" class="text-sm font-medium">Usuario:</label>
				    <input type="text" id="usuario" name="usuario" value="<%= usuario.getUsuario() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
				</div>
                <div class="mb-4">
                    <label for="password" class="text-sm font-medium">Contraseña:</label>
                    <div class="flex">
                        <input type="password" id="password" name="password" value="<%= usuario.getContraseña() %>" class="w-full border border-gray-300 rounded-md p-2" readonly>
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