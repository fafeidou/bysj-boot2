<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/tag.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 引用jquery easy ui的js库及css -->
<LINK rel="stylesheet" type="text/css"
	href="/res/js/easyui/styles/default.css">
<%@ include file="/WEB-INF/back_page/common_css.jsp"%>
<%@ include file="/WEB-INF/back_page/common_js.jsp"%>

<title>课题列表</title>

<script type="text/javascript">
	//datagrid列定义
	var columns_v = [ [
			{
				field : 'thesisTitle',//对应json中的key
				title : '论文标题',
				width : 250,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					return row.thesistopic.thesisTitle;
				}
			},
			{
				field : 'topicState',//对应json中的key
				title : '状态',
				width : 150,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					if (row.thesistopic.topicState == '0') {
						return "教研室未审核";
					} else if (row.thesistopic.topicState == '1') {
						return "教研室未通过";
					} else if (row.thesistopic.topicState == '2') {
						return "院系未审核";
					} else if (row.thesistopic.topicState == '3') {
						return "院系审核未通过";
					} else if (row.thesistopic.topicState == '4') {
						return "等待选择";
					} else if (row.thesistopic.topicState == '5') {
						return "等待分配";
					} else if (row.thesistopic.topicState == '6') {
						return "完成";
					}
				}

			},
			{
				field : 'studentName',//对应json中的key
				title : '学生姓名',
				width : 120,
			},
			{
				field : 'opt3',
				title : '课题详细信息',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:viewDetails('"
							+ row.thesistopic.thesisTopicId + "')>查看详细</a>";
				}
			},
			{
				field : 'opt4',
				title : '修改课题',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:updateThesisTopic('"
							+ row.thesistopic.thesisTopicId + "')>修改</a>";
				}
			} ] ];

	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnadd',
		text : '添加',
		iconCls : 'icon-add',
		handler : function() {
			//打开一个窗口，用户添加页面
			//参数：窗口的title、宽、高、url地址
			createmodalwindow("添加论文", 400, 350,
					'/teacher/orTeacher/myselfThesistopic/toAddThesisTopic.do');
		}
	} ];
	function thesistopicExprot_callback(data) {
		//在这里提示信息中有文件下载链接
		message_alert(data);
	}
	//加载datagrid
	$(function() {
		$('#sysuserlist')
				.datagrid(
						{
							title : '个人课题列表',//数据列表标题
							nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
							striped : true,//条纹显示效果
							url : '/teacher/orTeacher/myselfThesistopic/listMyselfThesistopic.do',//加载数据的连接，引连接请求过来是json数据
							idField : 'teacherId',//此字段很重要，数据结果集的唯一约束(重要)，如果写错影响 获取当前选中行的方法执行
							loadMsg : '',
							columns : columns_v,
							pagination : true,//是否显示分页
							rownumbers : true,//是否显示行号
							pageList : [ 15, 30, 50 ],
							toolbar : toolbar_v
						});
	});
	//查询方法
	function queryuser() {
		//datagrid的方法load方法要求传入json数据，最终将 json转成key/value数据传入action
		//将form表单数据提取出来，组成一个json
		var formdata = $("#sysuserqueryForm").serializeJson();
		$('#sysuserlist').datagrid('load', formdata);
	}

	//查看详细信息
	function viewDetails(id) {
		//打开修改窗口
		createmodalwindow("课题详细信息", 500, 600,
				'/teacher/orTeacher/myselfThesistopic/viewDetails.do?thesisTopicId='
						+ id);
	}
	//修改课题信息
	function updateThesisTopic(id) {
		//打开修改窗口
		createmodalwindow("课题详细信息", 500, 400,
				'/teacher/orTeacher/myselfThesistopic/toEditThesisTopic.do?thesisTopicId='
						+ id);
	}
</script>

</head>
<body>
	<!-- html的静态布局 -->
	<form id="sysuserqueryForm" action="" method="post">
		<!-- 查询条件 -->
		<TABLE class="table_search" style="margin-left: 6px">
			<TBODY>
				<TR>
					<TD class="left">论文来源类型：</TD>
					<td><select id="" name="topicSourceTypeId"
						style="width: 100px">
							<option value="">全部</option>
							<c:forEach items="${topicsourcetypes}" var="topicsourcetype">
								<option value="${topicsourcetype.topicSourceTypeId}">${topicsourcetype.sourceTypeName}</option>
							</c:forEach>
					</select></td>
					<TD class="left">论文类型：</TD>
					<td><select id="" name="topicTypeId" style="width: 100px">
							<option value="">全部</option>
							<c:forEach items="${topictypes}" var="topictype">
								<option value="${topictype.topicTypeId}">${topictype.typeName}</option>
							</c:forEach>
					</select></td>
					<TD class="left">状态：</TD>
					<td><select id="" name="topicState" style="width: 100px">
							<option value="">全部</option>
							<option value="0">教研室未审核</option>
							<option value="1">教研室未通过</option>
							<option value="2">院系未审核</option>
							<option value="3">院系审核未通过</option>
							<option value="4">等待选择</option>
							<option value="5">等待分配</option>
							<option value="6">完成</option>
					</select></td>
					<TD class="left">论文标题：</td>
					<td><INPUT type="text" name="thesisTitle" /></TD>
					<TD class="left">毕业年份：</td>
					<td><INPUT type="text" name="graduationYear" /></TD>
					<td><a id="btn" href="#" onclick="queryuser()"
						class="easyui-linkbutton" iconCls='icon-search'>查询</a></td>
				</TR>
			</TBODY>
		</TABLE>
		<!-- 查询列表 -->
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="99%" align=center>
			<TBODY>
				<TR>
					<TD>
						<table id="sysuserlist"></table>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>
</body>
</html>