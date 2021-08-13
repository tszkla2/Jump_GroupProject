<%@ include file="librarian_header.jsp" %>

<div class="container">
	
	<h1>Patron List</h1>
	<br>
	<br>
	<table class="table table-striped">
	
		<thead>
			<tr>
				<th>id</th>
				<th>Name </th>
				<th>Username</th>
				<th>Status</th>
			</tr>
		</thead>
		
		<tbody>
		
			<c:forEach var="patron" items="${ allPatrons }">
				
				<tr>
					<td>
						<c:out value="${ patron.id }" />
					</td>
					
					<td>
						<c:out value="${ patron.first_name }" /> <c:out value="${ patron.last_name }" />
					</td>
					
					<td>
						<c:out value="${ patron.username}" />
					</td>
                    <c:if test="${ patron.account_frozen == false}">
                    <td>
						<a href="freeze?username=<c:out value='${ patron.username }' />">
							<button class="btn btn-primary">Freeze</button>
						</a>
					</td>
                    </c:if>

                    <c:if test="${ patron.account_frozen == true}">
                    <td>
						<a href="freeze?username=<c:out value='${ patron.username }' />">
							<button class="btn btn-primary">Unfreeze</button>
						</a>
					</td>
                    </c:if>
					
				</tr>
			
			</c:forEach>
		
		</tbody>
	
	</table>
	

</div>


<%@ include file="footer.jsp" %>