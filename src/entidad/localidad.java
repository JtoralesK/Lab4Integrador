package entidad;

public class localidad {
	private int id;
	private String nombre;
	private provincia provincia;
	
	public localidad(int id, provincia provincia) {
		this.id = id;
		this.provincia = provincia;
	}
	public localidad(int id, String nombre, provincia provincia) {
		this.id = id;
		this.nombre = nombre;
		this.provincia = provincia;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public provincia getProvincia() {
		return provincia;
	}
	public void setProvincia(provincia provincia) {
		this.provincia = provincia;
	}
	
	
}
