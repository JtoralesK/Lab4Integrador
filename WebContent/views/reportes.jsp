<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://cdn.tailwindcss.com"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"></head>
 <script
      src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.3/Chart.min.js"
      charset="utf-8"
    ></script>
<body>
<jsp:include page="navbar.jsp" />

<div class="container px-40 py-10 bg-gray-200 h-screen w-screen">
	<div class="flex flex-row justify-around gap-5">
	
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-landmark"></i></span>
		<div class="flex flex-col mt-2">
		<b><p class="text-2xl">$2500K</p></b>
		<p class="text-sm">Prestamo Banco Galicia </p>
		<p class="text-sm">Cuota 2/3 </p>
		</div>
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-regular fa-credit-card"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$5000K</p></b>
		<p class="text-md">Dinero Transferido</p>
		</div>
		</div>
		
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-coins"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$20000K</p></b>
		<p class="text-md">Dinero Ingresado</p>
		</div>
		</div>
		<div class="h-36 flex-1 bg-white p-8 shadow-md overflow-hidden">
		<span class=" rounded-3xl p-4 bg-gray-100"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		<div class="flex flex-col mt-3">
		<b><p class="text-2xl">$15000K</p></b>
		<p class="text-md">Dinero Egresado</p>
		</div>
		</div>
		
	</div>
	
	<div>
	
<div class="relative overflow-x-auto py-10">
    <table class="w-full text-sm text-left text-gray-500 dark:text-gray-400">
        <thead class="text-xs text-gray-700 uppercase bg-gray-100 dark:bg-gray-700 dark:text-gray-400">
            <tr>
                <th scope="col" class="px-6 py-3 rounded-l-lg">
                    Ultimos Movimientos
                </th>
                <th scope="col" class="px-6 py-3">
                    Fecha
                </th>
                <th scope="col" class="px-6 py-3 rounded-r-lg">
                    Cantidad
                </th>
            </tr>
        </thead>
        <tbody>
            <tr class="bg-white dark:bg-gray-800">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    Egreso
                </th>
                <td class="px-6 py-4">
                    18/8/2023
                </td>
                <td class="px-6 py-4">
                    $2999
                </td>
            </tr>
            <tr class="bg-white dark:bg-gray-800">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    Transaferencia
                </th>
                <td class="px-6 py-4">
                    20/8/2023
                </td>
                <td class="px-6 py-4">
                    $1999
                </td>
            </tr>
            <tr class="bg-white dark:bg-gray-800">
                <th scope="row" class="px-6 py-4 font-medium text-gray-900 whitespace-nowrap dark:text-white">
                    Transferencia
                </th>
                <td class="px-6 py-4">
                    21/8/2023
                </td>
                <td class="px-6 py-4">
                    $99
                </td>
            </tr>
        </tbody>
        <tfoot>
            <tr class="font-semibold text-gray-900 dark:text-white">
                <th scope="row" class="px-6 py-3 text-base">Total</th>
                <td class="px-6 py-3">3</td>
                <td class="px-6 py-3">21,000</td>
            </tr>
        </tfoot>
    </table>
</div>
	</div>
	</div>
	
</body>
</html>