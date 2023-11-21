package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import javax.print.attribute.standard.DateTimeAtCompleted;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import datos.PrestamoDao;
import entidad.cuenta;
import entidad.movimiento;
import entidad.prestamo;
import negocio.cuentaNeg;
import negocio.movimientosNeg;

/**
 * Servlet implementation class servletInformeMovimiento
 */
@WebServlet("/servletInformeMovimiento")
public class servletInformeMovimiento extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletInformeMovimiento() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("filterInforme") !=null){
			LocalDate fechaInicio=LocalDate.MIN;
			LocalDate fechaFin=LocalDate.now();
			if(!request.getParameter("fechaFin").isEmpty()) {
				fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
				request.setAttribute("fechaFin",fechaFin);
			}
			if(!request.getParameter("fechaInicio").isEmpty()) {
				fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
				request.setAttribute("fechaInicio",fechaInicio);
			}
			double[] movimientos = getIngresosEgresos(fechaInicio, fechaFin);
			double filtroIngresos = movimientos[0];
			request.setAttribute("filtroIngresos", filtroIngresos);
			double filtroEgresos = movimientos[1];
			request.setAttribute("filtroEgresos", filtroEgresos);
		}
		double indicadorActuales = getTenenciasCuentas();
		request.setAttribute("indicadorActuales", indicadorActuales);
		double indicadorPrestamos = getPrestamosTotalPendiente();
		request.setAttribute("indicadorPrestamos", indicadorPrestamos);
		double[] movimientos = getIngresosEgresos();
		double indicadorIngresos = movimientos[0];
		request.setAttribute("indicadorIngresos", indicadorIngresos);
		double indicadorEgresos = movimientos[1];
		request.setAttribute("indicadorEgresos", indicadorEgresos);
		request.getRequestDispatcher("/views/repMovimientos.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private double getTenenciasCuentas() {
		double total = 0;
		List<cuenta> listadoCuentas = new cuentaNeg().selectAll();
		for(cuenta cuenta : listadoCuentas) {
			if(cuenta.isEstado())total+=cuenta.getSaldo();
		}
		return total;
	}
	private double[] getIngresosEgresos() {
		return getIngresosEgresos(LocalDate.now().minusDays(30), LocalDate.now());
	}
	private double[] getIngresosEgresos(LocalDate fechaInicio, LocalDate fechaFin) {
		double ingresos = 0;
		double egresos = 0;
		List<movimiento> listaMovimientos = new movimientosNeg().listarMovimientos();
		for (movimiento movimiento : listaMovimientos) {
			if(movimiento.getFecha().isAfter(fechaInicio) ||
					movimiento.getFecha().isEqual(fechaInicio) && 
					movimiento.getFecha().isBefore(fechaFin) ||
					movimiento.getFecha().isEqual(fechaFin)) {
				if(movimiento.getImporte()>0) ingresos += movimiento.getImporte();
				else egresos += movimiento.getImporte();
			}
		}
		double[] array = {ingresos,egresos*-1};
		return array;
	}
	
	private double getPrestamosTotalPendiente() {
		double total = 0;
		List<prestamo> listaPrestamos = new PrestamoDao().listar();
		for (prestamo prestamo : listaPrestamos) {
			int plazo = prestamo.getPlazo();
			int cuotasPagas = prestamo.getCuotasPagas();
			int cuotasImpagas = plazo-cuotasPagas;
			double importeCuotas = prestamo.getImporte();
			total += importeCuotas*cuotasImpagas;
		}
		return total;
	}
}
