package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import datos.localidadDao;
import datos.nacionalidadDao;

import entidad.Usuario;
import entidad.cliente;
import entidad.direccion;
import entidad.eSexo;
import negocio.UsuarioNeg;

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
		
		String query = "INSERT INTO clientes (dni, cuil, nombre, apellido, id_sexo, id_nacionalidad, fecha_Nacimiento, direccion, id_localidad, mail, telefono, id_usuario) "
				+"VALUES ("+cliente.getDni()+","+cliente.getCuil()+",'"+cliente.getNombre()+"','"+cliente.getApellido()+"',"+(cliente.getSexo().ordinal() + 1) +","+cliente.getNacionalidad().getId()
				+",'"+cliente.getFechaNacimiento().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))+"','"+cliente.getDireccion().getDireccion()+"',"+cliente.getDireccion().getLocalidad().getId()
				+",'"+cliente.getEmail()+"',"+cliente.getTelefono()+","+idUsuario+")";
		
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
        
		cn = new conexion();
        cn.Open();

        String query = "UPDATE clientes SET " + 
        		"dni = "+cliente.getDni()+" ," + 
        		"cuil = "+cliente.getCuil()+" ," + 
        		"nombre = '"+cliente.getNombre()+"' ," + 
        		"apellido = '"+cliente.getApellido()+"' ," + 
        		"id_sexo = "+(cliente.getSexo().ordinal() + 1)+" ," + 
        		"id_nacionalidad = "+cliente.getNacionalidad().getId() +" ," + 
        		"fecha_nacimiento = '"+cliente.getFechaNacimiento()+"' ," + 
        		"direccion = '"+cliente.getDireccion().getDireccion()+"' ," + 
        		"id_localidad = "+cliente.getDireccion().getLocalidad().getId()+" ," + 
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
        
		cn = new conexion();
        cn.Open();
        try {
			UsuarioNeg usuarioNeg = new UsuarioNeg();
			cliente cl = this.obtenerPorId(idCliente);
			Usuario usuario = usuarioNeg.getUsuarioPorNombre(cl.getUsuario());
			estado = usuarioNeg.bajaUsuario(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
        return estado;   
    }
    
    public cliente obtenerPorId(int idCliente) {
    	cliente cliente = null;
    	
		cn = new conexion();
        Connection connection = cn.Open(); 

        String query = "SELECT id_cliente, dni, cuil, nombre, apellido, id_sexo, id_nacionalidad, fecha_nacimiento, direccion, C.id_localidad, L.id_provincia, mail, id_usuario, telefono FROM clientes C INNER JOIN localidades L ON C.id_localidad = L.id_localidad WHERE id_cliente = ?";

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
                			eSexo.values()[rs.getInt("id_sexo") - 1],
                			new nacionalidadDao().obtenerUno(rs.getInt("id_nacionalidad")),
                			LocalDate.parse(rs.getString("fecha_nacimiento"), formatter),
                			rs.getString("mail"),
                			rs.getLong("telefono")
                			);
                cliente.setId(rs.getInt("id_cliente"));
                
                Usuario usuario = new UsuarioDao().obtenerPorId(rs.getInt("ID_usuario"));
                cliente.setUsuario(usuario.getUsuario());
                cliente.setPassword(usuario.getContraseña());
                cliente.setTipoUsuario(usuario.getTipoUsuario());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return cliente;
    }
    
    public List<cliente> listarClientes(boolean activo) {
        List<cliente> clientes = new ArrayList<>();
        cn = new conexion();
        Connection connection = cn.Open();

        String query = "SELECT C.id_cliente, C.dni, C.cuil, C.nombre, C.apellido, C.id_sexo, C.id_nacionalidad, C.fecha_nacimiento, C.direccion, C.id_localidad, L.id_provincia, C.mail, C.telefono, U.usuario, U.estado " +
                "FROM clientes C " +
                "INNER JOIN localidades L ON C.id_localidad = L.id_localidad " +
                "INNER JOIN usuarios U ON C.id_usuario = U.id_usuario";
        
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
            	boolean estado = rs.getBoolean("estado");
                if (activo == estado) {
                    cliente cliente = new cliente(
                        new direccion(rs.getString("direccion"), new localidadDao().obtenerUno(rs.getInt("id_localidad"))),
                        rs.getInt("dni"),
                        rs.getLong("cuil"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        eSexo.values()[rs.getInt("id_sexo") - 1],
                        new nacionalidadDao().obtenerUno(rs.getInt("id_nacionalidad")),
                        LocalDate.parse(rs.getString("fecha_nacimiento"), formatter),
                        rs.getString("mail"),
                        rs.getLong("telefono")
                    );
                    cliente.setId(rs.getInt("id_cliente"));
                    cliente.setEstado(rs.getBoolean("estado"));
                    cliente.setUsuario(rs.getString("usuario"));
                    clientes.add(cliente);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return clientes;
    }
    public List<cliente> listarClientes() {
    	return listarClientes(true);
    }

    public String obtenerPassword(int idCliente) {
    	String password = "";
    	
		cn = new conexion();
        Connection connection = cn.Open(); 

        String query = "SELECT contraseña " + 
		        		"FROM usuarios U " + 
		        		"INNER JOIN clientes C ON U.id_usuario = C.id_usuario " + 
		        		"WHERE id_cliente = ? ";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idCliente);
            ResultSet rs = preparedStatement.executeQuery();
    		
            if (rs.next()) {
            	password = rs.getString("apellido");
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        
        return password;
    }
    public Boolean actualizarPassword(int idCliente, String nuevaPassword) {
    	Boolean estado = true;
    	
		cn = new conexion();
        Connection connection = cn.Open(); 

        String query = "UPDATE usuarios " + 
        		"SET contraseña = ? " + 
        		"WHERE id_usuario = (" + 
        		"    SELECT id_usuario " + 
        		"    FROM (" + 
        		"        SELECT U.id_usuario " + 
        		"        FROM usuarios U " + 
        		"        INNER JOIN clientes C ON U.id_usuario = C.id_usuario " + 
        		"        WHERE id_cliente = ? " + 
        		"    ) AS subquery "
        		+ ");";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nuevaPassword);
            preparedStatement.setInt(2, idCliente);

            estado = preparedStatement.execute(query);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        
        return estado;
    }
}
