package datos;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	    /****Listar por clientes****/
	    public List<movimientos> listarmovimientosxclientes(int id_cliente) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_cliente="+id_cliente;

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
	    
	    /****Listar por fecha****/
	    public List<movimientos> listarxmovimientosxfecha(int id_cliente,Date fecha) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos whwre fecha="+fecha+"&& id_cliente="+id_cliente;

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
	    
	    /****Listar por tipos movimietos ****/
	    public List<movimientos> listarmovimientosxtipomovimientos(int id_cliente,int id_tipo_movimiento) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+id_tipo_movimiento+"&& id_cliente="+id_cliente;

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
	    
	    /****Listar X tipos movimietos X fecha ****/
	   
	    public List<movimientos> listarmovimientosxtipovimientosxfecha(int id_cliente,int id_tipo_movimiento,Date fecha) {
	        List<movimientos> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+id_tipo_movimiento+"&& id_cliente="+id_cliente+"&& fecha="+fecha;

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
	    /****Saca el importe segun el tipo de movimiento  ****/
	    public Double Dinero(int id_cliente,int id_tipo_movimiento) {
	        cn.Open();
	    	Double dinero = 0.0;
	        String query = "select sum(importe) FROM movimientos where id_tipo_movimiento="+id_tipo_movimiento+"&& id_cliente="+id_cliente;

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
