package entidad;

public class tipo_movimientos {
private int id_tipo_movimiento;
private String descripcion;

public tipo_movimientos(int id_tipo_movimiento) {
	this.id_tipo_movimiento=id_tipo_movimiento;
}
public tipo_movimientos(int id_tipo_movimiento,String descripcion) 
{
	this.id_tipo_movimiento=id_tipo_movimiento;
	this.descripcion=descripcion;
}

public int getId_tipo_movimiento() {
	return id_tipo_movimiento;
}
public void setId_tipo_movimiento(int id_tipo_movimiento) {
	this.id_tipo_movimiento = id_tipo_movimiento;
}
public String getDescripcion() {
	return descripcion;
}
public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
}


}
