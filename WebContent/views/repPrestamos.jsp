<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Reporte de préstamos");
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
		<b><p class="text-2xl">$800M</p></b>
		<p class="text-sm">Préstamos pendientes de pago</p>
		</div>
		</div>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-regular fa-credit-card"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">10</p></b>
		<p class="text-md">Aprobados últimos 30 días</p>
		</div>
		</div>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-coins"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$200M</p></b>
		<p class="text-md">Importe últimos 30 días</p>
		</div>
		
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">3</p></b>
		<p class="text-md">Rechazados últimos 30 días</p>
		</div>
		</div>
		
	</div>
	<div class="flex justify-center my-8">
    <div class="w-11/12 flex justify-between items-center">
        <div class="w-1/4">
            <label for="fechaInicio" class="text-sm font-medium">Fecha Inicio:</label>
            <input type="date" id="fechaInicio" name="fechaInicio" class="w-full border border-gray-300 rounded-md p-2">
        </div>
        <div class="w-1/4">
            <label for="fechaFin" class="text-sm font-medium">Fecha Fin:</label>
            <input type="date" id="fechaFin" name="fechaFin" class="w-full border border-gray-300 rounded-md p-2">
        </div>
        <div class="w-1/4">
            <label for="estado" class="text-sm font-medium">Estado:</label>
            <select id="estado" name="estado" class="w-full border border-gray-300 rounded-md p-2">
                <option value="aprobado">Aprobado</option>
                <option value="rechazado">Rechazado</option>
                <option value="pendiente">Pendiente</option>
            </select>
        </div>
        <div class="w-1/4 flex justify-end">
            <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Buscar</button>
        </div>
    </div>
</div>
	<div class="w-11/12 mx-auto mt-6 bg-white p-4 shadow-md rounded-md">
    <p class="text-xl font-semibold">Importe total del período:</p>
    <p id="importeTotal" class="text-2xl">$0.00</p>
</div>
	</div>
	
</body>
</html>