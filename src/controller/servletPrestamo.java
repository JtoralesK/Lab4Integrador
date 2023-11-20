package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

import negocio.cuentaNeg;
import negocio.prestamoNeg;
import entidad.Usuario;
import entidad.cliente;
import entidad.cuenta;
import entidad.eEstadoPrestamo;
import entidad.prestamo;
import excepciones.ArgumentoInvalidoException;

@WebServlet("/servletPrestamo")
public class servletPrestamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public servletPrestamo() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("accion") != null || request.getAttribute("accion") != null)
		{
			if ("adminPrestamo".equals(request.getParameter("accion")))
			{
				List<prestamo> prestamos = new prestamoNeg().listar();
	        	request.getSession().setAttribute("lista", prestamos);
		        request.getRequestDispatcher("/servletPaginacion?redirectUrl=adminPrestamos.jsp").forward(request, response);			
			}
			if ("clientePrestamo".equals(request.getParameter("accion")) || "clientePrestamo".equals(request.getAttribute("accion")))
			{
				cliente cliente = (cliente)request.getSession().getAttribute("loggedCliente");
				List<cuenta> cuentas = new cuentaNeg().selectAllByOneClientId(cliente.getId());
				request.getSession().setAttribute("cuentas", cuentas);
	        	request.getSession().setAttribute("lista", new prestamoNeg().listarXcliente(cliente.getId()));
		        request.getRequestDispatcher("/servletPaginacion?redirectUrl=prestamo.jsp").forward(request, response);			
			}
			return;
		}
		
		if (request.getParameter("btnAprobar") != null || request.getParameter("btnRechazar") != null)
		{
			String texto = "";
			if (request.getParameter("btnAprobar") != null)
			{
				texto = AprobarRechazarPrestamo(request, eEstadoPrestamo.Aprobado);
			}
			
			if (request.getParameter("btnRechazar") != null)
			{
				texto = AprobarRechazarPrestamo(request, eEstadoPrestamo.Rechazado);
			}
			List<prestamo> prestamos = new prestamoNeg().listar();
	        request.setAttribute("texto", texto);
			request.setAttribute("modal", true);
			request.getSession().setAttribute("lista", prestamos);
			request.getRequestDispatcher("/servletPaginacion?redirectUrl=adminPrestamos.jsp").forward(request, response);		
		}
		
		if (request.getParameter("btnPagarPrestamo") != null)
		{
			String texto = "";
			try
			{
				int nCuenta = Integer.parseInt(request.getParameter("cbCuenta"));
				int contadorPrestamo = Integer.parseInt(request.getParameter("btnPagarPrestamo"));
				if (nCuenta == -1)
				{
					throw new ArgumentoInvalidoException("Seleccione una cuenta para pagar");
				}
				
				int cuotasPagar = Integer.parseInt(request.getParameter("cbCuotasPagar"));
				float cuota = Float.parseFloat(request.getParameter("cuota"+contadorPrestamo));
				cuenta cuenta = new cuentaNeg().buscarPorIdCuenta(nCuenta);
				
				if (cuenta.getSaldo() < cuotasPagar * cuota)
				{
					throw new ArgumentoInvalidoException("Su saldo es insuficiente para pagar esta cantidad de cuotas");	
				}
				
				Long idPrestamo = Long.parseLong(request.getParameter("idPrestamo"+contadorPrestamo));
				cliente cliente = (cliente)request.getSession().getAttribute("loggedCliente");
				int cuotasPagas = Integer.parseInt(request.getParameter("cuotasPagas"+contadorPrestamo));

				if (new prestamoNeg().pagarPrestamo(cliente, nCuenta, idPrestamo, cuotasPagar, cuotasPagas, cuota))
				{
					texto = "Se han pagado con exito las cuotas seleccionadas";
				}
				else
				{
					texto = "No se pudieron pagar el totalidad de las cuotas seleccionadas";
				}
			}
			catch (ArgumentoInvalidoException e)
			{
				texto = e.getMessage();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				texto = "Error fatal, consultar con el desarrollador";
			}
			finally
			{
		        request.setAttribute("texto", texto);
				request.setAttribute("modal", true);
				request.setAttribute("accion", "clientePrestamo");
				request.getRequestDispatcher("/servletPrestamo").forward(request, response);	
			}
		}
		
		if (request.getParameter("btnSolicitarPrestamo") != null)
		{
			String texto = "";

			try
			{
				Long nCuenta = Long.parseLong(request.getParameter("cbCuentaSolicitar"));
				
				if (nCuenta == -1)
				{
					throw new ArgumentoInvalidoException("Seleccione una cuenta a la cual solicitar el prestamo");
				}
				
				int plazo = Integer.parseInt(request.getParameter("cbPlazoSolicitar"));
				float monto = Float.parseFloat(request.getParameter("txtMonto"));
				cliente cliente = (cliente)request.getSession().getAttribute("loggedCliente");
				
				prestamo prestamo = new prestamo(); 
				prestamo.setIdCliente(cliente.getId());
				prestamo.setIdCuenta(nCuenta);
				prestamo.setImporte(monto);
				prestamo.setPlazo(plazo);
				
				if (new prestamoNeg().insertar(prestamo))
				{
					texto = "Prestamo solicitado con exito, por favor espere su aprobación";
				}
				else
				{
					texto = "El prestamo no pudo ser solicitado";
				}
			}
			catch (ArgumentoInvalidoException e)
			{
				texto = e.getMessage();
			}
			catch (Exception e)
			{
				e.printStackTrace();
				texto = "Error fatal, consultar con el desarrollador";
			}
			finally
			{
		        request.setAttribute("texto", texto);
				request.setAttribute("modal", true);
				request.setAttribute("accion", "clientePrestamo");
				request.getRequestDispatcher("/servletPrestamo").forward(request, response);	
			}
		}
	}
	
	private String AprobarRechazarPrestamo(HttpServletRequest request, eEstadoPrestamo estadoPrestamo)
	{
		prestamo prestamo = new prestamo();
		prestamo.setId(Long.parseLong(request.getParameter("idPrestamo")));
		prestamo.setEstadoPrestamo(estadoPrestamo);
		if (new prestamoNeg().actualizar(prestamo))
		{
			return "El prestamo fue actualizado con exito";
		}
		else
		{
			return "El prestamo no pudo ser actualizado";
		}

	}
}
