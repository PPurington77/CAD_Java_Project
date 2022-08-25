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
	   <h1>Create Incident</h1>
	   <form:form action="/incident/create" method="post" modelAttribute="newIncident">
	    	
	    	
	    	
	    	<form:label path="signal">Incident Type:</form:label>
	    	<form:select path="signal">
	    		<c:forEach var="signal" items="${signal}">
	    			<form:option value="${signal.id}"><c:out value="${signal.name}"/></form:option>
	    		</c:forEach>
	    	</form:select>
	    	
	    	<form:label path="streetNumber">Street Number:</form:label>
	    	<form:errors path="streetNumber" class="alert-danger"/>
	    	<form:input type="number" path="streetNumber"/>
	    	
	    	<form:label path="streetDirection">Street Direction:</form:label>
	    	<form:errors path="streetDirection" class="alert-danger"/>
	    	<form:input type="text" path="streetDirection"/>
	    	
	    	<form:label path="streetName">Street Name:</form:label>
	    	<form:errors path="streetName" class="alert-danger"/>
	    	<form:input type="text" path="streetName"/>
	    	
	    	<form:label path="city">City:</form:label>
	    	<form:errors path="city" class="alert-danger"/>
	    	<form:input type="text" path="city"/>
	    	
	    	<form:label path="callerName">Callers Name:</form:label>
	    	<form:errors path="callerName" class="alert-danger"/>
	    	<form:input type="text" path="callerName"/>
	    	
	    	<form:label path="phone">Phone Number:</form:label>
	    	<form:errors path="phone" class="alert-danger"/>
	    	<form:input type="text" path="phone"/>
	    	
	    	<form:label path="contact">Caller wants contact?:</form:label>
	    	<form:errors path="contact" class="alert-danger"/>
	    	<form:checkbox path="contact"/>
	    	
	    	<form:label path="description">Description:</form:label>
	    	<form:errors path="description" class="alert-danger"/>
	    	<form:textarea path="description" style="width: 100%"/>
	    	
	    	<form:label path="isHandled">Has the incident been dispatched?:</form:label>
	    	<form:errors path="isHandled" class="alert-danger"/>
	    	<form:checkbox path="isHandled"/>
	    	
	    	<button type="submit" id="button">Create</button>
	   
	   </form:form>
   </div>
</body>
</html>