package negocio;

import java.util.List;

import datos.nacionalidadDao;
import entidad.nacionalidad;

public class nacionalidadNeg {
	nacionalidadDao dao = new nacionalidadDao();
	
	public List<nacionalidad> listarNacionalidades(){
		return dao.listarNacionalidades();
	}
}
