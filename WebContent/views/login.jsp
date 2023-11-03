<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
 <script src="https://cdn.tailwindcss.com"></script>
<title>Login</title>
</head>
<body>
<div>
  <div class="flex flex-row">
   <div class="w-8/12 h-screen bg-blue-800 ">
   <div class="px-10 pt-20 ">
    <h1 class="text-5xl text-white pt-[12rem]">Te damos la bienvenida a <b>Bice</b> Digital</h1>
   <p class="text-3xl text-white ">Accede a tu cuenta</p>
   </div>
  </div>
  <div class="w-4/12 h-screen bg-white">
 <form method="post" action="servletUsuario" class="flex flex-item  justify-center">
 <div class="w-4/6 mt-[12rem]">
  <div class="mb-6">
    <label for="email" class="block mb-2 text-sm font-medium text-gray-900 dark:text-black">Usuario</label>
    <input type="text" name="usuario" class="bg-gray-50 border border-gray-300 text-grey-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"  required>
  </div>
  <div class="mb-6">
    <label for="password" class="block mb-2 text-sm font-medium text-gray-900 dark:text-black">Contraseña</label>
    <input type="password" id="password" name="password" class="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-full p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500" required>
  </div>
  <div class="flex items-start mb-6">
    <div class="flex items-center h-5">
      <input id="remember" type="checkbox" value="" class="w-4 h-4 border border-gray-300 rounded bg-gray-50 focus:ring-3 focus:ring-blue-300 dark:bg-gray-700 dark:border-gray-600 dark:focus:ring-blue-600 dark:ring-offset-gray-800 dark:focus:ring-offset-gray-800">
    </div>
    <label for="remember" class="ml-2 text-sm font-medium text-gray-900 dark:text-gray-300">Remember me</label>
  </div>
  <button type="submit" name="btnLogin" value="login" class="w-full h-10 border rounded bg-black text-white">Iniciar Sesión</button>
 </div>
</form>
  </div>
 
  </div>
</div>
<jsp:include page="modal.jsp" />
</body>
</html>