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
    <link rel="stylesheet" href="/css/incidentDashboard.css"><!-- change to match your file/naming structure...this is for your style sheet -->
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
			<li><a href="/incident/create">Create Call</a></li>
			<li><a href="/user/helpdesk">HelpDesk</a></li>
			<li><a href="/user/logout">Logout</a></li>
		</ul>
	</div>
	<div id="container">
		<h3>Assigned Incidents</h3>
		<div id="pending">
			<table id="pendingTable" class="table table-striped">
	   			<thead>
	   				<tr>
	   					<th scope="col" class="text-center">ID</th>
	   					<th scope="col" class="text-center">Priority</th>
	   					<th scope="col" class="text-center">Signal</th>
	   					<th scope="col" class="text-center">Update</th>
	   					<th scope="col" class="text-center">Delete</th>
	   					<th scope="col" class="text-center">Address</th>
	   					
	   				</tr>
	   			</thead>
	   			<tbody>
	   				<c:forEach var="incident" items="${incident}">
	   					<c:choose>
	   						<c:when test="${incident.isHandled == true}">
			   					<tr>
			   						<td class="text-center"><c:out value="${incident.id}"/></td>
			   						<td class="text-center"><c:out value="${incident.signal.priority}"/></td>
			   						<td class="text-center"><a href="/incident/view/${incident.id}">S<c:out value="${incident.signal.number}"/> <c:out value="${incident.signal.name}"/></a></td>
			   						<td class="text-center"><a href="/incident/update/${incident.id}"><button class="btn">Update</button></a></td>
			   							<td class="text-center">
				   							<form action="/incident/delete/${incident.id}" method="post">
											<input type="hidden" name="_method" value="delete"/>
											<input type="submit" value="Delete" class="btn"/>
											</form>
										</td>
			   						<td class="text-center"><c:out value="${incident.streetNumber}"/> <c:out value="${incident.streetDirection}"/> <c:out value="${incident.streetName}"/></td>
			   					</tr>
			   				</c:when>
						</c:choose>
	   				</c:forEach>
	   			</tbody>
	   		</table>
		</div>
		<h3>All Units</h3>
		<div id="units">
			<table id="unitTable" class="table table-striped">
	   			<thead>
	   				<tr>
	   					<th scope="col" class="text-center">Call Sign</th>
	   					<th scope="col" class="text-center">Status</th>
	   					<th scope="col" class="text-center">Date Updated</th>
	   				</tr>
	   			</thead>
	   			<tbody>
	   				<c:forEach var="unit" items="${unit}">
	   					<tr>
	   						<td class="text-center"><a href="/unit/up/${unit.id}"><c:out value="${unit.name}"/></a></td>
	   						<c:choose>
	   							<c:when test="${unit.isDispatched == false}">
	   								<td class="text-center">Available</td>
	   							</c:when>
	   							<c:otherwise>
	   								<td class="text-center">Dispatched <c:out value="${unit.incident.streetNumber}"/> <c:out value="${unit.incident.streetDirection}"/> <c:out value="${unit.incident.streetName}"/></td>
	   							</c:otherwise>
	   						</c:choose>
	   						<td class="text-center"><fmt:formatDate type="date" value="${unit.updatedAt}"/></td>		
	   					</tr>
	   				</c:forEach>
	   			</tbody>
	   		</table>
		</div>
				<h3>Pending Incidents</h3>
		<div id="pending">
			<table id="pendingTable" class="table table-striped">
	   			<thead>
	   				<tr>
	   					<th scope="col" class="text-center">ID</th>
	   					<th scope="col" class="text-center">Priority</th>
	   					<th scope="col" class="text-center">Signal</th>
	   					<th scope="col" class="text-center">Actions</th>
	   					<th scope="col" class="text-center">Address</th>
	   					
	   				</tr>
	   			</thead>
	   			<tbody>
	   				<c:forEach var="incident" items="${incident}">
	   					<c:choose>
	   						<c:when test="${incident.isHandled == false}">
			   					<tr>
			   						<td class="text-center"><c:out value="${incident.id}"/></td>
			   						<td class="text-center"><c:out value="${incident.signal.priority}"/></td>
			   						<td class="text-center"><a href="/incident/view/${incident.id}">S<c:out value="${incident.signal.number}"/> <c:out value="${incident.signal.name}"/></a></td>
			   						<td class="text-center"><a href="/incident/update/${incident.id}"><button class="btn">Update</button></a></td>
			   						<td class="text-center"><c:out value="${incident.streetNumber}"/> <c:out value="${incident.streetDirection}"/> <c:out value="${incident.streetName}"/></td>
			   					</tr>
			   				</c:when>
						</c:choose>
	   				</c:forEach>
	   			</tbody>
	   		</table>
		</div>
	</div>
</body>
</html>