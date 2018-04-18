<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Update box</title>
	</head>
	<body>
	
		<h3>Update box with id ${id}</h3><br>
		
		<form action="boxUpdate" method="post">
			<input type="hidden" name="id" value="${id}">
			Enter color: 
			<input type="text" name="color"><br>
			Enter size:
			<input type="text" name="size"><br><br>
			<input type="submit" value="Update box">
		</form>
	</body>
</html>