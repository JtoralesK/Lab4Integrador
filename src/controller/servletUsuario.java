 package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import entidad.Usuario;
import entidad.cliente;
import entidad.eTipoUsuario;
import negocio.UsuarioNeg;
import negocio.clienteNeg;

@WebServlet("/servletUsuario")
public class servletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletUsuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	if (request.getParameter("userInfo")!= null) {
    		HttpSession session = request.getSession();
    		Usuario loggedUser = (Usuario) session.getAttribute("loggedUser");
    		if(loggedUser.getTipoUsuario() == eTipoUsuario.Cliente) {
    			clienteNeg clienteNeg = new clienteNeg();
    			cliente loggedCliente = clienteNeg.obtenerClientePorIdUsuario(loggedUser.getId());
    			session.setAttribute("loggedCliente", loggedCliente);
    		}
    		
    		response.sendRedirect(request.getContextPath() + "/views/userInfo.jsp");
    		
    	}
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnLogin") != null) {
            String usuario = request.getParameter("usuario");
            String password = request.getParameter("password");

            String textoAMostrar = "";

            if (usuario.trim().length() < 1) {
                textoAMostrar = "Ingrese un usuario válido";
                ErrorLogin(request, response, textoAMostrar);
                return;
            }
            if (password.trim().length() < 1) {
                textoAMostrar = "Ingrese una contraseña válida";
                ErrorLogin(request, response, textoAMostrar);
                return;
            }
            
            UsuarioNeg usuarioNeg = new UsuarioNeg();
            Usuario foundUser = usuarioNeg.getUsuarioPorNombre(usuario);
            
            boolean loginCorrect = foundUser != null && foundUser.getContraseña().equals(password);
            
            if (loginCorrect) {
            	request.getSession().setAttribute("loggedUser", foundUser);
        		if(foundUser.getTipoUsuario() == eTipoUsuario.Cliente) {
        			clienteNeg clienteNeg = new clienteNeg();
        			cliente loggedCliente = clienteNeg.obtenerClientePorIdUsuario(foundUser.getId());
        			request.getSession().setAttribute("loggedCliente", loggedCliente);
        		}
            	response.sendRedirect(request.getContextPath() + "/servletHome");
            } else {
                textoAMostrar = "El usuario o la contraseña son incorrectos";
                ErrorLogin(request, response, textoAMostrar);
            }

        } else if (request.getParameter("logout") != null) {
            request.getSession().removeAttribute("loggedUser");
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }             
    }
    
    private void ErrorLogin(HttpServletRequest request, HttpServletResponse response, String mensaje) throws ServletException, IOException
    {
        request.setAttribute("texto", mensaje);
        request.setAttribute("modal", true);
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }
}
