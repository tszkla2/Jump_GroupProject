<%@ include file="not_logged_in_header.jsp" %>
 
<div class="container">
	

		<% String formType = "trylogin"; %>


	<!-- if need to add a new product: action = add
	     if you need to edit a product: action = update -->
	<form action="<%= formType %>" method="post" >
	<!-- <form action="LoginServlet" method="post"> -->
	
		
		
	  <div class="form-group">
	    
	    <label for="username">Username:</label>
	    <input type="text" class="form-control" id="username" name="username" required>
	    <%-- <input type="text" class="form-control" id="username" name="username" 
	    	value="<c:out value='${ patron.username }' />" required> --%>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="password">Password:</label>
	    <input type="text" class="form-control" id="password" name="password" required>
	    <%-- <input type="text" class="form-control" id="password" name="password"
	    	value="<c:out value='${ patron.password }' />" required> --%>
	    
	  </div>
	  
	<INPUT TYPE="radio" class="form-check-input" type="checkbox" name="choice" value="0"/>Patron
	<INPUT TYPE="radio"  class="form-check-input" type="checkbox" name="choice" value="1"/>Librarian
	
	  <br>
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
		
<%--		<%  formType = "librarian"; %> --%>
	 
	</form>


</div>

<%-- 
<%@ include file="footer.jsp" %>
	<c:if test="${ product != null }">
	
		<h1>Update Product</h1>
		
	</c:if>
	
	<c:if test = "${ product == null }">
		
		<h1>Please Log In</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	 --%>