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
        <h1 class="text-2xl font-semibold mb-4 text-center">Administraci�n de Cuentas de Clientes</h1>
        <!-- Filtros de b�squeda y bot�n de agregar cuenta -->
        <div class="flex justify-center">
		<div class="w-11/12 flex justify-between">
            <h2 class="text-lg font-semibold">Filtros</h2>
            <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">Agregar Cuenta</button>
        </div>
        </div>
        <!-- Filtros de b�squeda dentro de un formulario -->
       <form method="get" action="<%= request.getContextPath() %>/servletCuenta" class="flex justify-center mb-4">
    <div class="w-11/12">
     <input type="hidden" name="accion" value="blCuentas">
        <input type="text" placeholder="cbu" name="cbu" class="w-64 border border-gray-300 rounded-md p-2">
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
            <td class="p-2 text-center text-gray-400"><%= cuenta.getCbu() %></td>
            <td class="p-2 text-center">
                <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Eliminar</button>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
     
    </div>
</body>
</html>