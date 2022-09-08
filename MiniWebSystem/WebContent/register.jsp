<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<title>注册新用户</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script type="text/javascript">
function dosubmit(){
	
	var th = document.form1;
	if(th.username.value==""){
		alert("用户名不能为空！");
		th.username.focus();
		return;
	}
	if(th.pswd.value==""){
		alert("密码不能为空！");
		th.pswd.focus();
		return;
	}
	th.action="<%=path%>/servlet/RegisterAction";
	th.submit();

}
function back(){
 	alert("退回主页！");
	th = document.form1;
	th.acton="<%=path%>
	/index.jsp";
		th.submit;
	}
</script>

</head>

<body>
	<div style="text-align: center">
		<form action="" name="form1" method="post">
			<table style="margin: auto">
				<tr>
					<td colspan="3">用户注册</td>
				</tr>
				<tr>
					<td>用户名：</td>
					<td><input type="text" name="username"></input></td>
					<td>必须填写！</td>
				</tr>
				<tr>
					<td>密 码：</td>
					<td><input type="password" name="pswd"></input></td>
					<td>必须填写！</td>

				</tr>
				<tr>

					<td colspan="3" align="center">
						<button type="button" name="" onclick="dosubmit()">确定</button>
						<button type="button" name="" value=""
							onclick="javascript:location.href='index.jsp'">返回</button>
					</td>
				</tr>

			</table>
		</form>

	</div>

</body>
</html>