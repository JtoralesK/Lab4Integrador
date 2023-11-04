<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
<head>
<%
	request.setAttribute("titulo", "Registrar Cliente");
%>
<jsp:include page="head.jsp" />
</head>
<body class="bg-gray-100">
	<jsp:include page="navbar.jsp" />

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
						<option value="1">Masculino</option>
						<option value="2">Femenino</option>
						<option value="3">Otro</option>
					</select>
				</div>
				<div>
					<label for="cbNacionalidad">Nacionalidad:</label> 
					<input type="text" id="cbNacionalidad" name="cbNacionalidad" class="w-full p-2 rounded" required>
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
					<label for="cbLocalidad">Localidad:</label> 
					<input type="text" id="cbLocalidad" name="cbLocalidad" class="w-full p-2 rounded" required>
				</div>
				<div>
					<label for="cbProvincia">Provincia:</label> 
					<input type="text" id="cbProvincia" name="cbProvincia" class="w-full p-2 rounded" required>
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
        
        var provinciaSelect = document.getElementById('cbProvincia');
        var localidadSelect = document.getElementById('cbLocalidad');

        provinciaSelect.addEventListener('change', function() {
            var selectedProvincia = provinciaSelect.value;
            var xhr = new XMLHttpRequest();

            xhr.open('GET', '/ProjectBeta1/servletCliente?provincia=' + selectedProvincia, true);

            xhr.onload = function() {
                if (xhr.status === 200) {
                    // Parsear la respuesta JSON
                    var response = JSON.parse(xhr.responseText);

                    // Limpiar el select de localidades
                    localidadSelect.innerHTML = '';

                    // Llenar el select de localidades con las localidades recibidas
                    for (var i = 0; i < response.length; i++) {
                        var option = document.createElement('option');
                        option.value = response[i];
                        option.text = response[i];
                        localidadSelect.appendChild(option);
                    }
                }
            };

            xhr.send();
        });
    </script>
</body>
</html>
