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
	    	Long idCliente = Long.parseLong(request.getParameter("btnModificarCliente"));
	    	cliente clienteModificar = new clienteNeg().obtenerCliente(idCliente);
    		request.setAttribute("clienteModificar", clienteModificar);
	    }

	    if (request.getParameter("busqueda") != null || request.getParameter("filtroEstado") != null) {
	        String filtroBusqueda = request.getParameter("busqueda");
	        String filtroEstado = request.getParameter("filtroEstado");
	        clienteNeg clienteNeg = new clienteNeg();
	        boolean getAll = false;
	        if(filtroEstado.equals("all")) getAll= true;
	        List<cliente> lista = clienteNeg.listarClientes(Boolean.parseBoolean(filtroEstado),getAll);
	        
	        if(!filtroBusqueda.trim().isEmpty()) {
	        	List<cliente> listaFiltrada = filtrarListadoClientes(lista, filtroBusqueda);
	        	request.getSession().setAttribute("lista", listaFiltrada);
	        }else {
	        	request.getSession().setAttribute("lista", lista);
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
				if (request.getAttribute("idCliente") != null)
				{
					Long idCliente = Long.parseLong(request.getAttribute("idCliente").toString());
					cliente clienteModificar = new clienteNeg().obtenerCliente(idCliente);
					request.setAttribute("clienteModificar", clienteModificar);				
				}
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
	    	Long idCliente = Long.parseLong(request.getParameter("idCliente"));

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
		
		
		if (request.getParameter("btnToggleEstado") != null)
		{
			Long clientId = Long.parseLong(request.getParameter("btnToggleEstado"));
			
			boolean modificado= clienteNeg.toggleEstado(clientId);
			if(modificado)
			{
	    		request.setAttribute("texto", "Estado de cliente modificado con exito");
			}
			else
			{
	    		request.setAttribute("texto", "No se pudo modificar el estado del Cliente");
			}
			
	    	request.setAttribute("modal", true);
	    	request.getSession().setAttribute("lista", clienteNeg.listarClientes());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCliente.jsp");
			dispatcher.forward(request, response);	
			return;
		}
		
		if(request.getParameter("btnCambiarPassword")!=null)
		{
	    	Long idCliente = Long.parseLong(request.getParameter("idClientePassword"));
	    	String clientePassword = clienteNeg.obtenerPassword(idCliente);
	    	String passwordActual = request.getParameter("txtPasswordActual");
	    	String nuevaPassword = request.getParameter("txtNuevaPassword");
	    	String repetirPassword = request.getParameter("txtPasswordRepetida");
	    	String textoAMostrar = "";
	    	try
	    	{
		    	if (!clientePassword.equals(passwordActual))
		    	{
		    		throw new ArgumentoInvalidoException("Error al ingresar la contraseña actual no es igual a la registrada");
		    	}
		    	if (!nuevaPassword.equals(repetirPassword))
		    	{
		    		throw new ArgumentoInvalidoException("La nueva contraseña ingresada no coincide con la repetida");
		    	}
		    	if (clientePassword.equals(nuevaPassword))
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
	    		request.setAttribute("idCliente", idCliente);
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
