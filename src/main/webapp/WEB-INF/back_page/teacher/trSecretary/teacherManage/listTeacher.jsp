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

<title>教师列表</title>

<script type="text/javascript">
	//datagrid列定义
	var columns_v = [ [
			{
				field : 'employeeNum',//对应json中的key
				title : '工号',
				width : 80
			},
			{
				field : 'teacherName',//对应json中的key
				title : '姓名',
				width : 80
			},
			{
				field : 'professionalRank',//对应json中的key
				title : '职称',
				width : 80
			},
			{
				field : 'juniorCollegeQuota',//对应json中的key
				title : '专科人数',
				width : 60
			},
			{
				field : 'undergraduateQuota',//对应json中的key
				title : '本科人数',
				width : 60
			},
			{
				field : 'qq',//对应json中的key
				title : 'QQ号',
				width : 135
			},
			{
				field : 'phone',//对应json中的key
				title : '手机号',
				width : 135
			},
			{
				field : 'email',//对应json中的key
				title : '邮箱',
				width : 135
			},
			{
				field : 'teacherSchoolState',//对应json中的key
				title : '在职状态',
				width : 60
			},
			{
				field : 'opt2',
				title : '操作',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:toEditTeacher('" + row.teacherId
							+ "')>修改</a>";
				}
			},
			{
				field : 'opt3',
				title : '操作',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:initTeacherPassword('"
							+ row.teacherId + "')>初始密码</a>";
				}
			},
			{
				field : 'opt4',
				title : '指定教研室主任',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:specifieTrDirector('"
							+ row.teacherId + "')>指定</a>";
				}
			} ] ];

	//定义 datagird工具
	var toolbar_v = [
			{//工具栏
				id : 'btnadd',
				text : '添加',
				iconCls : 'icon-add',
				handler : function() {
					//打开一个窗口，用户添加页面
					//参数：窗口的title、宽、高、url地址
					createmodalwindow("添加教师", 400, 350,
							'/teacher/trSecretary/teacherManage/toAddTeacher.do');
				}
			},
			{//工具栏
				id : 'btnExport',
				text : '导出',
				iconCls : 'icon-redo',
				handler : function() {
					//打开一个窗口，用户添加页面
					//参数：窗口的title、宽、高、url地址
					jquerySubByFId('sysuserqueryForm', teacherExprot_callback,
							null, "json");
				}
			},
			{//工具栏
				id : 'btnIxport',
				text : '导入',
				iconCls : 'icon-add',
				handler : function() {
					//打开一个窗口，用户添加页面
					//参数：窗口的title、宽、高、url地址
					createmodalwindow("批量添加教师", 400, 200,
							'/teacher/trSecretary/teacherManage/toAddTeacherByExcel.do');
				}
			} ];
	function teacherExprot_callback(data) {
		//在这里提示信息中有文件下载链接
		message_alert(data);
	}
	//加载datagrid
	$(function() {
		$('#sysuserlist').datagrid({
			title : '用户查询',//数据列表标题
			nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
			striped : true,//条纹显示效果
			url : '/teacher/trSecretary/teacherManage/listTeacher.do',//加载数据的连接，引连接请求过来是json数据
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

	//修改教师信息
	function toEditTeacher(id) {
		//打开修改窗口
		createmodalwindow("修改教师信息", 800, 250,
				'/teacher/trSecretary/teacherManage/toEditTeacher.do?teacherId='
						+ id);
	}
	//初始化教师密码
	function initTeacherPassword(id) {
		_confirm(
				'您确认初始化教师密码么？',
				null,
				function() {
					var url = "/teacher/trSecretary/teacherManage/initTeacherPassword.do";
					var params = {
						"teacherId" : id
					};
					$.post(url, params, function(data) {
						var type = data.resultInfo.type;
						//结果提示信息
						var message = data.resultInfo.message;
						//alert(message);
						if (type == 0) {
							//如果type等于0表示失败，调用 jquery easyui的信息提示组件
							$.messager.alert('提示信息', message, 'error');
						} else if (type == 1) {
							$.messager.alert('提示信息', message, 'success');
						} else if (type == 2) {
							$.messager.alert('提示信息', message, 'warning');
						} else if (type == 3) {
							$.messager.alert('提示信息', message, 'info');
						}
					}, "json");
				});
	}
	//教研室秘书指定教研室主任
	function specifieTrDirector(teacherId) {
		//打开修改窗口
		_confirm(
				'您确认指定教研室主任么？',
				null,
				function() {
					var url = "/teacher/trSecretary/teacherManage/specifieTrDirector.do";
					var params = {
						"teacherId" : teacherId
					};
					$.post(url, params, function(data) {
						var type = data.resultInfo.type;
						//结果提示信息
						var message = data.resultInfo.message;
						if (type == 0) {
							//如果type等于0表示失败，调用 jquery easyui的信息提示组件
							$.messager.alert('提示信息', message, 'error');
						} else if (type == 1) {
							$.messager.alert('提示信息', message, 'success');
						} else if (type == 2) {
							$.messager.alert('提示信息', message, 'warning');
						} else if (type == 3) {
							$.messager.alert('提示信息', message, 'info');
						}
					}, "json");
				});
	}
</script>

</head>
<body>

	<!-- html的静态布局 -->
	<form id="sysuserqueryForm"
		action="/base/trSecretary/teacherManage/teacherExport.do"
		method="post">
		<!-- 查询条件 -->
		<TABLE class="table_search" style="margin-left: 6px">
			<TBODY>
				<TR>
					<TD class="left">教研室名称：</TD>
					<td><select id="" name="trsectionId" style="width: 150px">
							<option value="">全部</option>
							<c:forEach items="${trsections}" var="trsection">
								<option value="${trsection.trsectionId}">${trsection.sectionName}</option>
							</c:forEach>
					</select></td>
					<TD class="left">教师名字：</td>
					<td><INPUT type="text" name="teacherName" /></TD>
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
	<form id="trsectiondeleteform"
		action="/teacher/trSecretary/teacherManage/deleteTeacher.do"
		method="post">
		<input type="hidden" id="delete_id" name="trsectionId" />
	</form>
</body>
</html>