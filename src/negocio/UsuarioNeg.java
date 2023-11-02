package negocio;

import datos.UsuarioDao;
import entidad.Usuario;
import entidad.eTipoUsuario;

public class UsuarioNeg {
    private UsuarioDao usuarioDao;

    public UsuarioNeg() {
        usuarioDao = new UsuarioDao();
    }

    public boolean GuardarUsuario(String usuario, String contraseņa, eTipoUsuario tipoUsuario) {

        Usuario nuevoUsuario = new Usuario(usuario, contraseņa, tipoUsuario);

        return usuarioDao.insertar(nuevoUsuario);
    }

}
