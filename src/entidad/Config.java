package entidad;		

public final class Config {
	private static boolean devMode = false;
	private boolean admin = true;
	private static Usuario activeUser;
	
	private static final Config INSTANCE = new Config();
	 
	public Config() {
		if (admin)activeUser = new Usuario(1,"devAdmin","",eTipoUsuario.Administrador,1);
		else activeUser = new Usuario(1,"devCliente","",eTipoUsuario.Cliente,1);
	}

	public static boolean isDevMode() {
		return devMode;
	}
	
	public static Usuario switchUser() {
		if(activeUser.getTipoUsuario() == eTipoUsuario.Administrador) {
			activeUser = new Usuario(1,"devCliente","",eTipoUsuario.Cliente,1);
		}else {
			activeUser = new Usuario(1,"devAdmin","",eTipoUsuario.Administrador,1);
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
