<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="entidad.eSexo" %>
<%@ page import="entidad.localidad" %>
<%@ page import="entidad.nacionalidad" %>
<%@ page import="entidad.provincia" %>
<%@ page import="entidad.cliente" %>
<!DOCTYPE html>
<html lang="es">
<head>
<%
	request.setAttribute("titulo", "Registrar Cliente");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
<%
	List<eSexo> sexos = (List<eSexo>)request.getAttribute("sexos");
	List<localidad> localidades = (List<localidad>)request.getAttribute("localidades");
	List<nacionalidad> nacionalidades = (List<nacionalidad>)request.getAttribute("nacionalidades");
	List<provincia> provincias = (List<provincia>)request.getAttribute("provincias");
	boolean hayClienteModificar = request.getAttribute("clienteModificar") != null;
	boolean flagLocalidad = true;
	cliente  clienteModificar = (cliente)request.getAttribute("clienteModificar");
%>
	<jsp:include page="navbar.jsp" />

	<div class="bg-blue-200 border-b container mx-auto p-4 w-8/12 rounded m-4">
		<h1 class="text-2xl font-semibold mb-4 text-center"><%=hayClienteModificar ? "Modificar cliente" : "Registro de Cliente"%></h1>
		<form action="/ProjectBeta1/servletCliente" method="post">
			<div class="grid grid-cols-2 gap-4">
				<div>
					<label for="txtDni">DNI:</label> 
					<input type="number" id="txtDni" value="<%=hayClienteModificar ? clienteModificar.getDni() : ""%>" name="txtDni" class="w-full p-2 rounded" max="99999999" required>
				</div>
				<div>
					<label for="txtCuil">CUIL:</label> 
					<input type="text" id="txtCuil" name="txtCuil" value="<%=hayClienteModificar ? clienteModificar.getCuil() : ""%>" class="w-full p-2 rounded" maxlength="12" oninput="formatCuil(this)" required>
				</div>
				<div>
					<label for="txtNombre">Nombre:</label> 
					<input type="text" id="txtNombre" name="txtNombre" value="<%=hayClienteModificar ? clienteModificar.getNombre() : ""%>" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtApellido">Apellido:</label> 
					<input type="text" id="txtApellido" name="txtApellido" value="<%=hayClienteModificar ? clienteModificar.getApellido() : ""%>" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cbSexo">Sexo:</label> 
					<select id="cbSexo" name="cbSexo" class="w-full p-2 rounded" selectedIndex="1" required>
					<%for (eSexo sexo : sexos) {
						if (hayClienteModificar && sexo.ordinal() == clienteModificar.getSexo().ordinal()) { %>
							<option value="<%=sexo.ordinal()%>" selected><%=sexo.name()%></option>
						<%} else {%>
							<option value="<%=sexo.ordinal()%>"><%=sexo.name()%></option>
						<%} %>
						<%} %>
					</select>
				</div>
				<div>
					<label for="cbNacionalidad">Nacionalidad:</label> 
					<select id="cbNacionalidad" name="cbNacionalidad" class="w-full p-2 rounded" required>
						<option value="-1">Seleccione una nacionalidad</option>
					<%for (nacionalidad nacionalidad : nacionalidades) {
						if (hayClienteModificar && nacionalidad.getId() == clienteModificar.getNacionalidad().getId()) { %>
							<option value="<%=nacionalidad.getId()%>" selected><%=nacionalidad.getNombre()%></option>
						<%} else {%>
							<option value="<%=nacionalidad.getId()%>"><%=nacionalidad.getNombre()%></option>
						<%} %>
						<%} %>
					</select>
				</div>
				<div>
					<label for="txtFechaNacimiento">Fecha de Nacimiento:</label> 
					<input type="date" id="txtFechaNacimiento" value="<%=hayClienteModificar ? clienteModificar.getFechaNacimiento() : ""%>"name="txtFechaNacimiento" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtDireccion">Dirección:</label> 
					<input type="text" id="txtDireccion" name="txtDireccion" value="<%=hayClienteModificar ? clienteModificar.getDireccion().getDireccion() : ""%>" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cbProvincia">Provincia:</label> 
					<select id="cbProvincia" name="cbProvincia" class="w-full p-2 rounded" required>
						<option value="-1">Seleccione una provincia</option>		
					<%for (provincia provincia : provincias) {
						if (hayClienteModificar && provincia.getId() == clienteModificar.getDireccion().getLocalidad().getProvincia().getId()) { %>
							<option value="<%=provincia.getId()%>" selected><%=provincia.getNombre()%></option>
						<%} else {%>
							<option value="<%=provincia.getId()%>"><%=provincia.getNombre()%></option>
						<%} %>
					<%} %>
	
					</select>
				</div>
				<div>
					<label for="cbLocalidad">Localidad:</label> 
					<select id="cbLocalidad" name="cbLocalidad" class="w-full p-2 rounded" required>
						<option value="-1">Seleccione una provincia primero</option>
					</select>					
				</div>
				<div>
					<label for="txtEmail">Correo Electrónico:</label> 
					<input type="email" id="txtEmail" name="txtEmail" value="<%=hayClienteModificar ? clienteModificar.getEmail() : ""%>" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtTelefono">Teléfono:</label> 
					<input type="number" id="txtTelefono" name="txtTelefono" value="<%=hayClienteModificar ? clienteModificar.getTelefono() : ""%>" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtUsuario">Usuario:</label> 
					<% if (hayClienteModificar) {%>
						<input type="text" id="txtUsuario" name="txtUsuario" value="<%=clienteModificar.getUsuario() %>" disabled class="w-full p-2 rounded" required>			
					<%} else { %>
						<input type="text" id="txtUsuario" name="txtUsuario" class="w-full p-2 rounded" required>
					<%} %>
				</div>
				<div>
					<div class="flex flex-col">
						<label for="txtPassword">Contraseña:</label> 
						<% if (hayClienteModificar) {%>
						  <label onclick="openModalPassword()" class="bg-blue-500 hover:bg-blue-700 text-white text-center font-bold py-2 px-4 rounded m-4">
						    Cambiar Contraseña
						  </label>				
						<%} else { %>
							<input type="password" id="txtPassword" name="txtPassword" class="w-full p-2 rounded" required>
						<%} %>					
					</div>
				</div>
				<div></div>
				<div>
					<% if (!hayClienteModificar) {%>
						<div class="flex flex-col">
							<label for="txtRepetirPassword">Repita Contraseña:</label> 
							<input type="password" id="txtRepetirPassword" name="txtRepetirPassword" class="w-full p-2 rounded" required>
						</div>
					<%} %>					
				</div>
			</div>
			<div class="flex">
				<button id="<%=hayClienteModificar ? "btnModificarCliente" : "btnCrearCliente"%>" name="<%=hayClienteModificar ? "btnModificarCliente" : "btnCrearCliente"%>" type="submit" class="bg-blue-500 text-white p-3 mt-5 rounded hover:bg-blue-700 ml-auto"><%=hayClienteModificar ? "Actualizar Cliente" : "Crear Cliente"%></button>
			</div>
			<% if (hayClienteModificar) {%>
			<input type="hidden" id="idCliente" name="idCliente" value="<%=clienteModificar.getId()%>">
			<%} %>	
		</form>
	</div>
  <div id="passwordModal" class="fixed hidden inset-0 bg-gray-500 bg-opacity-75 p-6">
    <div class="bg-white p-6 rounded shadow-md max-w-md mx-auto">
      <div class="mb-4">
        <h1 class="text-2xl font-bold">Cambiar Contraseña</h1>
      </div>
      <form action="/ProjectBeta1/servletCliente" method="post">
        <div class="mb-4">
          <label for="currentPassword" class="block text-gray-700 text-sm font-bold mb-2">Contraseña actual:</label>
          <input required type="password" id="txtPasswordActual" name="txtPasswordActual" class="w-full p-2 border border-gray-300 rounded">
        </div>

        <div class="mb-4">
          <label for="newPassword" class="block text-gray-700 text-sm font-bold mb-2">Nueva contraseña:</label>
          <input required type="password" id="txtNuevaPassword" name="txtNuevaPassword" class="w-full p-2 border border-gray-300 rounded">
        </div>

        <div class="mb-4">
          <label for="repeatPassword" class="block text-gray-700 text-sm font-bold mb-2">Repita su contraseña:</label>
          <input required type="password" id="txtPasswordRepetida" name="txtPasswordRepetida" class="w-full p-2 border border-gray-300 rounded">
        </div>

        <div class="flex justify-between">
          <input type="hidden" id="idClientePassword" name="idClientePassword" value="<%=hayClienteModificar ? clienteModificar.getId() : -1%>">
          <button onclick="closeModalPassword()" type="button" class="bg-red-500 hover:bg-red-700 text-white font-bold py-2 px-4 rounded">Cancelar</button>
          <button id="btnCambiarPassword" name="btnCambiarPassword" type="submit" class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">Cambiar Contraseña</button>
        </div>
      </form>
    </div>
  </div>
	<jsp:include page="modal.jsp" />
    <script>
        function formatCuil(input) {
            const cuil = input.value.replace(/\D/g, '');

            if (cuil.length <= 2) {
                input.value = cuil;
            } else if (cuil.length <= 10){
                input.value = cuil.slice(0, 2) + '-' + cuil.slice(2, 10);
            } else if (cuil.length <= 12) {
            	input.value = cuil.slice(0, 2) + '-' + cuil.slice(2, 10) + '-' + cuil.slice(10,11);
            }
            else{
                input.value = cuil.slice(0, 12);
            }
        }       
	</script>
	<script>
     
        var cbProvincia = document.getElementById('cbProvincia');
        var cbLocalidad = document.getElementById('cbLocalidad');         
        
        cbProvincia.addEventListener('change', function() {
			var selectedProvinciaId = cbProvincia.value;
			cbLocalidad.innerHTML = '';
			var localidades = [];
			

			<%
            int idLocalidadCliente = hayClienteModificar ? clienteModificar.getDireccion().getLocalidad().getId() : -1;
            for (localidad localidad : localidades){
				if (localidad.getId() == idLocalidadCliente){%>
				localidades.push({ id: <%=localidad.getId()%>, nombre: '<%=localidad.getNombre()%>', idProvincia: <%=localidad.getProvincia().getId()%>, selected: true });
				<%}else{%>
				localidades.push({ id: <%=localidad.getId()%>, nombre: '<%=localidad.getNombre()%>', idProvincia: <%=localidad.getProvincia().getId()%> });
			<%}}%>
			
	        localidades.forEach(function(localidad) {
	        	if(localidad.idProvincia ==  selectedProvinciaId)
        		{
		            var option = document.createElement('option');
		            option.value = localidad.id;
		            option.textContent = localidad.nombre;
		            if(localidad.selected)
	            	{
		            	option.selected = true;
	            	}
		            cbLocalidad.appendChild(option);  		
        		}
	        });

        });
        
        <% if (hayClienteModificar && flagLocalidad) {%>
			var cbProvincia = document.getElementById('cbProvincia');
			var eventoCambio = new Event('change');
			var txtCuil = document.getElementById('txtCuil');
			formatCuil(txtCuil);
			cbProvincia.dispatchEvent(eventoCambio);
		<%flagLocalidad = false;
		}%>
    </script>
    <script>
	    function openModalPassword() {
	      document.getElementById('passwordModal').classList.remove('hidden');
	    }
	
	    function closeModalPassword() {
	      document.getElementById('passwordModal').classList.add('hidden');
	    }
  </script>
</body>
</html>
