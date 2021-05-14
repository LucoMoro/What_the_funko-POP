<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
    
<!DOCTYPE html>

<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart"%>
<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO</title>
</head>
<body>
<h1> Descrizione del prodotto</h1>

<% ProductBean product= (ProductBean) request.getAttribute("prodotto"); %>

<p> <%= product.getDescription() %></p>
<br></br>
<a href="product?action=addC&id=<%=product.getCode()%>">Add to cart</a>
</body>
</html>