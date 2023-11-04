<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="datos.cuentaDao" %>
<%@ page import="entidad.cuenta" %>
<%@page import="java.util.List"%>
<%
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Administrador de cuentas</title>
</head>
<body class="bg-gray-100">

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
        <form method="post" class="flex justify-center mb-4">
            <div class="w-11/12 ">
                <input type="text" id="filtroNombre" name="filtroNombre" placeholder="Filtrar" class="w-64 border border-gray-300 rounded-md p-2">
                <select id="cuenta" name="cuenta" class="w-64 border border-gray-300 rounded-md p-2">
                	<option value="" disabled selected>Tipo de cuenta</option>
                    <option value="ca">Caja de ahorro</option>
                    <option value="cc">Cuenta corriente</option>
                </select>
                <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-2">Buscar</button>
            </div>
        </form>
        <!-- Listado de cuentas -->
  <table class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto table-fixed">
    <thead>
        <!-- Encabezados de la tabla -->
    </thead>
    <tbody>
    <% 
    List<cuenta> cuentas = (List<cuenta>) request.getAttribute("cuentas");
    for (cuenta cuenta : cuentas) { 
    	  System.out.println(cuenta.toString());
    %>
  
        <tr>
            <td class="p-2 text-left"><%= cuenta.getId_cuenta() %></td>
            <td class="p-2 text-left"><%= cuenta.getId_cliente() %></td>
            <td class="p-2 text-left"><%= cuenta.getId_tipo_cuenta() %></td>
            <td class="p-2 text-right">
                <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Eliminar</button>
            </td>
        </tr>
    <% } %>
    </tbody>
</table>
 <div class="flex justify-center">
    <a href="?page=<%= (int) request.getAttribute("page") - 5 %>" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-2">Anterior</a>
    <a href="?page=<%= (int) request.getAttribute("page") + 5 %>" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-2">Siguiente</a>
</div>       
    </div>
</body>
</html>