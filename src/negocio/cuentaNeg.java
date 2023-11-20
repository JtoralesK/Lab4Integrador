package negocio;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import datos.cuentaDao;
import entidad.cuenta;
import entidad.eTipoCuenta;
import entidad.eTipoMovimiento;
import entidad.movimiento;
import excepciones.SaldoInsuficienteException;

public class cuentaNeg {
	
	cuentaDao cuentaDao = new cuentaDao();
	
	public List<cuenta> selectAll() { 
		return cuentaDao.selectAll();
	}
	
	public List<cuenta> selectAllByTypeOf(eTipoCuenta e, String prefix) {
		return cuentaDao.selectAllByTypeOf(e, prefix);
	}
	
	public List<cuenta> selectAllByOneUserId(int idUsuario) {
		return cuentaDao.selectAllByOneUserId(idUsuario);
	}
	public List<cuenta> selectAllByOneClientId(Long idCliente) {
		return cuentaDao.selectAllByOneClientId(idCliente);
	}
	public Boolean updateRegisterState(int n_cuenta, Long id_Cliente, boolean stateToChange) {
		return cuentaDao.updateRegisterState(n_cuenta, id_Cliente,stateToChange);
	}
	
	public cuenta buscarPorIdCuenta(int idCuenta) {
		return cuentaDao.buscarPorIdCuenta(idCuenta);
	}
	
	public cuenta buscarPorCbu(String cbu) {
		return cuentaDao.buscarPorCbu(cbu);
	}
	
	public boolean altaCuenta(Long idCliente,eTipoCuenta tipoCuenta) {
		boolean estado= false;
		cuenta cuenta = new cuenta();
		int cantCuentas = selectAllByOneClientId(idCliente).size();
		int tipo = tipoCuenta == eTipoCuenta.CajaDeAhorro ? 10 : 20;
		String stringCuenta = "12" + Long.toString(idCliente) + Integer.toString(tipo)+ Integer.toString(cantCuentas); 
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
		cuenta.setSaldo(0);
		cuenta.setFecha_creacion(LocalDate.now());
		cuenta.setCbu(sb.toString());
		cuenta.setEstado(true);
		
		if(cuentaDao.altaCuenta(cuenta)) {
			new movimientosNeg().nuevoMovimiento(new movimiento(
					cuenta.getId_cuenta(),
					cuenta.getId_cliente(),
					eTipoMovimiento.AltaCuenta,
					LocalDate.now(),
					LocalTime.now(),
					10000.00
					));
			estado=true;
		}
		return estado;
	}
	
	public boolean modificarSaldo(int idCuenta, double importe) {
		double saldo = buscarPorIdCuenta(idCuenta).getSaldo();
		saldo += importe;
		if(saldo<0) throw new SaldoInsuficienteException();
		return new cuentaDao().modificarSaldo(idCuenta, saldo);
	}
}
