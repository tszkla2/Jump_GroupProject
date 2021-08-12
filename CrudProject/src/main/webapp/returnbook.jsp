<%@ include file="patron_header.jsp" %>

<div class="container">
	
	<h1>My Books</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Description</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book" items="${ patronBooks }">
				
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
						<a href="return?isbn=<c:out value='${ book.isbn }' />">
							<button class="btn btn-primary">Return</button>
						</a>
					</td>
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	
	
	
	<h1>History</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>#</th>
				<th>Title</th>
				<th>Description</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book" items="${ patronBooks }">
				
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
				
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	
	

</div>


<%@ include file="footer.jsp" %>