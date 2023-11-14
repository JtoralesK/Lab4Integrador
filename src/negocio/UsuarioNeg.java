package negocio;

import datos.UsuarioDao;
import entidad.Usuario;
import entidad.eTipoUsuario;

public class UsuarioNeg {
    private UsuarioDao usuarioDao;

    public UsuarioNeg() {
        usuarioDao = new UsuarioDao();
    }

    public boolean GuardarUsuario(Usuario nuevoUsuario) {

        return usuarioDao.insertar(nuevoUsuario);
    }
    
    public Usuario getUsuarioPorNombre(String nombreUsuario) {
        return usuarioDao.obtenerPorNombre(nombreUsuario);
    }

    public boolean bajaUsuario(Usuario usuario) {
    	return usuarioDao.baja(usuario);
    }
    
    public boolean altaUsuario(Usuario usuario) {
    	return usuarioDao.alta(usuario);
    }
}
