<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Reporte de activos y movimientos");
	LocalDate fechaInicio = (LocalDate)request.getAttribute("fechaInicio");
	LocalDate fechaFin = (LocalDate)request.getAttribute("fechaFin");
	String indicadorActuales = String.format("%,.2f", (double)request.getAttribute("indicadorActuales"));
	String indicadorPrestamos = String.format("%,.2f", (double) request.getAttribute("indicadorPrestamos"));
	String indicadorIngresos = String.format("%,.2f", (double) request.getAttribute("indicadorIngresos"));
	String indicadorEgresos = String.format("%,.2f", (double) request.getAttribute("indicadorEgresos"));
	String filtroIngresos = String.format("%,.2f", request.getAttribute("filtroIngresos") != null ? (double) request.getAttribute("filtroIngresos") : 0);
	String filtroEgresos = String.format("%,.2f", request.getAttribute("filtroEgresos") != null ? (double) request.getAttribute("filtroEgresos") : 0);

%>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"></head>
 <script
      src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
      charset="utf-8"
    ></script>
<body>
<jsp:include page="navbar.jsp" />

<h1 class="text-2xl font-semibold m-4 text-center">Reporte de activos y movimientos</h1>
<div class="container px-40 py-10 bg-gray-200 h-screen w-screen">
	<div class="flex flex-row justify-around gap-5">
	
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-landmark"></i></span>
		<div class="flex flex-col mt-2">
		<b><p class="text-2xl">$ <%= indicadorActuales %></p></b>
		<p class="text-sm">Activos actuales</p>
		</div>
		</div>
		
		<a href="repPrestamos.jsp">
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-regular fa-credit-card"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$ <%= indicadorPrestamos %></p></b>
		<p class="text-md">Préstamos pendientes</p>
		</div>
		</div>
		</a>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-coins"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$ <%=indicadorIngresos %></p></b>
		<p class="text-md">Dinero Ingresado últimos 30 días</p>
		</div>
		
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$ <%= indicadorEgresos %></p></b>
		<p class="text-md">Dinero Egresado últimos 30 días</p>
		</div>
		</div>
		
	</div>
	<form method="get" action="<%= request.getContextPath()%>/servletInformeMovimiento">
	    <div class="flex justify-center my-8">
	        <div class="w-1/2 flex items-center  	space-x-4">
	            <div class="flex-1">
	                <label for="fechaInicio" class="text-sm font-medium">Fecha Inicio:</label>
	                <input type="date" id="fechaInicio" name="fechaInicio" value="<%= fechaInicio %>" class="w-full border border-gray-300 rounded-md p-2">
	            </div>
	            <div class="flex-1">
	                <label for="fechaFin" class="text-sm font-medium">Fecha Fin:</label>
	                <input type="date" id="fechaFin" name="fechaFin" value="<%= fechaFin %>" class="w-full border border-gray-300 rounded-md p-2">
	            </div>
	            
	            <div class="flex-initial items-center">
	                <button type="submit" name="filterInforme" value="true" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Buscar</button>
	            </div>
	        </div>
	    </div>
	</form>
	<div class="w-1/3 bg-white p-4 shadow-md rounded-md m-8 mx-auto text-right">
            <p class="text-xl font-semibold text-green-500">Total Ingresos: $ <%=filtroIngresos %></p>
            <p class="text-xl font-semibold text-red-500">Total Egresos: -$ <%= filtroEgresos %></p>
        </div>
	</div>
	
</body>
</html>