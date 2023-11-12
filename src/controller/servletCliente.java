package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import datos.localidadDao;
import datos.nacionalidadDao;
import datos.provinciaDao;
import entidad.cliente;
import entidad.eSexo;
import entidad.localidad;
import entidad.nacionalidad;
import entidad.provincia;
import negocio.clienteNeg;
import negocio.localidadNeg;
import negocio.nacionalidadNeg;
import negocio.provinciaNeg;
import excepciones.ArgumentoInvalidoException;

@WebServlet("/servletCliente")
public class servletCliente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletCliente() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RellenarSelect(request);
	    if(request.getParameter("btnModificarCliente")!=null)
	    {
	    	int idCliente = Integer.parseInt(request.getParameter("btnModificarCliente"));
	    	cliente clienteModificar = new clienteNeg().obtenerCliente(idCliente);
    		request.setAttribute("clienteModificar", clienteModificar);
	    }

	    if(request.getParameter("busqueda") != null) {
	    	String filtro = request.getParameter("busqueda");
	    	clienteNeg clienteNeg = new clienteNeg();
	    	List<cliente> listaCompleta = clienteNeg.listarClientes();
	    	if(!filtro.trim().isEmpty()) {
	    		List<cliente> listaFiltrada = filtrarListadoClientes(listaCompleta, filtro);
	    		request.getSession().setAttribute("lista",listaFiltrada);
	    	}else {;
	    		request.getSession().setAttribute("lista",listaCompleta);
	    	}
	    	request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCliente.jsp").forward(request, response);
	    	return;
	    }
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/amCliente.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		RellenarSelect(request);
		clienteNeg clienteNeg = new clienteNeg();
		if(request.getParameter("accion") != null || request.getAttribute("accion") != null) {
			String accion = request.getParameter("accion") != null ? request.getParameter("accion") : request.getAttribute("accion").toString();
			if("blCliente".equals(accion)) {
				HttpSession session = request.getSession();
				List<cliente> listaCompleta = clienteNeg.listarClientes();				
				session.setAttribute("lista", listaCompleta);
				request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCliente.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/views/amCliente.jsp").forward(request, response);
			}
			return;
		}
		
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
	    	return;

	    }
        
		if (request.getParameter("btnModificarCliente") != null)
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
	    	int idCliente = Integer.parseInt(request.getParameter("idCliente"));

	    	String textoAMostrar = "";
	    	try
	    	{
		    	ValidarDatosCliente(dni, cuil, nombre, apellido, email, localidad, provincia);		    	

				boolean updateCorrecto = clienteNeg.actualizarCliente(dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento
	    									, direccion, localidad, provincia, email, telefono, idCliente);
				if(updateCorrecto)
				{
					textoAMostrar = "Cliente Modificado con exito";
				}
				else
				{
					textoAMostrar = "No se pudo modificar el Cliente";
				}
	    		request.setAttribute("accion", "amCliente");
	    	}
	    	catch (ArgumentoInvalidoException e)
	    	{
	    		textoAMostrar = e.getMessage();
	    		e.printStackTrace();
	    		request.setAttribute("accion", "blCliente");
	    	}
	    	finally
	    	{
	    		request.setAttribute("texto", textoAMostrar);
		    	request.setAttribute("modal", true);
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/servletCliente");
				dispatcher.forward(request, response);
	    	}
	    	return;
		}
		
		if (request.getParameter("modificar") != null) {
			int clientId = Integer.parseInt(request.getParameter("modificar"));
			cliente clienteModificar = clienteNeg.obtenerCliente(clientId);
			request.setAttribute("clienteModificar", clienteModificar);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/views/amCliente.jsp");
		    dispatcher.forward(request, response);
		}
		
		if (request.getParameter("btnEliminar") != null)
		{
			int clientId = Integer.parseInt(request.getParameter("btnEliminar"));
			boolean deleteCorrecto = clienteNeg.eliminarCliente(clientId);
			
			if(deleteCorrecto)
			{
	    		request.setAttribute("texto", "Cliente Eliminado con exito");
			}
			else
			{
	    		request.setAttribute("texto", "No se pudo eliminar el Cliente");
			}
			
	    	request.setAttribute("modal", true);
	    	request.getSession().setAttribute("lista", clienteNeg.listarClientes());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCliente.jsp");
			dispatcher.forward(request, response);	
			return;
		}
		
		if(request.getParameter("btnCambiarContraseña")!=null)
		{
	    	int idCliente = Integer.parseInt(request.getParameter("idClientePassword"));
	    	String clientePassword = clienteNeg.obtenerPassword(idCliente);
	    	String passwordActual = request.getParameter("txtContraseñaActual");
	    	String nuevaPassword = request.getParameter("txtNuevaContraseña");
	    	String repetirPassword = request.getParameter("txtContraseñaRepetida");
	    	String textoAMostrar = "";
	    	try
	    	{
		    	if (!clientePassword.equals(passwordActual))
		    	{
		    		throw new ArgumentoInvalidoException("Error al ingresar la contraseña actual");
		    	}
		    	if (!nuevaPassword.equals(repetirPassword))
		    	{
		    		throw new ArgumentoInvalidoException("La nueva contraseña ingresada no coincide con la repetida");
		    	}
		    	if (!clientePassword.equals(nuevaPassword))
		    	{
		    		throw new ArgumentoInvalidoException("La nueva contraseña no puede ser igual a la contraseña actual");
		    	}
		    	if (clienteNeg.actualizarPassword(idCliente, nuevaPassword))
		    	{
		    		textoAMostrar = "La contraseña se ha modificado con exito";
		    	}
		    	else
		    	{
		    		textoAMostrar = "La contraseña no pudo ser modificada";
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
	    		request.setAttribute("accion", "amCliente");
		    	RequestDispatcher dispatcher = request.getRequestDispatcher("/servletCliente");
				dispatcher.forward(request, response);
	    	}	    	
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
		
		List<nacionalidad> nacionalidades = new nacionalidadNeg().listarNacionalidades();
		request.setAttribute("nacionalidades", nacionalidades);
		
		List<provincia> provincias = new provinciaNeg().listarProvincias();
		request.setAttribute("provincias", provincias);

		List<localidad> localidades = new localidadNeg().listarLocalidades();
		request.setAttribute("localidades", localidades);
	}
	
	public List<cliente> filtrarListadoClientes(List<cliente> listado,String filtro){
		List<cliente> listadoFiltrado = new ArrayList<cliente>();
		for (cliente cliente : listado) {
	        if (
	        	String.valueOf(cliente.getDni()).contains(filtro.toLowerCase()) ||
	            String.valueOf(cliente.getCuil()).contains(filtro.toLowerCase()) ||
	            cliente.getNombre().toLowerCase().contains(filtro.toLowerCase()) ||
	            cliente.getApellido().toLowerCase().contains(filtro.toLowerCase()) ||
	            cliente.getFechaNacimiento().toString().contains(filtro) ||
	            cliente.getEmail().toLowerCase().contains(filtro.toLowerCase()) ||
	            String.valueOf(cliente.getTelefono()).contains(filtro.toLowerCase()) ||
	            String.valueOf(cliente.getId()).contains(filtro) ||
	           // cliente.getUsuario().toLowerCase().contains(filtro) ||
	            cliente.getNacionalidad().getNombre().toLowerCase().equals(filtro) ||
	            cliente.getDireccion().getLocalidad().getNombre().toLowerCase().equals(filtro.toLowerCase()) ||
	            cliente.getDireccion().getLocalidad().getProvincia().getNombre().toLowerCase().equals(filtro.toLowerCase())
	            ) {
	        	listadoFiltrado.add(cliente);
	        }
	    }
		return listadoFiltrado;
	}
}
