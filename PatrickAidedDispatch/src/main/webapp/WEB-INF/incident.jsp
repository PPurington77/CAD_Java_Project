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
    <link rel="stylesheet" href="/css/createIncident.css"> <!-- change to match your file/naming structure...this is for your style sheet -->
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
   		<h1>Viewing Incident ID <c:out value="${incident.id}"/></h1>
   		<p>Type: S<c:out value="${incident.signal.number}"/> <c:out value="${incident.signal.name}"/></p>
   		<p>Address: <c:out value="${incident.streetNumber}"/> <c:out value="${incident.streetDirection}"/> <c:out value="${incident.streetName}"/> <c:out value="${incident.city}"/></p>
   		<p>Caller: <c:out value="${incident.callerName}"/></p>
   		<p>Phone Number: <c:out value="${incident.phone}"/></p>
   		<p>
   			<c:choose>
   				<c:when test="${incident.contact == false}">
   					Caller does not want contact
   				</c:when>
   				<c:otherwise>
   					Caller does want contact
   				</c:otherwise>
   			</c:choose>
   		</p>
   		<p>Description: <c:out value="${incident.description}"/></p>
   		<p>
   			<c:choose>
   				<c:when test="${incident.isHandled == false}">
   					Incident is still pending
   				</c:when>
   				<c:otherwise>
   					A unit is assigned
   				</c:otherwise>
   			</c:choose>
   		</p>
   		<a href="/incident/dashboard"><button id="button">Dashboard</button></a>
   </div>
</body>
</html>