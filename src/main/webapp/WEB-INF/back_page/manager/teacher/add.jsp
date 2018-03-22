<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>指定院系教研秘书</title>
<!-- <link rel="stylesheet"
 	href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" /> -->
<!-- <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>  -->

<!-- autocomplete  插件的使用 -->
<!-- <link rel="stylesheet" href="/res/css/jquery-ui.css" />
<script src="/res/js/jquery-1.9.1.min.js"></script>
<script src="/res/js/jquery.ui.autocomplete.js"></script>
<script src="/res/js/jquery-ui.js"></script> -->
</head>
<body>
	<form action="/manage/teacher/add.do" class="form-horizontal"
		role="form" method="post">
		<div class="container">
			<div class="row">
				<!-- 查询条件 -->
				<div class="panel panel-primary" class="col-md-6">
					<div class="panel-heading">
						<h3 class="panel-title">查询条件</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label class="col-sm-3 control-label">院系名称:</label>
							<div class="col-sm-2">
								<select class="selectpicker show-tick form-control"
									name="departmentId" onchange="changeDepartment(this.value)">
									<c:forEach items="${departments }" var="department">
										<option id="option" value="${department.departmentId }">${department.departmentName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">教研室名称:</label>
							<div class="col-sm-3">
								<select class="selectpicker show-tick form-control"
									name="trsectionId" id="trsection">
									<c:forEach items="${trsections }" var="trsection">
										<option id="option" name="trsectionId" value="${trsection.trsectionId }">${trsection.sectionName }</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>

				<!-- 教师信息 -->
				<div class="panel panel-primary" class="col-md-6">
					<div class="panel-heading">添加教研室</div>
					<div class="panel-body">
						<div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">职工号:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="employeeNum"
										style="width:300px" placeholder="请填入职工号" required="required"/>
								</div><br/>
								<label for="inputEmail3" class="col-sm-2 control-label">姓名:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name=teacherName
										style="width:300px" placeholder="请填入教师姓名" required="required"/>
								</div><br/>
								<label for="inputEmail3" class="col-sm-2 control-label">教师职称:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="professionalRank"
										style="width:300px" required="required" placeholder="请填入教师职称" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-offset-2 col-sm-10">
									<button type="submit" class="btn btn-primary">添加</button>${message }
								</div>
							</div>
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	function changeDepartment(code) {
		//change to city
		var url = "/manage/teacher/trsection.do";
		var params = {
			"code" : code
		};
		$.post(url, params, function(data) {
			var trsections = data.trsections;
			var html = '<option value="" selected>教研室</option>';
			for (var i = 0; i < trsections.length; i++) {
				html += '<option value="'+ trsections[i].trsectionId +'" >'
						+ trsections[i].sectionName + '</option>';
			}
			$("#trsection").html(html);
		}, "json");
	}
</script>
</html>

