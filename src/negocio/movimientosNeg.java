package negocio;

import java.sql.Date;
import java.util.List;

import datos.movimientosDao;
import entidad.eTipoMovimiento;
import entidad.movimiento;

public class movimientosNeg {

	public  List<movimiento> listarMovimientos() 
	{
		movimientosDao movimientos = new movimientosDao();
		return movimientos.listarMovimientos();
	}
	
	public List<movimiento> listarMovimientosPorIdCliente(int idCliente)
	{
		return new movimientosDao().listarMovimientosPorIdCliente(idCliente);
	}
	
	public List<movimiento> listarMovimientosPorIdClienteYFecha(int idCliente,Date fecha)
	{
		return new movimientosDao().listarMovimientosPorIdClienteYFecha(idCliente,fecha);
	}
	
	public List<movimiento> listarMovimientosPorIdClienteYTipo(int idCliente,eTipoMovimiento tipoMovimiento) 
	{
		return new movimientosDao().listarMovimientosPorIdClienteYTipo(idCliente, tipoMovimiento);
	}
	
	public List<movimiento> listarMovimientosPorTipoYFecha(int idCliente,eTipoMovimiento tipoMovimiento,Date fecha) 
	{
		return new movimientosDao().listarMovimientosPorTipoYFecha(idCliente,tipoMovimiento,fecha);
	
	}
	/****Saca el importe segun el tipo de movimiento  ****/
	
	public Double getImportePorClienteYTipo(int idCliente,eTipoMovimiento tipoMovimiento)
	{
		return new movimientosDao().getImportePorClienteYTipo(idCliente, tipoMovimiento);
	}
	
	public Double getImportePorTipoMovimiento(eTipoMovimiento tipoMovimiento)
	{
		return new movimientosDao().getImportePorTipoMovimiento(tipoMovimiento);
	}
	public boolean nuevoMovimiento(movimiento movimiento) {
		boolean movCuenta = new cuentaNeg().modificarSaldo(movimiento.getN_cuenta(), movimiento.getImporte());
		System.out.println("saldo modificado");
		boolean regMov = new movimientosDao().insertar(movimiento);
		if(regMov)System.out.println("movimiento registrado");
		return regMov && movCuenta;
	}
}


