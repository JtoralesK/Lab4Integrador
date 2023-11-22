package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidad.cuenta;
import entidad.eTipoUsuario;
import negocio.cuentaNeg;

/**
 * Servlet implementation class servletHome
 */
@WebServlet("/servletHome")
public class servletHome extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletHome() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("sisoy");
		Usuario loggedUser = (Usuario) request.getSession().getAttribute("loggedUser");
		if(loggedUser.getTipoUsuario() == eTipoUsuario.Cliente) {
			List<cuenta> cuentas = new cuentaNeg().selectAllByOneUserId(loggedUser.getId());
			request.setAttribute("cuentas", cuentas);
		}
		request.getRequestDispatcher("/views/home.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
