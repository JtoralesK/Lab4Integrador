package datos;

import entidad.Usuario;
import entidad.eTipoUsuario;

import java.sql.SQLException;
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

        String query = "INSERT INTO usuarios (usuario, contraseña, tipoUsuario, estado) " +
                "VALUES ('" + usuario.getUsuario() + "', '" + usuario.getContraseña() + "', " + usuario.getTipoUsuario() + ", true)";

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

        String query = "UPDATE usuarios SET contraseña = '" + usuario.getContraseña() + "', tipoUsuario = " + (usuario.getTipoUsuario()) +
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
        Usuario usuario = new Usuario();
        cn.Open();

        String query = "SELECT * FROM usuarios WHERE usuario = '" + nombreUsuario + "'";

        try {
            ResultSet rs = cn.query(query);
            if (rs.next()) {
                usuario.setUsuario(rs.getString("usuario"));
                usuario.setContraseña(rs.getString("contraseña"));
                int tipoUsuarioOrdinal = rs.getInt("tipoUsuario") - 1;
                usuario.setTipoUsuario(eTipoUsuario.values()[tipoUsuarioOrdinal]);
            }else return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
                int tipoUsuarioOrdinal = rs.getInt("tipoUsuario") - 1;
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

}
