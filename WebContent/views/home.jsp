<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.eTipoUsuario"%>
<%@page import="java.util.List"%>
<%@ page import="entidad.cuenta" %>

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
			<div class="h-32 border rounded-lg border-gray-300 px-6 py-2 shadow-md">
				<h3 class="text-lg">Accesos Directos</h3>
				<%if(user.getTipoUsuario()==eTipoUsuario.Administrador){ %>
				<div class="flex flex-row gap-5 py-3">
					<div class="w-1/5 h-16  border rounded-lg border-gray-300  ">
					<a class=" w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200" href="<%= request.getContextPath() %>/servletInformeMovimiento">
					<span class="text-2xl"><i class="fa-solid fa-money-bill-transfer"></i></span>
					<p class="text-lg font-bold">Movimientos</p>
					</a>
					</div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300">
					<a class=" w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200 " href="<%= request.getContextPath() %>/servletInformePrestamos">
					<span class="text-2xl"><i class="fa-solid fa-chart-pie"></i></span>
					<p class="text-lg font-bold">Reporte Prestamos</p>
					</a>
					</div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300 ">
					<form class="w-full h-full" method="post" action="<%= request.getContextPath() %>/servletCliente">
		            <input type="hidden" name="accion" value="altaCliente">
		            <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
		            <span class="text-2xl"><i class="fa-solid fa-user-plus"></i></span>
		           	<p class="text-lg font-bold">Alta Cliente</p>
		            </button>
		       	    </form>
					</div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300">
					<form class="w-full h-full" method="get" action="<%= request.getContextPath() %>/servletCuenta">
		            <input type="hidden" name="accion" value="blCuentas">
		            <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
		            <span class="text-1xl"><i class="fa-solid fa-users"></i></span>
		           	<p class="text-lg font-bold">Administrar cuentas</p>
		            </button>
		       	    </form>
					</div>
					<div class="w-1/5 h-16  border rounded-lg border-gray-300">
					<form class="w-full h-full" method="post" action="<%= request.getContextPath() %>/servletPrestamo">
		            <input type="hidden" name="accion" value="adminPrestamo">
		            <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
		            <span class="text-2xl"><i class="fa-solid fa-hand-holding-dollar"></i></span>
		           	<p class="text-lg font-bold">Gestión Prestamos</p>
		            </button>
		       	    </form>
					</div>
				</div>
				<%}else{ %>
				<div class="flex flex-row gap-5 py-3">
					<div class="w-1/4 h-16  border rounded-lg border-gray-300  ">
					<form class="w-full h-full" method="get" action="<%= request.getContextPath() %>/servletTransferencia">
        	   	   <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
		            <span class="text-2xl"><i class="fa-solid fa-money-bill-transfer"></i></span>
		           	<p class="text-lg font-bold">Transferir</p>
		            </button>
      				 </form>
					</div>
					<div class="w-1/4 h-16  border rounded-lg border-gray-300">
					<form class="w-full h-full" method="get" action="<%= request.getContextPath() %>/servletCuentasClientes">
        	   	   <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
					<span class="text-2xl"><i class="fa-regular fa-eye"></i></span>
		           	<p class="text-lg font-bold">Gestionar Cuentas</p>
		            </button>
      				 </form>
					</div>
					<div class="w-1/4 h-16  border rounded-lg border-gray-300 ">
					 <form class="w-full h-full"  method="post" action="<%= request.getContextPath() %>/servletPrestamo">
           			<input type="hidden" name="accion" value="clientePrestamo">
           			  <button class="w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200">
		            <span class="text-2xl"><i class="fa-solid fa-landmark"></i></span>
		           	<p class="text-lg font-bold">Pedir Prestamo</p>
		            </button>
      				 </form>
					</div>
					<div class="w-1/4 h-16  border rounded-lg border-gray-300">
					<a class=" w-full h-full flex flex-row items-center gap-3 justify-center hover:bg-gray-200" href="/ProjectBeta1/views/userInfo.jsp">
					<span class="text-2xl"><i class="fa-solid fa-circle-user"></i></i></span>
					<p class="text-lg font-bold">Ver Perfil</p>
					</a>
					</div>
					
				</div>
					<%} %>
			</div>
			<%if(user.getTipoUsuario()==eTipoUsuario.Cliente){ %>
			<div class="h-44 border rounded-lg border-gray-300 px-6 py-2">
			<div class="flex flex-row gap-2 items-center ">
			<h3 class="text-lg">Todas las cuentas</h3>
			<span class="text-2xl cursor-pointer" onclick="toggleSaldos()"><i id="iconoOjo" class="fa-solid fa-eye"></i></span>
			</div>
		
			<div class="flex flex-row gap-5 py-5 ">
				<%
					List<cuenta> list = (List<cuenta>)request.getAttribute("cuentas");
					if (list != null && !list.isEmpty()) {
						for(cuenta c : list){
				%>
				<form method="post" action="<%= request.getContextPath() %>/servletCuentasClientes" class="w-1/3 h-24  border rounded-lg border-gray-300 py-3 px-6">
					<input type="hidden" name="IdCuenta" value="<%=c.getId_cuenta()%>">					
					<button class="w-full">
						<div class="flex flex-row gap-6">
							<span class="text-4xl text-blue-600"><i class="fa-solid fa-closed-captioning"></i></span>
							<div class="flex flex-col w-full">
								<p class="font-bold text-lg">N°<%=c.getId_cuenta() %></p>
								<p class="saldo">$<%=c.getSaldo() %></p>
								<div class="w-full flex flex-row justify-between">
									<span></span>
								</div>
							</div>
						</div>
					</button>
				</form>
				<%				
			} } else { %>
				<p> Todavia no posee cuentas activas </p>
			<%}%>
			</div>
		</div>
			
			<%} %>
		</div>
	</div>
	<script>
	    function toggleSaldos() {
	        var saldos = document.querySelectorAll(".saldo");
	        for (var i = 0; i < saldos.length; i++) {
	            if (saldos[i].innerText === "********") {
	                saldos[i].innerText = saldos[i].dataset.originalValue;
	            } else {
	                saldos[i].dataset.originalValue = saldos[i].innerText;
	                saldos[i].innerText = "********";
	            }
	        }
	        var iconoOjo = document.getElementById("iconoOjo");
	        if (iconoOjo.classList.contains("fa-eye")) {
	            iconoOjo.classList.remove("fa-eye");
	            iconoOjo.classList.add("fa-eye-slash");
	        } else {
	            iconoOjo.classList.remove("fa-eye-slash");
	            iconoOjo.classList.add("fa-eye");
	        }
	    }
	</script>
</body>
</html>