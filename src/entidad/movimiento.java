package entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.sun.org.apache.bcel.internal.generic.RETURN;


public class movimiento {
private int	idMovimiento;
private Long nCuenta;
private Long idCliente;
private eTipoMovimiento tipoMovimiento;
private LocalDate fecha;
private LocalTime  hora;
private Double importe;
private String concepto="";


public movimiento() {}
public movimiento(int idMovimiento,Long nCuenta,Long idCliente,eTipoMovimiento tipoMovimiento,LocalDate fecha, LocalTime  hora,Double importe, String concepto) 
{
	this.idMovimiento=idMovimiento;
	this.nCuenta=nCuenta;
	this.idCliente=idCliente;
	this.tipoMovimiento=tipoMovimiento;
	this.fecha=fecha;
	this.hora=hora;
	this.importe=importe;
	this.concepto=concepto;
	
}
public movimiento(Long nCuenta,Long idCliente,eTipoMovimiento tipoMovimiento,LocalDate fecha, LocalTime  hora,Double importe) 
{
	this.nCuenta=nCuenta;
	this.idCliente=idCliente;
	this.tipoMovimiento=tipoMovimiento;
	this.fecha=fecha;
	this.hora=hora;
	this.importe=importe;
	
}

public int getId_movimiento() {
	return idMovimiento;
}
public void setId_movimiento(int idMovimiento) {
	this.idMovimiento = idMovimiento;
}
public Long getN_cuenta() {
	return nCuenta;
}
public void setN_cuenta(Long nCuenta) {
	this.nCuenta = nCuenta;
}
public Long getId_cliente() {
	return idCliente;
}
public void setId_cliente(Long idCliente) {
	this.idCliente = idCliente;
}
public eTipoMovimiento getTipoMovimiento() {
	return tipoMovimiento;
}
public void setTipoMovimiento(eTipoMovimiento tipoMovimiento) {
	this.tipoMovimiento = tipoMovimiento;
}
public LocalDate getFecha() {
	return fecha;
}
public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}
public LocalTime getHora() {
	return hora;
}
public void setHora(LocalTime hora) {
	this.hora = hora;
}
public Double getImporte() {
	return importe;
}
public void setImporte(Double importe) {
	this.importe = importe;
}
public String getConcepto() {
	return concepto;
}
public void setConcepto(String concepto) {
	this.concepto = concepto;
}
public LocalDateTime getFechaYHora() {
	if(fecha == null || hora == null) return null;
	return LocalDateTime.of(fecha, hora);	
}

}
