package negocio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import datos.clienteDao;
import entidad.cliente;
import entidad.direccion;
import entidad.eSexo;
import entidad.eTipoUsuario;
import entidad.localidad;
import entidad.nacionalidad;
import entidad.provincia;

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
		
		cliente cliente = new cliente(direccion, dni, cuil, nombre, apellido, sexo, nacionalidad, fechaNacimiento, email, telefono, usuario, password, eTipoUsuario.Cliente);
				
		return clienteDao.insertar(cliente);
	}
	
	public boolean eliminarCliente(int idCliente)
	{						
		return clienteDao.eliminar(idCliente);
	}
	
	public boolean actualizarCliente(String dniStr, String cuilStr, String nombre, String apellido, String sexoId, String nacionalidadId, String fechaNacimientoStr, String direccionStr, String localidadId, String provinciaId, String email, String telefonoStr)
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
				
		return clienteDao.actualizar(cliente);
	}
	
	public cliente obtenerCliente(int idCliente)
	{						
		return clienteDao.obtenerPorId(idCliente);
	}
}
