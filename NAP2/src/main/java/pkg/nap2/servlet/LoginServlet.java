package pkg.nap2.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pkg.nap2.connection.DbCon;
import pkg.nap2.dao.ClienteDao;
import pkg.nap2.model.Cliente;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user-login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("login.jsp");
	}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try(PrintWriter out = response.getWriter()){
			String email = request.getParameter("login-email");
			String senha = request.getParameter("login-password");
			
			try {
				ClienteDao udao = new ClienteDao(DbCon.getConnection());
				Cliente cliente = udao.userLogin(email, senha);
				
				if(cliente != null) {
					out.print("Logado");
					request.getSession().setAttribute("auth", cliente);
					response.sendRedirect("index.jsp");
				}else {
					out.print("Login falhou");
				}
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
