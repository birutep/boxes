<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="springForm" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Manage box information</title>
		<link rel="stylesheet" type="text/css" href="./resources/css/style.css">
	</head>
	
	<body>
	
		<springForm:form action="addUpdateBox" method="POST" commandName="boxInputForm">
			
			<h3>${boxInputForm.pageName}</h3><br>
			
			<table>
				<tr>
					<td><springForm:hidden path="pageName"/></td>
					<td><springForm:hidden path="buttonName"/></td>
					<td><springForm:hidden path="box.id"/></td>
				</tr>
				<tr>
					<td>Enter color:</td>
					<td><springForm:input path="box.color" /></td>
					<td><springForm:errors path="box.color" cssClass="error" /></td>
				</tr>
				<tr>
					<td>Enter size:</td>
					<td><springForm:input path="box.size"/></td>
					<td><springForm:errors path="box.size" cssClass="error" /></td>
				</tr>
				<tr>
					<td colspan="3"><input type="submit" value="${boxInputForm.buttonName}"></td>
				</tr>
			</table>
		</springForm:form>	
			
	</body>
</html>