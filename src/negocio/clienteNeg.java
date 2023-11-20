package negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import datos.UsuarioDao;
import datos.clienteDao;
import entidad.cliente;
import entidad.direccion;
import entidad.eSexo;
import entidad.eTipoUsuario;
import entidad.localidad;
import entidad.nacionalidad;
import entidad.provincia;
import excepciones.ArgumentoInvalidoException;

public class clienteNeg {

	clienteDao clienteDao = new clienteDao();
	
	public boolean guardarCliente(String dniStr, String cuilStr, String nombre, String apellido, String sexoId, String nacionalidadId, String fechaNacimientoStr, String direccionStr, String localidadId, String provinciaId, String email, String telefonoStr, String usuario, String password)
	{	
		int dni = Integer.parseInt(dniStr);
		Long cuil = Long.parseLong(cuilStr.replace("-", ""));
		eSexo sexo = eSexo.values()[Integer.parseInt(sexoId)];
		provincia provincia = new provincia(Integer.parseInt(provinciaId));
		localidad localidad = new localidad(Integer.parseInt(localidadId), provincia);
		direccion direccion = new direccion(direccionStr, localidad);
		nacionalidad nacionalidad = new nacionalidad(Integer.parseInt(nacionalidadId));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNacimiento =  LocalDate.parse(fechaNacimientoStr, formatter);
		Long telefono = Long.parseLong(telefonoStr);
		
		if (!clienteDao.validarDniCuil(dni,cuil))
		{
			throw new ArgumentoInvalidoException("El Dni o el Cuil ingresado ya se encuentra registrado");
		}
		
		if (new UsuarioDao().obtenerPorNombre(usuario) != null)
		{
			throw new ArgumentoInvalidoException("El usuario ingresado ya se encuentra registrado");
		}
		
		cliente cliente = new cliente(direccion, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, email, telefono, usuario, password, eTipoUsuario.Cliente);
				
		return clienteDao.insertar(cliente);
	}
	
	public boolean eliminarCliente(Long idCliente)
	{						
		return clienteDao.eliminar(idCliente);
	}
	
	public boolean activarCliente(Long idCliente)
	{						
		return clienteDao.altaLogica(idCliente);
	}
	
	public boolean toggleEstado(Long idCliente) {
		boolean toggled = false;
		if(obtenerCliente(idCliente).getEstado()) toggled = eliminarCliente(idCliente);
		else toggled = activarCliente(idCliente);
		return toggled;
	}
	
	public boolean actualizarCliente(String dniStr, String cuilStr, String nombre, String apellido, String sexoId, String nacionalidadId, String fechaNacimientoStr, String direccionStr, String localidadId, String provinciaId, String email, String telefonoStr, Long idCliente)
	{	
		int dni = Integer.parseInt(dniStr);
		Long cuil = Long.parseLong(cuilStr.replace("-", ""));
		eSexo sexo = eSexo.values()[Integer.parseInt(sexoId)];
		provincia provincia = new provincia(Integer.parseInt(provinciaId));
		localidad localidad = new localidad(Integer.parseInt(localidadId), provincia);
		direccion direccion = new direccion(direccionStr, localidad);
		nacionalidad nacionalidad = new nacionalidad(Integer.parseInt(nacionalidadId));
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fechaNacimiento =  LocalDate.parse(fechaNacimientoStr, formatter);
		Long telefono = Long.parseLong(telefonoStr);
		
		cliente cliente = new cliente(direccion, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, email, telefono);
		cliente.setId(idCliente);		
		return clienteDao.actualizar(cliente);
	}
	
	public cliente obtenerCliente(Long idCliente)
	{						
		return clienteDao.obtenerPorId(idCliente);
	}
	
	public cliente obtenerClientePorIdUsuario(int idUsuario) {
		return clienteDao.obtenerPorIdUsuario(idUsuario);
	}
	public List<cliente> listarClientes(boolean activo, boolean todos) {
	    return clienteDao.listarClientes(activo,todos);
	}
	public List<cliente> listarClientes() {
	    return clienteDao.listarClientes();
	}
	public String obtenerPassword(Long idCliente)
	{						
		return clienteDao.obtenerPassword(idCliente);
	}
	public Boolean actualizarPassword(Long idCliente, String nuevaPassword)
	{						
		return clienteDao.actualizarPassword(idCliente, nuevaPassword);
	}
}
