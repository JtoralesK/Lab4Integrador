package entidad;

import java.sql.Date;
import java.sql.Time;


public class movimiento {
private int	idMovimiento;
private int nCuenta;
private int idCliente;
private eTipoMovimiento tipoMovimiento;
private Date fecha;
private Time  hora;
private Double importe;
private String concepto;


public movimiento() {}
public movimiento(int idMovimiento,int nCuenta,int idCliente,eTipoMovimiento tipoMovimiento,Date fecha, Time  hora,Double importe, String concepto) 
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
public movimiento(int nCuenta,int idCliente,eTipoMovimiento tipoMovimiento,Date fecha, Time  hora,Double importe) 
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
public int getN_cuenta() {
	return nCuenta;
}
public void setN_cuenta(int nCuenta) {
	this.nCuenta = nCuenta;
}
public int getId_cliente() {
	return idCliente;
}
public void setId_cliente(int idCliente) {
	this.idCliente = idCliente;
}
public eTipoMovimiento getTipoMovimiento() {
	return tipoMovimiento;
}
public void setTipoMovimiento(eTipoMovimiento tipoMovimiento) {
	this.tipoMovimiento = tipoMovimiento;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
public Time getHora() {
	return hora;
}
public void setHora(Time hora) {
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


}
