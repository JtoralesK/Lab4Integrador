package datos;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import entidad.cuenta;
import entidad.eTipoCuenta;

public class cuentaDao {
	private conexion cn;

	public cuentaDao() {
		cn = new conexion();
	}

	public List<cuenta> selectAll() {
		// TODO Auto-generated method stub
		List<cuenta> cuentas = new ArrayList<>();
		Connection connection = cn.Open();
		String query = "SELECT * FROM cuentas";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				cuenta cta = new cuenta();
				cta.setId_cuenta(Integer.parseInt(rs.getString("n_cuenta")));
				cta.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
				int tipoCuentaOrdinal = rs.getInt("id_tipo_cuenta") - 1;
				cta.setId_tipo_cuenta(eTipoCuenta.values()[tipoCuentaOrdinal]);
				cta.setSaldo(Double.valueOf(rs.getString("saldo")));
				LocalDate fechaRegistro = rs.getDate("fecha_creacion").toLocalDate();
				cta.setFecha_creacion(fechaRegistro);
				cta.setCbu(rs.getString("cbu"));
				cuentas.add(cta);
				cta.setEstado(rs.getBoolean("estado"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuentas;
	}
	public List<cuenta> selectAllByTypeOf(eTipoCuenta e) {
		// TODO Auto-generated method stub
		List<cuenta> cuentas = new ArrayList<>();
		Connection connection = cn.Open();
		String query = "SELECT * FROM cuentas WHERE id_tipo_cuenta = ?";

	      try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	            preparedStatement.setInt(1,e==eTipoCuenta.CajaDeAhorro?1:2);
	            ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cuenta cta = new cuenta();
				cta.setId_cuenta(Integer.parseInt(rs.getString("n_cuenta")));
				cta.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
				int tipoCuentaOrdinal = rs.getInt("id_tipo_cuenta") - 1;
				cta.setId_tipo_cuenta(eTipoCuenta.values()[tipoCuentaOrdinal]);
				cta.setSaldo(Double.valueOf(rs.getString("saldo")));
				LocalDate fechaRegistro = rs.getDate("fecha_creacion").toLocalDate();
				cta.setFecha_creacion(fechaRegistro);
				cta.setCbu(rs.getString("cbu"));
				cuentas.add(cta);
				cta.setEstado(rs.getBoolean("estado"));
			}
		} catch (SQLException err) {
			err.printStackTrace();
		} finally {
			cn.close();
		}
		return cuentas;
	}
	public boolean insert(cuenta cta) {
	    boolean estado = true;
	    Connection connection = cn.Open();

	    String query = "INSERT INTO cuentas (n_cuenta, id_cliente, id_tipo_cuenta, saldo, fecha_creacion, cbu, estado) " +
	            "VALUES (?, ?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
	        preparedStatement.setInt(1, cta.getId_cuenta());
	        preparedStatement.setInt(2, cta.getId_cliente());
	        preparedStatement.setInt(3, cta.getId_tipo_cuenta().ordinal() + 1);
	        preparedStatement.setDouble(4, cta.getSaldo());
	        preparedStatement.setDate(5, java.sql.Date.valueOf(cta.getFecha_creacion()));
	        preparedStatement.setString(6, cta.getCbu());
	        preparedStatement.setBoolean(7, cta.isEstado());

	        estado = preparedStatement.executeUpdate() > 0;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        estado = false;
	    } finally {
	        cn.close();
	    }

	    return estado;
	}

	public Boolean logicalDelete(int n_cuenta, int id_Cliente) {
		 boolean estado = true;

		    try (Connection connection = cn.Open();
		         PreparedStatement preparedStatement = connection.prepareStatement("UPDATE cuentas SET estado = 0 WHERE n_cuenta = ? AND id_cliente = ?")) {

		        preparedStatement.setInt(1, n_cuenta);
		        preparedStatement.setInt(2, id_Cliente);

		        estado = preparedStatement.executeUpdate() > 0;

		    } catch (SQLException e) {
		        e.printStackTrace();
		        estado = false; 
		    }

		    return estado;
	}
}
