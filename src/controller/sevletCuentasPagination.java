package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.cuentaDao;
import entidad.cuenta;

/**
 * Servlet implementation class sevletCuentasPagination
 */
@WebServlet("/sevletCuentasPagination")
public class sevletCuentasPagination extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public sevletCuentasPagination() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				int page = 1; // Esta variable representa la página actual
				int limit = 5; // Cantidad de registros por página

				if (request.getParameter("page") != null) {
				    page = Integer.parseInt(request.getParameter("page"));
				}

				cuentaDao cta = new cuentaDao();
				List<cuenta> cuentas = cta.listarCuentas(page, limit);

				request.setAttribute("cuentas", cuentas);
				request.setAttribute("page", page); // Página actual
				request.getRequestDispatcher("/views/blCuentas.jsp").forward(request, response);
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
