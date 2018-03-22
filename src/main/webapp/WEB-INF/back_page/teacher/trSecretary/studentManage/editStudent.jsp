<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/back_page/common_css.jsp"%>
<%@ include file="/WEB-INF/back_page/common_js.jsp"%>
<title>修改学生信息</title>
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
		action="/teacher/trSecretary/studentManage/editStudent.do"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="studentId" value="${student.studentId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;学生信息</TD>
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
									<TD class="left">班级名称：</TD>
									<td><select id="" name="classisId" style="width: 250px">
											<c:forEach items="${classes}" var="classe">
												<option value="${classe.classisId}"
													<c:if test="${classe.classisId == student.classisId }">selected="selected"</c:if> >${classe.className}</option>
											</c:forEach>

									</select></td>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>学号：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="studentNo" value="${student.studentNo }"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>学生姓名：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="studentName" value="${student.studentName }"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>手机号：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="phone" value="${student.phone }"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>QQ号：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="qq" value="${student.qq }"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>邮箱：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="email" value="${student.email }"/>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
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