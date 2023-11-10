package negocio;

import java.util.List;

import datos.provinciaDao;
import entidad.provincia;

public class provinciaNeg {
	provinciaDao dao = new provinciaDao();
	
	public List<provincia> listarProvincias(){
		return dao.listarProvincias();
	}
}
