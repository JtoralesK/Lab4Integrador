package entidad;

import org.apache.catalina.User;
import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import negocio.UsuarioNeg;
import negocio.clienteNeg;

public final class Config {
	private static boolean devMode = true;
	private boolean admin = true;
	private static Usuario activeUser;
	private static Usuario userAdmin = new Usuario(1,"devAdmin","",eTipoUsuario.Administrador,true);
	private static Usuario userCliente;
	
	private static final Config INSTANCE = new Config();
	 
	public Config() {
		UsuarioNeg usuarioNeg = new UsuarioNeg();
		userCliente = usuarioNeg.getUsuarioPorNombre("devCliente");
		if(devMode && userCliente == null) {
			clienteNeg clienteNeg = new clienteNeg();
			clienteNeg.guardarCliente("1111111", "112312", "dev", "Cliente", "1", "1", "1111-11-11", "Calle falsa 123", "1", "1", "dev@mail.com", "1111111", "devCliente", "");
			userCliente = usuarioNeg.getUsuarioPorNombre("devCliente");
		}
		
		if (admin)activeUser = userAdmin;
		else activeUser = userCliente;
		
		
	}

	public static boolean isDevMode() {
		return devMode;
	}
	
	public static Usuario switchUser() {
		if(activeUser.getTipoUsuario() == eTipoUsuario.Administrador) {
			activeUser = userCliente;
		}else {
			activeUser = userAdmin;
		}
		return activeUser;
	}
	public boolean isAdmin() {
		return admin;
	}

	public static Usuario getActiveUser() {
		return activeUser;
	}
	
	public static Config getInstance() {
        return INSTANCE;
    }
}
