<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
</head>
<body>
<%= request.getParameter("use")%>
<%= request.getParameter("password")%>
<%
 String sex = request.getParameter("sex");
 if(sex.equals("1")) {
	 %>
	 <jsp:forward page="DataBaseHandler.jsp" />
	 <% 
   }else{
%>
<jsp:forward page="Index.jsp" />
<% 
   }%>
</body>
</html>