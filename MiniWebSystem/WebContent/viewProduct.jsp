<%@ page language="java" import="java.util.*" pageEncoding="utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
Map<String,Object> map = (Map<String,Object>)request.getAttribute("productMap");

%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看产品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div align="center">
  		
  		<table width=60% style="margin:auto">
  			
  			<tr>
  				<td height=100>
  					
  				</td>
  			</tr>
  			<tr>
  				<td >
  					产品信息
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<table width = 99% border =1 >
	  					<tr align="center">
	  						<td width = 20%>产品名称</td>
	  						<td width = 30%><%=map.get("proname") %></td>
	  						<td width = 20%>产品价格</td>
	  						<td><%=map.get("proprice") %></td>
	  						
	  					
	  					</tr>
	  					<tr align="center">
	  						<td >产品产地</td>
	  						<td colspan=3><%=map.get("proaddress") %></td>					
	  						
	  					
	  					</tr>
	  					<tr align="center">
	  						<td>产品图片</td>
	  						<td colspan=3><img src="/Users/xinxingwu/"+<%=map.get("proimage") %>></td>	  						
	  					<%=map.get("proimage") %>
	  					</tr>
  					
  					
  					</table>
  				</td>
  			</tr>
  			<tr>
  				<td align="center">
  					<button type="button" onclick="javascript:location.href='<%=path %>/main.jsp'">确定</button>
  					<button type="button" onclick="javascript:history.go(-1)">返回</button>
  				</td>
  			</tr>
  		
  		</table>
  		
  	
  </div>
  </body>
</html>