<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>教研室添加</title>
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
	<form action="/manage/trsection/add.do" class="form-horizontal"
		role="form" method="post">
		<div class="container">
			<div class="row">
				<div class="panel panel-primary" class="col-md-6">
					<div class="panel-heading">
						<h3 class="panel-title">查询条件</h3>
					</div>
					<div class="panel-body">
						<div class="form-group">
							<label for="inputEmail3" class="col-sm-2 control-label">查询院系:</label>
							<div class="col-sm-10">
								<input type="text" class="form-control" name="departmentName"
									id="departmentName" style="width:300px" placeholder="院系名称"
									value="${department.departmentName }"
									oninput="searchDapartmentName()" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-2 control-label">院系名称:</label>
							<div class="col-sm-2">
								<select class="selectpicker show-tick form-control" multiple
									data-live-search="false" name="departmentId">
									<c:forEach items="${departments }" var="department">
										<option id="option" value="${department.departmentId }">${department.departmentName }</option>
									</c:forEach>
								</select>
							</div>
							
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>


				<div class="panel panel-primary" class="col-md-6">
					<div class="panel-heading">添加教研室</div>
					<div class="panel-body">
						<div>
							<div class="form-group">
								<label for="inputEmail3" class="col-sm-2 control-label">教研室名称:</label>
								<div class="col-sm-10">
									<input type="text" class="form-control" name="sectionName"
										style="width:300px" placeholder="教研室名称"
										value="${trsection.sectionName }">
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
	function searchDapartmentName() {
		//首先将所有的都设置为不选

		$("select option").each(function() {
			$(this).attr("selected", false);
		});
		var m = $(departmentName).val();

		var url = "/manage/trsection/searchdepartmentName.do";
		var params = {
			"departmentName" : m
		};
		//var names = new Array();
		$.post(url, params, function(data) {
			/* var i = 0;
			for (key in data) {
				names[i++] = data[key];
			} */
			/* autocomplete  插件的使用
			$("#departmentName").autocomplete({
				source : names
			}); */
			
			 $("select option").each(function() {
				var val = $(this).text();
				for ( var key in data) {
					if (val == data[key]) {
						$(this).attr("selected", true);
					}

				}

			}); 
			/* for ( var key in data) {
				alert(data[key]);
				$("#option").find("option[value='" + data[key] + "']").attr(
						"selected", true);
			} */
		}, "json")
	}
</script>
</html>

