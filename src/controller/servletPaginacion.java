package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class servletPaginacion
 */
@WebServlet("/servletPaginacion")
public class servletPaginacion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*
         * CARGAR EN SESSION LAS LISTAS A PAGINAR BAJO LOS ATRIBUTOS "lista" y "lista2"
         * DEVUELVE LAS LISTAS PAGINADAS EN LOS ATRIBUTOS "listaPaginada" y "listaPaginada2"
         * EN LOS BOTONES PARA CAMBIAR DE PÁGINA LLAMAR AL SERVLET CON EL URLPARAM "page"
         * Y "redirectUrl" PARA A DONDE TIENE QUE REDIRIGIR
         */

        int paginaActual = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int paginaActual2 = (request.getParameter("page2") != null) ? Integer.parseInt(request.getParameter("page2")) : 1;
        int elementosPorPagina = 10;

        List<Object> lista = new ArrayList<>();
        List<Object> lista2 = new ArrayList<>();
        HttpSession session = request.getSession();

        if (session.getAttribute("lista") != null) {
            lista = (List<Object>) session.getAttribute("lista");
        }

        if (session.getAttribute("lista2") != null) {
            lista2 = (List<Object>) session.getAttribute("lista2");
        }

        int totalElementos = lista.size();
        int totalPaginas = (int) Math.ceil((double) totalElementos / elementosPorPagina);
        int indiceInicial = (paginaActual - 1) * elementosPorPagina;
        int indiceFinal = Math.min(indiceInicial + elementosPorPagina, totalElementos);

        List<Object> listaPaginada = lista.subList(indiceInicial, indiceFinal);

        int totalElementos2 = lista2.size();
        int totalPaginas2 = (int) Math.ceil((double) totalElementos2 / elementosPorPagina);
        int indiceInicial2 = (paginaActual2 - 1) * elementosPorPagina;
        int indiceFinal2 = Math.min(indiceInicial2 + elementosPorPagina, totalElementos2);

        List<Object> listaPaginada2 = lista2.subList(indiceInicial2, indiceFinal2);

        request.setAttribute("listaPaginada", listaPaginada);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);

        request.setAttribute("listaPaginada2", listaPaginada2);
        request.setAttribute("paginaActual2", paginaActual2);
        request.setAttribute("totalPaginas2", totalPaginas2);

        String redirectUrl = "/views/" + request.getParameter("redirectUrl");
        request.getRequestDispatcher(redirectUrl).forward(request, response);
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	