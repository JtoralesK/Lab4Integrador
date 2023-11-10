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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * CARGAR EN SESSION LA LISTA A PAGINAR BAJO EL ATRIBUTO "lista"
		 * 
		 */
		
		
		int paginaActual = (request.getParameter("page") != null) ? Integer.parseInt(request.getParameter("page")) : 1;
        int elementosPorPagina = 10;

        List<Object> lista = new ArrayList<>();
        HttpSession session = request.getSession();
        
        if(session.getAttribute("lista") != null) {
        	lista = (List<Object>)session.getAttribute("lista");
        }
        int totalElementos = lista.size();
        System.out.println(totalElementos);
        int totalPaginas = (int) Math.ceil((double) totalElementos / elementosPorPagina);
        int indiceInicial = (paginaActual - 1) * elementosPorPagina;
        int indiceFinal = Math.min(indiceInicial + elementosPorPagina, totalElementos);

        List<Object> listaPaginada = lista.subList(indiceInicial, indiceFinal);

        request.setAttribute("listaPaginada", listaPaginada);
        request.setAttribute("paginaActual", paginaActual);
        request.setAttribute("totalPaginas", totalPaginas);

        String redirectUrl = "/views/" + request.getParameter("redirectUrl");
        request.getRequestDispatcher(redirectUrl).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
	