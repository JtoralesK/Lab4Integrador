<%@page import="entidad.eTipoUsuario"%>
<%@page import="entidad.Usuario"%>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"></head>

<%
	Usuario user = (Usuario) session.getAttribute("loggedUser");
%>
<nav class="w-full h-20 bg-white p-5 flex flex-row justify-between items-center border-b border-gray-300">
	<img class="h-15 w-20"  src="Resources/bankLogo.png"></img>
	<div class="w-8/12 w-full flex flex-row justify-around items-center">
	<div class="flex flex-row gap-5">
	<a href="/ProjectBeta1/views/home.jsp">Home</a>
	<% if(user.getTipoUsuario() == eTipoUsuario.Administrador){ %>
		<a href="/ProjectBeta1/views/amCliente.jsp">Clientes</a>
        <a href="/ProjectBeta1/views/adminPrestamos.jsp">Prestamos</a>
        <a href="/ProjectBeta1/views/blCuentas.jsp">Cuentas</a>
		<div class="relative group">
		    <button class="group-hover:bg-gray-200 focus:outline-none">Informes</button>
		    <div class="hidden absolute bg-white group-hover:block">
		        <a href="/ProjectBeta1/views/repMovimientos.jsp" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center">Movimientos</a>
		        <a href="/ProjectBeta1/views/repPrestamos.jsp" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center">Prestamos</a>
		    </div>
		</div>
	<%} else {%>
	
	<a href="/ProjectBeta1/views/cuenta.jsp">Cuentas</a>
	<a href="/ProjectBeta1/views/transferencia.jsp">Transferencia</a>
	<a href="/ProjectBeta1/views/prestamo.jsp">Prestamos</a>
	<%}%>

	</div>
	</div>
	<div class="w-2/12 flex flex-row justify-center items-center gap-3">
    <div class="relative group">
        <span class="border rounded-full bg-gray-200 p-3 cursor-pointer"><i class="fa-regular fa-user"></i></span>
		<span><%= user.getUsuario() %></span>
        <div class="hidden absolute bg-white group-hover:block w-40 text-center mt-2">
            <a href="/ProjectBeta1/views/userInfo.jsp" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center">Ver Perfil</a>
            <form method="post" action="<%= request.getContextPath() %>/servletUsuario">
			    <input type="hidden" name="logout" value="true">
			    <button type="submit" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center w-full">Cerrar Sesión</button>
			</form>
        </div>
    </div>
</div>
</nav>