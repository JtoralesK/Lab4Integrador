<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.util.List"%>
<%@page import="entidad.prestamo"%>
<%@page import="entidad.cuenta"%>
<%@page import="entidad.eEstadoPrestamo"%>
<%@page import="java.text.DecimalFormat"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Prestamo");
	int paginaActual = (Integer) request.getAttribute("paginaActual");
	int totalPaginas = (Integer) request.getAttribute("totalPaginas");
 	List<prestamo> prestamos = (List<prestamo>) request.getAttribute("listaPaginada");
 	List<cuenta> cuentas = (List<cuenta>) request.getSession().getAttribute("cuentas");
    %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
	<div class="container mx-auto py-4 flex">
		<div class="w-4/12 pr-1">
			<h1 class="text-2xl font-semibold mb-4 text-center">Solicitud de Préstamo</h1>

			<form method="post" action="<%= request.getContextPath() %>/servletPrestamo" class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
				<div class="mb-4">
					<label for="txtMonto" class="text-sm font-medium">Monto del Préstamo:</label>
					<div class="flex">
						<span class="bg-gray-200 p-2 rounded-l-md">$</span> 
						<input type="number" id="txtMonto" name="txtMonto" placeholder="0" min="1" required class="w-full border border-gray-300 rounded-md p-2">
					</div>
				</div>
				<div class="mb-4">
					<label for="cbPlazoSolicitar" class="text-sm font-medium">Cuotas:</label>
					<select id="cbPlazoSolicitar" name="cbPlazoSolicitar" class="w-full border border-gray-300 rounded-md p-2">
						<option value="3">3 cuotas</option>
						<option value="4">4 cuotas</option>
						<option value="6">6 cuotas</option>
						<option value="9">9 cuotas</option>
						<option value="12">12 cuotas</option>
					</select>
				</div>
				<div class="mb-4">
					<label for="cbCuentaSolicitar" class="text-sm font-medium">Cuenta para el Depósito:</label> 
					<select id="cbCuentaSolicitar" name="cbCuentaSolicitar" class="w-full border border-gray-300 rounded-md p-2">
						<option value="-1">Seleccione una cuenta</option>
						<%for (cuenta cuenta : cuentas) {%>
								<option value="<%=cuenta.getId_cuenta()%>">Cuenta N° <%=cuenta.getId_cuenta()%></option>
						<%} %>
					</select>
				</div>
				<div class="flex">
					<button id="btnSolicitarPrestamo" name="btnSolicitarPrestamo" type="submit" class="bg-blue-500 text-white px-4 py-2 rounded-md hover:bg-blue-600 ml-auto">Solicitar Préstamo</button>
				</div>
			</form>
		</div>
		<div class="w-8/12 pl-1">
			<h2 class="text-2xl font-semibold mb-4 text-center">Menú de Pago de Préstamos</h2>
			<form method="post" action="<%= request.getContextPath() %>/servletPrestamo" class="mb-8">
			    <div class="flex w-11/12 items-center space-x-4 mx-auto">
			        <label for="filtroFechaDesde" class="mr-2">Fecha Desde:</label>
			        <input type="date" id="filtroFechaDesde" name="filtroFechaDesde" class="border border-gray-300 rounded-md p-2">

			        <label for="filtroFechaHasta" class="mr-2">Fecha Hasta:</label>
			        <input type="date" id="filtroFechaHasta" name="filtroFechaHasta" class="border border-gray-300 rounded-md p-2">

			        <label for="filtroEstado" class="mr-2">Estado:</label>
			        <select id="filtroEstado" name="filtroEstado" class="border border-gray-300 rounded-md p-2">
			            <option value="-1">Todos</option>
			            <% for (eEstadoPrestamo estado : eEstadoPrestamo.values()) { %>
			                <option value="<%= estado.ordinal() %>"><%= estado.name() %></option>
			            <% } %>
			        </select>
			        <input type="hidden" id="accion" name="accion" value="clientePrestamo">
				    <button id="btnFiltrar" name="btnFiltrar" type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md">Filtrar</button>
			    </div>			
			</form>
			<form method="post" action="<%= request.getContextPath() %>/servletPrestamo">
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
					<% 
					if (prestamos != null && !prestamos.isEmpty()) {
						int contador = 0;
						for (prestamo prestamo : prestamos) { 
						contador++;%>
						<tr>
							<td class="p-2 text-center">
							<select <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Aprobado || prestamo.getPlazo() - prestamo.getCuotasPagas() == 0 ? "disabled" : "" %> id="cbCuenta<%=contador%>" name="cbCuenta<%=contador%>" class="border border-gray-300 rounded-md p-2">
									<option value="-1">Seleccione una cuenta</option>						
								<%for (cuenta cuenta : cuentas) {%>
										<option value="<%=cuenta.getId_cuenta()%>">N° <%=cuenta.getId_cuenta()%></option>
								<%} %>
							</select></td>
							<td class="p-2 text-center"><%=prestamo.getFechaSolicitud()%></td>
							<td class="p-2 text-center">$<%=new DecimalFormat("#.##").format(prestamo.getImporte())%></td>
							<%float cuota = (prestamo.getImporte() * prestamo.getInteres()) / prestamo.getPlazo(); %>
							<td class="p-2 text-center">$<%=new DecimalFormat("#.##").format(cuota)%></td>
							<td class="p-2 text-center"><%=prestamo.getEstadoPrestamo() == eEstadoPrestamo.Aprobado ? prestamo.getPlazo() - prestamo.getCuotasPagas() : "x"%></td>
							<td class="p-2 text-center">
							<select <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Aprobado || prestamo.getPlazo() - prestamo.getCuotasPagas() == 0 ? "disabled" : "" %> id="cbCuotasPagar" name="cbCuotasPagar" class="border border-gray-300 rounded-md p-2">
								<%if(prestamo.getEstadoPrestamo() == eEstadoPrestamo.Aprobado && prestamo.getPlazo() - prestamo.getCuotasPagas() != 0){
								for (int i = 1; i <= prestamo.getPlazo() - prestamo.getCuotasPagas(); i++) {%>
									<option value="<%=i%>"><%=i%></option>
								<%}} else {%>
									<option value="-1">x</option>	
								<%} %>							
							</select></td>
							<td class="p-2 text-center <%=prestamo.getEstadoPrestamo() == eEstadoPrestamo.Aprobado ? "text-green-600" 
	                    		: prestamo.getEstadoPrestamo() == eEstadoPrestamo.Rechazado ? "text-red-600"
	                    		: "text-yellow-600"%>"><%=prestamo.getEstadoPrestamo()%></td>
							<td class="p-2 text-center">
								<input id="idPrestamo<%=contador%>" name="idPrestamo<%=contador%>" type="hidden" value="<%=prestamo.getId()%>">
								<input id="cuotasPagas<%=contador%>" name="cuotasPagas<%=contador%>" type="hidden" value="<%=prestamo.getCuotasPagas()%>">
								<input id="cuota<%=contador%>" name="cuota<%=contador%>" type="hidden" value="<%=cuota%>">
								<button id="btnPagarPrestamo" name="btnPagarPrestamo" value="<%=contador%>" type="submit" <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Aprobado || prestamo.getPlazo() - prestamo.getCuotasPagas() == 0 ? "disabled" : "" %> class="text-white px-4 py-2 rounded-md <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Aprobado || prestamo.getPlazo() - prestamo.getCuotasPagas() == 0 ? "bg-gray-400" : "bg-blue-500 hover:bg-blue-600"%> ">Pagar</button>
							</td>
						</tr>
			    	<%}
				    }else {
					    %>
					    <tr>
			                <td colspan="8" class="border px-4 py-2 text-center">No hay datos disponibles</td>
			            </tr>
		            <%}%>					
					</tbody>
				</table>
			</form>
			<jsp:include page="paginacion.jsp" />
		</div>
	</div>

	<jsp:include page="modal.jsp" />
</body>
</html>