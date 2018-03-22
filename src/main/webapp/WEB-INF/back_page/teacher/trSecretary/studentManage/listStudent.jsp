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

<title>学生列表</title>

<script type="text/javascript">
	//datagrid列定义
	var columns_v = [ [
			{
				field : 'studentNo',//对应json中的key
				title : '学号',
				width : 90
			},
			{
				field : 'studentName',//对应json中的key
				title : '姓名',
				width : 80
			},
			{
				field : 'phone',//对应json中的key
				title : '手机号',
				width : 100
			},
			{
				field : 'qq',//对应json中的key
				title : 'QQ号',
				width : 100
			},
			{
				field : 'email',//对应json中的key
				title : '邮箱',
				width : 150
			},
			{
				field : 'studentState',//对应json中的key
				title : '学生状态',
				width : 120,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					if (value == '1') {
						return "等待选择";
					} else if (value == '2') {
						return "等待分配";
					} else if (value == '3') {
						return "分配成功";
					} else if (value == '0') {
						return "信息未完善";
					}
				}

			},
			{
				field : 'studentSchoolState',//对应json中的key
				title : '学生在校状态',
				width : 135
			},
			{
				field : 'opt2',
				title : '操作',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:toEditStudent('" + row.studentId
							+ "')>修改</a>";
				}
			},
			{
				field : 'opt3',
				title : '操作',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:initStudentPassword('"
							+ row.studentId + "')>初始密码</a>";
				}
			},
			{
				field : 'opt4',
				title : '指定学生',
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
					createmodalwindow("添加学生", 450, 400,
							'/teacher/trSecretary/studentManage/toAddStudent.do');
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
					createmodalwindow("批量添加学生", 400, 200,
							'/teacher/trSecretary/studentManage/toAddStudentByExcel.do');
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
			url : '/teacher/trSecretary/studentManage/listStudent.do',//加载数据的连接，引连接请求过来是json数据
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

	//修改学生信息
	function toEditStudent(id) {
		//打开修改窗口
		createmodalwindow("修改教师信息", 800, 340,
				'/teacher/trSecretary/studentManage/toEditStudent.do?studentId='
						+ id);
	}
	//初始化教师密码
	function initStudentPassword(id) {
		_confirm(
				'您确认初始化学生密码么？',
				null,
				function() {
					var url = "/teacher/trSecretary/studentManage/initStudentPassword.do";
					var params = {
						"studentId" : id
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
		action="/teacher/trSecretary/studentManage/studentExport.do"
		method="post">
		<!-- 查询条件 -->
		<TABLE class="table_search" style="margin-left: 6px">
			<TBODY>
				<TR>
					<TD class="left">班级名称：</TD>
					<td><select id="" name="classisId" style="width: 250px">
							<option value="">全部</option>
							<c:forEach items="${classes}" var="classe">
								<option value="${classe.classisId}">${classe.className}</option>
							</c:forEach>
					</select></td>
					<TD class="left">学生名字：</td>
					<td><INPUT type="text" name="studentName" /></TD>
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