package entidad;

public class direccion {
	private String direccion;
	private localidad localidad;
	
	public direccion(String direccion, localidad localidad) {
		this.direccion = direccion;
		this.localidad = localidad;
	}
	
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(localidad localidad) {
		this.localidad = localidad;
	}
	
	
}
