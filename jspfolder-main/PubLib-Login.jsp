<%@page language="java"contentType="text/html;charset=ISO-8859-1 %>
<%@ include file="header.jsp" %>
<%@page import="bean.LoginDao"%>
<%@taglib prefix="sec"uri="http://www.springframework.org/security/tags"%>
<jsp:useBean id="obj"class="bean.LoginBean"/>
<jsp:setProperty property="*"name=obj"/><hr/>

<div class="container">
<h3>Login Form</h3>

<sec:authentication property="principal.authorities"/>

<sec:authorize access="!isAuthenticated()">
Login
</sec:authorize>
<sec:authorize access="isAuthenticated()">
Logout
</sec:authorize>

<sec:authorize access="hasRole('ADMIN')">
    Manage Users
</sec:authorize>

<sec:authorize access="hasRole('PREMIUM')">
    Manage Users
</sec:authorize>

<sec:authorize access="hasRole('STANDARD')">
    Manage Users
</sec:authorize>

<sec:authorize access="hasRole('GUEST')">
    Manage Users
</sec:authorize>

<sec:authorize access="isAuthenticated()">
    Welcome Back, <sec:authentication property="name"/>
</sec:authorize>

<% 
String profile_msg=(String)request.getAttribute("profile_msg");
if(profile_msg!=null){out.print(profile_msg);}

String login_msg=(String)request.getAttribute("login_msg");
if(login_msg!=null){out.print(login_msg);}

boolean status=LoginDao.validate(obj);
if(status){out.println("Ur successfully logged in");
session.setAttribute("session","TRUE");}
else{out.print("Sorry, email or password error");
if(this.email!=email){out.print("wrong email");}
if(this.pass!=pass){out.print("wrong password");}

String uname=request.getParameter("txtusername");
String password=request.getParameter("txtpassword");
PreparedStatement pst=conn.prepareStatement("Select user_type from user where user_name = ? and password = ? ");
pst.setString(1,uname);
pst.setString(2,password);
ResultSet rst=pst.executeQuery();
while(rst.next()){String type=rst.getString("user_type");
if("admin".equals(type)){System.out.println(type);}
//response.sendRedirect("admin.jsp");
else if("seller".equals(type)){System.out.println(type);}
//response.sendRedirect("seller.jsp");
else if("buyer".equals(type)){System.out.println(type);}}
//response.sendRedirect("buyer.jsp");}%>

<jsp:include page="index.jsp"></jsp:include></jsp:include><% }%>


<form action="loginprocess.jsp"method="post">
Email:<input type="text"name="email"/><br/><br/>
Password:<input type="password"name="password"/?><br/><br/>
<input type="submit"value="login"/></form></div>

<form method="post" class="searchform" action="#">
<table class="pretty-table">
<tr><td>Username</td><td><input type="text" name="txtusername" class="textbox" /></td></tr>

<tr><td>Password</td><td><input type="password" name="txtpassword" class="textbox" /></td></tr>
<tr><td>Password</td><td><input type="button" name="forgotEither" class="button"onClick=('<!-- sends a code -->' /></td></tr>
<tr><td><input name="btnLogin" type="submit" value="Login" class="button"/></td><td><input name="btnCancel" type="reset" value="Cancel" class="button"/></td></tr>
<tr>
<td><a href="register.jsp">Back to registration page</a></td>
<td><a href="index.jsp">Back to home page</a></td>
</tr>
</table>
</form>
<!-- by A Willis -->