<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.0/css/all.min.css"></head>

<nav class="w-full h-20 bg-white p-5 flex flex-row justify-between items-center border-b border-gray-300">
	<img class="h-15 w-20"  src="Resources/bankLogo.png"></img>
	<div class="w-8/12 w-full flex flex-row justify-around items-center">
	<div class="flex flex-row gap-5">
	<a href="/ProjectBeta1/views/home.jsp">Home</a>
<div class="relative group">
    <button class="group-hover:bg-gray-200 focus:outline-none">Cuentas</button>
    <div class="hidden absolute bg-white group-hover:block">
        <a href="/ProjectBeta1/views/cuenta.jsp" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center">Cuenta 1</a>
        <a href="/ProjectBeta1/views/cuenta.jsp" class="block hover:bg-gray-200 whitespace-nowrap p-2 text-center">Cuenta 2</a>
    </div>
</div>
	<!-- <select>
		<a href="#"><option value="cuenta1">Cuenta 1</option></a>
		<a href="#"><option value="cuenta2">Cuenta 2</option></a>
	</select> -->
		<a href="/ProjectBeta1/views/transferencia.jsp">Transferencia</a>
	
	<a href="/ProjectBeta1/views/prestamo.jsp">Prestamos</a>
	<a href="/ProjectBeta1/views/infousuario.jsp">Mi Perfil</a>
	</div>
	</div>
	<div class="w-2/12 flex flex-row justify-center items-center gap-3">
	<span class="border rounded-full bg-gray-200 p-3"><i class="fa-regular fa-user"></i></span>
	<a>Javier Torales</a>
	</div>
</nav>