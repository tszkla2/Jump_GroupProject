<%@page import="java.util.*;"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html><head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Book</title></head>

<body><table align="center"><%

List booklist=new ArrayList();
booklist=(ArrayList)request.getAttribute("booklist");
if(booklist!=null && booklist.size()>0 ){%>
<h2 align="center">Book List</h2><tr>

<th>Rank</th>
<th>BookName</th>
<th>Author</th>
<th>Genre</th>
<th>Publisher</th>
<th>ISBN</th>
<th>Quantity</th>
<th>Watchers</th>
<th>Rented</th>
<th>Bought</th>
<th>Rating</th>
<th>Picture</th>

</tr>

<%
for(int i=0;i<booklist.size();i++){
List book=(List)booklist.get(i);
%>

<tr>
<td><%=book.get(1) %></td>
<td><%=book.get(2) %></td>
<td><%=book.get(3) %></td>
<td><%=book.get(4) %></td>
<td><%=book.get(5) %></td>
<td><%=book.get(6) %></td>
<td><%=book.get(7) %></td>
<td><%=book.get(8) %></td>
<td><%=book.get(9) %></td>
<td><%=book.get(10) %></td>
<td><%=book.get(11) %></td>
<td><%=book.get(12) %></td>

</tr>

<%

}}else{

%>
Book Details Not Available
<%}%>

</table></body></html>