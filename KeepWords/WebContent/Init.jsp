<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>
��ӭʹ��
</title>
</head>
<body bgcolor="ffffff">
<%= new java.util.Date()%>
 <table border="0" align="center" width="60%">
  <form name="form" action="firstjudge.jsp" method="post">
    <tr>
     <td width="20%" align="center">�Ƿ����ʹ��: </td>
     <td><input type="radio" name="sex" value="1" checked>����&nbsp;<input type="radio" name="sex" value="1" >�� </td>
    </tr>
    <tr>
     <td align="center" colspan="2"><input type="submit" value="ȷ��" >&nbsp;<input type="reset" value="ȡ��" ></td>
    </tr>
  </form>
 </table>
</body>
</html>