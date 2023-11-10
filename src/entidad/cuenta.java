package entidad;

import java.time.LocalDate;

public class cuenta {
	private int id_cuenta;
	private int id_cliente;
	private eTipoCuenta id_tipo_cuenta;
	private double saldo;
	private LocalDate fecha_creacion;
	private String cbu;
	private boolean estado;

	public cuenta () {
		
	}
	public cuenta(int id_cuenta, int id_cliente, eTipoCuenta id_tipo_cuenta, double saldo, LocalDate fecha_creacion,
			String cbu) {
		super();
		this.id_cuenta = id_cuenta;
		this.id_cliente = id_cliente;
		this.id_tipo_cuenta = id_tipo_cuenta;
		this.saldo = saldo;
		this.fecha_creacion = fecha_creacion;
		this.cbu = cbu;
	}

	public int getId_cuenta() {
		return id_cuenta;
	}

	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}

	public int getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(int id_cliente) {
		this.id_cliente = id_cliente;
	}

	public eTipoCuenta getId_tipo_cuenta() {
		return id_tipo_cuenta;
	}

	public void setId_tipo_cuenta(eTipoCuenta id_tipo_cuenta) {
		this.id_tipo_cuenta = id_tipo_cuenta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public LocalDate getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(LocalDate fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

	public String getCbu() {
		return cbu;
	}

	public void setCbu(String cbu) {
		this.cbu = cbu;
	}
	public boolean isEstado() {
		return estado;
	}
	public void setEstado(boolean estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "cuenta [id_cuenta=" + id_cuenta + ", id_cliente=" + id_cliente + ", id_tipo_cuenta=" + id_tipo_cuenta
				+ ", saldo=" + saldo + ", fecha_creacion=" + fecha_creacion + ", cbu=" + cbu + ", estado=" + estado
				+ "]";
	}

	
}
