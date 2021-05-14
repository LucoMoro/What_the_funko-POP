<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% 	double somma=0;
	double bigSomma=0;
	double bigIVA;
   %>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.UserBean, 
	it.unisa.model.OrderBean, it.unisa.model.DS.OrderModelDS, it.unisa.model.CartItem, it.unisa.model.OrderItem, it.unisa.model.DS.ProductModelDS"%>

<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO</title>
</head>

<body>
	<h2>Questi sono gli ordini che hai effettuato</h2>


	<a href="product">List</a>
	<%		
		
		ArrayList<OrderBean> orders = (ArrayList<OrderBean>) request.getSession().getAttribute("ordini");
		ArrayList<Cart> bigCart = (ArrayList<Cart>) request.getSession().getAttribute("bigCart");
		Cart tmp = new Cart();
		ProductBean bean = null;
		int quantità;
	%>	
			<table border="1">
			<tr>
				<th>Order Code</th>
				<th>Code</th>
				<th>Name</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Rarity</th>
				<th>Dimension</th>
				<th>Franchise Name</th>
				<th>Series</th>
				<th>Medium Score</th>
				<th>Action</th>
				<th>Date</th>
				<th>Total</th>
			</tr>
	
			 <% 
				for(int i = 0; i<orders.size(); i++)
				{
					tmp = bigCart.get(i);
					
				%>
				<tr>
				<td><%= orders.get(i).getOrderCode() %></td>
				<%
					for(int j = 0; j< tmp.size(); j++)
					{
						somma = 0;
						bean = tmp.get(j).getProduct();
						quantità = tmp.get(j).getQuantity();
				%>

			<td><%=bean.getCode()%></td> 
			<td><%=bean.getName()%></td>
			<td><%=bean.getPrice()%></td>
			<td><%=quantità%></td>
			<td><%=bean.getRarity() %></td>
			<td><%=bean.getDimension() %></td>
			<td><%=bean.getFranchiseName() %></td>
			<td><%=bean.getSeries() %></td>
			<th><%=bean.getMediumScore() %>
			<td><form action="Prodotto" method="post">
					<input type="hidden" name="id" value="<%= bean.getCode() %>">
					<input type="submit" value="Dettagli">
				</form></td>
			<% somma= somma + (bean.getPrice()* quantità); %>
			<% bigSomma=bigSomma+ somma;%>
				<%
				  }
				%>
				<%
				bigIVA=(bigSomma*22)/100; 
				bigSomma = bigSomma + bigIVA;
				%>
				
			<td><%= orders.get(i).getDataOrdine() %></td>
			<td><%= bigSomma %></td>
		</tr>
		<% } %>
</table>


</body>

</html>