<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Reporte de activos y movimientos</title>
<script src="https://cdn.tailwindcss.com"></script>
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
		<b><p class="text-2xl">$2942M</p></b>
		<p class="text-sm">Activos actuales</p>
		</div>
		</div>
		
		<a href="repPrestamos.jsp">
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-regular fa-credit-card"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$800M</p></b>
		<p class="text-md">Préstamos pendientes</p>
		</div>
		</div>
		</a>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-coins"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$20000M</p></b>
		<p class="text-md">Dinero Ingresado</p>
		</div>
		
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$15000M</p></b>
		<p class="text-md">Dinero Egresado</p>
		</div>
		</div>
		
	</div>
	<div class="flex justify-center mt-8">
            <form method="post">
                <div class="w-11/12 flex justify-between">
                    <h2 class="text-lg font-semibold">Filtros</h2>
                    <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Buscar</button>
                </div>
                <div class="w-11/12">
                    <input type="text" id="filtroFechaInicio" name="filtroFechaInicio" placeholder="Fecha de inicio" class="w-64 border border-gray-300 rounded-md p-2">
                    <input type="text" id="filtroFechaFin" name="filtroFechaFin" placeholder="Fecha de fin" class="w-64 border border-gray-300 rounded-md p-2">
                    <select id="filtroEstado" name="filtroEstado" class="w-64 border border-gray-300 rounded-md p-2">
                        <option value="todos">Todos</option>
                        <option value="ingresos">Ingresos</option>
                        <option value="egresos">Egresos</option>
                    </select>
                </div>
            </form>
        </div>
	<div class="w-1/4 bg-white p-4 shadow-md rounded-md m-8 mx-auto text-right">
            <p class="text-xl font-semibold text-green-500">Total Ingresos: $1200</p>
            <p class="text-xl font-semibold text-red-500">Total Egresos: -$800</p>
        </div>
	</div>
	
</body>
</html>