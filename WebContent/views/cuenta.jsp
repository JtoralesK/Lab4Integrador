<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Cuenta"); %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
<br>
<h2 class="text-center mx-auto text-4xl">Historial de movimientos</h2>
<br>
	<table class="w-10/12 divide-y divide-gray-200 mx-auto">
		<thead>
			<tr>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipo
					de Movimiento</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Hora</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Importe</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Concepto</th>
			</tr>
		</thead>
		<tbody class="bg-white divide-y divide-gray-200">
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">Transferencia</td>
				<td class="px-6 py-4 whitespace-nowrap">20/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">14:00</td>
				<td class="px-6 py-4 whitespace-nowrap text-red-500">$5000</td>
				<td class="px-6 py-4 whitespace-nowrap">CBU Destino:
					0021548410054</td>

			</tr>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">Alta de préstamo</td>
				<td class="px-6 py-4 whitespace-nowrap">18/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">13:15</td>
				<td class="px-6 py-4 whitespace-nowrap text-green-500">$150000</td>
				<td class="px-6 py-4 whitespace-nowrap">Préstamo ID: 2</td>
			</tr>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">Pago de préstamo</td>
				<td class="px-6 py-4 whitespace-nowrap">15/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">13:15</td>
				<td class="px-6 py-4 whitespace-nowrap text-red-500">$6500</td>
				<td class="px-6 py-4 whitespace-nowrap">Préstamo ID: 1</td>
			</tr>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">Alta de préstamo</td>
				<td class="px-6 py-4 whitespace-nowrap">07/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">13:15</td>
				<td class="px-6 py-4 whitespace-nowrap text-green-500">$12000</td>
				<td class="px-6 py-4 whitespace-nowrap">Préstamo ID: 1</td>
			</tr>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">Alta de cuenta</td>
				<td class="px-6 py-4 whitespace-nowrap">01/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">10:25</td>
				<td class="px-6 py-4 whitespace-nowrap text-green-500">$10000</td>
				<td class="px-6 py-4 whitespace-nowrap">Nueva cuenta creada</td>
			</tr>
		</tbody>
	</table>
<br>
<h1 class="text-center mx-auto text-4xl">Historial de Préstamos</h1>
<br>
	
	<table class="w-10/12 divide-y divide-gray-200 mx-auto">
		<thead>
			<tr>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Préstamo ID</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Importe total</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Cantidad de cuotas</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Solicitud</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Aprobación</th>
			</tr>
		</thead>
		<tbody class="bg-white divide-y divide-gray-200">
			<tr>
				<td class="px-6 py-4 whitespace-nowrap">2</td>
				<td class="px-6 py-4 whitespace-nowrap">$150000</td>
				<td class="px-6 py-4 whitespace-nowrap">9</td>
				<td class="px-6 py-4 whitespace-nowrap">15/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">18/10/2023</td>
			</tr>

			<tr>
				<td class="px-6 py-4 whitespace-nowrap">1</td>
				<td class="px-6 py-4 whitespace-nowrap">$12000</td>
				<td class="px-6 py-4 whitespace-nowrap">3</td>
				<td class="px-6 py-4 whitespace-nowrap">05/10/2023</td>
				<td class="px-6 py-4 whitespace-nowrap">07/10/2023</td>
			</tr>
		</tbody>
	</table>
</body>
</html>