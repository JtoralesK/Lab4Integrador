package datos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.tipo_movimientos;

public class tipo_movimientosDao {
	private conexion cn;
	 public tipo_movimientosDao() {
	        cn = new conexion();
	    }
	    public List<tipo_movimientos> listarTipo_movimientos() {
	        List<tipo_movimientos> tipo_movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM tipo_movimientos";

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	tipo_movimientos tipo_movimiento = new tipo_movimientos(rs.getInt("id_tipo_movimiento"), rs.getString("descripcion"));
	            	tipo_movimientos.add(tipo_movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return tipo_movimientos;
	    }
	    
	    public tipo_movimientos obtenerUno(int id_tipo_movimiento) {
	        cn.Open();

	        String query = "SELECT * FROM tipo_movimientos WHERE id_tipo_movimiento = " + id_tipo_movimiento;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	return new tipo_movimientos(rs.getInt("id_tipo_movimiento"), rs.getString("descripcion"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return null;
	    }
	    
	    
	    public String obtenerxId(int id_tipo_movimiento) {
	        cn.Open();

	        String query = "SELECT descripcion FROM tipo_movimientos WHERE id_tipo_movimiento = " + id_tipo_movimiento;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	return rs.getString("descripcion");
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return null;
	    }
}
