<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教师分配权限</title>
<!-- <link rel="stylesheet"
 	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>  -->

<!-- autocomplete  插件的使用 -->
<!-- <link rel="stylesheet" href="/res/css/jquery-ui.css" />
<script src="/res/js/jquery-1.9.1.min.js"></script>
<script src="/res/js/jquery.ui.autocomplete.js"></script>
<script src="/res/js/jquery-ui.js"></script> -->
<style type="text/css">
table tr td, table tr th {
	white-space: nowrap;
}

@media only screen and (max-width: 800px) {
	#flip-scroll .cf:after {
		visibility: hidden;
		display: block;
		font-size: 0;
		content: " ";
		clear: both;
		height: 0;
	}
	#flip-scroll * html .cf {
		zoom: 1;
	}
	#flip-scroll *:first-child+html .cf {
		zoom: 1;
	}
	#flip-scroll table {
		width: 100%;
		border-collapse: collapse;
		border-spacing: 0;
	}
	#flip-scroll table td, th {
		vertical-align: middle !important;
		text-align: center;
	}
	#flip-scroll th, #flip-scroll td {
		margin: 0;
		vertical-align: top;
	}
	#flip-scroll th {
		text-align: left;
	}
	#flip-scroll table {
		display: block;
		position: relative;
		width: 100%;
	}
	#flip-scroll thead {
		display: block;
		float: left;
	}
	#flip-scroll tbody {
		display: block;
		width: auto;
		position: relative;
		overflow-x: auto;
		white-space: nowrap;
	}
	#flip-scroll thead tr {
		display: block;
	}
	#flip-scroll th {
		display: block;
		text-align: right;
		vertical-align: middle !important;
	}
	#flip-scroll tbody tr {
		display: inline-block;
		vertical-align: middle !important;
	}
	#flip-scroll td {
		display: block;
		min-height: 1.25em;
		text-align: left;
	}
	td, th {
		text-align: center;
		vertical-align: middle !important;
	}
	#controllTable {
		width: 600px;
		margin: 60 auto 0;
	}
	a {
		text-decoration: none;
	}
}
</style>
</head>
<body style="margin: margin: 0 auto 0;">
	<form action="/manage/role/toAssignRoles.do" class="form-horizontal"
		role="form" method="post">
		<div class="container-fluid">
			<div class="row">
				<!-- 查询条件 -->
				<div class="panel panel-primary" class="col-md-6">
					<div class="panel-heading">
						<h3 class="panel-title">查询条件</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-2 control-label">院系名称:</label>
							<div class="col-sm-3">
								<select class="selectpicker show-tick form-control"
									name="departmentId" onchange="changeDepartment(this.value)">
									<c:forEach items="${departments }" var="department">
										<option id="option" value="${department.departmentId }">${department.departmentName }</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-1 control-label">教研室名称:</label>
							<div class="col-sm-3">
								<select class="selectpicker show-tick form-control"
									name="trsectionId" id="trsection">
									<c:forEach items="${trsections }" var="trsection">
										<option id="option" value="${trsection.trsectionId }">${trsection.sectionName }</option>
									</c:forEach>
								</select>
							</div>
							<button type="submit" class="btn btn-primary">查询</button>${message }
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>

				<!-- 教师信息 -->

				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>院系</th>
							<th>教研室</th>
							<th>职工号</th>
							<th>教师姓名</th>
							<th>角色</th>
							<th>修改|保存</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagination.list }" var="teacher">
							<tr>
								<td><input type="text" class="form-control"
									style="text-align: center;" value="${teacher.departmentName }"
									readonly /></td>
								<td><input type="text" class="form-control"
									style="text-align: center;" value="${teacher.trsectionName }"
									readonly /></td>
								<td><input type="text" class="form-control"
									style="text-align: center;" value="${teacher.employeeNum }"
									readonly /></td>
								<td><input type="text" class="form-control"
									style="text-align: center;" value="${teacher.teacherName }"
									readonly /></td>
								<td><select class="selectpicker show-tick form-control"
									id="t${teacher.teacherId }" name="departmentId"
									disabled="disabled">
										<c:forEach items="${roles}" var="role">
											<option id="option" value="${role.roleId }"
												id="r${teacher.teacherId }"
												<c:forEach items="${teacher.roles }"  var="tRole"><c:if test="${role.roleId == tRole.roleId }">selected="selected"</c:if></c:forEach>>${role.roleName }</option>
										</c:forEach>
								</select></td>
								<td><span class="label label-warning"><a
										href="javascript:updateTeacherRole(${teacher.teacherId })">修改</a></span>
									|&nbsp;<span class="label label-success"> <a
										href="javascript:saveTeacherRole(${teacher.teacherId })"
										class="success">保存</a></span></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6" align="center"><c:forEach
									items="${pagination.pageView }" var="page">
							${page }
							</c:forEach></td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	//教研室联动
	function changeDepartment(code) {
		//change to city
		var url = "/manage/teacher/trsection.do";
		var params = {
			"code" : code
		};
		$.post(url, params, function(data) {
			var trsections = data.trsections;
			var html = '';
			for (var i = 0; i < trsections.length; i++) {
				html += '<option value="'+ trsections[i].trsectionId +'" >'
						+ trsections[i].sectionName + '</option>';
			}
			$("#trsection").html(html);
		}, "json");
	}
	//让select亮起来
	function updateTeacherRole(teacherId) {
		$("#t" + teacherId).attr("disabled", false);
		/*$("#r" + teacherId).children().each(function(i, dom) {
			if ($(this).val() != 0) {  除了普通老师不能亮
				$(this).attr("disabled", false);
			}
			i++;
		});*/
	}
	// 0  表示的是普通教师角色不能修改
	function saveTeacherRole(teacherId) {
		var roleId = $("#t" + teacherId).val();
		//alert(roleId);
		var url = "/manage/role/updateTeacherRole.do";
		var params = {
			"teacherId" : teacherId,
			"roleId" : roleId
		};
		$.post(url, params, function(data) {
			alert(data.message);
			$("#t" + teacherId).attr("disabled", true);
			window.location.reload();
		}, "json");

		/*var roleIds = "";
		var i = 0;
		$("#r" + teacherId).children().each(function(i, dom) {
			if ($(this).is(":checked")) {
				roleIds += $(this).val() + ",";
			}
			i++;
		});
		//alert(roleIds);
		var url = "/manage/role/updateTeacherRole.do";
		var params = {
			"teacherId" : teacherId,
			"roleIds" : roleIds
		};
		$.post(url, params, function(data) {
			alert(data.message);
			$("#r" + teacherId).children().attr("disabled", true);
			window.location.reload();
		}, "json");*/
	}
</script>
</html>

<!-- --<td id="${teacher.teacherId }"><c:forEach items="${roles}"
										var="role">
										<input type="checkbox" value="${role.roleId }"
											name="${teacher.teacherId }" disabled="disabled"
											<c:forEach items="${teacher.roles }" var="tRole"><c:if test="${role.roleId == tRole.roleId }">checked="checked"</c:if></c:forEach> />${role.roleName }
											</c:forEach></td> -->