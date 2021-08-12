<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Catalog</title>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">

</head>
<body >
	
	<div class="container">

		<h1 class="display-4">Cognixia JUMP Library</h1>
	
		<nav class="navbar navbar-expand-lg navbar-light bg-light">
			<div class="container-fluid">
			
<!-- 				The home button-->				
 				<a class="navbar-brand" href="<%= request.getContextPath() %>/">Home</a>
				
				<button class="navbar-toggler" type="button"
					data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup"
					aria-controls="navbarNavAltMarkup" aria-expanded="false"
					aria-label="Toggle navigation">
					<span class="navbar-toggler-icon"></span>
				</button>
				
				
				<!-- 				Login buttons-->				
				
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav">
					
						<a class="nav-link" 
						   aria-current="page" 
						   href="<%= request.getContextPath() %>/about">About</a> 
						
						<a class="nav-link" 
						   href="<%= request.getContextPath() %>/login">Login</a> 
						
					</div>
				</div>
				
				
				
			</div>
		</nav>
