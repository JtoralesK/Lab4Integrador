<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Alta de cuentas");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
        <jsp:include page="navbar.jsp" />
        <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Crear Nueva Cuenta</h1>
        <!-- Formulario para crear una cuenta -->
        <form method="post" class="w-1/4 bg-white p-4 shadow-md rounded-md mx-auto">
            <div class="mb-4">
                <label for="nroCliente" class="text-sm font-medium">Nro de Cliente:</label>
                <input type="number" id="nroCliente" name="nroCliente" class="w-full border border-gray-300 rounded-md p-2" required>
            </div>
            <div class="mb-4">
                <label for="tipoCuenta" class="text-sm font-medium">Tipo de Cuenta:</label>
                <select id="tipoCuenta" name="tipoCuenta" class="w-full border border-gray-300 rounded-md p-2" required>
                	<option value="" disabled selected>Tipo de cuenta</option>
                    <option value="ca">Caja de ahorro</option>
                    <option value="cc">Cuenta corriente</option>
                </select>
            </div>
            <div class="flex justify-center">
                <button class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Crear Cuenta</button>
            </div>
        </form>
    </div>
    <jsp:include page="modal.jsp" />
</body>
</html>