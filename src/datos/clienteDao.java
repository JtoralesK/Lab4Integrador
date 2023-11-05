package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import datos.localidadDao;
import datos.nacionalidadDao;
import datos.provinciaDao;

import entidad.Usuario;
import entidad.cliente;
import entidad.direccion;
import entidad.eSexo;
import entidad.eTipoUsuario;
import entidad.localidad;
import entidad.nacionalidad;
import entidad.provincia;

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
	
    public boolean actualizar(cliente cliente) {
        boolean estado = true;
        cn.Open();

        String query = "UPDATE clientes SET " + 
        		"dni = "+cliente.getDni()+" ," + 
        		"cuil = "+cliente.getCuil()+" ," + 
        		"nombre = '"+cliente.getNombre()+"' ," + 
        		"apellido = '"+cliente.getApellido()+"' ," + 
        		"ID_Sexo = "+(cliente.getSexo().ordinal() - 1)+" ," + 
        		"id_nacionalidad = "+cliente.getNacionalidad()+" ," + 
        		"fecha_nacimiento = '"+cliente.getFechaNacimiento()+"' ," + 
        		"direccion = '"+cliente.getDireccion()+"' ," + 
        		"id_localidad = "+cliente.getDireccion().getLocalidad()+" ," + 
        		"mail = '"+cliente.getEmail()+"' ," + 
        		"telefono = "+cliente.getTelefono()+" " + 
        		"WHERE id_cliente = " + cliente.getId();

        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    public boolean eliminar(int idCliente) {
        boolean estado = true;
        cn.Open();

        String query = "DELETE FROM clientes WHERE id_cliente = " + idCliente +";";

        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }
    public cliente obtenerPorId(int idCliente) {
    	cliente cliente = null;
        Connection connection = cn.Open(); 

        String query = "SELECT id_cliente, dni, cuil, nombre, apellido, ID_Sexo, id_nacionalidad, fecha_nacimiento, direccion, C.id_localidad, L.id_provincia, mail, ID_usuario, telefono FROM clientes C INNER JOIN localidades L ON C.id_localidad = L.id_localidad WHERE id_cliente = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    		
            if (rs.next()) {
                cliente = new cliente(
                			new direccion(rs.getString("direccion"), 
            					new localidadDao().obtenerUno(rs.getInt("id_localidad"))),
                			rs.getInt("dni"),
                			rs.getLong("cuil"),
                			rs.getString("nombre"),
                			rs.getString("apellido"),
                			eSexo.values()[rs.getInt("ID_Sexo") - 1],
                			new nacionalidadDao().obtenerUno(rs.getInt("id_nacionalidad")),
                			LocalDate.parse(rs.getString("fecha_nacimiento"), formatter),
                			rs.getString("email"),
                			rs.getLong("telefono")
                			);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return cliente;
    }
}
