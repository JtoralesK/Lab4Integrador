package negocio;

import java.util.List;

import datos.PrestamoDao;
import entidad.prestamo;

public class prestamoNeg {
	PrestamoDao prestamoDao = new PrestamoDao();
	
	public List<prestamo> listar(){
		return prestamoDao.listar();
	}
}
