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
    <link rel="stylesheet" href="/css/adminDash.css"><!-- change to match your file/naming structure...this is for your style sheet -->
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
   		<h1>Welcome, <c:out value="${admin.firstName}"/></h1>
   		<p><a href="/admin/logout">Logout</a></p>
   	</div>
   <div id="container">
   		<div class="h3">
   			<h3>All Users</h3>
   			<p>Click a user to view their information</p>
   		</div>
   		<div id="users">
   			<table class="table table-striped">
   				<thead>
   					<tr>
   						<th scope="col" class="text-centered">First Name</th>
   						<th scope="col" class="text-centered">Last Name</th>
   						<th scope="col" class="text-centered">Date Created</th>
   					</tr>	
   				</thead>
   				<tbody>
   					<c:forEach var="user" items="${user}">
   						<tr>
   							<td class="text-center"><a href="/user/admin/view/${user.id}"><c:out value="${user.firstName}"/></a></td>
   							<td><c:out value="${user.lastName}"/></td>
   							<td><fmt:formatDate type="date"  value="${user.createdAt}"/></td>
   						</tr>
   					</c:forEach>
   				</tbody>
   			</table>
   			<a href="/user/"></a>
   				
   		</div>
   		<div class="h3">
   			<h3>All Signals</h3>
   			<p>Click a signal to view/edit/delete it</p>
   		</div>
   		<div id="signals">
   			<table class="table table-striped">
   				<thead>
   					<tr>
   						<th scope="col" class="text-centered">Number</th>
   						<th scope="col" class="text-centered">Name</th>
   						<th scope="col" class="text-centered">Priority</th>
   					</tr>
   				</thead>
   				<tbody>
   					<c:forEach var="signal" items="${signal}">
   						<tr>
   							<td><c:out value="${signal.number}"/></td>
   							<td class="text-center"><a href="/signal/view/${signal.id}"><c:out value="${signal.name}"/></a></td>
   							<td><c:out value="${signal.priority}"/></td>
   						</tr>
   					</c:forEach>
   				</tbody>
   			</table>
   			<a href="/signal/create"><button class="btn btn-light">Create Signal</button></a>
   		</div>
   		<div class="h3">
   			<h3>All Units</h3>
   			<p>Click a unit to view/update/delete it</p>
   		</div>
   		<div id="units">
   			<table class="table table-striped">
   				<thead>
   					<tr>
   						<th scope="col" class="text-centered">Call Sign</th>
   						<th scope="col" class="text-centered">Date Created</th>
   						<th scope="col" class="text-centered">Date Updated</th>
   					</tr>
   				</thead>
   				<tbody>
   					<c:forEach var="unit" items="${unit}">
   						<tr>
   							<td class="text-center"><a href="/unit/view/${unit.id}"><c:out value="${unit.name}"/></a></td>
   							<td><fmt:formatDate type="date"  value="${unit.createdAt}"/></td>
   							<td><fmt:formatDate type="date" value="${unit.updatedAt}"/></td>		
   						</tr>
   					</c:forEach>
   				</tbody>
   			</table>
   			<a href="/unit/create"><button class="btn btn-light">Create Unit</button></a>
   		</div>
   	</div>
</body>
</html>