<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Boxes Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add Box
</h1>

<c:url var="addAction" value="/box/add" ></c:url>

<form:form action="${addAction}" commandName="box">
<table>
	<c:if test="${!empty box.color}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="color">
				<spring:message text="Color"/>
			</form:label>
		</td>
		<td>
			<form:input path="color" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="size">
				<spring:message text="Size"/>
			</form:label>
		</td>
		<td>
			<form:input path="size" />
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty box.color}">
				<input type="submit"
					value="<spring:message text="Edit Box"/>" />
			</c:if>
			<c:if test="${empty box.color}">
				<input type="submit"
					value="<spring:message text="Add Box"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Box List</h3>
<c:if test="${!empty listBoxes}">
	<table class="tg">
	<tr>
		<th width="80">Box ID</th>
		<th width="120">Box Color</th>
		<th width="120">Box Size</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	</tr>
	<c:forEach items="${listBoxes}" var="box">
		<tr>
			<td>${box.id}</td>
			<td>${box.color}</td>
			<td>${box.size}</td>
			<td><a href="<c:url value='/edit/${box.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/remove/${box.id}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>