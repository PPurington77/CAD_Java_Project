<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Formatting (dates) --> 
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/user.css"> <!-- change to match your file/naming structure...this is for your style sheet -->
    <script src="/webjars/jquery/jquery.min.js"></script>
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <!-- Favicon Information below -->
    <link rel="icon" type="image/png" sizes="32x32" href="/images/favicon-32x32.png">
    <link rel="icon" type="image/png" sizes="16x16" href="/images/favicon-16x16.png">
    <link rel="manifest" href="/site.webmanifest">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon">
	<title>Patrick Aided Dispatch</title>
</head>
<body>
	<div id="container">
		<div id="header">
			<h1>Welcome, <c:out value="${user.firstName}"/></h1>
   			<p><a href="/admin/logout">Logout</a></p>
		</div>
		<div id="body">
			<form:form action="/unit/update/${unit.id}" method="post" modelAttribute="unit">
				<input type="hidden" name="_method" value="put"/>
			
			<form:label path="name">Name:</form:label>
			<form:errors path="name" class="alert-danger"/>
			<form:input type="text" path="name"/>	
			
			<form:label path="isDispatched">Is the Unit Busy?</form:label>
			<form:errors path="isDispatched"/>
			<form:checkbox path="isDispatched"/>
			
			<form:label path="incident">Dispatch to Incident:</form:label>
			<form:select path="incident">
				<form:option value=""></form:option>
				<c:forEach var="incident" items="${incident}">
					<form:option value="${incident.id}"><c:out value="${incident.id}"/> <c:out value="${incident.signal.name}"/> <c:out value="${incident.streetNumber}"/> <c:out value="${incident.streetDirection}"/> <c:out value="${incident.streetName}"/></form:option>
				</c:forEach>
			</form:select>
			
			<input type="submit" value="Update" class="btn btn-light"/>
			</form:form>
			<a href="/admin/dashboard"><button class="btn btn-light">Dashboard</button></a>	
		</div>
	</div>
</body>
</html>