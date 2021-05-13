<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<% double somma=0;
	double bigSomma=0;
	double bigIVA;
    OrderModelDS model= new OrderModelDS();
	
	ProductModelDS modelP= new ProductModelDS();
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
		UserBean user= (UserBean) request.getSession().getAttribute("utente");
		String ID= user.getID();
		
		System.out.println(ID);
		
		ArrayList<OrderBean> arr1= null;
		
		arr1= model.doRetrieveAll(ID);
		
		ArrayList<OrderItem> arr2= null;
	
		ProductBean bean = null;
		
		int quantità=0;
		
		for(int i=0; i<arr1.size(); i++){
			arr2= arr1.get(i).getOrderItems();
			bigSomma=0; %>
			<p> Order Code:	 <%=arr1.get(i).getOrderCode() %></p>
			
			<table border="1">
			<tr>
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

			 <% for (int x=0; x<arr2.size(); x++){
				bean=modelP.doRetrieveByKey(arr2.get(x).getProductCode());
				quantità=arr2.get(x).getOrderQuantity();
				
				
			 %>

		<tr>
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
			<td><%= arr1.get(i).getDataOrdine() %></td>
			<% somma= somma + (bean.getPrice()* quantità); %>
			<td><%= somma %></td>

		</tr>
		<% bigSomma=bigSomma+ somma;
			bigIVA=(bigSomma*22)/100;
			bigSomma=bigSomma + bigIVA;%>
		<% } %>
			<p>il totale per l'ordine è <%=bigSomma %></p>
		<%} %>
</table>


</body>

</html>