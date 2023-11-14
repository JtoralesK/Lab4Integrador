package entidad;		

public final class Config {
	private static boolean devMode = true;
	private boolean admin = true;
	private static Usuario activeUser;
	
	private static final Config INSTANCE = new Config();
	 
	public Config() {
		if (admin)activeUser = new Usuario(1,"devAdmin","",eTipoUsuario.Administrador,true);
		else activeUser = new Usuario(1,"devCliente","",eTipoUsuario.Cliente,true);
	}

	public static boolean isDevMode() {
		return devMode;
	}
	
	public static Usuario switchUser() {
		if(activeUser.getTipoUsuario() == eTipoUsuario.Administrador) {
			activeUser = new Usuario(1,"devCliente","",eTipoUsuario.Cliente,true);
		}else {
			activeUser = new Usuario(1,"devAdmin","",eTipoUsuario.Administrador,true);
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
