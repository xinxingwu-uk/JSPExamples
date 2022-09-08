<%@ page language="java" contentType="text/html; charset=GB18030"
	pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<body bgcolor="#FFFFFF">
	<%@ page import="java.sql.*,java.util.*,TransForData.*"%>

	<%
		Connection con = null;
		/* String username = new String(request.getParameter("username")
				.getBytes("ISO-8859-1"), "gb2312"); */
		String username = new String(DataTrans.gb2312ToUnicode(request.getParameter("username")));
				
		String email = new String(request.getParameter("email").getBytes(
				"ISO-8859-1"), "gb2312");
		String doc = new String(request.getParameter("doc").getBytes(
				"ISO-8859-1"), "gb2312");
		String url = request.getRemoteAddr(); 
		
		
		
		
		

		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:/Users/xinxingwu/note.db");
			Statement stat = con.createStatement();
			String sql1 = "create table if not exists Info (username varchar(32),email varchar(32),doc varchar(32),url varchar(32),PRIMARY KEY(username))";
			stat.executeUpdate(sql1);
			String sql2 = "insert into Info values(?,?,?,?);";
			PreparedStatement pstmt = con.prepareStatement(sql2);
			pstmt.setString(1, username);
			pstmt.setString(2, email);
			pstmt.setString(3, doc);
			pstmt.setString(4, url);
			pstmt.executeUpdate();
			pstmt.close();

			ResultSet rs = stat.executeQuery("select * from Info;");
			while (rs.next()) {
				System.out.print("username = " + rs.getString("username"));
				System.out.print("email = " + rs.getString("email"));
				System.out.println("doc = " + rs.getString("doc"));
				System.out.println("url = " + rs.getString("url"));
			}
			rs.close();
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