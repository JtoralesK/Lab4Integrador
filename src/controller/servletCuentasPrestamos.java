package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.movimiento;
import entidad.prestamo;
import negocio.movimientosNeg;
import negocio.prestamoNeg;

/**
 * Servlet implementation class servletCuentasPrestamos
 */
@WebServlet("/servletCuentasPrestamos")
public class servletCuentasPrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /** 
     * @see HttpServlet#HttpServlet()
     */
    public servletCuentasPrestamos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		List<prestamo>ListaPrestamos = new prestamoNeg().listarXcliente((long) 101);;
		request.setAttribute("ListaPrestamos", ListaPrestamos);
		//RequestDispatcher rd = request.getRequestDispatcher("/views/cuenta.jsp");
		int numeroDePagina = 1;
		int elementosPorPagina = 10;
		RequestDispatcher dr = request.getRequestDispatcher("/servletPaginacion?redirectUrl=cuenta.jsp&p=" + numeroDePagina + "&s=" + elementosPorPagina);	
       dr.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
