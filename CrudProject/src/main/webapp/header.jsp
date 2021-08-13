<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Catalog</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-KyZXEAg3QhqLMpG8r+8fhAXLRk2vvoC2f3B09zVXn8CA5QIVfZOJ3BCsw2P0p/We"
	crossorigin="anonymous">

</head>
<body >
	
	<div class="container">


		<h1 class="display-4">Cognixia JUMP Library</h1>
	
		<nav class="navbar navbar-expand-lg navbar navbar-dark bg-primary">
			<div class="container-fluid">

			
<!-- 				<button class="btn"><i class="fa fa-home"></i> Home</button>
 -->				<a class="navbar-brand" href="<%= request.getContextPath() %>/"><i class="fa fa-home"></i>Home</a>
				
				<!-- 				Login buttons-->				
				
				<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
					<div class="navbar-nav">
					
						<a class="nav-link" 
						   aria-current="page" 
						   href="<%= request.getContextPath() %>/about"><i class="fas fa-question-square"></i>About</a> 
						
						<a class="nav-link" 
						   href="<%= request.getContextPath() %>/login"><i class="fas fa-sign-in-alt"></i>Login</a> 
						
					</div>
				</div>
				
				
				
			</div>
		</nav>