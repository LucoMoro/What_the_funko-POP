<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Collection<?> products = (Collection<?>) request.getAttribute("products");
	if(products == null) {
		response.sendRedirect("./product");	
		return;
	}
	
	ProductBean product = (ProductBean) request.getAttribute("product"); //definisce una varibile di tipo ProductBean e preleva i valori
	
	Cart cart = (Cart) request.getAttribute("cart"); //definisce una variabile di tipo Cart e preleva i valori
	
	//request.getSession().removeAttribute("utente");
	
	UserBean utente= (UserBean) request.getSession().getAttribute("utente"); //definisce una variabile di tipo UserBean e preleva i valori
	
	request.getSession().setAttribute("Action", "view"); //definisce la variabile @action= view
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.UserBean "%>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="Style.css" rel="stylesheet" type="text/css">
	<title>WHAT THE FUNKO</title>
</head>

<body>

<img src="./Images/logo.png">

<% if(utente==null){ %>
	<p> Non sei connesso, <a href="Login.jsp"> accedi </a> </p>
<%}

else { %>
<p> Benvenuto <%= utente.getID() %></p>
	
<%}%>
<h2>Products</h2>
	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Code <a href="product?sort=code">Sort</a></th>
			<th>Name <a href="product?sort=name">Sort</a></th>
			<th>Price</th>
            <th>Quantity</th>
            <th>Rarity</th>
            <th>Dimension</th>
            <th>Franchise Name</th>
            <th>Series</th>
            <th>Medium score</th>
			<th>Action</th>
		</tr>
		<%
			if (products != null && products.size() != 0) {
				Iterator<?> it = products.iterator();
				while (it.hasNext()) {
					ProductBean bean = (ProductBean) it.next();
		%>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<td><%=bean.getPrice()%></td>
			<td><%=bean.getQuantity()%></td>
			<td><%=bean.getRarity() %></td>
            <td><%=bean.getDimension() %></td>
            <td><%=bean.getFranchiseName() %></td>
            <td><%=bean.getSeries() %></td>
            <td><%=bean.getMediumScore() %></td>
			 <td>
				
				<form action="Prodotto" method="post">
				<input type="hidden" name="id" value="<%= bean.getCode() %>"> 
				<input type="submit" value="Dettagli">
				</form>
				
				<a href="product?action=addC&id=<%=bean.getCode()%>">Add to cart</a>
				
				</td>
		</tr>
		<%
				}
			} else {
		%>
		<tr>
			<td colspan="6">No products available</td>
		</tr>
		<%
			}
		%>
	</table>
	
	<a href="Carrello.jsp" target="_blank"> Vai al carrello </a>
	
</body>
</html>