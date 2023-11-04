package datos;

import java.time.format.DateTimeFormatter;

import entidad.Usuario;
import entidad.cliente;

public class clienteDao {
	private conexion cn;
	
	public boolean insertar(cliente cliente) {
		boolean estado=true;

		cn = new conexion();
		cn.Open();	
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		
		usuario.setUsuario(cliente.getUsuario());
		usuario.setContraseña(cliente.getPassword());
		usuario.setTipoUsuario(cliente.getTipoUsuario());
		usuarioDao.insertar(usuario);
		
		int idUsuario = usuarioDao.obtenerPorNombre(cliente.getUsuario()).getId();
		
		String query = "INSERT INTO clientes (dni, cuil, nombre, apellido, ID_Sexo, ID_Nacionalidad, fecha_Nacimiento, direccion, id_localidad, mail, telefono, ID_usuario) "
				+"VALUES ("+cliente.getDni()+","+cliente.getCuil()+",'"+cliente.getNombre()+"','"+cliente.getApellido()+"',"+(cliente.getSexo().ordinal() + 1) +","+cliente.getNacionalidad().getId()
				+",'"+cliente.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"','"+cliente.getDireccion().getDireccion()+"',"+cliente.getDireccion().getLocalidad().getId()
				+",'"+cliente.getEmail()+"',"+cliente.getTelefono()+","+idUsuario+")";
		
		System.out.println(query);
		try
		{
			estado=cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		return estado;
	}
}
