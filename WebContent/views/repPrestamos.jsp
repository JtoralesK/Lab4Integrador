<%@page import="entidad.eEstadoPrestamo"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Reporte de préstamos");
	String importeTotal = String.format("%,.2f",request.getAttribute("importeTotal")!=null ? (double)request.getAttribute("importeTotal") : 0);
	LocalDate fechaInicio = (LocalDate)request.getAttribute("fechaInicio");
	LocalDate fechaFin = (LocalDate)request.getAttribute("fechaFin");
	eEstadoPrestamo selectedState = (eEstadoPrestamo)request.getAttribute("estado");
	String indicadorPendientes = String.format("%,.2f",(float) request.getAttribute("indicadorPendientes"));
	int aprobados30 = (int) request.getAttribute("indicadorAprobados");
	String indicadorImporte30 = String.format("%,.2f",(float) request.getAttribute("indicadorImporte"));
	int rechazados30 = (int) request.getAttribute("indicadorRechazados");
%>
<jsp:include page="head.jsp" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"></head>
 <script
      src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
      charset="utf-8"
    ></script>
<body>
<jsp:include page="navbar.jsp" />

<h1 class="text-2xl font-semibold m-4 text-center">Reporte de préstamos</h1>
<div class="container px-40 py-10 bg-gray-200 h-screen w-screen">
	<div class="flex flex-row justify-around gap-5">
	
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-landmark"></i></span>
		<div class="flex flex-col mt-2">
		<b><p class="text-2xl">$ <%=indicadorPendientes %></p></b>
		<p class="text-sm">Préstamos pendientes de pago</p>
		</div>
		</div>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-regular fa-credit-card"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl"><%=aprobados30 %></p></b>
		<p class="text-md">Aprobados últimos 30 días</p>
		</div>
		</div>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-coins"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$ <%=indicadorImporte30 %></p></b>
		<p class="text-md">Importe últimos 30 días</p>
		</div>
		
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl"><%= rechazados30 %></p></b>
		<p class="text-md">Rechazados últimos 30 días</p>
		</div>
		</div>
		
	</div>
	<form method="get" action="<%= request.getContextPath()%>/servletInformePrestamos">
	<div class="flex justify-center my-8">
	    <div class="w-11/12 flex justify-between items-center">
	        <div class="w-1/4">
	            <label for="fechaInicio" class="text-sm font-medium">Fecha Inicio:</label>
	            <input type="date" id="fechaInicio" name="fechaInicio" value="<%= fechaInicio %>" class="w-full border border-gray-300 rounded-md p-2">
	        </div>
	        <div class="w-1/4">
	            <label for="fechaFin" class="text-sm font-medium">Fecha Fin:</label>
	            <input type="date" id="fechaFin" name="fechaFin" value="<%= fechaFin %>" class="w-full border border-gray-300 rounded-md p-2">
	        </div>
	        <div class="w-1/4">
	            <label for="estado" class="text-sm font-medium">Estado:</label>
	            <select id="estado" name="estado" class="w-full border border-gray-300 rounded-md p-2" required>
				    <option disabled value="" selected>Estado</option> 
				    <option value="1" <%=(selectedState == eEstadoPrestamo.Aprobado) ? "selected" : ""%>>Aprobado</option>
				    <option value="0" <%=(selectedState == eEstadoPrestamo.Rechazado) ? "selected" : ""%>>Rechazado</option>
				    <option value="2" <%=(selectedState == eEstadoPrestamo.Pendiente) ? "selected" : ""%>>Pendiente</option>
				</select>
	        </div>
	        <div class="w-1/4 flex justify-end">
	            <button type="submit" name="filterInforme"  value="true" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Buscar</button>
	        </div>
	    </div>
</div>
	</form>
	<div class="w-11/12 mx-auto mt-6 bg-white p-4 shadow-md rounded-md">
    <p class="text-xl font-semibold">Importe total de los prestamos aprobados del período seleccionado:</p>
    <p id="importeTotal" class="text-2xl">$ <%= importeTotal %></p>
</div>
	</div>
	
</body>
</html>