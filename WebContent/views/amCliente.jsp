<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="entidad.eSexo" %>
<%@ page import="entidad.localidad" %>
<%@ page import="entidad.nacionalidad" %>
<%@ page import="entidad.provincia" %>
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
%>

	<div class="bg-blue-200 border-b container mx-auto p-4 w-8/12 rounded mt-4">
		<h1 class="text-2xl font-semibold mb-4 text-center">Registro de Cliente</h1>
		<form action="/ProjectBeta1/servletCliente" method="post">
			<div class="grid grid-cols-2 gap-4">
				<div>
					<label for="txtDni">DNI:</label> 
					<input type="number" id="txtDni" name="txtDni" class="w-full p-2 rounded" max="99999999" required>
				</div>
				<div>
					<label for="txtCuil">CUIL:</label> 
					<input type="text" id="txtCuil" name="txtCuil" class="w-full p-2 rounded" maxlength="12" oninput="formatCuil(this)" required>
				</div>
				<div>
					<label for="txtNombre">Nombre:</label> 
					<input type="text" id="txtNombre" name="txtNombre" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtApellido">Apellido:</label> 
					<input type="text" id="txtApellido" name="txtApellido" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cbSexo">Sexo:</label> 
					<select id="cbSexo" name="cbSexo" class="w-full p-2 rounded" required>
					<%for (eSexo sexo : sexos) {%>
						<option value="<%=sexo.ordinal()%>"><%=sexo.name()%></option>
					<%} %>
					</select>
				</div>
				<div>
					<label for="cbNacionalidad">Nacionalidad:</label> 
					<select id="cbNacionalidad" name="cbNacionalidad" class="w-full p-2 rounded" required>
					<%for (nacionalidad nacionalidad : nacionalidades) {%>
						<option value="<%=nacionalidad.getId()%>"><%=nacionalidad.getNombre()%></option>
					<%} %>
					</select>
				</div>
				<div>
					<label for="txtFechaNacimiento">Fecha de Nacimiento:</label> 
					<input type="date" id="txtFechaNacimiento" name="txtFechaNacimiento" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtDireccion">Dirección:</label> 
					<input type="text" id="txtDireccion" name="txtDireccion" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cbProvincia">Provincia:</label> 
					<select id="cbProvincia" name="cbProvincia" class="w-full p-2 rounded" required>
						<option value="-1">Seleccione una provincia</option>		
					<%for (provincia provincia : provincias) {%>
						<option value="<%=provincia.getId()%>"><%=provincia.getNombre()%></option>
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
					<input type="email" id="txtEmail" name="txtEmail" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtTelefono">Teléfono:</label> 
					<input type="number" id="txtTelefono" name="txtTelefono" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtUsuario">Usuario:</label> 
					<input type="text" id="txtUsuario" name="txtUsuario" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="txtPassword">Contraseña:</label> 
					<input type="password" id="txtPassword" name="txtPassword" class="w-full p-2 rounded" required>
				</div>
			</div>
			<div class="flex">
				<button id="btnCrearCliente" name="btnCrearCliente" type="submit" class="bg-blue-500 text-white p-3 mt-5 rounded hover:bg-blue-700 ml-auto">Crear Cliente</button>
			</div>
		</form>
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
			
			<%for (localidad localidad : localidades){%>
				localidades.push({ id: <%=localidad.getId()%>, nombre: '<%=localidad.getNombre()%>', idProvincia: <%=localidad.getProvincia().getId()%> });
			<%}%>
			
	        localidades.forEach(function(localidad) {
	        	if(localidad.id ==  selectedProvinciaId)
        		{
		            var option = document.createElement('option');
		            option.value = localidad.id;
		            option.textContent = localidad.nombre;
		            cbLocalidad.appendChild(option);  		
        		}
	        });

        });
    </script>
</body>
</html>
