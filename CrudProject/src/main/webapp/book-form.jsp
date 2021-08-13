<%@ include file="librarian_header.jsp" %>

<div class="container">

	<% String formType = "update"; %>

	<c:if test="${ book != null }">
	
		<h1>Update Book</h1>
		
	</c:if>
	
	<c:if test = "${ book == null }">
		
		<h1>Add New Book</h1>
		
		<% formType = "add"; %>
		
	</c:if>
	
	<!-- if need to add a new product: action = add
	     if you need to edit a product: action = update -->
	<form action="<%= formType %>" method="get" >
	
		<c:if test="${ book != null }">
		
			<%-- hidden input, won't show up on the page, but will pass our id for the product --%>
			<input type="hidden" name="isbn" value="<c:out value='${ book.isbn }' />">
		
		</c:if>
		
	  <div class="form-group">
	    
	    <label for="title">Title</label>
	    <input type="text" class="form-control" id="title" name="title" 
	    	value="<c:out value='${ book.title }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="rented">Availability</label>
	    <input type="boolean" class="form-control" id="rented" name="rented"
	    	value="<c:out value='${ book.rented }' />" required>
	    
	  </div>
	  
	  <div class="form-group">
	  
	    <label for="description">Description</label>
	    <input type="text" class="form-control" id="description" name="description"
	    	value="<c:out value='${ book.description }' />" required>
	    
	  </div>

	  <%-- <div class="form-group">
	  
	    <label for="addTo">Added to Library</label>
	    <input type="text" class="form-control" id="addTo" name="addTo"
	    	value="<c:out value='${ book.added_to_library }' />" required>
	    
	  </div> --%>

	  
	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>