package negocio;

import java.sql.Date;
import java.util.List;

import datos.movimientosDao;
import datos.tipo_movimientosDao;
import entidad.movimientos;
import entidad.tipo_movimientos;

public class movimientosNeg {

public  List<movimientos> listar_movimientos(int n) 
{
	movimientosDao movimientos = new movimientosDao();
	return movimientos.listarmovimientos();
}

public  List<movimientos> listar_movimientos_cliete(int n) 
{
	movimientosDao movimientos = new movimientosDao();
	return movimientos.listarmovimientosxclientes(n);
}

public List<movimientos> listado_movimientos_clientes(int id_cliente)
{
	return new movimientosDao().listarmovimientosxclientes(id_cliente);
}

public List<movimientos> listar_movimiento_fecha(int id_cliente,Date fecha)
{
	return new movimientosDao().listarxmovimientosxfecha(id_cliente,fecha);
}

public List<movimientos> listado_movimientos_tipovimientos(int id_cliente,int id_tipo_movimiento) 
{
	return new movimientosDao().listarmovimientosxtipomovimientos(id_cliente, id_tipo_movimiento);
}

public List<movimientos> listado_movimientos_tipovimientos_fecha(int id_cliente,int id_tipo_movimiento,Date fecha) 
{
	return new movimientosDao().listarmovimientosxtipovimientosxfecha(id_cliente,id_tipo_movimiento,fecha);

}

public List<tipo_movimientos> listado_TipoMovimientos()
{
	return new tipo_movimientosDao().listarTipo_movimientos();
}

/****Saca el importe segun el tipo de movimiento  ****/

public Double Dinero_Segun_tipomovimiento(int id_cliente,int id_tipo_movimiento)
{
	return new movimientosDao().Dinero(id_cliente, id_tipo_movimiento);
}
}

