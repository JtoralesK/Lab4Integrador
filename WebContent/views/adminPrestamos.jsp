<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Administrar Prestamos</title>
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Administrar Préstamos</h1>
        <table class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
            <thead>
                <tr>
                    <th class="border-b-2 p-2">Cliente</th>
                    <th class="border-b-2 p-2">Scoring</th>
                    <th class="border-b-2 p-2">Fecha de Solicitud</th>
                    <th class="border-b-2 p-2">Importe Total</th>
                    <th class="border-b-2 p-2">Importe por Cuota</th>
                    <th class="border-b-2 p-2">Cuotas</th>
                    <th class="border-b-2 p-2">Estado</th>
                    <th class="border-b-2 p-2">Acción</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="p-2 text-center">887410233</td>
                    <td class="p-2 text-center">903</td>
                    <td class="p-2 text-center">10/11/23</td>
                    <td class="p-2 text-center">$250</td>
                    <td class="p-2 text-center">$100</td>
                    <td class="p-2 text-center">3</td>
                    <td class="p-2 text-center text-yellow-600">Pendiente</td>
                    <td class="p-2 text-center">
                        <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">Aprobar</button>
                        <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Rechazar</button>
                    </td>
                </tr>
                <tr>
                    <td class="p-2 text-center">9848394</td>
                    <td class="p-2 text-center">700</td>
                    <td class="p-2 text-center">10/11/23</td>
                    <td class="p-2 text-center">$250</td>
                    <td class="p-2 text-center">$100</td>
                    <td class="p-2 text-center">3</td>
                    <td class="p-2 text-center text-green-600">Aprobado</td>
                    <td class="p-2 text-center">
						<button class="bg-gray-300 text-gray-600 px-4 py-2 rounded-md cursor-not-allowed" disabled>Aprobar</button>
						<button class="bg-gray-300 text-gray-600 px-4 py-2 rounded-md cursor-not-allowed" disabled>Rechazar</button>
					</td>
                </tr>
                <tr>
                    <td class="p-2 text-center">8874155564</td>
                    <td class="p-2 text-center">1500</td>
                    <td class="p-2 text-center">10/11/23</td>
                    <td class="p-2 text-center">$250</td>
                    <td class="p-2 text-center">$100</td>
                    <td class="p-2 text-center">3</td>
                    <td class="p-2 text-center text-yellow-600">Preaprobado</td>
                    <td class="p-2 text-center">
                        <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600" disabled>Aprobar</button>
                        <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600" disabled>Rechazar</button>
                    </td>
                </tr>
                
                <%request.setAttribute("texto", "¡Prestamo aprobado!");%>
				<%request.setAttribute("modal", true);%>
				
				<jsp:include page="modal.jsp" />
            </tbody>
        </table>
    </div>
</body>
</html>
