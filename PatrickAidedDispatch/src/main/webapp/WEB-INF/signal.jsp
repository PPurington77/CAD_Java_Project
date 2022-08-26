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
			<h1>Welcome, <c:out value="${admin.firstName}"/></h1>
   			<p><a href="/admin/logout">Logout</a></p>
		</div>
		<div id="body">
			<p>Number: <c:out value="${signal.number}"/></p>
			<p>Name: <c:out value="${signal.name}"/></p>
			<p>Priority: <c:out value="${signal.priority}"/></p>
			<div id="buttons">
			<form action="/signal/delete/${signal.id}" method="post">
				<input type="hidden" name="_method" value="delete"/>
				<input type="submit" value="Delete" class="btn btn-light"/>
			</form>
			<a href="/admin/dashboard"><button class="btn btn-light">Dashboard</button></a>
			<a href="/signal/update/${signal.id}"><button class="btn btn-light">Update</button></a>
			</div>
		</div>
	</div>
</body>
</html>