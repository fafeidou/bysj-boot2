<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/tag.jsp"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/back_page/common_css.jsp"%>
<%@ include file="/WEB-INF/back_page/common_js.jsp"%>
<title>审核课题</title>
<script type="text/javascript">
	function sysusersave() {

		jquerySubByFId('userform', sysusersave_callback, null, "json");

	}
	//ajax调用的回调函数，ajax请求完成调用此函数，传入的参数是action返回的结果
	function sysusersave_callback(data) {

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
	}
</script>
</head>
<body>


	<form id="userform"
		action="/teacher/trDirector/thesistopicManage/reviewThesistopic.do"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="thesisTopicId"
			value="${thesisTopicId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;审核课题</TD>
									<TD align=right>&nbsp;</TD>
								</TR>
							</TBODY>
						</TABLE>
					</TD>
				</TR>

				<TR>
					<TD>
						<TABLE class="toptable grid" border=1 cellSpacing=1 cellPadding=4
							align=center>
							<TBODY>
								<TR>
									<TD class="left">审核状态：</TD>
									<td><select id="" name="topicState" style="width: 250px">
											<option value="2">审核通过</option>
											<option value="1">审核不通过</option>
									</select></td>
								</TR>
								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="sysusersave()">提交</a> <a id="closebtn"
										class="easyui-linkbutton" iconCls="icon-cancel" href="#"
										onclick="parent.closemodalwindow()">关闭</a></td>
								</tr>
							</TBODY>
						</TABLE>
					</TD>
				</TR>
			</TBODY>
		</TABLE>
	</form>
</body>
</html>