package entidad;

import java.sql.Date;
import java.sql.Time;


public class movimientos {
private int	id_movimiento;
private int n_cuenta;
private int id_cliente;
private tipo_movimientos id_tipo_movimiento;
private Date fecha;
private Time  hora;
private Double importe;
private String concepto;


public movimientos() {}
public movimientos(int id_movimiento,int n_cuenta,int id_cliente,tipo_movimientos id_tipo_movimiento,Date fecha, Time  hora,Double importe, String concepto) 
{
	this.id_movimiento=id_movimiento;
	this.n_cuenta=n_cuenta;;
	this.id_cliente=id_cliente;
	this.id_tipo_movimiento=id_tipo_movimiento;
	this.fecha=fecha;
	this.hora=hora;
	this.importe=importe;
	this.concepto=concepto;
	
}
public movimientos(int n_cuenta,int id_cliente,tipo_movimientos id_tipo_movimiento,Date fecha, Time  hora,Double importe) 
{
	this.n_cuenta=n_cuenta;;
	this.id_cliente=id_cliente;
	this.id_tipo_movimiento=id_tipo_movimiento;
	this.fecha=fecha;
	this.hora=hora;
	this.importe=importe;
	
}

public int getId_movimiento() {
	return id_movimiento;
}
public void setId_movimiento(int id_movimiento) {
	this.id_movimiento = id_movimiento;
}
public int getN_cuenta() {
	return n_cuenta;
}
public void setN_cuenta(int n_cuenta) {
	this.n_cuenta = n_cuenta;
}
public int getId_cliente() {
	return id_cliente;
}
public void setId_cliente(int id_cliente) {
	this.id_cliente = id_cliente;
}
public tipo_movimientos getId_tipo_movimiento() {
	return id_tipo_movimiento;
}
public void setId_tipo_movimiento(tipo_movimientos id_tipo_movimiento) {
	this.id_tipo_movimiento = id_tipo_movimiento;
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
