<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% UserBean utente= (UserBean) request.getSession().getAttribute("utente"); 
	request.getSession().setAttribute("Action", "buy");
%>

<!DOCTYPE html>

<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.UserBean,  it.unisa.model.CartItem"%>

<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO</title>
</head>

<body>
	<h1>Questo è il tuo carrello</h1>
	
	

	<%
	if (utente!=null){ %>
		<p> Benvenuto <%= utente.getID() %> </p>
	<%}
	
	Cart cart = (Cart)request.getSession().getAttribute("cart");
	double somma=0;
	
	if((cart == null) || (cart.getProducts().size()==0)) {
			%>
	<p>Il tuo carrello è vuoto</p>
	<a href="ProductView.jsp"> Ritorna al sito</a>
	<a href="Orders.jsp"> Vedi i tuoi ordini</a>
	<%}
		
		else { %>
	<table border="1">
		<tr>
			<th>Code </th>
			<th>Name</th>
			<th>Price</th>
			<th>Quantity</th>
		</tr>
		<%
			ArrayList<CartItem> carrello= cart.getProducts();
		
			ProductBean beancart;
			
			int quantità=0;
			
		for(int i=0; i<carrello.size(); i++){
			beancart=carrello.get(i).getProduct();
			quantità=carrello.get(i).getQuantity();
			 %>
		<tr>
			<td><%=beancart.getCode()%></td>
			<td><%=beancart.getName()%></td>
			<td><%=beancart.getPrice()%></td>
			<td><%=quantità%></td>
		</tr>
		<% somma= somma + (beancart.getPrice()* quantità); %>
		<% } %>
		
		<p>
		il totale è <%= somma %>
	</p>

	<% if(utente==null){
		%>
	
	<form action="Login.jsp" method="post">
		<input type="submit" value="procedi all'acquisto">
	</form>
	<%}

else { %>
	<form action="CancellaCarrello" method="post">
		<input type="submit" value="procedi all'acquisto">
	</form>

	<%}

		} %>

		
</body>
</html>