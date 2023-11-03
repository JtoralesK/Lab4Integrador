package entidad;

public class Usuario {
	private int id = 0;
    private String usuario;
    private String contrase�a;
    private eTipoUsuario tipoUsuario;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(String usuario, String contrase�a, eTipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.contrase�a = contrase�a;
        this.tipoUsuario = tipoUsuario;
        this.estado = true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrase�a() {
        return contrase�a;
    }

    public void setContrase�a(String contrase�a) {
        this.contrase�a = contrase�a;
    }

    public eTipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(eTipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
    
    public boolean getEstado() {
    	return estado;
    }
    
    public void setEstado(boolean estado) {
    	this.estado = estado;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }
}
