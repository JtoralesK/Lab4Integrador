package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import negocio.clienteNeg;

@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    if(request.getParameter("btnCrearCliente")!=null)
	    {	    	
	    	String dni = request.getParameter("txtDni");
	    	String cuil = request.getParameter("txtCuil");
	    	String nombre = request.getParameter("txtNombre");
	    	String apellido = request.getParameter("txtApellido");
	    	String sexo = request.getParameter("cbSexo");
	    	String nacionalidad = request.getParameter("cbNacionalidad");
	    	String fechaNacimiento = request.getParameter("txtFechaNacimiento");
	    	String direccion = request.getParameter("txtDireccion");
	    	String localidad = request.getParameter("cbLocalidad");
	    	String provincia = request.getParameter("cbProvincia");
	    	String email = request.getParameter("txtEmail");
	    	String telefono = request.getParameter("txtTelefono");
	    	String usuario = request.getParameter("txtUsuario");
	    	String password = request.getParameter("txtPassword");
	    	
	    	String textoAMostrar = "";
	    	
	    	if (dni.length() < 8)
	    	{
	    		textoAMostrar = "El Dni tiene menos de 8 digitos";
	    	}	    	
	    	if (cuil.length() < 12)
	    	{
	    		textoAMostrar = "El CUIL tiene menos de 11 digitos";
	    	}	    	
	    	if (nombre.trim().length() < 1)
	    	{
	    		textoAMostrar = "Complete el nombre correctamente";
	    	}
	    	if (apellido.trim().length() < 1)
	    	{
	    		textoAMostrar = "Complete el apellido correctamente";
	    	}
	    	if (email.trim().length() < 1)
	    	{
	    		textoAMostrar = "Complete el email correctamente";
	    	}
	    	if (usuario.trim().length() < 1)
	    	{
	    		textoAMostrar = "Complete el usuario correctamente";
	    	}
	    	if (password.trim().length() < 1)
	    	{
	    		textoAMostrar = "Complete la password correctamente";
	    	}
	    	
	    	clienteNeg clienteNeg = new clienteNeg();
	    	
	    	boolean insertCorrecto = clienteNeg.GuardarCliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento
	    			, direccion, localidad, provincia, email, telefono, usuario, password);
	    
	    	if (insertCorrecto)
	    	{
	    		textoAMostrar = "Usuario registrado correctamente";
	    	}
	    	else
	    	{
	    		textoAMostrar = "El usuario no se pudo registrar";
	    	}
	    	
    		request.setAttribute("texto", textoAMostrar);
	    	request.setAttribute("modal", true);
	    
	    }
        
        
        
	}

}
