<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>Table</title>
		
	</head>
	<body>

		

		<div align="center">
		
			<h3>Table of boxes in array</h3>
			
			<table>
				<tr>
					<th>ID</th>
					<th>Color</th>
					<th>Size</th>
					<th colspan="3">Possible actions</th>
				</tr>
				<c:forEach var="box" items="${boxDAO}">
					<tr>
						<td><c:out value="${box.id}"/></td>	
						<td><c:out value="${box.color}"/></td>	
						<td><c:out value="${box.size}" /></td>
						<td>
							<form action="table" method="post">
								<input type="hidden" name="id" value="${box.id}">
								<input type="submit" value="Delete">
							</form>
						</td>	
						<td>
							<form action="boxUpdate" method="get">
								<input type="hidden" name="id" value="${box.id}">
								<input type="submit" value="Update">
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
					
		</div>		
</body>
</html>