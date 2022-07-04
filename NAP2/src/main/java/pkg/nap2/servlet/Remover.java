package pkg.nap2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.nap2.model.Carrinho;

/**
 * Servlet implementation class Remover
 */
@WebServlet("/remover")
public class Remover extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			String id = request.getParameter("id");
			if(id != null) {
				ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) request.getSession().getAttribute("carrinho-lista");
				if(carrinho_lista != null) {
					for(Carrinho c:carrinho_lista) {
						if(c.getId() == Integer.parseInt(id)) {
							carrinho_lista.remove(carrinho_lista.indexOf(c));
							break;
						}
					}
					response.sendRedirect("carrinho.jsp");
				}
			}else {
				response.sendRedirect("carrinho.jsp");
			}
		}
	}

}
