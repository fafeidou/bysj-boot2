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
<title>教师个人信息</title>
<script type="text/javascript">
	function sysusersave() {
		//第一个参数是提示信息，第二个参数，取消执行的函数指针，第三个参是，确定执行的函数指针
		_confirm('您确认修改吗？', null, function() {

			//sysuserdeleteform：form的id，userdel_callback：删除回调函数，
			//第三个参数是url的参数
			//第四个参数是datatype，表示服务器返回的类型
			jquerySubByFId('userform', sysusersave_callback, null, "json");

		});

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
		action="/teacher/orTeacher/myselfInfo/modifyPassword.do" method="post">
		<!-- 更新用户的id -->
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>
			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;修改信息</TD>
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
									<TD height=30 width="15%" align=right>旧密码：</TD>
									<TD class=category width="35%">
										<div>
											<input type="password" class="c" style="width: 160px"
												id="sysuser_userid" name="oldPassword" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>新密码：</TD>
									<TD class=category width="35%">
										<div>
											<input type="password" class="c" style="width: 160px"
												id="sysuser_userid" name="newPassword"
												/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>确认密码：</TD>
									<TD class=category width="35%">
										<div>
											<input type="password" class="c" style="width: 160px"
												id="sysuser_userid" name="confirmPassword"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="sysusersave()">修改</a></td>
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