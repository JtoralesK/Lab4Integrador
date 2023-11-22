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
				cuenta cta = mapResultSetToCuenta(rs);
				cuentas.add(cta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuentas;
	}

	public List<cuenta> selectAllByTypeOf(eTipoCuenta e, String cbu, String cliente , Boolean estado) {
		// TODO Auto-generated method stub
		List<cuenta> cuentas = null;
		if (e != null || !cbu.isEmpty() || estado != null || !cliente.isEmpty()) {
			cuentas = new ArrayList<>();
			Connection connection = cn.Open();
			String query = "SELECT * FROM cuentas WHERE 1 = 1 ";

			if (e != null) {
				query += " AND id_tipo_cuenta = ?";
			}
			
			if (!cliente.isEmpty()) {
				query += " AND id_cliente = ?";
			}
			
			if (!cbu.isEmpty()) {
				query += " AND cbu LIKE ?";
			}
			
			if (estado != null) {
				query += " AND estado = ?";
			}
			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				int paramIndex = 1;
				if (e != null) {
					preparedStatement.setInt(paramIndex++, e == eTipoCuenta.CajaDeAhorro ? 1 : 2);
				}

				if (!cliente.isEmpty()) {
					preparedStatement.setLong(paramIndex++, Long.parseLong(cliente));
				}
				
				if (!cbu.isEmpty()) {
					preparedStatement.setString(paramIndex++,"%"+ cbu + "%");
				}
								
				if (estado != null) {
					preparedStatement.setBoolean(paramIndex, estado);
				}
				ResultSet rs = preparedStatement.executeQuery();

				while (rs.next()) {
					cuenta cta = mapResultSetToCuenta(rs);
					cuentas.add(cta);
				}
			} catch (SQLException err) {
				err.printStackTrace();
			} finally {
				cn.close();
			}
		}

		return cuentas;
	}

	public List<cuenta> selectAllByOneUserId(int idUsuario) {
		List<cuenta> cuentas = new ArrayList<>();
		Connection connection = cn.Open();
		String query = "select n_cuenta,cuentas.id_cliente,id_tipo_cuenta,saldo,fecha_creacion,cbu,cuentas.estado from usuarios "
				+ "inner join clientes on usuarios.id_usuario = clientes.id_usuario "
				+ "inner join cuentas on clientes.id_cliente = cuentas.id_cliente where clientes.id_usuario = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, idUsuario);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				cuenta cta = mapResultSetToCuenta(rs);
				cuentas.add(cta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuentas;
	}
	
	public List<cuenta> selectAllByOneClientId(Long idCliente, boolean estado){
		List<cuenta> cuentas = new ArrayList<>();
		Connection connection = cn.Open();
		String query = "select n_cuenta,id_cliente,id_tipo_cuenta,saldo,fecha_creacion,cbu,estado from cuentas "
				+ "WHERE id_cliente = ? AND estado = ?;";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, idCliente);
			preparedStatement.setBoolean(2, estado);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				cuenta cta = mapResultSetToCuenta(rs);
				cuentas.add(cta);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuentas;
	}
	
	public cuenta buscarPorIdCuenta(Long idCuenta) {
		cuenta cuenta = new cuenta();
		Connection connection = cn.Open();
		String query = "select n_cuenta,id_cliente,id_tipo_cuenta,saldo,fecha_creacion,cbu,estado from cuentas "
				+ "WHERE n_cuenta = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, idCuenta);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				cuenta = mapResultSetToCuenta(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuenta;
	}
	
	public cuenta buscarPorCbu(String cbu) {
		cuenta cuenta = new cuenta();
		Connection connection = cn.Open();
		String query = "select n_cuenta,id_cliente,id_tipo_cuenta,saldo,fecha_creacion,cbu,estado from cuentas "
				+ "WHERE cbu = ?";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, cbu);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				cuenta = mapResultSetToCuenta(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			cn.close();
		}
		return cuenta;
	}
	
	public boolean modificarSaldo(Long idCuenta,double saldo) {
		boolean estado = true;

		try (Connection connection = cn.Open();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE cuentas SET saldo = ? WHERE n_cuenta = ?")) {
			preparedStatement.setDouble(1, saldo);
			preparedStatement.setLong(2, idCuenta);

			estado = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			estado = false;
		}finally {
			cn.close();
		}

		return estado;
	}
	
	public boolean altaCuenta(cuenta cta) {
		boolean estado = true;
		Connection connection = cn.Open();

		String query = "INSERT INTO cuentas (n_cuenta, id_cliente, id_tipo_cuenta, saldo, fecha_creacion, cbu, estado) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)";

		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setLong(1, cta.getId_cuenta());
			preparedStatement.setLong(2, cta.getId_cliente());
			preparedStatement.setInt(3, cta.tipoCuenta().ordinal() + 1);
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


	public Boolean updateRegisterState(int n_cuenta, Long id_Cliente,boolean stateToChange) {
		boolean estado = true;

		try (Connection connection = cn.Open();
				PreparedStatement preparedStatement = connection
						.prepareStatement("UPDATE cuentas SET estado = ? WHERE n_cuenta = ? AND id_cliente = ?")) {
			preparedStatement.setBoolean(1, stateToChange);
			preparedStatement.setInt(2, n_cuenta);
			preparedStatement.setLong(3, id_Cliente);

			estado = preparedStatement.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
			estado = false;
		}finally {
			cn.close();
		}

		return estado;
	}

	private cuenta mapResultSetToCuenta(ResultSet rs) throws SQLException {
		cuenta cta = new cuenta();
		cta.setId_cuenta(Long.parseLong(rs.getString("n_cuenta")));
		cta.setId_cliente(Long.parseLong(rs.getString("id_cliente")));
		int tipoCuentaOrdinal = rs.getInt("id_tipo_cuenta") - 1;
		cta.setId_tipo_cuenta(eTipoCuenta.values()[tipoCuentaOrdinal]);
		cta.setSaldo(Double.valueOf(rs.getString("saldo")));
		LocalDate fechaRegistro = rs.getDate("fecha_creacion").toLocalDate();
		cta.setFecha_creacion(fechaRegistro);
		cta.setCbu(rs.getString("cbu"));
		cta.setEstado(rs.getBoolean("estado"));
		return cta;
	}
	
}
