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
  	<link rel="stylesheet" href="/css/helpdesk.css"><!-- change to match your file/naming structure...this is for your style sheet -->
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
   <div id="header">
		<ul>
			<li>Dispatcher <c:out value="${user.firstName}"/></li>
			<li><a href="/incident/dashboard">Dashboard</a></li>
			<li><a href="/user/logout">Logout</a></li>
		</ul>
	</div>
	<div id="container">
		<p>Welcome to the help desk <c:out value="${user.firstName}"/>.</p>
		<p>If you need help or would like to update your account, please click the button below to email an administrator.</p>
		<p><a href="mailto:papurington@gmail.com"><button id="button">Email</button></a></p>
	</div>
</body>
</html>