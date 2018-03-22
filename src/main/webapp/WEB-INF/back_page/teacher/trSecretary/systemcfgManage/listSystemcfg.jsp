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
<title>修改教研室</title>
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
	/* 	function updateSystemCfg() {
	 //让修改行亮起来
	 $(".a").removeAttr("disabled");
	 $(".b").removeAttr("disabled");
	 } */
</script>
</head>
<body>
	<form id="userform"
		action="/teacher/trSecretary/systemcfgManage/editSystemcfg.do"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="systemCfgId"
			value="${systemcfgCustom.systemCfgId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;系统信息</TD>
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
									<TD height=30 width="15%" align=right>每个学生可选择的题目数：</TD>
									<TD class=category width="35%">
										<div>
											<select id="sysuser_userid" class="a"
												name="thesisNumPerStudentCanSelect" style="width: 140px">
												<option value="1"
													<c:if test="${systemcfgCustom.thesisNumPerStudentCanSelect == 1}">selected="selected"</c:if>>1</option>
												<option value="2"
													<c:if test="${systemcfgCustom.thesisNumPerStudentCanSelect == 2}">selected="selected"</c:if>>2</option>
												<option value="3"
													<c:if test="${systemcfgCustom.thesisNumPerStudentCanSelect == 3}">selected="selected"</c:if>>3</option>
												<option value="4"
													<c:if test="${systemcfgCustom.thesisNumPerStudentCanSelect == 4}">selected="selected"</c:if>>4</option>

											</select>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>每个题目可被选择的最大的学生数：</TD>
									<TD class=category width="35%">
										<div>
											<select id="sysuser_userid" class="b"
												name="maxStudentNumPerThesisCanBeSelected"
												style="width: 140px">
												<option value="1"
													<c:if test="${systemcfgCustom.maxStudentNumPerThesisCanBeSelected == 1}">selected="selected"</c:if>>1</option>
												<option value="2"
													<c:if test="${systemcfgCustom.maxStudentNumPerThesisCanBeSelected == 2}">selected="selected"</c:if>>2</option>
												<option value="3"
													<c:if test="${systemcfgCustom.maxStudentNumPerThesisCanBeSelected == 3}">selected="selected"</c:if>>3</option>
												<option value="4"
													<c:if test="${systemcfgCustom.maxStudentNumPerThesisCanBeSelected == 4}">selected="selected"</c:if>>4</option>
											</select>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第一轮学生选择开始时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="c"
												style="width: 160px" id="sysuser_userid"
												name="firstRoundStudentSelectBeginTime"
												value="${systemcfgCustom.firstRoundStudentSelectBeginTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第一轮学生选择结束时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="d"
												style="width: 160px" id="sysuser_userid"
												name="firstRoundStudentSelectEndTime"
												value="${systemcfgCustom.firstRoundStudentSelectEndTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第一轮教师选择开始时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="e"
												style="width: 160px" id="sysuser_userid"
												name="firstRoundTeacherSelectBeginTime"
												value="${systemcfgCustom.firstRoundTeacherSelectBeginTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第一轮教师选择结束时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="d"
												style="width: 160px" id="sysuser_userid"
												name="firstRoundTeacherSelectEndTime"
												value="${systemcfgCustom.firstRoundTeacherSelectEndTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第二轮学生选择开始时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="f"
												style="width: 160px" id="sysuser_userid"
												name="secondRoundStudentSelectBeginTime"
												value="${systemcfgCustom.secondRoundStudentSelectBeginTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第二轮学生选择结束时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="g"
												style="width: 160px" id="sysuser_userid"
												name="secondRoundStudentSelecEndTime"
												value="${systemcfgCustom.secondRoundStudentSelecEndTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第二轮教师选择开始时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="h"
												style="width: 160px" id="sysuser_userid"
												name="secondRoundTeacherSelectBeginTime"
												value="${systemcfgCustom.secondRoundTeacherSelectBeginTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>第二轮教师选择结束时间：</TD>
									<TD class=category width="35%">
										<div>
											<input class="easyui-datetimebox" class="i"
												style="width: 160px" id="sysuser_userid"
												name="secondRoundTeacherSelectEndTime"
												value="${systemcfgCustom.secondRoundTeacherSelectEndTime}"
												editable="false" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="sysusersave()">保存</a></td>
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