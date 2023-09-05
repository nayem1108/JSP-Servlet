<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Welcome <%= session.getAttribute("name") %></h1><br><br>
	<form action="logout" method="post">
		<button type="submit">Logout</button>
	</form>
	
	<h5>Hello</h5>
</body>
</html>