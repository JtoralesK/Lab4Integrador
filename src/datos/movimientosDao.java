package datos;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.movimiento;
import entidad.eTipoMovimiento;



public class movimientosDao {
	private conexion cn;
	 public movimientosDao() {
	        cn = new conexion();
	    }
	
	 public boolean insertar(movimiento movimiento) {
	        boolean estado = true;
	        cn.Open();

	        String query = "INSERT INTO movimientos(n_cuenta,id_cliente,id_tipo_movimiento,fecha,hora,importe,concepto) " +
	                "VALUES ('" + movimiento.getN_cuenta() + "', '" + movimiento.getId_cliente() + "', " + movimiento.getTipoMovimiento().ordinal()+1 + ","+ movimiento.getFecha()+","+movimiento.getHora()+","+movimiento.getImporte()+","+movimiento.getConcepto()+")";
	        try {
	            estado = cn.execute(query);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return estado;
	    }
	 
	    public List<movimiento> listarMovimientos() {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos";

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getInt("n_cuenta"),
	            			rs.getInt("id_cliente"),
	            			eTipoMovimiento.values()[rs.getInt("id_tipo_movimiento")-1],
	            			rs.getDate("fecha").toLocalDate(),
	            			rs.getTime("hora").toLocalTime(),
	            			rs.getDouble("importe"),
	            			rs.getString("concepto")
            			);
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    /****Listar por clientes****/
	    public List<movimiento> listarMovimientosPorIdCliente(int idCliente) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getInt("n_cuenta"),
	            			rs.getInt("id_cliente"),
	            			eTipoMovimiento.values()[rs.getInt("id_tipo_movimiento")-1],
	            			rs.getDate("fecha").toLocalDate(),
	            			rs.getTime("hora").toLocalTime(),
	            			rs.getDouble("importe"),
	            			rs.getString("concepto")
            			);
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
	    /****Listar por fecha****/
	    public List<movimiento> listarMovimientosPorIdClienteYFecha(int idCliente,Date fecha) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos whwre fecha="+fecha+"&& id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getInt("n_cuenta"),
	            			rs.getInt("id_cliente"),
	            			eTipoMovimiento.values()[rs.getInt("id_tipo_movimiento")-1],
	            			rs.getDate("fecha").toLocalDate(),
	            			rs.getTime("hora").toLocalTime(),
	            			rs.getDouble("importe"),
	            			rs.getString("concepto")
            			);
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
	    /****Listar por tipos movimietos ****/
	    public List<movimiento> listarMovimientosPorIdClienteYTipo(int idCliente,eTipoMovimiento tipoMovimiento) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();
	        int idTipoMovimiento = tipoMovimiento.ordinal() + 1;
	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+idTipoMovimiento+"&& id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getInt("n_cuenta"),
	            			rs.getInt("id_cliente"),
	            			eTipoMovimiento.values()[rs.getInt("id_tipo_movimiento")-1],
	            			rs.getDate("fecha").toLocalDate(),
	            			rs.getTime("hora").toLocalTime(),
	            			rs.getDouble("importe"),
	            			rs.getString("concepto")
            			);;
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
	    /****Listar X tipos movimietos X fecha ****/
	   
	    public List<movimiento> listarMovimientosPorTipoYFecha(int idCliente,eTipoMovimiento tipoMovimiento
	    		,Date fecha) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();
	        int idTipoMovimiento = tipoMovimiento.ordinal() + 1;
	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+idTipoMovimiento+"&& id_cliente="+idCliente+"&& fecha="+fecha;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getInt("n_cuenta"),
	            			rs.getInt("id_cliente"),
	            			eTipoMovimiento.values()[rs.getInt("id_tipo_movimiento")-1],
	            			rs.getDate("fecha").toLocalDate(),
	            			rs.getTime("hora").toLocalTime(),
	            			rs.getDouble("importe"),
	            			rs.getString("concepto")
            			);;
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    /****Saca el importe segun el tipo de movimiento  ****/
	    public Double getImportePorClienteYTipo(int idCliente,eTipoMovimiento tipoMovimiento) {
	        cn.Open();
	    	Double dinero = 0.00;
	    	int idTipoMovimiento = tipoMovimiento.ordinal()+1;
	        String query = "select sum(importe) FROM movimientos where id_tipo_movimiento="+idTipoMovimiento+"&& id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	return dinero = rs.getDouble("importe");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return dinero;
	    }
	    
	    public Double getImportePorTipoMovimiento(eTipoMovimiento tipoMovimiento) {
	        cn.Open();
	    	Double dinero = 0.00;
	    	int idTipoMovimiento = tipoMovimiento.ordinal()+1;
	        String query = "select sum(importe) FROM movimientos where id_tipo_movimiento="+idTipoMovimiento;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	return dinero = rs.getDouble("importe");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return dinero;
	    }
	    
}