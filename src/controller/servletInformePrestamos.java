package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidad.eEstadoPrestamo;
import entidad.prestamo;
import negocio.prestamoNeg;


/**
 * Servlet implementation class servletInforme
 */
@WebServlet("/servletInformePrestamos")
public class servletInformePrestamos extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletInformePrestamos() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("filterInforme")!= null) {
			LocalDate fechaFin=LocalDate.now();
			LocalDate fechaInicio=LocalDate.MIN;
			eEstadoPrestamo estado = eEstadoPrestamo.values()[Integer.parseInt(request.getParameter("estado"))];
			request.setAttribute("estado", estado);
			if(!request.getParameter("fechaFin").isEmpty()) {
				fechaFin = LocalDate.parse(request.getParameter("fechaFin"));
				request.setAttribute("fechaFin",fechaFin);
			}
			if(!request.getParameter("fechaInicio").isEmpty()) {
				fechaInicio = LocalDate.parse(request.getParameter("fechaInicio"));
				request.setAttribute("fechaInicio",fechaInicio);
			}
			double importeTotal = getImportePrestamosFiltrados(fechaInicio, fechaFin, estado);
			System.out.println(importeTotal);
			request.setAttribute("importeTotal", importeTotal);
		}
		float indicadorPendientes = getImporteTotalPendiente();
		request.setAttribute("indicadorPendientes", indicadorPendientes);
		int aprobados30 = getCantidad30dias(eEstadoPrestamo.Aprobado);
		request.setAttribute("indicadorAprobados", aprobados30);
		float indicadorImporte30 = getImportePrestamosFiltrados(LocalDate.now().minusDays(30),LocalDate.now(), eEstadoPrestamo.Aprobado);
		request.setAttribute("indicadorImporte", indicadorImporte30);
		int rechazados30 = getCantidad30dias(eEstadoPrestamo.Rechazado);
		request.setAttribute("indicadorRechazados", rechazados30);
		request.getRequestDispatcher("/views/repPrestamos.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private float getImportePrestamosFiltrados(LocalDate fechaInicio,LocalDate fechaFin, eEstadoPrestamo estado) {
		List<prestamo> listaPrestamos = new prestamoNeg().listar();
		float importeTotal = 0;
		for(prestamo prestamo : listaPrestamos) {
			if(prestamo.getFechaRevision().isAfter(fechaInicio) &&
				prestamo.getFechaRevision().isBefore(fechaFin) &&
				prestamo.getEstadoPrestamo() == estado) {
					importeTotal += prestamo.getImporte();
			}
		}
		return importeTotal;
	}
	
	private float getImporteTotalPendiente() {
		float total = 0;
		List<prestamo> listaPrestamos = new prestamoNeg().listar();
		for (prestamo prestamo : listaPrestamos) {
			int plazo = prestamo.getPlazo();
			int cuotasPagas = prestamo.getCuotasPagas();
			int cuotasImpagas = plazo-cuotasPagas;
			float importeCuotas = prestamo.getImporte();
			total += importeCuotas*cuotasImpagas;
		}
		return total;
	}
	
	private int getCantidad30dias(eEstadoPrestamo estado) {
		int cantidad = 0;
		LocalDate dias30 = LocalDate.now().minusDays(30);
		List<prestamo> listaPrestamos = new prestamoNeg().listar();
		for (prestamo prestamo : listaPrestamos) {
			if(prestamo.getFechaSolicitud().isAfter(dias30) &&
				prestamo.getEstadoPrestamo()==estado) {
				cantidad++;
			}
		}
		return cantidad;
	}
}
