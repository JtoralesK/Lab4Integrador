package controller;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class loggedUserFilter
 */
@WebFilter("/loggedUserFilter")
public class loggedUserFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		chain.doFilter(request, response);
		/* 
		 * DESCOMENTAR ESTA SECCIÓN PARA ACTIVAR FILTRO DE LOGIN Y BORRAR FILA 22
		HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        HttpSession session = httpRequest.getSession(false);
        boolean isLoggedIn = session != null && session.getAttribute("loggedUser") != null;

        if (!isLoggedIn && !httpRequest.getRequestURI().endsWith("login.jsp") && !httpRequest.getRequestURI().endsWith("servletUsuario")) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/views/login.jsp");
        } else {
            chain.doFilter(request, response);
        }
		 */
	}

	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
