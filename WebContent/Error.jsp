<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<%
String Controllo = (String) request.getSession().getAttribute("Controllo");
%>

<title>WHAT THE FUNKO</title>
</head>

<body>
	<%
	if (Controllo.contentEquals("login")) {
	%>
	<form action="LoginServlet" method="POST">
		<fieldest align=center>
		<p>
			Inserisci il nome utente <input type="text" name="ID"> <br>
			Inserisci la password <input type="password" name="password">
		</p>
		<input type="submit" value="Accedi"> <br>
		<br>
		<br>
		<b> Combinazione ID, password errata </b>
		<p>
			Non sei ancora iscritto? <a href="Register.jsp"> Registrati </a>
		</p>
		</fieldest>

	</form>

	<%
	}

	else {
	%>

	<form action="RegisterServlet" method="POST">

		<fieldest align=center>
		<p>
			Inserisci il nome utente <input type="text" name="ID"> <br>
			Inserisci la password <input type="password" name="password">
			<br> Inserisci la email <input type="email" name="email">
		</p>
		<b> Combinazione ID, password, email errata </b>
		<input type="submit" value="Registrati"> </fieldest>

	</form>

	<%
	}
	%>

</body>
</html>