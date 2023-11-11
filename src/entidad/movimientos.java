package entidad;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class movimientos {
private int	id_movimiento;
private int n_cuenta;
private int id_cliente;
private int id_tipo_movimiento;
private LocalDate fecha;
private LocalDateTime  hora;
private float importe;
private String concepto;



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



public int getId_tipo_movimiento() {
	return id_tipo_movimiento;
}



public void setId_tipo_movimiento(int id_tipo_movimiento) {
	this.id_tipo_movimiento = id_tipo_movimiento;
}



public LocalDate getFecha() {
	return fecha;
}



public void setFecha(LocalDate fecha) {
	this.fecha = fecha;
}



public LocalDateTime getHora() {
	return hora;
}



public void setHora(LocalDateTime hora) {
	this.hora = hora;
}



public float getImporte() {
	return importe;
}



public void setImporte(float importe) {
	this.importe = importe;
}



public String getConcepto() {
	return concepto;
}



public void setConcepto(String concepto) {
	this.concepto = concepto;
}




}
