<%@ include file="patron_header.jsp" %>

<div class="container">

	<form action="<%= "updatePatronAccount" %>" method="get" >
	
		<c:if test="${ patron != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="id" value="<c:out value='${ patron.getId() }' />">
		
		</c:if>
	  
      <div class="form-group">
	    
	    <label for="title">First Name</label>
	    <input type="text" class="form-control" id="firstName" name="firstName" 
	    	value="<c:out value='${patron.first_name }' />" required>
	    
	  </div>

      <div class="form-group">
	    
	    <label for="title">Last Name</label>
	    <input type="text" class="form-control" id="lastName" name="lastName" 
	    	value="<c:out value='${patron.last_name }' />" required>
	    
	  </div>

	  <div class="form-group">
	    
	    <label for="title">Username</label>
	    <input type="text" class="form-control" id="username" name="username" 
	    	value="<c:out value='${patron.username }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="description">Password</label>
	    <input type="password" class="form-control" id="password" name="password"
	    	value="<c:out value='${ patron.password }' />" required>
	    
	  </div>
	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>