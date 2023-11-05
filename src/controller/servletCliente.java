package controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.localidadDao;
import datos.nacionalidadDao;
import datos.provinciaDao;
import entidad.eSexo;
import entidad.localidad;
import entidad.nacionalidad;
import entidad.provincia;
import negocio.clienteNeg;
import excepciones.ArgumentoInvalidoException;

@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RellenarSelect(request);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/amCliente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RellenarSelect(request);
		clienteNeg clienteNeg = new clienteNeg();
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
	    	try
	    	{
		    	ValidarDatosCliente(dni, cuil, nombre, apellido, email, localidad, provincia);		    	
		    	ValidarDatosUsuario(usuario, password);		    	

	    		boolean insertCorrecto = clienteNeg.guardarCliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento
	    				, direccion, localidad, provincia, email, telefono, usuario, password);
	    		
	    		if (insertCorrecto)
	    		{
	    			textoAMostrar = "Usuario registrado correctamente";
	    		}
	    		else
	    		{
	    			textoAMostrar = "El usuario no se pudo registrar";
	    		}

	    	}
	    	catch (ArgumentoInvalidoException e)
	    	{
	    		textoAMostrar = e.getMessage();
	    		e.printStackTrace();
	    	}
	    	finally
	    	{
	    		request.setAttribute("texto", textoAMostrar);
		    	request.setAttribute("modal", true);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/views/amCliente.jsp");
				dispatcher.forward(request, response);
	    	}	    	

	    }
        
		if (request.getParameter("btnModificar") != null)
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
	    	
	    	ValidarDatosCliente(dni, cuil, nombre, apellido, email, localidad, provincia);		    	

			boolean updateCorrecto = clienteNeg.actualizarCliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento
    									, direccion, localidad, provincia, email, telefono);
			if(updateCorrecto)
			{
	    		request.setAttribute("texto", "Cliente Modificado con exito");
			}
			else
			{
	    		request.setAttribute("texto", "No se pudo modificar el Cliente");
			}
			
	    	request.setAttribute("modal", true);
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/ProjectBeta1/blCliente.jsp");
			dispatcher.forward(request, response);		
		}
		
		if (request.getParameter("btnElminar") != null)
		{
			boolean deleteCorrecto = clienteNeg.eliminarCliente(1);
			
			if(deleteCorrecto)
			{
	    		request.setAttribute("texto", "Cliente Eliminado con exito");
			}
			else
			{
	    		request.setAttribute("texto", "No se pudo eliminar el Cliente");
			}
			
	    	request.setAttribute("modal", true);
	    	
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/views/blCliente.jsp");
			dispatcher.forward(request, response);	
		}
        
	}
	
	public boolean ValidarDatosCliente(String dni, String cuil, String nombre, String apellido, String email, String localidad, String provincia) throws ArgumentoInvalidoException 
	{
    	if (dni.length() < 8)
    	{
    		throw new ArgumentoInvalidoException("El Dni tiene menos de 8 digitos");
    	}	    	
    	if (cuil.length() < 12)
    	{
    		throw new ArgumentoInvalidoException("El CUIL tiene menos de 11 digitos");
    	}	    	
    	if (nombre.trim().length() < 1)
    	{
    		throw new ArgumentoInvalidoException("Complete el nombre correctamente");
    	}
    	if (apellido.trim().length() < 1)
    	{
    		throw new ArgumentoInvalidoException("Complete el apellido correctamente");
    	}
    	if (email.trim().length() < 1)
    	{
    		throw new ArgumentoInvalidoException("Complete el email correctamente");
    	}
    	if (localidad == "-1")
    	{
    		throw new ArgumentoInvalidoException("No has seleccionado una localidad");
    	}
    	if (provincia == "-1")
    	{
    		throw new ArgumentoInvalidoException("No has seleccionado una provincia");
    	}
    	
    	return true;
	}
	
	public boolean ValidarDatosUsuario(String usuario, String password) throws ArgumentoInvalidoException 
	{
    	if (usuario.trim().length() < 1)
    	{
    		throw new ArgumentoInvalidoException("Complete el usuario correctamente");
    	}
    	if (password.trim().length() < 1)
    	{
    		throw new ArgumentoInvalidoException("Complete la password correctamente");
    	}
    	
    	return true;
	}
	
	public void RellenarSelect(HttpServletRequest request)
	{
		List<eSexo> sexos = Arrays.asList(eSexo.values());
		request.setAttribute("sexos", sexos);
		
		List<nacionalidad> nacionalidades = new nacionalidadDao().listarNacionalidades();
		request.setAttribute("nacionalidades", nacionalidades);
		
		List<provincia> provincias = new provinciaDao().listarProvincias();
		request.setAttribute("provincias", provincias);

		List<localidad> localidades = new localidadDao().listarLocalidades();
		request.setAttribute("localidades", localidades);
	}
	
}
