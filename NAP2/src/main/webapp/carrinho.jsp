<%@page import="pkg.nap2.dao.ProdutoDao"%>
<%@page import="java.text.DecimalFormat" %>
<%@page import="java.util.*"%>
<%@page import="pkg.nap2.connection.DbCon"%>
<%@page import="pkg.nap2.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
DecimalFormat dcf = new DecimalFormat("#.##");
request.setAttribute("dcf", dcf);
Cliente auth = (Cliente) request.getSession().getAttribute("auth");
if (auth != null) {
	request.setAttribute("auth", auth);
}

ArrayList<Carrinho> carrinho_lista = (ArrayList<Carrinho>) session.getAttribute("carrinho-lista");
List<Carrinho> carrinhoProduto = null;
if(carrinho_lista != null){
	ProdutoDao pDao = new ProdutoDao(DbCon.getConnection());
	carrinhoProduto = pDao.getCartProducts(carrinho_lista);
	double total = pDao.getTotalPrice(carrinho_lista);
	request.setAttribute("carrinho_lista", carrinho_lista);
	request.setAttribute("total", total);
}
%>
<!DOCTYPE html>
<html>
<head>
<%@include file="includes/head.jsp"%>
<style type="text/css">
.table tbody td {
	vartical .align: middle;
}

.btn-incre, .btn-decre {
	box-shadow: none;
	font-size: 25px;
}
</style>
<title>Carrinho de Compras</title>
</head>
<body>
	<%@include file="includes/navbar.jsp"%>

	<div class="container">
		<div class="d-flex py-3">
			<h3>Preço Total: ${ (total>0)?dcf.format(total):0 } R$</h3>
			<a class="mx-3 btn btn-primary" href="#">Finalizar</a>
		</div>
		<table class="table table-loght">
			<thead>
				<tr>
					<th scope="col">Nome</th>
					<th scope="col">Categoria</th>
					<th scope="col">Preço</th>
					<th scope="col">Quantidade</th>
					<th scope="col">Cancelar</th>
				</tr>
			</thead>
			<tbody>
			<% 
			if(carrinho_lista != null){
				for(Carrinho c:carrinhoProduto){%>
					<tr>
					<td><%= c.getNome() %></td>
					<td><%= c.getCategoria() %></td>
					<td><%= dcf.format(c.getPreco()) %> R$</td>
					<td>
						<form action="" method="post" class="form-inline">
							<input type="hidden" name="id" value="<%= c.getId() %>" class="form-input">
							<div class="form-group d-flex justify-content-between">
								<a class="btn btn-sm btn-decre" href="quantidade?action=remove&id=<%= c.getId()%>"><i
									class="fas fa-minus-square"></i></a> <input type="text"
									name="quantity" class="form-control" value="<%=c.getQuantidade() %>" readonly>
								<a class="btn btn-sm btn-incre" href="quantidade?action=add&id=<%= c.getId()%>"><i
									class="fas fa-plus-square"></i></a>
							</div>
						</form>
					</td>
					<td><a class="btn btn-sm btn-danger" href="remover?id=<%= c.getId() %>">Remover</a></td>
				</tr>
				<%}
			}
			%>
				
			</tbody>
		</table>
	</div>

	<%@include file="includes/footer.jsp"%>

</body>
</html>