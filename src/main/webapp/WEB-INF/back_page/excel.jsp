<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'excel.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	function uploadPic(){
	//定义参数
	var options = {
		url : "/uploadExcel.do",
		dataType : "json",
		type :  "post",
		success : function(data){
			//回调 二个路径  
			//url
			//path
			//$("#allImgUrl").attr("src",data.url);
			//$("#path").val(data.path);
		}
	};
	alert("sdsdf");
	//jquery.form使用方式
	$("#jvForm1").ajaxSubmit(options);
	
}
</script>
  </head>
  
  <body>
  <form id="jvForm1" action="/uploadExcel.do" method="post" enctype="multipart/form-data">
  	<input type="file"  name="excel"/>
  	<input type="submit" value="提交" >
  </form>
  <form id="jvForm" action="downloadExcel.do" method="post" enctype="multipart/form-data">
  	<input type="submit" value="导出数据"/>
  </form>
  </body>
</html>
