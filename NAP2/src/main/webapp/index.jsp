<%@page import="java.util.*" %>
<%@page import="pkg.nap2.dao.ProdutoDao" %>
<%@page import="pkg.nap2.connection.DbCon"%>
<%@page import="pkg.nap2.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
Cliente auth = (Cliente) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ProdutoDao pd = new ProdutoDao(DbCon.getConnection());
List<Produto> produtos = pd.getAllProducts();

ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) session.getAttribute("carrinho-lista");
if(carrinho_lista != null){
	request.setAttribute("carrinho_lista", carrinho_lista);
}

%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<title>Livraria E-Cursos</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="card-header my-3">Todos os livros</div>
		<div class="row">
		<%
			if( !produtos.isEmpty()){
				for(Produto p:produtos){%>
					<div class="col-md-4 my-3">
					<div class="card w-100" style="width: 18rem;">
						<img class="card-img-top" src="foto-produtos/<%=p.getImage() %>" alt="Card image cap">
						<div class="card-body">
							<h5 class="card-title"><%=p.getNome() %></h5>
							<h6 class="price">R$ <%=p.getPreco() %></h6>
							<h6 class="category"><%=p.getCategoria() %></h6>
							<div class="mt-3 d-flex justify-content-between">
							<a href="add-to-cart?id=<%= p.getId() %>" class="btn btn-dark">Adicionar ao Carrinho</a>
							<a href="#" class="btn btn-primary">Comprar</a>
							</div>
						</div>
					</div>
				</div>
					
				<%}
			}
		%>
		</div>
	</div>

	<%@include file="includes/footer.jsp"%>

</body>
</html>