package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entidad.Usuario;
import entidad.cuenta;
import entidad.eTipoCuenta;
import entidad.eTipoUsuario;
public class cuentaDao implements DaoEstructura<cuenta> {
	private conexion cn;

	public cuentaDao() {
		cn = new conexion();
	}
	@Override
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
	    }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            cn.close(); 
        }
        return cuentas;
	}
	@Override
	public Boolean insert() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Boolean logicalDelete() {
		// TODO Auto-generated method stub
		return null;
	}
}
