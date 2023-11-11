package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.cuentaDao;
import entidad.cuenta;
import entidad.eTipoCuenta;

/**
 * Servlet implementation class servletCuenta
 */
@WebServlet("/servletCuenta")
public class servletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cuentaDao ctaDao = new cuentaDao();
		if(request.getParameter("accion") != null || request.getAttribute("accion") != null) {
			String accion = request.getParameter("accion") != null ? request.getParameter("accion") : request.getAttribute("accion").toString();
			if("blCuentas".equals(accion)) {
				HttpSession session = request.getSession();
				List<cuenta> listaCompleta = ctaDao.selectAll();
				if(request.getParameter("filter") != null || (request.getParameter("filterCbu") != null )) {
					//filtrar lista
					String cbu = "";
					if(!request.getParameter("filterCbu").isEmpty()) {
						cbu = request.getParameter("filterCbu");
					}
					eTipoCuenta tipoCuenta = null;
					if(request.getParameter("filter") != null) {
						tipoCuenta = Integer.parseInt(request.getParameter("filter"))==1?eTipoCuenta.CajaDeAhorro:eTipoCuenta.CuentaCorriente;
					}
					// si algunos de los 2 es verdadero, se filtra
					List<cuenta> listaFiltrada = ctaDao.selectAllByTypeOf(tipoCuenta,cbu);
					
					//si se ingreso el cbu pero esta vacio y el tipo de cuenta es null, se devuelve la lista completa
					if(request.getParameter("filterCbu").isEmpty() && request.getParameter("filter") == null) {
						listaFiltrada = ctaDao.selectAll();// => lista completa
					}
					session.setAttribute("lista", listaFiltrada);
				}else {
					session.setAttribute("lista", listaCompleta);
				}
				request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCuentas.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/views/blCuentas.jsp").forward(request, response);
			}
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
