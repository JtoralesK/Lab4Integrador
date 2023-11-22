package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidad.cliente;
import entidad.cuenta;
import entidad.eEstadoPrestamo;
import entidad.eTipoMovimiento;
import entidad.movimiento;
import entidad.prestamo;
import excepciones.ArgumentoInvalidoException;
import negocio.cuentaNeg;
import negocio.movimientosNeg;
import negocio.prestamoNeg;

/**
 * Servlet implementation class servletCuentasClientes
 */
@WebServlet("/servletCuentasClientes")
public class servletCuentasClientes extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public servletCuentasClientes() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		cliente cliente = (cliente)request.getSession().getAttribute("loggedCliente");
		List<cuenta> cuentas = new cuentaNeg().selectAllByOneClientId(cliente.getId());
		List<movimiento>ListaMovimientos = new movimientosNeg().listarMovimientosPorIdCliente(cliente.getId());
		Collections.sort(ListaMovimientos, Comparator.comparing(movimiento::getFechaYHora).reversed());
		List<prestamo>ListaPrestamos = new prestamoNeg().listarXcliente(cliente.getId());
		Collections.sort(ListaPrestamos, Comparator.comparing(prestamo::getFechaSolicitud).reversed());
		HttpSession session = request.getSession();
		
		try
		{
			if (request.getParameter("btnFiltrarPrestamos") != null)
			{
				filtrarPrestamos(request, ListaPrestamos);						
			}
			if (request.getParameter("btnFiltrarMovimientos") != null)
			{
				filtrarMovimientos(request, ListaMovimientos);
			}
		}
		catch (ArgumentoInvalidoException e)
		{
			request.setAttribute("texto", e.getMessage());
			request.setAttribute("modal", true);
		}
		
		session.setAttribute("cuentasLoggedCliente", cuentas);
		session.setAttribute("lista", ListaMovimientos);
		session.setAttribute("lista2", ListaPrestamos);
		session.setAttribute("idCuenta", -1);

		RequestDispatcher dr = request.getRequestDispatcher("/servletPaginacion?redirectUrl=cuenta.jsp");	
		dr.forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("IdCuenta") != null)
		{
			Long idCuenta = Long.parseLong(request.getParameter("IdCuenta"));			
			cliente cliente = (cliente)request.getSession().getAttribute("loggedCliente");
			List<cuenta> cuentas = new cuentaNeg().selectAllByOneClientId(cliente.getId());
			boolean existeCuenta = false;
			for (cuenta cuenta : cuentas)
			{
				if (cuenta.getId_cuenta() == idCuenta)
				{
					existeCuenta = true;
				}
			}
			
			if (existeCuenta)
			{
				List<movimiento>ListaMovimientos = new movimientosNeg().listarMovimientosPorIdCuenta(idCuenta);
				Collections.sort(ListaMovimientos, Comparator.comparing(movimiento::getFechaYHora).reversed());				
				List<prestamo>ListaPrestamos = new prestamoNeg().listarXcuenta(idCuenta);
				Collections.sort(ListaPrestamos, Comparator.comparing(prestamo::getFechaSolicitud).reversed());
				
				HttpSession session = request.getSession();
				
				try
				{
					if (request.getParameter("btnFiltrarPrestamos") != null)
					{
						filtrarPrestamos(request, ListaPrestamos);						
					}
					if (request.getParameter("btnFiltrarMovimientos") != null)
					{
						filtrarMovimientos(request, ListaMovimientos);
					}
				}
				catch (ArgumentoInvalidoException e)
				{
					request.setAttribute("texto", e.getMessage());
					request.setAttribute("modal", true);
				}
				
				session.setAttribute("cuentasLoggedCliente", cuentas);
				session.setAttribute("lista", ListaMovimientos);
				session.setAttribute("lista2", ListaPrestamos);
				session.setAttribute("idCuenta", idCuenta);
				
				RequestDispatcher dr = request.getRequestDispatcher("/servletPaginacion?redirectUrl=cuenta.jsp");	
				dr.forward(request, response);
				
				return;
			}			
		}
		
		doGet(request,response);
	}
	
	private void filtrarMovimientos(HttpServletRequest request, List<movimiento> movimientos)
	{
		if (!request.getParameter("filtroFechaDesde").isEmpty() && !request.getParameter("filtroFechaHasta").isEmpty())
		{
			LocalDate fechaDesde = LocalDate.parse(request.getParameter("filtroFechaDesde"));
			LocalDate fechaHasta = LocalDate.parse(request.getParameter("filtroFechaHasta"));
			if (fechaDesde.isAfter(fechaHasta))
			{
				throw new ArgumentoInvalidoException("La Fecha Desde no puede ser posterior a la Fecha Hasta");	
			}
		}
		
		if (!request.getParameter("filtroFechaDesde").isEmpty())
		{
			LocalDate fechaDesde = LocalDate.parse(request.getParameter("filtroFechaDesde"));
			movimientos.removeIf(x -> x.getFecha().isBefore(fechaDesde));
		}
		if (!request.getParameter("filtroFechaHasta").isEmpty())
		{
			LocalDate fechaHasta = LocalDate.parse(request.getParameter("filtroFechaHasta"));
			movimientos.removeIf(x -> x.getFecha().isAfter(fechaHasta));
		}
		if (request.getParameter("filtroMovimiento") != null)
		{
			int idMovimiento = Integer.parseInt(request.getParameter("filtroMovimiento"));
			if (idMovimiento > -1)
			{
				eTipoMovimiento movimiento = eTipoMovimiento.values()[idMovimiento];
				movimientos.removeIf(x -> x.getTipoMovimiento() != movimiento);
			}
		}
	}
	private void filtrarPrestamos(HttpServletRequest request, List<prestamo> prestamos)
	{
		if (!request.getParameter("filtroFechaDesde").isEmpty() && !request.getParameter("filtroFechaHasta").isEmpty())
		{
			LocalDate fechaDesde = LocalDate.parse(request.getParameter("filtroFechaDesde"));
			LocalDate fechaHasta = LocalDate.parse(request.getParameter("filtroFechaHasta"));
			if (fechaDesde.isAfter(fechaHasta))
			{
				throw new ArgumentoInvalidoException("La Fecha Desde no puede ser posterior a la Fecha Hasta");	
			}
		}
				
		if (request.getParameter("filtroSelectFecha") != null)
		{
			boolean fechaSolicitud = request.getParameter("filtroSelectFecha").equals("Fecha Solicitud");	
			
			if (!request.getParameter("filtroFechaDesde").isEmpty())
			{
				LocalDate fechaDesde = LocalDate.parse(request.getParameter("filtroFechaDesde"));
				prestamos.removeIf(x -> fechaSolicitud 
						? x.getFechaSolicitud().isBefore(fechaDesde)
								: x.getFechaRevision().isBefore(fechaDesde));
			}
			if (!request.getParameter("filtroFechaHasta").isEmpty())
			{
				LocalDate fechaHasta = LocalDate.parse(request.getParameter("filtroFechaHasta"));
				prestamos.removeIf(x -> fechaSolicitud 
						? x.getFechaSolicitud().isAfter(fechaHasta)
								: x.getFechaRevision().isAfter(fechaHasta));
			}
		}		
		if (request.getParameter("filtroEstado") != null)
		{
			int idEstado = Integer.parseInt(request.getParameter("filtroEstado"));
			if (idEstado > -1)
			{
				eEstadoPrestamo estado = eEstadoPrestamo.values()[idEstado];
				prestamos.removeIf(x -> x.getEstadoPrestamo() != estado);
			}
		}
	}
}
