<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改教师信息</title>
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
	text-align: center;
	vertical-align: middle !important;
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
<body style="margin:margin: 0 auto 0;">
	<form action="/manage/teacher/toEdit.do" class="form-horizontal"
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
							<label for="inputEmail3" class="col-sm-2 control-label">教师姓名:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="teacherName"
									style="width:300px" value="${teacherName}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">院系:</label>
							<div class="col-sm-3">
								<select class="selectpicker show-tick form-control"
									name="departmentId" onchange="changeDepartment(this.value)">
									<c:forEach items="${departments }" var="department">
										<option id="option" value="${department.departmentId }">${department.departmentName }</option>
									</c:forEach>
								</select>
							</div>
							<label class="col-sm-1 control-label">教研室:</label>
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
							<th>职工号</th>
							<th>教师姓名</th>
							<th>手机号</th>
							<th>QQ号</th>
							<th>邮箱</th>
							<th>教师职称</th>
							<th>教师在校状态</th>
							<th>修改|保存</th>
							<th>初始密码</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagination.list }" var="teacher">
							<tr>
								<td><input type="text" class="form-control"
									id="q${teacher.teacherId }" style="text-align:center;"
									value="${teacher.employeeNum }" readonly required="required" /></td>
								<td><input type="text" class="form-control"
									id="w${teacher.teacherId }" style="text-align:center;"
									value="${teacher.teacherName }" readonly required="required" /></td>
								<td><input type="text" class="form-control"
									id="e${teacher.teacherId }" style="text-align:center;"
									value="${teacher.phone }" readonly /></td>
								<td><input type="text" class="form-control"
									id="r${teacher.teacherId }" style="text-align:center;"
									value="${teacher.qq }" readonly /></td>
								<td><input type="text" class="form-control"
									id="t${teacher.teacherId }" style="text-align:center;"
									value="${teacher.email }" readonly /></td>
								<td><input type="text" class="form-control"
									id="y${teacher.teacherId }" style="text-align:center;"
									value="${teacher.professionalRank }" readonly
									required="required" /></td>
								<td><input type="text" class="form-control"
									id="u${teacher.teacherId }" style="text-align:center;"
									value="${teacher.teacherSchoolState }" readonly
									required="required" /></td>
								<td><span class="label label-warning"><a
										href="javascript:updateTeacherMessage(${teacher.teacherId })">修改</a></span>
									|&nbsp;<span class="label label-success"> <a
										href="javascript:saveTeacherMessage(${teacher.teacherId })"
										class="success">保存</a></span></td>
								<td><span class="label label-info" style="color:red"><a
										href="javascript:initTeacherPassword(${teacher.teacherId })">初始</a></span>
								</td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="9"><c:forEach items="${pagination.pageView }"
									var="page">
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
		var url = "/manage/teacher/trsection.do";
		var params = {
			"code" : code
		};
		var html = null;
		$.post(url, params, function(data) {
			var trsections = data.trsections;
			for (var i = 0; i < trsections.length; i++) {
				html += '<option value="'+ trsections[i].trsectionId +'" >'
						+ trsections[i].sectionName + '</option>';
			}
			$("#trsection").html(html);
		}, "json");
	}

	//让修改行亮起来
	function updateTeacherMessage(teacherId) {
		$("#q" + teacherId).attr("readonly", false);
		$("#w" + teacherId).attr("readonly", false);
		$("#e" + teacherId).attr("readonly", false);
		$("#r" + teacherId).attr("readonly", false);
		$("#t" + teacherId).attr("readonly", false);
		$("#y" + teacherId).attr("readonly", false);
		$("#u" + teacherId).attr("readonly", false);
	}

	function saveTeacherMessage(teacherId) {
		var employeeNum = $("#q" + teacherId).val();
		var teacherName = $("#w" + teacherId).val();
		var phone = $("#e" + teacherId).val();
		var qq = $("#r" + teacherId).val();
		var email = $("#t" + teacherId).val();
		var professionalRank = $("#y" + teacherId).val();
		var teacherSchoolState = $("#u" + teacherId).val();
		if (employeeNum == "") {
			alert("教师工号不能为空!");
			return;
		}
		if (teacherName == "") {
			alert("教师名字不能为空!");
			return;
		}
		if (professionalRank == "") {
			alert("教师职称不能为空!");
			return;
		}
		if (teacherSchoolState == "") {
			alert("教师在校状态不能为空!");
			return;
		}
		var url = "/manage/teacher/update.do";
		var employeeNum = $("#q" + teacherId).val();
		var teacherName = $("#w" + teacherId).val();
		var phone = $("#e" + teacherId).val();
		var qq = $("#r" + teacherId).val();
		var email = $("#t" + teacherId).val();
		var professionalRank = $("#y" + teacherId).val();
		var teacherSchoolState = $("#u" + teacherId).val();
		var params = {
			"teacherId" : teacherId,
			"employeeNum" : employeeNum,
			"teacherName" : teacherName,
			"phone" : phone,
			"qq" : qq,
			"email" : email,
			"professionalRank" : professionalRank,
			"teacherSchoolState" : teacherSchoolState
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.reload();
		}, "json");

	}
	function initTeacherPassword(teacherId) {
		if (!confirm("确定初始化教师密码么?")) {
			return;
		}
		var url = "/manage/teacher/initTeacherPassword.do";
		var params = {
			"teacherId" : teacherId
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.reload();
		}, "json");

	}
</script>
</html>

