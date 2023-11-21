<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.prestamo" %>
<%@ page import="entidad.eEstadoPrestamo" %>
<%@ page import="entidad.eFiltro" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@page import="java.text.DecimalFormat"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Administrar Prestamos");
	List<prestamo> prestamos = (List<prestamo>)request.getAttribute("listaPaginada");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
    <jsp:include page="navbar.jsp" />
    <div class="container mx-auto py-4">
        <h1 class="text-2xl font-semibold mb-4 text-center">Administrar Préstamos</h1>
		<form method="post" action="<%= request.getContextPath() %>/servletPrestamo" class="mb-8">
		    <div class="flex w-11/12 items-center mx-auto my-4">
   		        <div class="w-1/3 flex items-center mb-4 p-2">
	   		        <label for="filtroCliente" class="mr-2">Cliente</label>
			    	<input type="number" id="filtroCliente" name="filtroCliente" min="1" class="border border-gray-300 rounded-md p-2 flex-1">
   		        </div>
		        <div class="w-1/3 flex items-center mb-4 p-2">
			        <label for="filtroImporte" class="mr-2">Importe Total</label>
	     		    <select id="filtroImporteComparacion" name="filtroImporteComparacion" class="border border-gray-300 rounded-md p-2 text-center">
			            <option value="-1">Ninguno</option>
			            <% for (eFiltro filtro : eFiltro.values()) { %>
			                <option value="<%= filtro.ordinal() %>"><%= filtro.toString() %></option>
			            <% } %>	            
			        </select>
			        <input type="number" id="filtroImporte" name="filtroImporte" min="1" class="border border-gray-300 rounded-md p-2 flex-1 ml-2">
   		        </div>
	            <div class="w-1/3 flex items-center mb-4 p-2">
	   		        <label for="filtroCuotas" class="mr-2">Cuotas:</label>
 		        	<select id="filtroCuotasComparacion" name="filtroCuotasComparacion" class="border border-gray-300 rounded-md p-2 text-center">
			            <option value="-1">Ninguno</option>
			            <% for (eFiltro filtro : eFiltro.values()) { %>
			                <option value="<%= filtro.ordinal() %>"><%= filtro.toString() %></option>
			            <% } %>	                
			        </select>
			    	<select id="filtroCuotas" name="filtroCuotas" class="border border-gray-300 rounded-md p-2 flex-1 ml-2 text-center">
			            <option value="-1">Ninguno</option>
         				<option value="3">3 cuotas</option>
						<option value="4">4 cuotas</option>
						<option value="6">6 cuotas</option>
						<option value="9">9 cuotas</option>
						<option value="12">12 cuotas</option>             
			        </select>
   		        </div>
		    </div>		
   		    <div class="flex w-11/12 items-center justify-between mx-auto my-4">
	   		    <div class="w-1/3 flex items-center mb-4 p-2">
			        <label for="filtroFechaDesde" class="mr-2">Fecha Desde:</label>
			        <input type="date" id="filtroFechaDesde" name="filtroFechaDesde" class="border border-gray-300 rounded-md p-2 flex-1 text-center">
			        </div>
				<div class="w-1/3 flex items-center mb-4 p-2">
			        <label for="filtroFechaHasta" class="mr-2">Fecha Hasta:</label>
			        <input type="date" id="filtroFechaHasta" name="filtroFechaHasta" class="border border-gray-300 rounded-md p-2 flex-1 text-center">
		        </div>
				<div class="w-1/3 flex items-center mb-4 p-2 justify-between">
				        <label for="filtroEstado" class="mr-2">Estado:</label>
				        <select id="filtroEstado" name="filtroEstado" class="border border-gray-300 rounded-md p-2 text-center flex-1">
				            <option value="-1">Todos</option>
				            <% for (eEstadoPrestamo estado : eEstadoPrestamo.values()) { %>
				                <option value="<%= estado.ordinal() %>"><%= estado.name() %></option>
				            <% } %>
				        </select>   

	           			<input type="hidden" id="accion" name="accion" value="adminPrestamo">     		
				    	<button id="btnFiltrar" name="btnFiltrar" type="submit" class="bg-blue-500 hover:bg-blue-600 text-white px-6 py-2 rounded-md ml-4">Filtrar</button>
		        </div>		    
		    </div>		
		</form>
        <table class="w-11/12 bg-white p-4 shadow-md rounded-md mb-8 mx-auto">
            <thead>
                <tr>
                    <th class="border-b-2 p-2">Cliente</th>
                    <th class="border-b-2 p-2">Fecha de Solicitud</th>
                    <th class="border-b-2 p-2">Importe Total</th>
                    <th class="border-b-2 p-2">Importe por Cuota</th>
                    <th class="border-b-2 p-2">Cuotas</th>
                    <th class="border-b-2 p-2">Estado</th>
                    <th class="border-b-2 p-2">Acción</th>
                </tr>
            </thead>
            <tbody>
            <%for (prestamo prestamo : prestamos) {%>
                <tr>
                    <td class="p-2 text-center"><%=prestamo.getIdCliente()%></td>
                    <td class="p-2 text-center"><%=prestamo.getFechaSolicitud()%></td>
                    <td class="p-2 text-center">$<%=new DecimalFormat("#.##").format(prestamo.getImporte())%></td>
                    <td class="p-2 text-center">$<%=new DecimalFormat("#.##").format((prestamo.getImporte() * prestamo.getInteres()) / prestamo.getPlazo())%></td>
                    <td class="p-2 text-center"><%=prestamo.getPlazo()%></td>
                    <td class="p-2 text-center <%=prestamo.getEstadoPrestamo() == eEstadoPrestamo.Aprobado ? "text-green-600" 
                    		: prestamo.getEstadoPrestamo() == eEstadoPrestamo.Rechazado ? "text-red-600"
                    		: "text-yellow-600"%>"><%=prestamo.getEstadoPrestamo()%></td>
                    <td class="p-2 text-center">
                    <form method="post" action="<%= request.getContextPath() %>/servletPrestamo">
                    	<input type="hidden" name="idPrestamo" value="<%=prestamo.getId()%>">
                        <button <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Pendiente ? "disabled" : "" %> id="btnAprobar" name="btnAprobar" class="text-white px-4 py-2 rounded-md  <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Pendiente ? "bg-gray-400" : "bg-green-500 hover:bg-green-600"%> ">Aprobar</button>       
                        <button <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Pendiente ? "disabled" : "" %> id="btnRechazar" name="btnRechazar" class="text-white px-4 py-2 rounded-md  <%=prestamo.getEstadoPrestamo() != eEstadoPrestamo.Pendiente ? "bg-gray-400" : "bg-red-500 hover:bg-red-600"%>">Rechazar</button>
                    </form>
                    </td>
                </tr>    
             <%} %>            				
            </tbody>
        </table>
    </div>
	<jsp:include page="paginacion.jsp" />
	<jsp:include page="modal.jsp" />
</body>
</html>
