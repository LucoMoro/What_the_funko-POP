<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart"%>

<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO </title>
</head>

<body>
<h1> Questo è il tuo carrello </h1>

<%
	Cart cart = (Cart)request.getSession().getAttribute("cart");
	double somma=0;
	
		if(cart == null) {
			%> <p> Il tuo carrello è vuoto </p>
			<a href="ProductView.jsp"> Ritorna al sito</a>
		<%}
		else { %>
		  <table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name</th>
			<th>Price</th>
            <th>Quantity</th>
		</tr>
		<%
			List<ProductBean> prodcart = cart.getProducts(); 	
			   for(ProductBean beancart: prodcart) {
			
				 %>
		<tr>
			<td><%=beancart.getCode()%></td>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getPrice()%></td>
			<td><%=beancart.getQuantità() %></td>
		</tr>
          <% somma= somma + (beancart.getPrice()* beancart.getQuantità()); %>	
         <% }
		} %>
		
		<p> il totale è <%= somma %> </p>
		
		<form action="CancellaCarrello" method="post">
		<input type="submit" value="procedi all'acquisto">
		</form>
		
</body>
</html>