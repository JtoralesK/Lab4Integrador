package negocio;

import java.util.List;

import datos.tipo_movimientosDao;
import entidad.tipo_movimientos;

public class tipo_movimientosNeg {

	public List<tipo_movimientos> listado_TipoMovimientos()
	{
		return new tipo_movimientosDao().listarTipo_movimientos();
	}
	
	public tipo_movimientos TipoMovimientosXId(int id_tipo_movimiento)
	{
		return new tipo_movimientosDao().obtenerUno(id_tipo_movimiento);
	}
	

	
}
