<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Transferencias"); %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="bg-white p-4 shadow-md rounded-md w-10/12 mx-auto mt-8 flex">
        <div class="w-1/2 pr-4">
            <h1 class="text-2xl font-semibold mb-4 text-center">Transferencia Bancaria</h1>
            <form>
                <div class="mb-4">
                    <label for="origen" class="text-sm font-medium block">Cuenta de Origen:</label>
                    <select id="origen" class="w-full border border-gray-300 rounded-md p-2">
                        <option value="cuenta1">Cuenta 1</option>
                        <option value="cuenta2">Cuenta 2</option>
                    </select>
                </div>
                <div class="mb-4">
                    <label for="destino" class="text-sm font-medium block">CBU de Cuenta de Destino:</label>
                    <input type="text" id="destino" class="w-full border border-gray-300 rounded-md p-2">
                </div>
                <div class="mb-4">
                    <label for="cantidad" class="text-sm font-medium block">Cantidad:</label>
                    <div class="flex">
                        <span class="bg-gray-200 p-2 rounded-l-md">$</span>
                        <input type="number" id="cantidad" placeholder="0"
                            class="w-full border border-gray-300 rounded-md p-2 pl-2">
                    </div>
                </div>
                <div class="mb-4">
                    <label for="mensaje" class="text-sm font-medium block">Mensaje (Opcional):</label>
                    <textarea id="mensaje" class="w-full border border-gray-300 rounded-md p-2" rows="4"></textarea>
                </div>
                <div class="flex items-center justify-between">
                    <button type="submit" id="Transferir"
                        class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Transferir</button>
                    <button type="submit"
                        class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600">Cancelar</button>
                </div>
            </form>
        </div>
        <div class="w-1/2 pl-4">
            <h2 class="text-2xl font-semibold mb-4 text-center">Transferencias Recientes</h2>
            <table class="w-full border border-gray-300 rounded-md">
                <thead>
                    <tr>
                        <th class="border-b-2 p-2">Fecha</th>
                        <th class="border-b-2 p-2">Cuenta de Destino</th>
                        <th class="border-b-2 p-2">Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td class="p-2">01/10/2023</td>
                        <td class="p-2 cursor-pointer hover:bg-gray-200" onclick="document.getElementById('destino').value = '0001155489626200216'">0001155489626200216</td>
                        <td class="p-2">$100</td>
                    </tr>
                    <tr>
                        <td class="p-2">02/10/2023</td>
                        <td class="p-2 cursor-pointer hover:bg-gray-200" onclick="document.getElementById('destino').value = '0005545151655788545'">0005545151655788545</td>
                        <td class="p-2">$150</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <%request.setAttribute("texto", "�Transferencia Exitosa!"); %>
    <%request.setAttribute("modal", true); %>
    <jsp:include page="modal.jsp" />

</body>
</html>