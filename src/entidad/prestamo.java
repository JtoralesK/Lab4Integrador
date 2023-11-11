package entidad;

import java.time.LocalDate;

public class prestamo {
	private Long id;
	private Long idCliente;
	private Long idCuenta;
	private float importe;
	private LocalDate fechaSolicitud;
	private eEstadoPrestamo estadoPrestamo;
	private int plazo;
	private LocalDate fechaRevision;
	private int cuotasPagas;
	private float interes;
	
	public float getInteres() {
		return interes;
	}
	public void setInteres(float interes) {
		this.interes = interes;
	}
	public prestamo() {}
	public prestamo(Long idCliente, Long idCuenta, float importe, LocalDate fechaSolicitud,
			eEstadoPrestamo estadoPrestamo, int plazo, LocalDate fechaRevision) {
		this.idCliente = idCliente;
		this.idCuenta = idCuenta;
		this.importe = importe;
		this.fechaSolicitud = fechaSolicitud;
		this.estadoPrestamo = estadoPrestamo;
		this.plazo = plazo;
		this.fechaRevision = fechaRevision;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
	public Long getIdCuenta() {
		return idCuenta;
	}
	public void setIdCuenta(Long idCuenta) {
		this.idCuenta = idCuenta;
	}
	public float getImporte() {
		return importe;
	}
	public void setImporte(float importe) {
		this.importe = importe;
	}
	public LocalDate getFechaSolicitud() {
		return fechaSolicitud;
	}
	public void setFechaSolicitud(LocalDate fechaSolicitud) {
		this.fechaSolicitud = fechaSolicitud;
	}
	public eEstadoPrestamo getEstadoPrestamo() {
		return estadoPrestamo;
	}
	public void setEstadoPrestamo(eEstadoPrestamo estadoPrestamo) {
		this.estadoPrestamo = estadoPrestamo;
	}
	public int getPlazo() {
		return plazo;
	}
	public void setPlazo(int plazo) {
		this.plazo = plazo;
	}
	public LocalDate getFechaRevision() {
		return fechaRevision;
	}
	public void setFechaRevision(LocalDate fechaRevision) {
		this.fechaRevision = fechaRevision;
	}
	public int getCuotasPagas() {
		return cuotasPagas;
	}
	public void setCuotasPagas(int cuotasPagas) {
		this.cuotasPagas = cuotasPagas;
	}
	
}
