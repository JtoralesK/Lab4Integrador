package entidad;

public class Usuario {

	private int id = 0;
    private String usuario;
    private String contraseña;
    private eTipoUsuario tipoUsuario;
    private int estado;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String contraseña, eTipoUsuario tipoUsuario, int estado) {
    	this.id= id;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.tipoUsuario = tipoUsuario;
        this.estado = estado;
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
    
    public int getEstado() {
    	return estado;
    }
    
    public void setEstado(int estado) {
    	this.estado = estado;
    }
    
    public void setId(int id) {
    	this.id = id;
    }
    
    public int getId() {
    	return this.id;
    }
    @Override
	public String toString() {
		return "Usuario [id=" + id + ", usuario=" + usuario + ", contraseña=" + contraseña + ", tipoUsuario="
				+ tipoUsuario + ", estado=" + estado + "]";
	}
}
