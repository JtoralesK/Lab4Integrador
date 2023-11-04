<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://cdn.tailwindcss.com"></script>
<title>Administrador de cuentas</title>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
    <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Administraci�n de Cuentas de Clientes</h1>
        <!-- Filtros de b�squeda y bot�n de agregar cuenta -->
        <div class="flex justify-center">
		<div class="w-11/12 flex justify-between">
            <h2 class="text-lg font-semibold">Filtros</h2>
            <button class="bg-green-500 text-white px-4 py-2 rounded-md hover:bg-green-600">Agregar Cuenta</button>
        </div>
        </div>
        <!-- Filtros de b�squeda dentro de un formulario -->
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
                <tr>
                    <th class="border-b-2 p-2 text-left">Nro Cuenta</th>
                    <th class="border-b-2 p-2 text-left">Nro Cliente</th>
                    <th class="border-b-2 p-2 text-left">Tipo de Cuenta</th>
                    <th class="border-b-2 p-2 text-right">Acciones</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class="p-2 text-left">12345</td>
                    <td class="p-2 text-left">67890</td>
                    <td class="p-2 text-left">Caja de Ahorro</td>
                    <td class="p-2 text-right">
                        <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Eliminar</button>
                    </td>
                </tr>
                <tr>
                    <td class="p-2 text-left">002392</td>
                    <td class="p-2 text-left">123123</td>
                    <td class="p-2 text-left">Cuenta corriente</td>
                    <td class="p-2 text-right">
                        <button class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Eliminar</button>
                    </td>
                </tr>
                <!-- Otras cuentas -->
            </tbody>
        </table>
    </div>
</body>
</html>