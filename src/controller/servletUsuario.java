package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getParameter("btnCrearUsuario") != null) {
            String usuario = request.getParameter("txtUsuario");
            String contraseña = request.getParameter("txtContraseña");
            eTipoUsuario tipoUsuario = eTipoUsuario.valueOf(request.getParameter("cbTipoUsuario"));

            String textoAMostrar = "";

            if (usuario.trim().length() < 1) {
                textoAMostrar = "Complete el nombre de usuario correctamente";
            }
            if (contraseña.trim().length() < 1) {
                textoAMostrar = "Complete la contraseña correctamente";
            }

            UsuarioNeg usuarioNeg = new UsuarioNeg();
            Usuario nuevoUsuario = new Usuario(usuario,contraseña,tipoUsuario);
            

            boolean insertCorrecto = usuarioNeg.GuardarUsuario(nuevoUsuario);

            if (insertCorrecto) {
                textoAMostrar = "Usuario registrado correctamente";
            } else {
                textoAMostrar = "El usuario no se pudo registrar";
            }

            request.setAttribute("texto", textoAMostrar);
            request.setAttribute("modal", true);
        }
    }
}
