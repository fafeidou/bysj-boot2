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
				field : 'thesisTopicId',//对应json中的key
				title : 'id',
				width : 120,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					if(row.thesistopic){
                        return row.thesistopic.thesisTopicId;
                    }
                    return "";
				}
			},
			{
				field : 'teacherName',//对应json中的key
				title : '教师姓名',
				width : 120,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					if(row.teacher){
                        return row.teacher.teacherName;
                    }
                    return "";
				}
			},
			{
				field : 'thesisTitle',//对应json中的key
				title : '论文标题',
				width : 250,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					return row.thesistopic.thesisTitle;
				}
			},
			{
				field : 'studentState',//对应json中的key
				title : '状态',
				width : 150,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					if (row.student.studentState == '0') {
						return "信息未完善";
					} else if (row.student.studentState == '1') {
						return "等待选择";
					} else if (row.student.studentState == '2') {
						return "等待分配";
					} else if (row.student.studentState == '3') {
						return "完成";
					}
				}

			},
			{
				field : 'studentName',//对应json中的key
				title : '学生姓名',
				width : 120,
				formatter : function(value, row, index) {//通过此方法格式化显示内容,value表示从json中取出该单元格的值，row表示这一行的数据，是一个对象,index:行的序号
					return row.student.studentName;
				}
			},
			{
				field : 'opt2',
				title : '指定题目',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:toEditStudent('" + row.studentId
							+ "')>指定</a>";
				}
			},
			{
				field : 'opt3',
				title : '课题详细信息',
				width : 90,
				formatter : function(value, row, index) {
					return "<a href=javascript:viewDetails('"
							+ row.thesistopic.thesisTopicId + "')>查看详细</a>";
				}
			} ] ];

	//定义 datagird工具
	var toolbar_v = [ {//工具栏
		id : 'btnExport',
		text : '导出选题结果',
		iconCls : 'icon-redo',
		handler : function() {
			//打开一个窗口，用户添加页面
			//参数：窗口的title、宽、高、url地址
			jquerySubByFId('sysuserqueryForm', thesistopicExprot_callback,
					null, "json");
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
							title : '用户查询',//数据列表标题
							nowrap : true,//单元格中的数据不换行，如果为true表示不换行，不换行情况下数据加载性能高，如果为false就是换行，换行数据加载性能不高
							striped : true,//条纹显示效果
							url : '/teacher/trSecretary/studentThesistopicManage/listThesistopic.do',//加载数据的连接，引连接请求过来是json数据
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
				'/teacher/trSecretary/thesistopicManage/viewDetails.do?thesisTopicId='
						+ id);
	}
</script>

</head>
<body>

	<!-- html的静态布局 -->
	<form id="sysuserqueryForm"
		action="/teacher/trSecretary/studentThesistopicManage/studentThesistopicExport.do"
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
					<TD class="left">状态：</TD>
					<td><select id="" name="studentState" style="width: 100px">
							<option value="">全部</option>
							<option value="0">信息未完善</option>
							<option value="1">等待选择</option>
							<option value="2">等待分配</option>
							<option value="3">完成</option>
					</select></td>
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