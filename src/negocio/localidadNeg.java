package negocio;

import java.util.List;

import datos.localidadDao;
import entidad.localidad;

public class localidadNeg {
	localidadDao dao = new localidadDao();
	
	public List<localidad> listarLocalidades(){
		return dao.listarLocalidades();
	}
}
