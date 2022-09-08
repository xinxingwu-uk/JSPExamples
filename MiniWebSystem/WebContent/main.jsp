<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import="com.util.*" %>
<%@ page import="com.product.*" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//获取 session 中的 username;
String username = (String)session.getAttribute("username");
//获取从 servlet ProductActiion 中 传递的参数(数据库查询的结果)
List<Map<String,Object>> list =(List<Map<String,Object>>) request.getAttribute("listProduct");
// 获取 分页对象
DividePage dividePage = (DividePage) request.getAttribute("dividePage");
// 获取查询的关键词
String productName = (String) request.getAttribute("productName");
if(list==null){
	//第一次进 main.jsp页面，默认加载所有的产品
	ProductService service = new ProductDao(); 
	int totalRecord = service.getItemCount("");
	dividePage = new DividePage(5,totalRecord,1);
	int start = dividePage.fromIndex();
	int end = dividePage.toIndex();
	list = service.listProduct("", start, end);
}
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function searchProduct(){
		var th = document.form2;
		th.action="<%=path%>/servlet/ProductAction?action_flag=search";
		th.submit();
	}
	
	function first(){
		
		window.location.href = "<%=path%>/servlet/ProductAction?action_flag=search&pageNum=1";
		
	}
	function next(){
		
		window.location.href = "<%=path%>/servlet/ProductAction?action_flag=search&pageNum=<%=dividePage.getCurrentPage()+1%>";		
	
	}
	function forward(){
		
		window.location.href = "<%=path%>/servlet/ProductAction?action_flag=search&pageNum=<%=dividePage.getCurrentPage()-1%>";
		
	}
	function end(){
		
		window.location.href = "<%=path%>/servlet/ProductAction?action_flag=search&pageNum=<%=dividePage.getPageCount() %>";
			
	}
	
	function changePage(currentPage){
	
		window.location.href = "<%=path%>/servlet/ProductAction?action_flag=search&pageNum="+currentPage;
	
	}
	 
	function selectAll(flag){
		
		var ids = document.getElementsByName("ids");
		for(var i = 0 ; i < ids.length ; i++){
			ids[i].checked = flag;
		}
	
	}
	
	function getSelectedCount(){
	
		var ids = document.getElementsByName("ids");
		var count = 0;
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			ids[i].checked==true?count++:0;					
		}
		return count;
	
	}
	
	function del(){
	
		if(getSelectedCount()==0){
			
			alert("至少选择一个删除项！");
			return;
		
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/ProductAction?action_flag=del";
		th.submit();		
	
	}
	
	function getSelectedValue(){
		var ids = document.getElementsByName("ids");
		
		for(var i = 0 ; i < ids.length ; i++)
		{
						
			if(ids[i].checked){
				return ids[i].value;
			}				
		}
		
	}
	
	function view(){
	
		if(getSelectedCount()<1){
			
			alert("至少选择一个查看项！");
			return;
		
		}else if(getSelectedCount()>1){
			alert("只能选择一个查看项！");
			return;
		}
		
		var th = document.form1;
		th.action="<%=path%>/servlet/ProductAction?action_flag=view&proid="+getSelectedValue();
		th.submit();		
	
	}
	
	function logout(){
	
	window.location.href="<%=path %>/servlet/LogoutAction?action_flag=logout";
		
	}
	
	
	</script>

  </head>
  
  <body>
   <div>
   <table width=60% align="center">
   <tr>
   		<td align="left"><font size=2>欢迎您的光临，<%=username%><br><a href="javascript:logout();">退出</a></font></td>
   </tr>
   	<tr>
   		<td align="center">
   		<form name = "form2" action="" method="post">
   		<table>
   			<tr>
   				<td colspan="2">产品信息查询</td>
   				
   			</tr>
   			<tr>
   				<td >产品名称</td>
   				<td ><input type="text" name="proname" value="<%= productName!=null?productName:"" %>"/></td>
   				
   			</tr>
   			
   			<tr>
   				<td colspan="2" align="center">
   					<button type="button" onclick="searchProduct()" >查询</button>
   					<button type="button" onclick="javascript:location.href='<%=path %>/addProduct.jsp'">添加</button>   					
   					
   				</td>   				
   			</tr>   		
   		</table>  	
   		</form>	
   			
   		</td>
   	</tr>
   	
   	<tr>
   		<td height=50> </td>
   	</tr>
   	<tr>
   		<td> 查询结果</td>
   	</tr>
   	
   	<tr>
   	 	<td >
   	 	<form name="form1" action="" method="post">
   		<table border=1 width=100%>
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="checkall" onclick="javascript:selectAll(this.checked);" /></td>
   				<td width=30%>产品名称</td>
   				<td width=30%>产品产地</td>
   				<td>产品价格</td>
   			
   			</tr>
   			<%
   			if(list!=null && !list.isEmpty()){
   			
   				for(Map<String,Object> map :list){%>
   			
   				<tr align="center">
   				<td width=10%><input type="checkbox" name="ids" value="<%=map.get("proid") %>"/></td>
   				<td width=30%><%=map.get("proname") %></td>
   				<td width=30%><%=map.get("proaddress") %></td>
   				<td><%=map.get("proprice") %></td>
   				
   				<%}
   			
   			
   			}else{%>
   			
   			<tr align="center">
   				<td width=10%><input type="checkbox" name="" /></td>
   				<td width=30%></td>
   				<td width=30%></td>
   				<td></td>
   			
   			</tr><%
   			
   			}   			
   			 %>
   			
   	
   			
   		
   		</table>   		
   		</form>
   		</td>
   	
   	</tr>
   	
   	<tr>
   		<td>
   			<button type="button" onclick="javascript:del();">删除</button>
   			<button type="button" onclick="javascript:view();" >查看</button>
   		
   		</td>
   	</tr>
   	
   	<tr>
   		<td colspan="4" align="center">
   			共<%=dividePage.getPageCount()  %>页    
   			<a href="javascript:first();">首页</a>   
   			<a href="javascript:forward();">上一页</a> 
   			<a href="javascript:next();">下一页</a> 
   			<a href="javascript:end();">尾页</a> 
   			跳转到<select name="select" onchange="changePage(this.value)">
   			
   			<%
   			int pageCount = dividePage.getPageCount();
   			if(pageCount>0){
   			for(int i = 1 ; i<=pageCount;i++){%>
   			
   			<option value="<%=i %>" <%= (i==dividePage.getCurrentPage()?"selected":"")%>>  <%=i %>
   			</option>
   			
   			<%			
   			}
   			
   			}else{// 无记录
   				%>
   				<option value="1">1</option>   
   			 <%}			
   			
   			%>
   					
   			</select>
   		
   		</td>
   	</tr>
   			
   
   
   
   
   </table>
   
   
   
   </div>
   
   
  </body>
</html>