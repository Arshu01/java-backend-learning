<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
// <%@ page import="java.time.LocalDate" %>

<%-- 
<%! LocalDate ld=LocalDate.now();
%>
<%= ld %>
--%>



<%-- USING WITH SERVLET1  
<% String user=(String)session.getAttribute("myUsername"); %>
<% String pass=(String)session.getAttribute("myPassword"); %>
<%= user %>		
<%= pass %>
                                                           --%>
                                                           
       <%-- USING WITHOUT SERVLET1  ONLY FIRST.JSP--%>   
       
                                                     
<% String user=request.getParameter("username"); %>     
<% String pass= request.getParameter("password"); %>   

<%= "Hii your username is"+ user+"and password is "+pass %>


<h1>Your details printed by Expression Language</h1>
${param.username}   
${param.password}                                                                         
</body>
</html> 