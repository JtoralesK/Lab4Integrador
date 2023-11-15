package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import entidad.eEstadoPrestamo;
import entidad.prestamo;

public class PrestamoDao {
	private conexion cn;
	
	public boolean insertar(prestamo prestamo)
	{
		boolean estado=true;

		cn = new conexion();
		Connection connection  = cn.Open();	
		String query = "INSERT INTO prestamos (n_cuenta, id_cliente, importe, fecha_solicitud, id_estado, plazo, fecha_revision)"
				+ "VALUES (?,?,?,?,?,?,?)";
		
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {		
            preparedStatement.setLong(1, prestamo.getIdCuenta());
            preparedStatement.setLong(2, prestamo.getIdCliente());
            preparedStatement.setFloat(3, prestamo.getImporte());
            preparedStatement.setDate(4, java.sql.Date.valueOf(LocalDate.now()));
            preparedStatement.setInt(5, eEstadoPrestamo.Pendiente.ordinal() + 1);
            preparedStatement.setInt(6, prestamo.getPlazo());
            preparedStatement.setNull(7, java.sql.Types.NULL);

			estado=cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}
	
	public List<prestamo> listar()
	{
		List<prestamo> prestamos = new ArrayList<>();
		
		cn = new conexion();
		Connection connection  = cn.Open();	
		
		String query = "SELECT P.id_prestamo, P.n_cuenta, P.id_cliente, P.importe, P.fecha_solicitud, P.id_estado, P.plazo, P.fecha_revision"
				+ ", P.interes, COUNT(PP.cuota) AS cuotasPagas " + 
				"FROM prestamos P " + 
				"INNER JOIN pagos_prestamos PP ON P.id_prestamo = PP.id_prestamo " + 
				"GROUP BY  P.id_prestamo, P.n_cuenta, P.id_cliente, P.importe, P.fecha_solicitud, P.id_estado, P.plazo, P.fecha_revision; ";
		
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) 
        {		
            ResultSet rs = preparedStatement.executeQuery();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            while (rs.next()) {
            	prestamo prestamo = new prestamo(
            			rs.getLong("id_cliente"),
            			rs.getLong("n_cuenta"),
            			rs.getFloat("importe"),
            			LocalDate.parse(rs.getString("fecha_solicitud"), formatter),
            			eEstadoPrestamo.values()[rs.getInt("id_estado") - 1],
            			rs.getInt("plazo"),
            			LocalDate.parse(rs.getString("fecha_Revision"), formatter)
        			);
            	prestamo.setId(rs.getLong("id_prestamo"));
            	prestamo.setCuotasPagas(rs.getInt("cuotasPagas"));
            	prestamo.setInteres(rs.getFloat("interes"));
            	
            	prestamos.add(prestamo);
            }
            
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return prestamos;
	}
	
	public boolean actualizar(prestamo prestamo)
	{
		boolean estado=true;

		cn = new conexion();
		cn.Open();	
		String query = "UPDATE prestamos SET id_estado = "+ (prestamo.getEstadoPrestamo().ordinal() + 1) + " WHERE id_prestamo = " + prestamo.getId();
		
        try  
        {		
			estado=cn.execute(query);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			cn.close();
		}
		
		return estado;
	}
}
