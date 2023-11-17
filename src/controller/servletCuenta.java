package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.jasper.tagplugins.jstl.core.If;

import datos.cuentaDao;
import entidad.cliente;
import entidad.cuenta;
import entidad.eTipoCuenta;
import excepciones.ArgumentoInvalidoException;
import jdk.nashorn.internal.ir.RuntimeNode.Request;
import negocio.clienteNeg;
import negocio.cuentaNeg;

/**
 * Servlet implementation class servletCuenta
 */
@WebServlet("/servletCuenta")
public class servletCuenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servletCuenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		cuentaNeg cuentaNeg = new cuentaNeg();
		if(request.getParameter("accion") != null || request.getAttribute("accion") != null) {
			String accion = request.getParameter("accion") != null ? request.getParameter("accion") : request.getAttribute("accion").toString();
			if("blCuentas".equals(accion)) {
				HttpSession session = request.getSession();
				List<cuenta> listaCompleta = cuentaNeg.selectAll();
				if(request.getParameter("filter") != null || (request.getParameter("filterCbu") != null )) {
					//filtrar lista
					String cbu = "";
					if(!request.getParameter("filterCbu").isEmpty()) {
						cbu = request.getParameter("filterCbu");
					}
					eTipoCuenta tipoCuenta = null;
					if(request.getParameter("filter") != null) {
						tipoCuenta = Integer.parseInt(request.getParameter("filter"))==1?eTipoCuenta.CajaDeAhorro:eTipoCuenta.CuentaCorriente;
					}
					// si algunos de los 2 es verdadero, se filtra
					List<cuenta> listaFiltrada = cuentaNeg.selectAllByTypeOf(tipoCuenta,cbu);
					
					//si se ingreso el cbu pero esta vacio y el tipo de cuenta es null, se devuelve la lista completa
					if(request.getParameter("filterCbu").isEmpty() && request.getParameter("filter") == null) {
						listaFiltrada = cuentaNeg.selectAll();// => lista completa
					}
					session.setAttribute("lista", listaFiltrada);
				}else {
					session.setAttribute("lista", listaCompleta);
				}
				request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCuentas.jsp").forward(request, response);
			}else {
				request.getRequestDispatcher("/views/altaCuentas.jsp").forward(request, response);
			}
			return;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
		if(request.getParameter("nroCliente") != null) {
			try {
				 clienteNeg clienteNeg = new clienteNeg();
				 int clientId = Integer.parseInt(request.getParameter("nroCliente"));
				 cliente aux = clienteNeg.obtenerCliente(clientId);
				 boolean existe = aux != null;
				 boolean activo = existe ? aux.getEstado() : false;
				 if(!existe) throw new ArgumentoInvalidoException("El número de cliente indicado no existe");
				 if(!activo) throw new ArgumentoInvalidoException("El cliente no se encuentra activo");
				 cuentaNeg cuentaNeg = new cuentaNeg();
				 boolean tooMany = cuentaNeg.selectAllByOneClient(clientId).size() > 2;
				 if(tooMany) throw new ArgumentoInvalidoException("El cliente ya posee 3 cuentas");
				 eTipoCuenta tipoCuenta = request.getParameter("tipoCuenta").equals("ca") ? eTipoCuenta.CajaDeAhorro : eTipoCuenta.CuentaCorriente;
				 boolean alta = cuentaNeg.altaCuenta(clientId,tipoCuenta);
				 if(!alta) throw new ArgumentoInvalidoException("No se pudo crear la cuenta, intente nuevamente");
				 request.setAttribute("modal", true);
				 request.setAttribute("texto", "Cuenta creada correctamente");
			}catch (ArgumentoInvalidoException e) {
				request.setAttribute("modal", true);
				request.setAttribute("texto", e.getMessage());
			}catch (Exception e) {
				request.setAttribute("modal", true);
				request.setAttribute("texto", "Algo salió mal, contacte al soporte");
				System.out.println(e.getMessage());
			}finally {
				request.getRequestDispatcher("/views/altaCuentas.jsp").forward(request, response);
			}
		 }
		
		//eliminar-activar
		if (request.getParameter("btnCuentaId") != null && request.getParameter("btnClienteId") != null && request.getParameter("btnEstado") != null )
		{
			int cuentaId = Integer.parseInt(request.getParameter("btnCuentaId"));
			int id_Cliente = Integer.parseInt(request.getParameter("btnClienteId"));
			String btnEstado = request.getParameter("btnEstado");
			boolean stateToChange = Boolean.valueOf(btnEstado);
			cuentaNeg cuentaNeg = new cuentaNeg();

			boolean modificado= cuentaNeg.updateRegisterState(cuentaId, id_Cliente, !stateToChange);
			if(modificado)
			{
	    		request.setAttribute("texto", "Estado de cuenta modificado con exito");
			}
			else
			{
	    		request.setAttribute("texto", "No se pudo modificar el estado de la cuenta");
			}
			
	    	request.setAttribute("modal", true);
	    	request.getSession().setAttribute("lista", cuentaNeg.selectAll());
	    	RequestDispatcher dispatcher = request.getRequestDispatcher("/servletPaginacion?redirectUrl=blCuentas.jsp");
			dispatcher.forward(request, response);	
			return;
		}
	}

}
