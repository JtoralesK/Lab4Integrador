<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Prestamo"); %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
	<div class="container mx-auto py-4 flex">
		<div class="w-4/12 pr-1">
			<h1 class="text-2xl font-semibold mb-4 text-center">Solicitud de Préstamo</h1>

			<form method="post" class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
				<div class="mb-4">
					<label for="monto" class="text-sm font-medium">Monto del Préstamo:</label>
					<div class="flex">
						<span class="bg-gray-200 p-2 rounded-l-md">$</span> 
						<input type="number" id="monto" name="monto" placeholder="0" class="w-full border border-gray-300 rounded-md p-2">
					</div>
				</div>
				<div class="mb-4">
					<label for="cuotas" class="text-sm font-medium">Cuotas:</label>
					<select id="cuotas" name="cuotas" class="w-full border border-gray-300 rounded-md p-2">
						<option value="6">3 cuotas</option>
						<option value="6">4 cuotas</option>
						<option value="6">6 cuotas</option>
						<option value="9">9 cuotas</option>
						<option value="12">12 cuotas</option>
					</select>
				</div>
				<div class="mb-4">
					<label for="cuenta" class="text-sm font-medium">Cuenta para el Depósito:</label> 
					<select id="cuenta" name="cuenta" class="w-full border border-gray-300 rounded-md p-2">
						<option value="cuenta1">Cuenta 1</option>
						<option value="cuenta2">Cuenta 2</option>
					</select>
				</div>
				<div class="flex">
					<button type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-auto">Solicitar Préstamo</button>
				</div>
			</form>
		</div>
		<div class="w-8/12 pl-1">
			<h2 class="text-2xl font-semibold mb-4 text-center">Menú de Pago de Préstamos</h2>
			<form>
				<table
					class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
					<thead>
						<tr>
							<th class="border-b-2 p-2">Cuenta a Descontar</th>
							<th class="border-b-2 p-2">Fecha de Solicitud</th>
							<th class="border-b-2 p-2">Importe Total</th>
							<th class="border-b-2 p-2">Importe por Cuota</th>
							<th class="border-b-2 p-2">Cuotas Impagas</th>
							<th class="border-b-2 p-2">Cuotas a Pagar</th>
							<th class="border-b-2 p-2">Estado</th>
							<th class="border-b-2 p-2">Pagar</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td class="p-2 text-center">
							<select class="border border-gray-300 rounded-md p-2">
									<option value="cuenta1">Cuenta 1</option>
									<option value="cuenta2">Cuenta 2</option>
							</select></td>
							<td class="p-2 text-center">10/11/23</td>
							<td class="p-2 text-center">$250</td>
							<td class="p-2 text-center">$100</td>
							<td class="p-2 text-center">3</td>
							<td class="p-2 text-center">
							<select class="border border-gray-300 rounded-md p-2">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
							</select></td>
							<td class="p-2 text-center text-center text-green-600">Aprobado</td>
							<td class="p-2 text-center">
								<button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Pagar</button>
							</td>
						</tr>
						<tr>
							<td class="p-2 text-center">
							<select class="border border-gray-300 rounded-md p-2">
									<option value="cuenta1">Cuenta 1</option>
									<option value="cuenta2">Cuenta 2</option>
							</select></td>
							<td class="p-2 text-center">25/11/23</td>
							<td class="p-2 text-center">$550</td>
							<td class="p-2 text-center">$150</td>
							<td class="p-2 text-center">4</td>
							<td class="p-2 text-center">
							<select class="border border-gray-300 rounded-md p-2">
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
							</select></td>
							<td class="p-2 text-center text-yellow-600">Pendiente</td>
							<td class="p-2 text-center">
								<button class="bg-gray-300 text-gray-600 px-4 py-2 rounded-md cursor-not-allowed" disabled>Pagar</button>
							</td>
						</tr>
						<tr>
							<td class="p-2 text-center">
							<select class="border border-gray-300 rounded-md p-2">
									<option value="cuenta1">Cuenta 1</option>
									<option value="cuenta2">Cuenta 2</option>
							</select></td>
							<td class="p-2 text-center">12/12/23</td>
							<td class="p-2 text-center">$1200</td>
							<td class="p-2 text-center">$120</td>
							<td class="p-2 text-center">x</td>
							<td class="p-2 text-center">
							<select	class="border border-gray-300 rounded-md p-2" disabled>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
							</select></td>
							<td class="p-2 text-center text-red-600">Rechazado</td>
							<td class="p-2 text-center">
								<button class="bg-gray-300 text-gray-600 px-4 py-2 rounded-md cursor-not-allowed">Pagar</button>
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</div>

	<%request.setAttribute("texto", "¡Prestamo solicitado, espere confirmación!");%>
	<%request.setAttribute("modal", true);%>

	<jsp:include page="modal.jsp" />
</body>
</html>