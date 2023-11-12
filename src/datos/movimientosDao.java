package datos;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import entidad.movimientos;
import entidad.tipo_movimientos;



public class movimientosDao {
	private conexion cn;
	 public movimientosDao() {
	        cn = new conexion();
	    }
	
	 public boolean insertar(movimientos movimientos) {
	        boolean estado = true;
	        cn.Open();

	        String query = "INSERT INTO movimientos(n_cuenta,id_cliente,id_tipo_movimiento,fecha,hora,importe) " +
	                "VALUES ('" + movimientos.getN_cuenta() + "', '" + movimientos.getId_cliente() + "', " + movimientos.getId_tipo_movimiento().getId_tipo_movimiento() + ","+ movimientos.getFecha()+","+movimientos.getHora()+","+movimientos.getImporte()+")";
	        System.out.println(query);
	        try {
	            estado = cn.execute(query);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return estado;
	    }
	 
	    public List<movimientos> listarmovimientos() {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos";

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimientos movimiento = new movimientos(rs.getInt("id_movimiento"),rs.getInt("n_cuenta"),rs.getInt("id_cliente"),new tipo_movimientos(rs.getInt("id_tipo_movimiento")),rs.getDate("fecha"),rs.getTime("hora"),rs.getDouble("importe"), rs.getString("concepto"));
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
	    public List<movimientos> listarmoxfecha(Date fecha) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos whwre fecha="+fecha;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimientos movimiento = new movimientos(rs.getInt("id_movimiento"),rs.getInt("n_cuenta"),rs.getInt("id_cliente"),new tipo_movimientos(rs.getInt("id_tipo_movimiento")),rs.getDate("fecha"),rs.getTime("hora"),rs.getDouble("importe"), rs.getString("concepto"));
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
	    
	    public List<movimientos> listarmovimientosxtipovimientos(int id_tipo_movimiento) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+id_tipo_movimiento;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimientos movimiento = new movimientos(rs.getInt("id_movimiento"),rs.getInt("n_cuenta"),rs.getInt("id_cliente"),new tipo_movimientos(rs.getInt("id_tipo_movimiento")),rs.getDate("fecha"),rs.getTime("hora"),rs.getDouble("importe"), rs.getString("concepto"));
	            	movimientos.add(movimiento);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return movimientos;
	    }
	    
}
