package pkg.nap2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import pkg.nap2.model.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddToCartServlet
 */
@WebServlet("/add-to-cart")
public class AddToCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		try(PrintWriter out = response.getWriter()){
			ArrayList<Carrinho> carrinhoLista = new ArrayList<>();
			
			int id = Integer.parseInt(request.getParameter("id"));
			Carrinho cm = new Carrinho();
			cm.setId(id);
			cm.setQuantidade(1);
			
			
			HttpSession session = request.getSession();
			ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) session.getAttribute("carrinho-lista");
			
			if(carrinho_lista == null) {
				carrinhoLista.add(cm);
				session.setAttribute("carrinho-lista", carrinhoLista);
				response.sendRedirect("index.jsp");
			}else {
				carrinhoLista = carrinho_lista;
				boolean existe = false;
				for(Carrinho c:carrinhoLista) {
					if(c.getId() == id) {
						existe = true;
						out.println("<h3 style='color:crimson; text-align:center'>Livro já foi adicionado ao carrinho!<a href='carrinho.jsp'><br>Voltar para o Carrinho de Compras</a></h3>");
					}
				}
				if(!existe) {
					carrinhoLista.add(cm);
					response.sendRedirect("index.jsp");
				}
			}
			
		}
	}

}
