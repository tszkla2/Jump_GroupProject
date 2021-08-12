<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Patron-menu</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">

</head>
<body >
	
	<div class="container">

		<h1 class="display-4">Signed in as a patron</h1>
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
		
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav">
					
					
						<a class="nav-link" 
							   aria-current="page" 
							   href="<%= request.getContextPath() %>/viewCatalogue">View Catalogue</a> 
					
						<a class="nav-link" 
						   aria-current="page" 
						   href="<%= request.getContextPath() %>/checkoutbook">Checkout Book</a> 
						
						<a class="nav-link" 
							aria-current="page" 
						   href="<%= request.getContextPath() %>/returnbook">Return Book</a> 
						   
						   <a class="nav-link" 
							aria-current="page"
							pos 
						   href="<%= request.getContextPath() %>/logout">Logout</a> 
						
					</div>
				</div>
				
				
				
				
				
			</div>
		</nav>