package negocio;

import java.time.LocalDate;
import java.util.List;

import datos.cuentaDao;
import entidad.cuenta;
import entidad.eTipoCuenta;

public class cuentaNeg {
	
	cuentaDao cuentaDao = new cuentaDao();
	
	public List<cuenta> selectAll() { 
		return cuentaDao.selectAll();
	}
	
	public List<cuenta> selectAllByTypeOf(eTipoCuenta e, String prefix) {
		return cuentaDao.selectAllByTypeOf(e, prefix);
	}
	
	public List<cuenta> selectAllByOneClient(int id_Cliente) {
		return cuentaDao.selectAllByOneClient(id_Cliente);
	}
	
	public boolean altaCuenta(int idCliente,eTipoCuenta tipoCuenta) {
		cuenta cuenta = new cuenta();
		int cantCuentas = selectAllByOneClient(idCliente).size();
		int tipo = tipoCuenta == eTipoCuenta.CajaDeAhorro ? 10 : 20;
		String stringCuenta = "12" + Integer.toString(idCliente) + Integer.toString(tipo)+ Integer.toString(cantCuentas); 
		int nroCuenta = Integer.parseInt(stringCuenta);
		StringBuilder sb = new StringBuilder("2");
        int cantidadCeros = 22 - 1 - stringCuenta.length();
        for (int i = 0; i < cantidadCeros; i++) {
            sb.append("0");
        }
        sb.append(stringCuenta);
        cuenta.setId_cuenta(nroCuenta);
		cuenta.setId_cliente(idCliente);
		cuenta.setId_tipo_cuenta(tipoCuenta);
		cuenta.setSaldo(10000);
		cuenta.setFecha_creacion(LocalDate.now());
		cuenta.setCbu(sb.toString());
		cuenta.setEstado(true);

		return cuentaDao.altaCuenta(cuenta);
	}
}
