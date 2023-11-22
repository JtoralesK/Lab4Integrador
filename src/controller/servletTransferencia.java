	package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.Usuario;
import entidad.cliente;
import entidad.cuenta;
import entidad.eTipoMovimiento;
import entidad.movimiento;
import excepciones.ArgumentoInvalidoException;
import excepciones.CbuIncorrecto;
import excepciones.ErrorGenerico;
import negocio.clienteNeg;
import negocio.cuentaNeg;
import negocio.movimientosNeg;

/**
 * Servlet implementation class servletTransferencia
 */
@WebServlet("/servletTransferencia")
public class servletTransferencia extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletTransferencia() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Usuario loggedUser = (Usuario)request.getSession().getAttribute("loggedUser");
			cliente cliente = new clienteNeg().obtenerClientePorIdUsuario(loggedUser.getId());
			List<cuenta> cuentas = new cuentaNeg().selectAllByOneClientId(cliente.getId());
			List<movimiento> lista = new movimientosNeg().listarMovimientosPorIdClienteYTipo(cliente.getId(), eTipoMovimiento.Transferencia);
			Collections.reverse(lista);
			Collections.sort(lista, (m1, m2) -> m2.getFecha().compareTo(m1.getFecha()));
			request.setAttribute("lista", lista);
			request.setAttribute("cuentas", cuentas);
			request.getRequestDispatcher("/views/transferencia.jsp").forward(request, response);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("cancelar") != null) {
			doGet(request, response);			
		}

		if(request.getParameter("btnConfirmTransferencia") != null) {
			try{
				String concepto = request.getParameter("mensaje") != null ? request.getParameter("mensaje") : "";
				Long idCuentaOrigen = Long.parseLong(request.getParameter("origen"));
				String cbu = request.getParameter("destino");
				double importe = Double.parseDouble(request.getParameter("importe"));
				transferir(idCuentaOrigen, cbu, importe, concepto);
				request.setAttribute("texto", "Transferencia realizada correctamente");
				request.setAttribute("modal", true);
			}catch (Exception e) {
				request.setAttribute("texto", e.getMessage());
				request.setAttribute("modal", true);
			}
			finally {
				doGet(request, response);
			}
		}
	}
	
	private boolean transferir(Long idCuentaOrigen, String cbu, double importe, String concepto) {
		
		cuentaNeg cuentaNeg = new cuentaNeg();
		movimiento movimiento = new movimiento();
		movimientosNeg movNeg = new movimientosNeg();
		movimiento.setFecha(LocalDate.now());
		movimiento.setHora(LocalTime.now());
		movimiento.setConcepto(concepto);
		movimiento.setTipoMovimiento(eTipoMovimiento.Transferencia);
		cuenta cuentaOrigen = cuentaNeg.buscarPorIdCuenta(idCuentaOrigen);
		cuenta cuentaDestino = cuentaNeg.buscarPorCbu(cbu);
		cliente clienteDestino = new clienteNeg().obtenerCliente(cuentaDestino.getId_cliente());
		if(!clienteDestino.getEstado())throw new CbuIncorrecto();
		if(cuentaOrigen.getId_cuenta().equals(cuentaDestino.getId_cuenta())) throw new ArgumentoInvalidoException("No se puede transferir a la misma cuenta de origen");
		if(cuentaDestino == null || !cuentaDestino.isEstado()) throw new CbuIncorrecto();
		movimiento.setN_cuenta(idCuentaOrigen);
		movimiento.setId_cliente(cuentaOrigen.getId_cliente());
		movimiento.setImporte(importe*-1);
		if(movNeg.nuevoMovimiento(movimiento) == -1) throw new ErrorGenerico();
		//seteo cuenta destino
		movimiento.setN_cuenta(cuentaDestino.getId_cuenta());
		movimiento.setId_cliente(cuentaDestino.getId_cliente());
		movimiento.setImporte(importe);
		if(movNeg.nuevoMovimiento(movimiento) == -1) throw new ErrorGenerico();
		return true;
	}
}
