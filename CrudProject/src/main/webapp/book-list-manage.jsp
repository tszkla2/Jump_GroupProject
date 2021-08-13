<%@ include file="librarian_header.jsp" %>

<div class="container">
	
	<h1>Book List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Description</th>
				<th>Manage</th>
                
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book" items="${ allBooks }">
                <c:if test="${ book.rented == false}">
				
				<tr>
					<td>
						<c:out value="${ book.isbn }" />
					</td>
					
					<td>
						<c:out value="${ book.title }" />
					</td>
					
					<td>
						<c:out value="${ book.description }" />
					</td>
					
					<td>
						
						<a href="edit?isbn=<c:out value='${ book.isbn }' />">
							<button class="btn btn-primary">Edit</button>
						</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="delete?isbn=<c:out value='${ book.isbn }' />">
						
							<button class="btn btn-danger">Delete</button>
							
						</a>
						
					</td>

				</tr>
                </c:if>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>