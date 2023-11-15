<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="entidad.prestamo" %>
<%@ page import="entidad.eEstadoPrestamo" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>

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
                    <td class="p-2 text-center">$<%=prestamo.getImporte()%></td>
                    <td class="p-2 text-center">$<%=(prestamo.getImporte() * prestamo.getInteres()) / prestamo.getPlazo()%></td>
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
