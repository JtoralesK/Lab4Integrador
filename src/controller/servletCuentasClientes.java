package controller;

import java.io.IOException;
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
import entidad.movimiento;
import entidad.prestamo;
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
		session.setAttribute("cuentasLoggedCliente", cuentas);
		session.setAttribute("lista", ListaMovimientos);
		session.setAttribute("lista2", ListaPrestamos);
		
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
				session.setAttribute("cuentasLoggedCliente", cuentas);
				session.setAttribute("lista", ListaMovimientos);
				session.setAttribute("lista2", ListaPrestamos);
				
				RequestDispatcher dr = request.getRequestDispatcher("/servletPaginacion?redirectUrl=cuenta.jsp");	
				dr.forward(request, response);
				
				return;
			}			
		}
		
		doGet(request,response);
	}

}
