<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

    <%@page import="entidad.movimiento"%>
    <%@page import="negocio.movimientosNeg"%>
    <%@page import="entidad.eTipoMovimiento"%>
    <%@page import="java.util.List"%>
    <%@page import="java.util.ArrayList"%>
    <%@page import="entidad.Usuario"%>
    <%@page import="entidad.cliente"%>
    <%@page import="negocio.clienteNeg"%>
	<%@page import="entidad.prestamo"%>
    <%@page import="negocio.prestamoNeg"%>


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
 <%
 	Usuario usuario = (Usuario)session.getAttribute("loggedUser"); 
  cliente cliente = new cliente();
  cliente=new clienteNeg().obtenerClientePorIdUsuario(usuario.getId());
 %>
<h2 class="text-center mx-auto text-4xl">ID Cliente <%=cliente.getId()%></h2>
<h2 class="text-center mx-auto text-4xl">Historial de movimientos</h2>
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
			List<movimiento>ListaMovimientos = new movimientosNeg().listarMovimientosPorIdCliente(101);
		 		for(movimiento movimiento : ListaMovimientos ){
		%>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimiento.getId_movimiento() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimiento.getN_cuenta() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimiento.getTipoMovimiento().name()%></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimiento.getFecha() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%= movimiento.getHora() %>HS</td>
				<td class="px-6 py-4 whitespace-nowrap text-red-500">$<%= movimiento.getImporte() %></td>
			</tr>
		<%}%>
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
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Fecha de Aprobación</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Interes</th>
				<th
					class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Cuotas Pagas</th>
			</tr>
		</thead>
		<tbody class="bg-white divide-y divide-gray-200">
		<% List<prestamo>ListaPrestamos = new prestamoNeg().listarXcliente(101);
 		for(prestamo prestamos : ListaPrestamos ){ %>
			<tr>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getId()%></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getIdCuenta() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getImporte() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getFechaSolicitud() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getEstadoPrestamo() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getPlazo() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getFechaRevision() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getInteres() %></td>
				<td class="px-6 py-4 whitespace-nowrap"><%=prestamos.getCuotasPagas() %></td>
			</tr>
		<%}%>
		</tbody>
	</table>
</body>
</html>