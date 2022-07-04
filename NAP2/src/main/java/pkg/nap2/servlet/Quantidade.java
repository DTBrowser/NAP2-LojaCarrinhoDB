package pkg.nap2.servlet;

import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.nap2.model.Carrinho;

/**
 * Servlet implementation class Quantidade
 */
@WebServlet("/quantidade")
public class Quantidade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()) {
			String action = request.getParameter("action");
			int id = Integer.parseInt(request.getParameter("id"));
			
			ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) request.getSession().getAttribute("carrinho-lista");
			if(action != null && id>=1) {
				if(action.equals("add")) {
					for(Carrinho c:carrinho_lista) {
						if(c.getId() == id) {
							int quantidade = c.getQuantidade();
							quantidade++;
							c.setQuantidade(quantidade);
							response.sendRedirect("carrinho.jsp");
						}
					}
				}
				if(action != null && id>=1) {
					if(action.equals("remove")) {
						for(Carrinho c:carrinho_lista) {
							if(c.getId() == id && c.getQuantidade() > 1) {
								int quantidade = c.getQuantidade();
								quantidade--;
								c.setQuantidade(quantidade);
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
}
