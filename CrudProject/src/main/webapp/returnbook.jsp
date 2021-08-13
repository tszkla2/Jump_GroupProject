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
				<th>Checked Out</th>
				<th>Due Date</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book_date" items="${ bookDate }">
				<c:if test="${ empty book_date.returned }">
				<tr>
					<td>
						<c:out value="${ book_date.book.isbn }" />
					</td>
					
					<td>
						<c:out value="${ book_date.book.title }" />
					</td>
					
					<td>
						<c:out value="${ book_date.book.description }" />
					</td>
					<td>
						<c:out value="${ book_date.checkedout }" />
					</td>
					
					<td>
						<c:out value="${ book_date.due_date }" />
					</td>
					
				
					<td>
						<a href="return?isbn=<c:out value='${ book_date.book.isbn }' />">
							<button class="btn btn-primary">Return</button>
						</a>
					</td>
				</tr>
				</c:if> 
			
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
				<th>Checked Out</th>
				<th>Due Date</th>
				<th>Returned</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="book_date" items="${ bookDate }">
				<c:if test="${not empty book_date.returned }">
				<tr>
					<td>
						<c:out value="${ book_date.book.isbn }" />
					</td>
					
					<td>
						<c:out value="${ book_date.book.title }" />
					</td>
					
					<td>
						<c:out value="${ book_date.book.description }" />
					</td>
					<td>
						<c:out value="${ book_date.checkedout }" />
					</td>
					
					<td>
						<c:out value="${ book_date.due_date }" />
					</td>
					<td>
						<c:out value="${ book_date.returned }" />
					</td>
					
				</tr>
				</c:if>
			</c:forEach>
		
		</tbody>
	
	</table>
	
	

</div>
 

<%@ include file="footer.jsp" %>