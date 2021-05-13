<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.model.ProductBean,it.unisa.model.Cart, it.unisa.model.UserBean "%>


<head>
<meta charset="ISO-8859-1">
<title>WHAT THE FUNKO</title>
</head>

<body>

	<form action="LoginServlet" method="POST">
		<fieldest align=center>
		<p>
			Inserisci il nome utente <input type="text" name="ID"> <br>
			Inserisci la password <input type="password" name="password">
		</p>
		<input type="submit" value="Accedi"> <br>
		<br>
		<br>
		<p>
			Non sei ancora iscritto? <a href="Register.jsp"> Registrati </a>
		</fieldest>

	</form>
</body>
</html>