package datos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

import entidad.movimiento;
import entidad.eTipoMovimiento;



public class movimientosDao {
	private conexion cn;
	 public movimientosDao() {
	        cn = new conexion();
	    }
	
	 public Long insertar(movimiento movimiento) {
		 Long idRegistroInsertado = -1L;
	        
	        Connection connection = cn.Open();
	        
	        String query = "INSERT INTO movimientos(n_cuenta,id_cliente,id_tipo_movimiento,fecha,hora,importe,concepto) " +
	                "VALUES (?, ?, ?, ?, ?, ?, ?)";
	        
	        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
	            preparedStatement.setLong(1, movimiento.getN_cuenta());
	            preparedStatement.setLong(2, movimiento.getId_cliente());
	            preparedStatement.setInt(3, movimiento.getTipoMovimiento().ordinal() + 1); 
	            preparedStatement.setDate(4, java.sql.Date.valueOf(movimiento.getFecha()));
	            preparedStatement.setTime(5, java.sql.Time.valueOf(movimiento.getHora()));
	            preparedStatement.setDouble(6, movimiento.getImporte());
	            preparedStatement.setString(7, movimiento.getConcepto());

	            if (preparedStatement.executeUpdate() == 1)
	            {
	            	try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
	            		if (generatedKeys.next()) {
	            			idRegistroInsertado = generatedKeys.getLong(1);
	            		}
	            	}	            	
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            cn.close();
	        }
	        return idRegistroInsertado;
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
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
	    public List<movimiento> listarMovimientosPorIdCliente(Long idCliente) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
	    /****Listar por cuenta****/
	    public List<movimiento> listarMovimientosPorIdCuenta(Long idCuenta) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();

	        String query = "SELECT * FROM movimientos where n_cuenta="+idCuenta;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
	    public List<movimiento> listarMovimientosPorIdClienteYTipo(Long idCliente,eTipoMovimiento tipoMovimiento) {
	        List<movimiento> movimientos = new ArrayList<>();
	        cn.Open();
	        int idTipoMovimiento = tipoMovimiento.ordinal() + 1;
	        String query = "SELECT * FROM movimientos where id_tipo_movimiento="+idTipoMovimiento+"&& id_cliente="+idCliente;

	        try {
	            ResultSet rs = cn.query(query);
	            while (rs.next()) {
	            	movimiento movimiento = new movimiento(
	            			rs.getInt("id_movimiento"),
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
	            			rs.getLong("n_cuenta"),
	            			rs.getLong("id_cliente"),
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
