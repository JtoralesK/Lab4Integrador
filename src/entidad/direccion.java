package entidad;

public class direccion {
	private String nombreCalle;
	private int altura;
	private localidad localidad;
	
	public direccion(String nombreCalle, int altura, localidad localidad) {
		super();
		this.nombreCalle = nombreCalle;
		this.altura = altura;
		this.localidad = localidad;
	}
	
	public String getNombreCalle() {
		return nombreCalle;
	}
	public void setNombreCalle(String nombreCalle) {
		this.nombreCalle = nombreCalle;
	}
	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}
	public localidad getLocalidad() {
		return localidad;
	}
	public void setLocalidad(localidad localidad) {
		this.localidad = localidad;
	}
	
	
}
