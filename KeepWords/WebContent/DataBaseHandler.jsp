<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body bgcolor="#FFFFFF">
	<%@ page import="java.sql.*,java.util.*"%>

	<%
		Connection con = null;

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:/Users/xinxingwu/note.db");
			Statement stat = con.createStatement();
			String sql1 = "create table if not exists Info (username varchar(32),email varchar(32),doc varchar(32),url varchar(32))"; //,PRIMARY KEY(username)
			stat.executeUpdate(sql1);
			con.close();
	%>
	<jsp:forward page="Success.jsp" />
	<%
		} catch (Exception e) {
			out.println(e.getMessage());
	%>
	<jsp:forward page="Failure.jsp" />
	<%
		}
	%>