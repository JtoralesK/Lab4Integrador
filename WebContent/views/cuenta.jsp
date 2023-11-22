<%@page import="entidad.cuenta"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

    <%@page import="entidad.movimiento"%>
    <%@page import="entidad.eTipoMovimiento"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.cliente"%>
	<%@page import="entidad.prestamo"%>
    <%@ page import="java.time.LocalDate" %>
	<%@ page import="java.time.LocalTime" %>
    <%@page import="entidad.eEstadoPrestamo"%>
    


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%
    	request.setAttribute("titulo", "Cuenta");
    %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />
<br>
<div class="flex justify-center">
	<div class="w-2/3 flex flex-row gap-5 py-5 ">
		<%
			List<cuenta> list = (List<cuenta>)request.getSession().getAttribute("cuentasLoggedCliente");
				for(cuenta c : list){
		%>
		<form method="post" action="<%= request.getContextPath() %>/servletCuentasClientes" class="w-1/3 h-24 bg-white border rounded-lg border-gray-300 py-3 px-6">
			<input type="hidden" name="IdCuenta" value="<%=c.getId_cuenta()%>">	
			<button class="w-full">
				<div class="flex flex-row gap-6">
				<span class="text-4xl text-blue-600"><i class="fa-solid fa-closed-captioning"></i></span>
					<div class="flex flex-col w-full">
						<p class="font-bold text-lg">N°<%=c.getId_cuenta() %></p>
						<p>$<%=c.getSaldo() %></p>
						<div class="w-full flex flex-row justify-between">
							<span></span>
						</div>
					</div>
				</div>
			</button>
		</form>
		<%}%>
	</div>
</div>
<h2 class="text-center mx-auto text-4xl">Historial de movimientos</h2>
<br>
<form method="post" action="<%= request.getContextPath() %>/servletCuentasClientes" class="flex w-9/12 items-center mx-auto">
    <div class="flex w-11/12 items-center space-x-4 mx-auto">
        <label for="filtroFechaDesde" class="mr-2">Fecha Desde:</label>
        <input type="date" id="filtroFechaDesde" name="filtroFechaDesde" class="border border-gray-300 rounded-md p-2">

        <label for="filtroFechaHasta" class="mr-2">Fecha Hasta:</label>
        <input type="date" id="filtroFechaHasta" name="filtroFechaHasta" class="border border-gray-300 rounded-md p-2">

        <label for="filtroMovimiento" class="mr-2">Tipo Movimiento:</label>
        <select id="filtroMovimiento" name="filtroMovimiento" class="border border-gray-300 rounded-md p-2">
            <option value="-1">Todos</option>
            <% for (eTipoMovimiento movimiento : eTipoMovimiento.values()) { %>
                <option value="<%= movimiento.ordinal() %>"><%= movimiento.toString() %></option>
            <% } %>
        </select>
        <input type="hidden" id="IdCuenta" name="IdCuenta" value="<%=request.getSession().getAttribute("idCuenta")%>">
	    <button id="btnFiltrarMovimientos" name="btnFiltrarMovimientos" type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md">Filtrar</button>
    </div>			
</form>
<br>
	<table class="w-10/12 divide-y divide-gray-200 mx-auto">
		<thead>
			<tr>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Id Movimiento</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Nº Cuenta</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Tipo Movimiento</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Hora</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Importe</th>
			</tr>
		</thead>
		<tbody class="bg-white divide-y divide-gray-200">
<%
//List<movimiento>ListaMovimientos = new movimientosNeg().listarMovimientosPorIdCliente(cliente.getId());
int paginaActual = (Integer) request.getAttribute("paginaActual");
int totalPaginas = (Integer) request.getAttribute("totalPaginas");
int paginaActual2 = (Integer) request.getAttribute("paginaActual2");
int totalPaginas2 = (Integer) request.getAttribute("totalPaginas2");
List<movimiento>ListaMovimientos = (List<movimiento>)request.getAttribute("listaPaginada");
	if(!ListaMovimientos.isEmpty()){
		for(movimiento movimientos : ListaMovimientos ){ 
			String importeClass = (movimientos.getImporte() >= 0) ? "text-green-500" : "text-red-500";
%>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimientos.getId_movimiento() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimientos.getN_cuenta() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimientos.getTipoMovimiento().toString()%></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimientos.getFecha() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimientos.getHora() %>HS</td>
				<td class="px-6 py-4 whitespace-nowrap <%=importeClass%>">$<%= movimientos.getImporte() %></td>
			</tr>
		<%}%>
		<%}else {%>
			<tr>
		        <td colspan="6" class="px-6 py-4 whitespace-nowrap text-center text-gray-500">No hay movimientos registrados.</td>
		    </tr>
		<%} %>
		</tbody>
	</table>
	<!-- PAGINACION 1 -->
	<div class="text-center mb-4">
		<nav aria-label="pagination">
			<ul class="inline-flex -space-x-px text-sm mt-1">
		        <%-- Anterior --%>
		        <% if (paginaActual > 1) { %>
		        <li>
		            <a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual - 1 %>&redirectUrl=cuenta.jsp&page2=<%= paginaActual2 %>" class="flex items-center shadow-md justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700">Anterior</a>
				</li>
				<% } %>
		        <%-- Números de página --%>
		        <% for (int i = 1; i <= totalPaginas; i++) { 
		        	if(paginaActual==i){
		        %>
		        <li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=cuenta.jsp&page2=<%= paginaActual2 %>" aria-current="page" class="flex items-center shadow-md justify-center px-3 h-8 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700"><%= i %></a>
	        	</li>
		        	<%}else {%>
	        	<li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=cuenta.jsp&page2=<%= paginaActual2 %>" class="flex items-center justify-center shadow-md px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"><%= i %></a>
	        	</li>
		        	<% } 
		        	}%>
		
		        <%-- Siguiente --%>
		        <% if (paginaActual < totalPaginas) { %>
		        <li>
					<a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual + 1 %>&redirectUrl=cuenta.jsp&page2=<%= paginaActual2 %>" class="flex items-center shadow-md justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700">Siguiente</a>
		        </li>
		        <% } %>
	        </ul>
	    </nav>
	</div>

<h1 class="text-center mx-auto text-4xl">Historial de Préstamos</h1>
<br>
<form method="post" action="<%= request.getContextPath() %>/servletCuentasClientes" class="flex w-9/12 items-center mx-auto">
    <div class="flex w-11/12 items-center space-x-4 mx-auto">
    	<select id="filtroSelectFecha" name="filtroSelectFecha" class="border border-gray-300 rounded-md p-2">
    		<option value="Fecha Solicitud">Fecha Solicitud</option>
    		<option value="Fecha Revision">Fecha Revision</option>
    	</select>
        <label for="filtroFechaDesde" class="mr-2">Fecha Desde:</label>
        <input type="date" id="filtroFechaDesde" name="filtroFechaDesde" class="border border-gray-300 rounded-md p-2">

        <label for="filtroFechaHasta" class="mr-2">Fecha Hasta:</label>
        <input type="date" id="filtroFechaHasta" name="filtroFechaHasta" class="border border-gray-300 rounded-md p-2">

        <label for="filtroEstado" class="mr-2">Estado:</label>
        <select id="filtroEstado" name="filtroEstado" class="border border-gray-300 rounded-md p-2">
            <option value="-1">Todos</option>
            <% for (eEstadoPrestamo estado : eEstadoPrestamo.values()) { %>
                <option value="<%= estado.ordinal() %>"><%= estado.toString() %></option>
            <% } %>
        </select>
        <input type="hidden" id="IdCuenta" name="IdCuenta" value="<%=request.getSession().getAttribute("idCuenta")%>">      
	    <button id="btnFiltrarPrestamos" name="btnFiltrarPrestamos" type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-4 py-2 rounded-md">Filtrar</button>
    </div>			
</form>
<br>
	<table class="w-10/12 divide-y divide-gray-200 mx-auto">
		<thead>
			<tr>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Préstamo ID</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider"> Nº cuenta</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Importe total</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Solicitud</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Estado</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Plazo</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Revisión</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Interes</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Cuotas Pagas</th>
			</tr>
		</thead>
		<tbody class="bg-white divide-y divide-gray-200">
		<%
		List<prestamo>ListaPrestamos = (List<prestamo>)request.getAttribute("listaPaginada2");
 		if(!ListaPrestamos.isEmpty()){
			for(prestamo prestamos : ListaPrestamos ){ %>
				<tr>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getId()%></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getIdCuenta() %></td>
					<td class="px-6 py-4 whitespace-nowrap">$ <%=prestamos.getImporte() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getFechaSolicitud() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getEstadoPrestamo() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getPlazo() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getFechaRevision() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getInteres() %></td>
					<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getCuotasPagas() %></td>
				</tr>
			<%}}else{%>
				<tr>
			        <td colspan="9" class="px-6 py-4 whitespace-nowrap text-center text-gray-500">No hay prestamos registrados.</td>
			    </tr>
			<%} %>
		</tbody>
	</table>
	<!-- PAGINACION 2 -->
	<div class="text-center mb-4">
		<nav aria-label="pagination2">
			<ul class="inline-flex -space-x-px text-sm mt-1">
		        <%-- Anterior --%>
		        <% if (paginaActual2 > 1) { %>
		        <li>
		            <a href="/ProjectBeta1/servletPaginacion?page2=<%= paginaActual2 - 1 %>&redirectUrl=cuenta.jsp&page=<%= paginaActual %>" class="flex items-center shadow-md justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700">Anterior</a>
				</li>
				<% } %>
		        <%-- Números de página --%>
		        <% for (int i = 1; i <= totalPaginas2; i++) { 
		        	if(paginaActual2==i){
		        %>
		        <li>
	        		<a href="/ProjectBeta1/servletPaginacion?page2=<%= i %>&redirectUrl=cuenta.jsp&page=<%= paginaActual %>" aria-current="page" class="flex items-center shadow-md justify-center px-3 h-8 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700"><%= i %></a>
	        	</li>
		        	<%}else {%>
	        	<li>
	        		<a href="/ProjectBeta1/servletPaginacion?page2=<%= i %>&redirectUrl=cuenta.jsp&page=<%= paginaActual %>" class="flex items-center justify-center shadow-md px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"><%= i %></a>
	        	</li>
		        	<% } 
		        	}%>
		
		        <%-- Siguiente --%>
		        <% if (paginaActual2 < totalPaginas2) { %>
		        <li>
					<a href="/ProjectBeta1/servletPaginacion?page2=<%= paginaActual2 + 1 %>&redirectUrl=cuenta.jsp&page=<%= paginaActual %>" class="flex items-center shadow-md justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700">Siguiente</a>
		        </li>
		        <% } %>
	        </ul>
	    </nav>
	</div>
	<jsp:include page="modal.jsp" />
</body>
</html>