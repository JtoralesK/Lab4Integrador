<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <%request.setAttribute("titulo", "Cuenta"); %>
    <jsp:include page="head.jsp"/>
</head>
<body class="bg-gray-100">
<jsp:include page="navbar.jsp" />
<h1  class="table-auto">Usuario</h1>
<br> 
<table  class="table-auto border-collapse border border-gray-300">
        <thead>
        <tr>
            <th class="px-4 py-2">Id</th>
            <th class="px-4 py-2">Tipo de Usuario</th>
            <th class="px-4 py-2">Dni</th>
            <th class="px-4 py-2">Cuil</th>
            <th class="px-4 py-2">Nombre</th>
            <th class="px-4 py-2">Apellido</th>
            <th class="px-4 py-2">Sexo</th>
            <th class="px-4 py-2">Nacionalidad</th>
            <th class="px-4 py-2">Fecha de nacimiento</th>
            <th class="px-4 py-2">Direccion</th>
            <th class="px-4 py-2">Loclidad</th>
            <th class="px-4 py-2">Mail</th>
            <th class="px-4 py-2">Telefono</th>
            <th class="px-4 py-2">Usuario</th>
            <th class="px-4 py-2">Contraseña</th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td class="border px-4 py-2">1 </td>
            <td class="border px-4 py-2">1 </td>
            <td class="border px-4 py-2">987900 </td>
            <td class="border px-4 py-2">1323 </td>
            <td class="border px-4 py-2">Javier </td>
            <td class="border px-4 py-2">Torales </td>
            <td class="border px-4 py-2">Masculino</td>
            <td class="border px-4 py-2">Peruana </td>
            <td class="border px-4 py-2">09-01-2002 </td>
            <td class="border px-4 py-2">urquiza 122 </td>
            <td class="border px-4 py-2">Tigre</td>
            <td class="border px-4 py-2">jewn@gmail.com  </td>
            <td class="border px-4 py-2">111313 </td>
            <td class="border px-4 py-2">jean5011 </td>
            <td class="border px-4 py-2">1234 </td>
            <td class="border px-4 py-2"><button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="button" id="Eliminar">Eliminar</button> </td>
            <td class="border px-4 py-2"><button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="button" id="Modificar">Modificar</button> </td>
        </tr>
        <tr>
            <td class="border px-4 py-2">1 </td>
            <td class="border px-4 py-2">1 </td>
            <td class="border px-4 py-2">987900 </td>
            <td class="border px-4 py-2">1323 </td>
            <td class="border px-4 py-2">jean </td>
            <td class="border px-4 py-2">esquen </td>
            <td class="border px-4 py-2">Masculino</td>
            <td class="border px-4 py-2">Peruana </td>
            <td class="border px-4 py-2">09-01-2002 </td>
            <td class="border px-4 py-2">urquiza 122 </td>
            <td class="border px-4 py-2">Tigre</td>
            <td class="border px-4 py-2">jewn@gmail.com  </td>
            <td class="border px-4 py-2">111313 </td>
            <td class="border px-4 py-2">jean5011 </td>
            <td class="border px-4 py-2">1234 </td>
            <td class="border px-4 py-2"><button class="bg-blue-500 bg-opacity-75 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="button" id="Eliminar">Eliminar</button> </td>
            <td class="border px-4 py-2"><button class="bg-blue-500 bg-opacity-75 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded" type="button" id="Modificar">Modificar</button> </td>
        </tr>
        </tbody>
    </table>
</body>
</html>