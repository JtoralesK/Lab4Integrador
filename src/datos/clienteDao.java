package datos;

import java.time.format.DateTimeFormatter;

import entidad.cliente;

public class clienteDao {
	private conexion cn;
	
	public boolean insertar(cliente cliente) {
		boolean estado=true;

		cn = new conexion();
		cn.Open();	

		String query = "INSERT INTO clientes (ID_TipoUsuario, dni, cuil, nombre, apellido, sexo, ID_Nacionalidad, fechaNacimiento, direccion, localidad, email, telefono, usuario, password) "
				+"VALUES ("+(cliente.getTipoUsuario().ordinal() + 1)+","+cliente.getDni()+","+cliente.getCuil()+",'"+cliente.getNombre()+"','"+cliente.getApellido()+"',"+(cliente.getSexo().ordinal() + 1) +","+cliente.getNacionalidad().getId()
				+",'"+cliente.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"','"+cliente.getDireccion().getDireccion()+"',"+cliente.getDireccion().getLocalidad().getId()
				+",'"+cliente.getEmail()+"',"+cliente.getTelefono()+",'"+cliente.getUsuario()+"','"+cliente.getPassword()+"')";
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
