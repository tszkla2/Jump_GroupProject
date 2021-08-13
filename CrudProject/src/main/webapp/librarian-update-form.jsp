<%@ include file="librarian_header.jsp" %>

<div class="container">

	<form action="<%= "updateLibrarianAccount" %>" method="get" >
	
		<c:if test="${ librarian != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="id" value="<c:out value='${ librarian.getlibrarian_Id() }' />">
		
		</c:if>
		
	  <div class="form-group">
	    
	    <label for="title">Username</label>
	    <input type="text" class="form-control" id="username" name="username" 
	    	value="<c:out value='${librarian.username }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="description">Password</label>
	    <input type="password" class="form-control" id="password" name="password"
	    	value="<c:out value='${ librarian.password }' />" required>
	    
	  </div>
	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>