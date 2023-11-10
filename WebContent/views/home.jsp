<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>

<%
	Usuario user = (Usuario) session.getAttribute("loggedUser");
	
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	request.setAttribute("titulo", "Home");
%>
<jsp:include page="head.jsp" />
</head>
<body class="h-screen  ">
	<jsp:include page="navbar.jsp" />
	<div class="flex flex-row justify-center w-full h-full">
		
		<div class="w-10/12 h-screen p-7 flex flex-col gap-8">
			<div class="h-16">
				<h2 class="text-xl">
					<b>Hola,<%=user.getUsuario() %></b>
				</h2>
			</div>
			<div class="h-32 border rounded-lg border-gray-300 px-3 py-2">
				<h3 class="text-lg">Accesos Directos</h3>
				<div class="flex flex-row gap-5 py-3">
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300"></div>
				</div>
			</div>
			<div class="h-44 border rounded-lg border-gray-300 p-3">
				<div class="flex flex-row gap-2 items-center ">
				<h3 class="text-lg">Todas las cuentas</h3>
				<span><i class="fa-solid fa-eye"></i></span>
				</div>
				<div class="flex flex-row gap-5 py-5">
					<div class="w-1/5 h-24  border rounded-lg border-gray-300"></div>
					<div class="w-1/5 h-24  border rounded-lg border-gray-300"></div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>