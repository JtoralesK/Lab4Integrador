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
	public List<cuenta> listarCuentas() {
        List<cuenta> cuentas = new ArrayList<>();
        cn.Open();
        String query = "SELECT * FROM cuentas";

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
                cuenta cta = new cuenta();
                cta.setId_cuenta(Integer.parseInt(rs.getString("id_cuenta")));
                cta.setId_cliente(Integer.parseInt(rs.getString("id_cliente")));
                int tipoCuentaOrdinal = rs.getInt("id_tipo_cuenta") - 1;
                cta.setId_tipo_cuenta(eTipoCuenta.values()[tipoCuentaOrdinal]);
                cta.setSaldo(Double.valueOf(rs.getString("saldo")));
                LocalDate fechaRegistro = rs.getDate("fecha_creacion").toLocalDate();
                cta.setFecha_creacion(fechaRegistro);
                cta.setCbu(rs.getString("cbu"));
                cuentas.add(cta);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return cuentas;
    }
}
