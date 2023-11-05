package datos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.provincia;

public class provinciaDao {
    private conexion cn;

    public provinciaDao() {
        cn = new conexion();
    }
    public List<provincia> listarProvincias() {
        List<provincia> provincias = new ArrayList<>();
        cn.Open();

        String query = "SELECT id_provincia, provincia FROM provincias";

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
            	provincia provincia = new provincia(rs.getInt("id_provincia"), rs.getString("provincia"));
                provincias.add(provincia);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return provincias;
    }
    public provincia obtenerUno(int idProvincia) {
        cn.Open();

        String query = "SELECT id_provincia, provincia FROM provincias WHERE id_provincia = " + idProvincia;

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
            	return new provincia(rs.getInt("id_provincia"), rs.getString("provincia"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return null;
    }
}
