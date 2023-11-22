<%@page import="entidad.eTipoCuenta"%>
<%@page import="entidad.cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="entidad.movimiento"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Transferencias"); %>
    <jsp:include page="head.jsp"/>
    
    <%
		List<movimiento> lista =(List<movimiento>) request.getAttribute("lista");    
    	List<cuenta> cuentas = (List<cuenta>) request.getAttribute("cuentas");
    %>
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="bg-white p-4 shadow-md rounded-md w-10/12 mx-auto mt-8 flex">
        <div class="w-1/2 pr-4">
                <form onsubmit="confirmarTransferencia(); return false;">
            <h1 class="text-2xl font-semibold mb-4 text-center">Transferencia Bancaria</h1>
                <div class="mb-4">
                    <label for="origen" class="text-sm font-medium block">Cuenta de Origen:</label>
                    <select name="cuenta" id="cuenta" class="w-full border border-gray-300 rounded-md p-2" required>
                        <% if (cuentas.size() == 0){%>
                        	<option value="" disabled selected> No hay cuentas registradas </option>
                        <%}else{%>
                        	<option value="" disabled selected> Cuenta a debitar </option>
                        <%
                        	for (cuenta cuenta : cuentas) {
                        		String tipoCuenta;
                        		if(cuenta.tipoCuenta() == eTipoCuenta.CajaDeAhorro) tipoCuenta = "CA";
                        		else tipoCuenta = "CC";
                        %>
                        	<option value=<%= cuenta.getId_cuenta() %>><%= tipoCuenta %> - <%= cuenta.getId_cuenta()%> - Saldo disponible $ <%= cuenta.getSaldo() %></option>
                        <%}} %>
                    </select>
                </div>
                <div class="mb-4">
                    <label class="text-sm font-medium block">CBU de Cuenta de Destino:</label>
                    <input type="text" required id="cbu" name="cbu" class="w-full border border-gray-300 rounded-md p-2" onblur="formatCbu()">
                </div>
                <div class="mb-4">
                    <label for="cantidad" class="text-sm font-medium block">Importe:</label>
                    <div class="flex">
                        <span class="bg-gray-200 p-2 rounded-l-md">$</span>
                        <input type="number" name="$$" id="$$" required step=".01" min="1" id="cantidad" placeholder="0.00"
                            class="w-full border border-gray-300 rounded-md p-2 pl-2">
                    </div>
                </div>
                <div class="mb-4">
                    <label for="mensaje" class="text-sm font-medium block">Concepto (Opcional = max 30 caracteres):</label>
                    <textarea id="msj" maxlength="30" class="w-full border border-gray-300 rounded-md p-2" rows="4"></textarea>
                </div>
                <div class="flex items-center justify-center gap-10">
                    <button type="button" name="cancelar" value="true"
                        class="bg-red-500 text-white px-4 py-2 rounded-md hover:bg-red-600" onclick="cancelarTransferencia()">Cancelar</button>
                    <button type="submit" name="transferir" value="true"
                        class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600">Transferir</button>
                </div>
                </form>
        </div>
        <div class="w-1/2 pl-4">
            <h2 class="text-2xl font-semibold mb-4 text-center">Transferencias Recientes Realizadas</h2>
            <table class="w-full border border-gray-300 rounded-md">
                <thead>
                    <tr>
                        <th class="border-b-2 p-2">Fecha</th>
                        <th class="border-b-2 p-2">Cuenta</th>
                        <th class="border-b-2 p-2">Importe</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
				        if (lista != null && !lista.isEmpty()) {
				            int count = 0;
				            for (movimiento mov : lista) {
				                if (count < 5) {
				    %>
				                    <tr>
				                        <td class="text-center border-b p-2"><%= mov.getFecha() %></td>
				                        <td class="text-center border-b p-2"><%= mov.getN_cuenta() %></td>
				                        <td class="text-center border-b p-2 <%= mov.getImporte() > 0 ? "text-green-600" : "text-red-600"%> ">$<%= mov.getImporte() %></td>
				                    </tr>
				    <%
				                    count++;
				                } else {
				                    break;  
				                }
				            }
				        } else {
				    %>
				            <tr>
				                <td colspan="3" class="text-center">No hay movimientos.</td>
				            </tr>
				    <%
				        }
				    %>
                </tbody>
            </table>
        </div>
    </div>
    <jsp:include page="modal.jsp" />
    <!-- MODAL DE CONFIRMACIÓN -->
     <div id="confirmModal" class="fixed inset-0 bg-gray-900 bg-opacity-50 flex items-center justify-center hidden">
        <div class="bg-white p-8 rounded shadow-lg">
            <p id="modalText" class="text-gray-700"></p>
            <div class="mt-4 flex justify-end">
                <form id="confirmForm" action="<%= request.getContextPath()%>/servletTransferencia" method="post">
                    <input type="hidden" name="btnConfirmTransferencia" id="btnConfirmTransferencia" value="">
                    <input type="hidden" name="origen" id="origen" value="">
                    <input type="hidden" name="importe" id="importe" value="">
                    <input type="hidden" name="destino" id="destino" value="">
					<input type="hidden" name="mensaje" id="mensaje" value="">
                    <button id="confirmBtn" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded mr-2" type="submit"></button>
                </form>
                <button class="bg-gray-300 hover:bg-gray-400 text-gray-700 font-bold py-2 px-4 rounded" onclick="closeModal()">Cancelar</button>
            </div>
        </div>
    </div>
    
	<script>
        function formatCbu() {
            var cbuInput = document.getElementById('cbu');
            var cleanedCBU = cbuInput.value.replace(/\D/g, '');
            cbuInput.value = cleanedCBU;
        }
        
        function cancelarTransferencia() {
            
            window.location.href = window.location.href;
        }
        
        function confirmarTransferencia() {
            const modal = document.getElementById("confirmModal");
            const confirmBtn = document.getElementById("confirmBtn");
            const btnConfirmTransferencia = document.getElementById("btnConfirmTransferencia");
            const modalText = document.getElementById("modalText");
			const origen = document.getElementById("origen");
			const destino = document.getElementById("destino");
			const importe = document.getElementById("importe");
			const mensaje = document.getElementById("mensaje");
			origen.value = document.getElementById("cuenta").value;
			destino.value = document.getElementById("cbu").value;
			importe.value = document.getElementById("$$").value;
			mensaje.value = document.getElementById("msj").value;
			
            modalText.textContent = '¿Seguro que desea realizar la transferencia?';
            confirmBtn.textContent = 'Transferir'; 

            confirmBtn.onclick = function () {
                btnConfirmTransferencia.value = "true";
                document.getElementById("confirmForm").submit();
                closeModal();
            };

            modal.style.display = "flex"; 
            return false
        }

        function closeModal() {
            const modal = document.getElementById("confirmModal");
            modal.style.display = "none";
        }

        window.onclick = function (event) {
            const modal = document.getElementById("confirmModal");
            if (event.target === modal) {
                modal.style.display = "none";
            }
        };
    </script>
</body>
</html>