package datos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.localidad;
import entidad.provincia;

public class localidadDao {
	private conexion cn;

    public localidadDao() {
        cn = new conexion();
    }
    public List<localidad> listarLocalidades() {
        List<localidad> localidades = new ArrayList<>();
        cn.Open();

        String query = "SELECT id_localidad, id_provincia, localidad FROM localidades";

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
            	localidad localidad = new localidad(rs.getInt("id_provincia"), rs.getString("localidad"), new provincia(rs.getInt("id_provincia")));
                localidades.add(localidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return localidades;
    }
}
