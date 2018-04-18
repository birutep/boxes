<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>New box</title>
	</head>
	<body>
	
		<h3>Create box with following info:</h3><br>
		
		<form action="boxCreationResult" method="post">
			Enter id:
			<input type="text" name="id"><br>
			Enter color: 
			<input type="text" name="color"><br>
			Enter size:
			<input type="text" name="size"><br><br>
			<input type="submit" value="Create new box">
		</form>
	</body>
</html>