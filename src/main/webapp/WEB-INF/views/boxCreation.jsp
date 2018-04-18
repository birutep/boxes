<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>New box</title>
		<link rel="stylesheet" type="text/css" href="../../resources/css/style.css">
	</head>
	
	<body>
	
		<h3>Create box with following info:</h3><br>
		
		<springForm:form method="POST" commandName="box" action="boxCreation">
			<table>
				<tr>
					<td>Enter ID:</td>
					<td><springForm:input path="id" /></td>
					<td><springForm:errors path="id" style="color:red" /></td>
				</tr>
				<tr>
					<td>Enter color:</td>
					<td><springForm:input path="color" /></td>
					<td><springForm:errors path="color" style="color:red" /></td>
				</tr>
				<tr>
					<td>Enter size:</td>
					<td><springForm:input path="size" /></td>
					<td><springForm:errors path="size" style="color:red" /></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="Create box"></td>
				</tr>
			</table>
		</springForm:form>
		
	</body>
</html>