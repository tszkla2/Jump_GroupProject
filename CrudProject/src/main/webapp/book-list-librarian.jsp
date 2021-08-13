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
				<th>Rented</th>
				<th>Added to Lib</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book" items="${ allBooks }">
				
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
						<c:out value="${ book.rented }" />
					</td>
					<td>
						<c:out value="${book.added_to_library}" />
					</td>
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>