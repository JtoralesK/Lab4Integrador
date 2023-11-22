package controller;

import entidad.Config;
import entidad.Usuario;
import entidad.eTipoUsuario;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/servletConfig")
public class servletConfig extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public servletConfig() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String toggleUserType = request.getParameter("toggleUserType");

        if (toggleUserType != null && toggleUserType.equals("true")) {
            
        	request.getSession().setAttribute("loggedUser", Config.switchUser());;

            response.sendRedirect(request.getContextPath() + "/servletHome");
        }
    }
}
