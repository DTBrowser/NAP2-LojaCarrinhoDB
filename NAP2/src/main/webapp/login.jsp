<%@page import="java.util.*" %>
<%@page import="pkg.nap2.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	 <%
    Cliente auth = (Cliente) request.getSession().getAttribute("auth");
    if(auth != null){
    	response.sendRedirect("index.jsp");
    }
    
    ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) session.getAttribute("carrinho-lista");
    if(carrinho_lista != null){
    	request.setAttribute("carrinho_lista", carrinho_lista);
    }
    
    %>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Página de Login</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>
	<div class="container">
		<div class="card w-50 mx-auto my-5">
			<div class="card-header text-center">Login do usuário</div>
			<div class="card-body">
				<form action="user-login" method="post">
					<div class="form-group">
						<label>Endereço de Email</label> <input type="email"
							class="form-control" name="login-email"
							placeholder="Digite o email" required>
					</div>
					<div class="form-group">
						<label>Senha</label> <input type="password" class="form-control"
							name="login-password" placeholder="********" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Login</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<%@include file="includes/footer.jsp"%>

</body>
</html>