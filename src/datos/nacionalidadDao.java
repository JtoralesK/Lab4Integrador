package datos;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import entidad.nacionalidad;


public class nacionalidadDao {
    private conexion cn;

    public nacionalidadDao() {
        cn = new conexion();
    }
    public List<nacionalidad> listarNacionalidades() {
        List<nacionalidad> nacionalidades = new ArrayList<>();
        cn.Open();

        String query = "SELECT id_nacionalidad, nacionalidad FROM nacionalidades";

        try {
            ResultSet rs = cn.query(query);
            while (rs.next()) {
            	nacionalidad nacionalidad = new nacionalidad(rs.getInt("id_nacionalidad"), rs.getString("nacionalidad"));
                nacionalidades.add(nacionalidad);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            cn.close();
        }
        return nacionalidades;
    }
}
