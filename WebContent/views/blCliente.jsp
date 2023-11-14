<%@page import="java.util.List"%>
<%@page import="entidad.cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Clientes");
	int paginaActual = (Integer) request.getAttribute("paginaActual");
	int totalPaginas = (Integer) request.getAttribute("totalPaginas");
	List<cliente> listadoClientes = (List<cliente>) request.getAttribute("listaPaginada");
%>
<jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
	<div class="flex justify-center">
		<div class="flex m-4 w-10/12 justify-between">
		    <form action="/ProjectBeta1/servletCliente" method="get">
		        <label for="busqueda" class="sr-only">Búsqueda:</label>
		        <input type="text" id="busqueda" name="busqueda" placeholder="Buscar..." class="w-64 border border-gray-300 rounded-md p-2">
		        
		        <select id="estado" name="filtroEstado" class="w-64 border border-gray-300 rounded-md p-2">
				    <option value="true" <%= request.getParameter("filtroEstado") != null && request.getParameter("filtroEstado").equals("true") ? "selected" : "" %>>Activo</option>
				    <option value="false" <%= request.getParameter("filtroEstado") != null && request.getParameter("filtroEstado").equals("false") ? "selected" : "" %>>Inactivo</option>
				    <option value="all" <%= request.getParameter("filtroEstado") != null && request.getParameter("filtroEstado").equals("all") ? "selected" : "" %>>Todos</option>
				</select>
		        <button type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded ml-2">Buscar</button>
		    </form>
		</div>
	</div>
	<div class="flex justify-center">
	
	<table class="w-10/12 bg-white p-4 shadow-md rounded-md mb-4 mx-auto table-fixed">
		<thead>
			<tr>
				<th class="px-4 py-2">Id</th>
				<th class="px-4 py-2">Dni</th>
				<th class="px-4 py-2">Cuil</th>
				<th class="px-4 py-2">Nombre</th>
				<th class="px-4 py-2">Apellido</th>
				<th class="px-4 py-2">Fecha de nacimiento</th>
				<th class="px-4 py-2">Mail</th>
				<th class="px-4 py-2">Telefono</th>
				<th class="px-4 py-2">Acciones</th>
				<th class="px-4 py-2"></th>
			</tr>
		</thead>
		<tbody>
			<%
		    if (listadoClientes != null && !listadoClientes.isEmpty()) {
		        for (cliente cliente : listadoClientes) {
		    %>
		    <tr>
		        <td class="border px-4 py-2 break-words"><%= cliente.getId() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getDni() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getCuil() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getNombre() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getApellido() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getFechaNacimiento() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getEmail() %></td>
		        <td class="border px-4 py-2 break-words"><%= cliente.getTelefono() %></td>
		        <td class="border px-4 py-2">
		            <form action="/ProjectBeta1/servletCliente" method="get">
		                <input type="hidden" name="btnModificarCliente" value="<%= cliente.getId() %>">
		                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnModificar">Ver/Editar</button>
		            </form>
		        </td>
		        <td class="border px-4 py-2">
				    <form action="/ProjectBeta1/servletCliente" method="post">
				        <input type="hidden" name="btnToggleEstado" value="<%= cliente.getId() %>">
				        
				        <% if (cliente.getEstado()) { %>
				            <button class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnEliminar">Eliminar</button>
				        <% } else { %>
				            <button class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnActivar">Activar</button>
				        <% } %>
				    </form>
				</td>

		    </tr>
		    <%
		        }
		    }else {
		    %>
		    <tr>
                <td colspan="10" class="border px-4 py-2 text-center">No hay datos disponibles</td>
            </tr>
            <%
            }
            %>
		</tbody>
	</table>
	
	</div>
		<jsp:include page="paginacion.jsp" />
		<jsp:include page="modal.jsp" />
	
</body>
</html>