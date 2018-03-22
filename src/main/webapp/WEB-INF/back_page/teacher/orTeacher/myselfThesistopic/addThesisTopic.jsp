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
<title>论文添加</title>
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
		action="/teacher/orTeacher/myselfThesistopic/addThesisTopic.do"
		method="post">
		<!-- 更新用户的id -->
		<input type="hidden" name="thesisTopicId"
			value="${thesistopicCustom.thesistopic.thesisTopicId}" />
		<TABLE border=0 cellSpacing=0 cellPadding=0 width="100%"
			bgColor=#c4d8ed>

			<TBODY>
				<TR>
					<TD background=images/r_0.gif width="100%">
						<TABLE cellSpacing=0 cellPadding=0 width="100%">
							<TBODY>
								<TR>
									<TD>&nbsp;课题信息</TD>
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
									<TD height=30 width="15%" align=right>论文标题：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" style="width: 220px;"
												name="thesisTitle"
												value="${thesistopicCustom.thesistopic.thesisTitle }" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>英文标题：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid"
												name="thesisEnglishTile"
												value="${thesistopicCustom.thesistopic.thesisEnglishTile }" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD align=right>论文来源类型：</TD>
									<td><select id="" name="topicSourceTypeId"
										style="width: 100px">
											<c:forEach items="${topicsourcetypes}" var="topicsourcetype">
												<option value="${topicsourcetype.topicSourceTypeId}">${topicsourcetype.sourceTypeName}</option>
											</c:forEach>
									</select></td>
								</TR>
								<TR>
									<TD align=right>论文类型：</TD>
									<td><select id="" name="topicTypeId" style="width: 100px">
											<c:forEach items="${topictypes}" var="topictype">
												<option value="${topictype.topicTypeId}">${topictype.typeName}</option>
											</c:forEach>
									</select></td>
								</TR>
								<TR>
									<TD align=right>其他教师是否可见：</TD>
									<td><select id="" name="otherTeacherCanSee"
										style="width: 100px">
											<option value="0">不可见</option>
											<option value="1">可见</option>
									</select></td>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>论文目标和要求：</TD>
									<TD class=category width="35%">
										<div>
											<textarea rows="3" name="projectRequirement" cols="30">${thesistopicCustom.thesistopic.projectRequirement }
											</textarea>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>论文主要研究内容：</TD>
									<TD class=category width="35%">
										<div>
											<textarea rows="3" name="workloadReqirement" cols="30">${thesistopicCustom.thesistopic.workloadReqirement }
											</textarea>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>论文简介：</TD>
									<TD class=category width="35%">
										<div>
											<textarea rows="3" cols="30" name="note">${thesistopicCustom.thesistopic.note }
											</textarea>
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<TR>
									<TD height=30 width="15%" align=right>毕业年份：</TD>
									<TD class=category width="35%">
										<div>
											<input type="text" id="sysuser_userid" name="graduationYear"
												value="${thesistopicCustom.thesistopic.graduationYear }" />
										</div> <!-- sysuser_useridTip用于显示提示信息，提示div的id等于校验input的id+Tip -->
										<div id="sysuser_useridTip"></div>
								</TR>
								<tr>
									<td colspan=4 align=center class=category><a
										id="submitbtn" class="easyui-linkbutton" iconCls="icon-ok"
										href="#" onclick="sysusersave()">添加</a> <a id="closebtn"
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