package negocio;

import java.util.List;

import datos.PrestamoDao;
import entidad.prestamo;

public class prestamoNeg {
	PrestamoDao prestamoDao = new PrestamoDao();
	
	public List<prestamo> listar(){
		return prestamoDao.listar();
	}
	public boolean actualizar(prestamo prestamo){
		return prestamoDao.actualizar(prestamo);
	}
	/*** Listar por cliente***/
	public List<prestamo> listarXcliente(int cliente){
		return prestamoDao.listarXcliente(cliente);
	}
}
