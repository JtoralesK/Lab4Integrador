package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import negocio.prestamoNeg;
import entidad.eEstadoPrestamo;
import entidad.prestamo;

@WebServlet("/servletPrestamo")
public class servletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletPrestamo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("accion") != null)
		{
			if ("adminPrestamo".equals(request.getParameter("accion")))
			{
				List<prestamo> prestamos = new prestamoNeg().listar();
				request.setAttribute("prestamos", prestamos);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminPrestamos.jsp");
				dispatcher.forward(request, response);				
			}			
		}
		
		if (request.getParameter("btnAprobar") != null || request.getParameter("btnRechazar") != null)
		{
			String texto = "";
			if (request.getParameter("btnAprobar") != null)
			{
				texto = AprobarRechazarPrestamo(request, eEstadoPrestamo.Aceptado);
			}
			
			if (request.getParameter("btnRechazar") != null)
			{
				texto = AprobarRechazarPrestamo(request, eEstadoPrestamo.Rechazado);
			}
			List<prestamo> prestamos = new prestamoNeg().listar();
			request.setAttribute("prestamos", prestamos);
			request.setAttribute("texto", texto);
			request.setAttribute("modal", true);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/adminPrestamos.jsp");
			dispatcher.forward(request, response);
		}
	}
	
	private String AprobarRechazarPrestamo(HttpServletRequest request, eEstadoPrestamo estadoPrestamo)
	{
		prestamo prestamo = new prestamo();
		prestamo.setId(Long.parseLong(request.getParameter("idPrestamo")));
		prestamo.setEstadoPrestamo(estadoPrestamo);
		if (new prestamoNeg().actualizar(prestamo))
		{
			return "El prestamo fue actualizado con exito";
		}
		else
		{
			return "El prestamo no pudo ser actualizado";
		}

	}
}
