<%@ page contentType="text/html;charset=GB18030" pageEncoding="GB18030"
	language="java" import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>

	<%
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager
					.getConnection("jdbc:sqlite:/Users/xinxingwu/note.db");
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery("select * from Info");
	%>
	<table border="1" width="100%" cellspacing="0" cellpadding="0"
		align="center" bordercolorlight="#CCCCFF" bordercolordark="#FFFFFF">
		<tr bgcolor="#FFFFFF">
			<td width="15%" height="25" align="center"><i>作者</i></td>
			<td width="28%" height="25" align="center"><i>发表时间</i></td>
			<td width="22%" height="25" align="center"><i>Email</i></td>
			<td width="35%" height="25" align="center"><i>留言内容</i></td>
		</tr>
		<%
			while (rs.next()) {
					out.println("<tr><td align=center><font size=2 color=#999999>"
							+ rs.getString("username") + "</td>");
					out.println("<td><font size=2color=#999999>"
							+ rs.getString("Email") + "</font></td>");
					out.println("<td><font size=2 color=#999999>"
							+ rs.getString("doc") + "</font></td>");
					out.println("<td><font size=2 color=#999999>"
							+ rs.getString("URL") + "</font></td＞＜/tr>");
				}

				//out.close();

				/* while (rs.next()) {
						out.println("＜tr＞＜td align=center＞＜font size=2 color=#999999＞"
								+ rs.getString("username") + "＜/td＞");
						out.println("＜td＞＜font size=2color=#999999＞"
								+ rs.getString("Email") + "＜/font＞＜/td＞");
						out.println("＜td＞＜font size=2 color=#999999＞"
								+ rs.getString("doc") + "＜/font＞＜/td＞");
						out.println("＜td＞＜font size=2 color=#999999＞"
								+ rs.getString("URL") + "＜/font＞＜/td＞＜/tr＞");
					} */
				rs.close();
				con.close();
			} catch (Exception e) {
				out.println(e.getMessage());
			}
		%>
	</table>
	<br>
		<a href="Note.jsp">继续填写留言</a>
</body>
</html>