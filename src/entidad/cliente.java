package entidad;
import java.time.LocalDate;

public class cliente {
	private int id;
	private direccion direccion;
	private int dni;
	private Long cuil;
	private String nombre;
	private String apellido;
	private eSexo sexo;
	private nacionalidad nacionalidad;
	private LocalDate fechaNacimiento;
	private String email;
	private Long telefono;
	private String usuario;
	private String password;
	private eTipoUsuario tipoUsuario;
	private boolean estado;
	
	public cliente(direccion direccion, int dni, Long cuil, String nombre, String apellido, eSexo sexo,
			nacionalidad nacionalidad, LocalDate fechaNacimiento, String email, Long telefono, String usuario,
			String password, eTipoUsuario tipoUsuario) {
		this.direccion = direccion;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.telefono = telefono;
		this.usuario = usuario;
		this.password = password;
		this.tipoUsuario = tipoUsuario;
	}
	public cliente(direccion direccion, int dni, Long cuil, String nombre, String apellido, eSexo sexo,
			nacionalidad nacionalidad, LocalDate fechaNacimiento, String email, Long telefono) {
		this.direccion = direccion;
		this.dni = dni;
		this.cuil = cuil;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.nacionalidad = nacionalidad;
		this.fechaNacimiento = fechaNacimiento;
		this.email = email;
		this.telefono = telefono;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public eTipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(eTipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}	
	public direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(direccion direccion) {
		this.direccion = direccion;
	}
	public int getDni() {
		return dni;
	}
	public void setDni(int dni) {
		this.dni = dni;
	}
	public Long getCuil() {
		return cuil;
	}
	public void setCuil(Long cuil) {
		this.cuil = cuil;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public eSexo getSexo() {
		return sexo;
	}
	public void setSexo(eSexo sexo) {
		this.sexo = sexo;
	}
	public nacionalidad getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(nacionalidad nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTelefono() {
		return telefono;
	}
	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean getEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	
	
}
