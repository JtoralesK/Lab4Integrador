package negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import datos.PrestamoDao;
import entidad.cliente;
import entidad.eEstadoPrestamo;
import entidad.eTipoMovimiento;
import entidad.movimiento;
import entidad.prestamo;

public class prestamoNeg {
	PrestamoDao prestamoDao = new PrestamoDao();
	
	public boolean insertar(prestamo prestamo){
		return prestamoDao.insertar(prestamo);
	}
	public List<prestamo> listar(){
		return prestamoDao.listar();
	}
	public boolean actualizar(prestamo prestamo){
		if (prestamo.getEstadoPrestamo() == eEstadoPrestamo.Aprobado)
		{
			movimiento nuevoMovimiento = new movimiento();
			nuevoMovimiento.setId_cliente(prestamo.getIdCliente());
			nuevoMovimiento.setN_cuenta(prestamo.getIdCuenta());
			nuevoMovimiento.setTipoMovimiento(eTipoMovimiento.AltaPrestamo);
			nuevoMovimiento.setFecha(LocalDate.now());
			nuevoMovimiento.setHora(LocalTime.now());
			nuevoMovimiento.setImporte((double) prestamo.getImporte());
			nuevoMovimiento.setConcepto("Nuevo prestamo adquirido");
			new movimientosNeg().nuevoMovimiento(nuevoMovimiento);
		}
		return prestamoDao.actualizar(prestamo);
	}
	/*** Listar por cliente***/
	public List<prestamo> listarXcliente(Long cliente){
		return prestamoDao.listarXcliente(cliente);
	}
	public boolean pagarPrestamo(cliente cliente, Long nCuenta, Long idPrestamo, int cuotasPagar, int cuotasPagas, float cuota)
	{	
		int contador = 0;
		for (int i = cuotasPagas + 1; i <= cuotasPagas + cuotasPagar; i++)
		{
			movimiento nuevoMovimiento = new movimiento();
			nuevoMovimiento.setId_cliente(cliente.getId());
			nuevoMovimiento.setN_cuenta(nCuenta);
			nuevoMovimiento.setTipoMovimiento(eTipoMovimiento.PagoPrestamo);
			nuevoMovimiento.setFecha(LocalDate.now());
			nuevoMovimiento.setHora(LocalTime.now());
			nuevoMovimiento.setImporte((double) cuota * -1);
			nuevoMovimiento.setConcepto("Cuota numero " + i);
			Long idMovimiento = new movimientosNeg().nuevoMovimiento(nuevoMovimiento);
			
			if (new PrestamoDao().pagarPrestamo(idPrestamo, idMovimiento, i))
			{
				contador++;
			}
			else
			{
				break;
			}
		}		
		
		return contador == cuotasPagar;
	}
}
