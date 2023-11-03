package entidad;

public class Usuario {
	private int id = 0;
    private String usuario;
    private String contraseña;
    private eTipoUsuario tipoUsuario;
    private boolean estado;

    public Usuario() {
    }

    public Usuario(String usuario, String contraseña, eTipoUsuario tipoUsuario) {
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.estado = true;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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
