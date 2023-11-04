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
				int page = 1; 
				int offset = 5; 
				int cantReg=0;
				if (request.getParameter("page") != null) {
				    page = Integer.parseInt(request.getParameter("page"));
				}
				if (request.getParameter("cantReg") != null) {
					cantReg = Integer.parseInt(request.getParameter("cantReg"));
				}
				cuentaDao cta = new cuentaDao();
				List<cuenta> cuentas = cta.listarCuentas();
				List<cuenta> sublista = cuentas.subList(page, page+offset);
				cantReg=cuentas.size();
				request.setAttribute("cuentas", sublista);
				request.setAttribute("page", page); 
				request.setAttribute("cantReg", cantReg); 

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
