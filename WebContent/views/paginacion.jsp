<%@page import="java.io.Console"%>
<%
	int paginaActual = (Integer) request.getAttribute("paginaActual");
	int totalPaginas = (Integer) request.getAttribute("totalPaginas");
	String urlCompleta = request.getRequestURL().toString();
    int lastSlashIndex = urlCompleta.lastIndexOf("/");
    String redirectUrl = urlCompleta.substring(lastSlashIndex + 1);
%>
<div class="text-center mb-4">
		<nav aria-label="pagination">
			<ul class="inline-flex -space-x-px text-sm mt-1">
		        <%-- Anterior --%>
		        <% if (paginaActual > 1) { %>
		        <li>
		            <a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual - 1 %>&redirectUrl=<%= redirectUrl %>" class="flex items-center shadow-md justify-center px-3 h-8 ms-0 leading-tight text-gray-500 bg-white border border-e-0 border-gray-300 rounded-s-lg hover:bg-gray-100 hover:text-gray-700">Anterior</a>
				</li>
				<% } %>
		        <%-- Números de página --%>
		        <% for (int i = 1; i <= totalPaginas; i++) { 
		        	if(paginaActual==i){
		        %>
		        <li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=<%= redirectUrl %>" aria-current="page" class="flex items-center shadow-md justify-center px-3 h-8 text-blue-600 border border-gray-300 bg-blue-50 hover:bg-blue-100 hover:text-blue-700"><%= i %></a>
	        	</li>
		        	<%}else {%>
	        	<li>
	        		<a href="/ProjectBeta1/servletPaginacion?page=<%= i %>&redirectUrl=<%= redirectUrl %>" class="flex items-center justify-center shadow-md px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 hover:bg-gray-100 hover:text-gray-700"><%= i %></a>
	        	</li>
		        	<% } 
		        	}%>
		
		        <%-- Siguiente --%>
		        <% if (paginaActual < totalPaginas) { %>
		        <li>
					<a href="/ProjectBeta1/servletPaginacion?page=<%= paginaActual + 1 %>&redirectUrl=<%= redirectUrl %>" class="flex items-center shadow-md justify-center px-3 h-8 leading-tight text-gray-500 bg-white border border-gray-300 rounded-e-lg hover:bg-gray-100 hover:text-gray-700">Siguiente</a>
		        </li>
		        <% } %>
	        </ul>
	    </nav>
	</div>