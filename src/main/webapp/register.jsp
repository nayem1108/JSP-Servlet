<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register Page</title>
</head>
<body>
	<form action="register" method="post">
	Name: <input type="text" name="name" placeholder="Enter Full Name"><br><br>
	Email: <input type="email" name="email" placeholder="Enter E-mail"><br><br>
	Password: <input type="text" name="password" placeholder="Enter Password"><br><br>
	Gender: <input type="radio" name="gender" value="male">Male <input type="radio" value="female" name="gender">Female<br><br>
	City: <select name="city"><br><br>
			<option selected disabled>Select City</option>
			<option value="Dhaka">Dhaka</option>
			<option value="Chattagram">Chattagram</option>
			<option value="Sylhet">Sylhet</option>
			<option value="Rajshahi">Rajshahi</option>
		</select><br><br>
	<input type="submit" value="Register">
	</form>
</body>
</html>