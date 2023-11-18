	package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidad.cliente;
import entidad.cuenta;
import negocio.clienteNeg;
import negocio.cuentaNeg;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("homeTransferencias")!= null) {
			Usuario loggedUser = (Usuario)request.getSession().getAttribute("loggedUser");
			cliente cliente = new clienteNeg().obtenerClientePorIdUsuario(loggedUser.getId());
			List<cuenta> cuentas = new cuentaNeg().selectAllByOneClientId(cliente.getId());
			request.setAttribute("cuentas", cuentas);
			
			request.getRequestDispatcher("/views/transferencia.jsp").forward(request, response);;
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
