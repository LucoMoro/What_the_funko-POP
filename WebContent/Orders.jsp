<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% Cart orders = (Cart) request.getSession().getAttribute("orders");
   GregorianCalendar newdata= (GregorianCalendar) request.getAttribute("data");

   double somma=0;
   
   %>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.UserBean "%>

<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO</title>
</head>

<body>
	<h2>Questi sono gli ordini che hai effettuato</h2>


	<a href="product">List</a>
	<table border="1">
		<tr>
			<th>Code </th>
			<th>Name </th>
			<!-- <th>Description</th> -->
			<th>Price</th>
			<th>Quantity</th>
			<th>Rarity</th>
			<th>Dimenison</th>
			<th>Series</th>
			<th>Action</th>
			<th> Date </th>
		</tr>
		<%
		ProductBean bean;
		ArrayList<ProductBean> ordini = orders.getProducts();
		
		for(int i=0; i<ordini.size(); i++){
			bean=ordini.get(i);
			 %>
		<tr>
			<td><%=bean.getCode()%></td>
			<td><%=bean.getName()%></td>
			<!-- <td><%=bean.getDescription()%></td> -->
			<td><%=bean.getPrice()%></td>
			<td><%=bean.getQuantità()%></td>
			<td><%=bean.getRarity() %></td>
			<td><%=bean.getDimension() %></td>
			<td><%=bean.getSeries() %></td>
			<td><form action="Prodotto" method="post">
					<input type="hidden" name="id" value="<%= bean.getCode() %>">
					<input type="submit" value="Dettagli">
				</form></td>
			<td><%= newdata %></td>
				<% somma= somma + (bean.getPrice()* bean.getQuantità()); %>
				

				

			</td>
		</tr>
		<% } %>
		<p>
					il totale è
					<%= somma %>
				</p>
	</table>

</body>

</html>