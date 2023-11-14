package datos;

import entidad.Usuario;
import entidad.eTipoUsuario;
import java.sql.Connection;

import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDao {	
    private conexion cn;

    public UsuarioDao() {
        cn = new conexion();
    }

    public boolean insertar(Usuario usuario) {
        boolean estado = true;
        cn.Open();

        String query = "INSERT INTO usuarios (usuario, contraseña, id_tipo_usuario, estado) " +
                "VALUES ('" + usuario.getUsuario() + "', '" + usuario.getContraseña() + "', " + (usuario.getTipoUsuario().ordinal() + 1) + ", true)";

        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    public boolean actualizar(Usuario usuario) {
        boolean estado = true;
        cn.Open();

        String query = "UPDATE usuarios SET contraseña = '" + usuario.getContraseña() + "', id_tipo_usuario = " + (usuario.getTipoUsuario()) +
                " WHERE usuario = '" + usuario.getUsuario() + "'";

        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    public boolean baja(Usuario usuario) {
        boolean estado = true;
        cn.Open();

        String query = "UPDATE usuarios SET estado = false WHERE usuario = '" + usuario.getUsuario() +"'";

        try {
            estado = cn.execute(query);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return estado;
    }

    public Usuario obtenerPorNombre(String nombreUsuario) {
    	Usuario usuario = null;
        Connection connection = cn.Open(); 

        String query = "SELECT * FROM usuarios WHERE usuario = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, nombreUsuario);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(Integer.valueOf(rs.getString("ID_usuario")));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setEstado(Integer.valueOf(rs.getString("estado")));
                int tipoUsuarioOrdinal = rs.getInt("id_tipo_usuario") - 1;
                usuario.setTipoUsuario(eTipoUsuario.values()[tipoUsuarioOrdinal]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return usuario;
    }


    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        cn.Open();

        String query = "SELECT * FROM usuarios";

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                int tipoUsuarioOrdinal = rs.getInt("id_tipo_usuario") - 1;
                usuario.setTipoUsuario(eTipoUsuario.values()[tipoUsuarioOrdinal]);
                usuarios.add(usuario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return usuarios;
    }

    public Usuario obtenerPorId(int idUsuario) {
    	Usuario usuario = null;
        Connection connection = cn.Open(); 

        String query = "SELECT * FROM usuarios WHERE ID_usuario = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, idUsuario);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(Integer.valueOf(rs.getString("ID_usuario")));
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setEstado(Integer.valueOf(rs.getString("estado")));
                int tipoUsuarioOrdinal = rs.getInt("id_tipo_usuario") - 1;
                usuario.setTipoUsuario(eTipoUsuario.values()[tipoUsuarioOrdinal]);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return usuario;
    }
}