package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import entidad.Usuario;
import entidad.eTipoUsuario;
import negocio.UsuarioNeg;

@WebServlet("/servletUsuario")
public class servletUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletUsuario() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnLogin") != null) {
            String usuario = request.getParameter("usuario");
            String contraseña = request.getParameter("password");

            String textoAMostrar = "";

            if (usuario.trim().length() < 1) {
                textoAMostrar = "Ingrese un usuario";
            }
            if (contraseña.trim().length() < 1) {
                textoAMostrar = "Ingrese una contraseña";
            }
            
            UsuarioNeg usuarioNeg = new UsuarioNeg();
            Usuario foundUser = usuarioNeg.getUsuarioPorNombre(usuario);
            
            boolean loginCorrect = foundUser != null && foundUser.getContraseña().equals(contraseña);
            
            if (loginCorrect) {
            	request.getSession().setAttribute("loggedUser", foundUser);
            	response.sendRedirect(request.getContextPath() + "/views/home.jsp");
            } else {
                textoAMostrar = "El usuario o la contraseña son incorrectos";
                request.setAttribute("texto", textoAMostrar);
                request.setAttribute("modal", true);
                request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            }

        } else if (request.getParameter("logout") != null) {
            request.getSession().removeAttribute("loggedUser");
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");
        }
        
        
    }
}
