<%@page import="java.util.List"%>
<%@page import="entidad.cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Cuenta");
	List<cliente> listadoClientes = (List<cliente>) request.getAttribute("listadoClientes");
%>
<jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
	<div class="flex justify-center">
	<table class="table-auto border-collapse border border-gray-300">
		<thead>
			<tr>
				<th class="px-4 py-2">Id</th>
				<th class="px-4 py-2">Tipo de Usuario</th>
				<th class="px-4 py-2">Dni</th>
				<th class="px-4 py-2">Cuil</th>
				<th class="px-4 py-2">Nombre</th>
				<th class="px-4 py-2">Apellido</th>
				<th class="px-4 py-2">Sexo</th>
				<th class="px-4 py-2">Nacionalidad</th>
				<th class="px-4 py-2">Fecha de nacimiento</th>
				<th class="px-4 py-2">Direccion</th>
				<th class="px-4 py-2">Mail</th>
				<th class="px-4 py-2">Telefono</th>
				<th class="px-4 py-2">Usuario</th>
				<th class="px-4 py-2">Contraseña</th>
			</tr>
		</thead>
		<tbody>
			<%
		    if (listadoClientes != null) {
		        for (cliente cliente : listadoClientes) {
		    %>
		    <tr>
		        <td class="border px-4 py-2"><%= cliente.getId() %></td>
		        <td class="border px-4 py-2"><%= cliente.getTipoUsuario() %></td>
		        <td class="border px-4 py-2"><%= cliente.getDni() %></td>
		        <td class="border px-4 py-2"><%= cliente.getCuil() %></td>
		        <td class="border px-4 py-2"><%= cliente.getNombre() %></td>
		        <td class="border px-4 py-2"><%= cliente.getApellido() %></td>
		        <td class="border px-4 py-2"><%= cliente.getSexo() %></td>
		        <td class="border px-4 py-2"><%= cliente.getNacionalidad() %></td>
		        <td class="border px-4 py-2"><%= cliente.getFechaNacimiento() %></td>
		        <td class="border px-4 py-2"><%= cliente.getDireccion() %></td>
		        <td class="border px-4 py-2"><%= cliente.getEmail() %></td>
		        <td class="border px-4 py-2"><%= cliente.getTelefono() %></td>
		        <td class="border px-4 py-2"><%= cliente.getUsuario() %></td>
		        <td class="border px-4 py-2"><%= cliente.getPassword() %></td>
		        <td class="border px-4 py-2">
		            <form action="/ProjectBeta1/servletCliente" method="get">
		                <input type="hidden" name="btnModificarCliente" value="<%= cliente.getId() %>">
		                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnModificar">Modificar</button>
		            </form>
		        </td>
		        <td class="border px-4 py-2">
		            <form action="/ProjectBeta1/servletCliente" method="post">
		                <input type="hidden" name="btnEliminar" value="<%= cliente.getId() %>">
		                <button class="bg-red-500 hover-bg-red-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnEliminar">Eliminar</button>
		            </form>
		        </td>
		    </tr>
		    <%
		        }
		    }
		    %>
		</tbody>
	</table>
	</div>
		<jsp:include page="modal.jsp" />
	
</body>
</html>