<%@ include file="librarian_header.jsp" %>

<div class="container">

	<% String formType = "delete"; %>

		<h1>Delete Book</h1>
		
		
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

	  <button type="submit" 
	  		  class="btn btn-primary"
	  		  style="margin:10px">Submit</button>
	  
	</form>

</div>


<%@ include file="footer.jsp" %>