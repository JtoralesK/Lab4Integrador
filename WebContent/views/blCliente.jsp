<%@page import="java.util.List"%>
<%@page import="entidad.cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Cuenta");
	int paginaActual = (Integer) request.getAttribute("paginaActual");
	int totalPaginas = (Integer) request.getAttribute("totalPaginas");
	List<cliente> listadoClientes = (List<cliente>) request.getAttribute("listaPaginada");
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
		        <td class="border px-4 py-2"><%= cliente.getId() %></td>
		        <td class="border px-4 py-2"><%= cliente.getDni() %></td>
		        <td class="border px-4 py-2"><%= cliente.getCuil() %></td>
		        <td class="border px-4 py-2"><%= cliente.getNombre() %></td>
		        <td class="border px-4 py-2"><%= cliente.getApellido() %></td>
		        <td class="border px-4 py-2"><%= cliente.getFechaNacimiento() %></td>
		        <td class="border px-4 py-2"><%= cliente.getEmail() %></td>
		        <td class="border px-4 py-2"><%= cliente.getTelefono() %></td>
		        <td class="border px-4 py-2">
		            <form action="/ProjectBeta1/servletCliente" method="get">
		                <input type="hidden" name="btnModificarCliente" value="<%= cliente.getId() %>">
		                <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="submit" name="btnModificar">Ver/Editar</button>
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
	<div class="text-center">
		<nav aria-label="pagination">
			<ul class="inline-flex -space-x-px text-sm mt-1">
		        <%-- Anterior --%>
		        <% if (paginaActual > 1) { %>
		        <li>
		            <a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual - 1 %>&redirectUrl=blCliente.jsp" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700">Anterior</a>
				</li>
				<% } %>
		        <%-- Números de página --%>
		        <% for (int i = 1; i <= totalPaginas; i++) { 
		        	if(paginaActual==i){
		        %>
		        <li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=blCliente.jsp" aria-current="page" class="flex items-center justify-center px-3 h-8 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700"><%= i %></a>
	        	</li>
		        	<%}else {%>
	        	<li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=blCliente.jsp" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"><%= i %></a>
	        	</li>
		        	<% } 
		        	}%>
		
		        <%-- Siguiente --%>
		        <% if (paginaActual < totalPaginas) { %>
		        <li>
					<a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual + 1 %>&redirectUrl=blCliente.jsp" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700">Siguiente</a>
		        </li>
		        <% } %>
	        </ul>
	    </nav>
	</div>
		<jsp:include page="modal.jsp" />
	
</body>
</html>