<%@page import="java.util.*" %>
<%@page import="pkg.nap2.connection.DbCon" %>
<%@page import="pkg.nap2.model.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    Cliente auth = (Cliente) request.getSession().getAttribute("auth");
    if(auth != null){
    	request.setAttribute("auth", auth);
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
<title>Compras</title>
</head>
<body>
<%@include file="includes/navbar.jsp"%>
<%@include file="includes/footer.jsp"%>

</body>
</html>