<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="datos.cuentaDao" %>
<%@ page import="entidad.cuenta" %>
<%@ page import="entidad.eTipoCuenta" %>
<%@page import="java.util.List"%>
<%
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Administrador de cuentas");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />

       <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Administración de Cuentas de Clientes</h1>
        <!-- Filtros de búsqueda y botón de agregar cuenta -->
        <div class="flex justify-center">
		<div class="w-11/12 flex justify-between">
            <h2 class="text-lg font-semibold">Filtros</h2>
            <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">Agregar Cuenta</button>
        </div>
        </div>
        <!-- Filtros de búsqueda dentro de un formulario -->
       <form method="get" action="<%= request.getContextPath() %>/servletCuenta" class="flex justify-center mb-4">
    <div class="w-11/12">
     <input type="hidden" name="accion" value="blCuentas">
        <input type="text" placeholder="cbu" name="filterCbu" class="w-64 border border-gray-300 rounded-md p-2">
        <select id="cuenta" name="filter" class="w-64 border border-gray-300 rounded-md p-2">
            <option value="blCuentas" disabled selected>Tipo de cuenta</option>
            <option value="1">Caja de ahorro</option>
            <option value="2">Cuenta corriente</option>
        </select>
        <button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-2">Buscar</button>
    </div>
</form>
        <!-- Listado de cuentas -->
  <table class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto table-fixed">
    <thead>
        <thead>
                <tr>
                    <th class="border-b-2 p-2">Numero de cuenta</th>
                    <th class="border-b-2 p-2">Cliente</th>
                    <th class="border-b-2 p-2">Tipo de cuenta</th>
                    <th class="border-b-2 p-2">Saldo</th>
               		<th class="border-b-2 p-2">Fecha creacion</th>
                    <th class="border-b-2 p-2">Cbu</th>
                   	<th class="border-b-2 p-2"></th> 
                </tr>
            </thead>
    </thead>
    <tbody>
    <% 
    request.setAttribute("titulo", "Cuenta");
	int paginaActual = (Integer) request.getAttribute("paginaActual");
	int totalPaginas = (Integer) request.getAttribute("totalPaginas");
	List<cuenta> listadoCuentas = (List<cuenta>) request.getAttribute("listaPaginada");

    for (cuenta cuenta : listadoCuentas) { 
    %>
  
        <tr>
            <td class="p-2 text-center"><%= cuenta.getId_cuenta() %></td>
            <td class="p-2 text-center"><%= cuenta.getId_cliente() %></td>
            <td class="p-2 text-center"><%= cuenta.getId_tipo_cuenta() %></td>
            <td class="p-2 text-center"><%= cuenta.getSaldo() %></td>
            <td class="p-2 text-center"><%= cuenta.getFecha_creacion() %></td>
            <td class="p-2 text-center text-green-600"><%= cuenta.getCbu() %></td>
            <td class="p-2 text-center">
                <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Eliminar</button>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
     <div class="text-center">
		<nav aria-label="pagination">
			<ul class="inline-flex -space-x-px text-sm mt-1">
		        <%-- Anterior --%>
		        <% if (paginaActual > 1) { %>
		        <li>
		            <a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual - 1 %>&redirectUrl=blCuentas.jsp" class="flex items-center justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700">Anterior</a>
				</li>
				<% } %>
		        <%-- Números de página --%>
		        <% for (int i = 1; i <= totalPaginas; i++) { 
		        	if(paginaActual==i){
		        %>
		        <li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=blCuentas.jsp" aria-current="page" class="flex items-center justify-center px-3 h-8 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700"><%= i %></a>
	        	</li>
		        	<%}else {%>
	        	<li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=blCuentas.jsp" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"><%= i %></a>
	        	</li>
		        	<% } 
		        	}%>
		
		        <%-- Siguiente --%>
		        <% if (paginaActual < totalPaginas) { %>
		        <li>
					<a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual + 1 %>&redirectUrl=blCuentas.jsp" class="flex items-center justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700">Siguiente</a>
		        </li>
		        <% } %>
	        </ul>
	    </nav>
	</div>
    </div>
</body>
</html>