<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改教研室信息</title>
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
	<form action="/manage/trsection/toEdit.do" class="form-horizontal"
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
							<button type="submit" class="btn btn-primary">查询</button>${message }
						</div>
					</div>
					<div class="panel-footer"></div>
				</div>

				<!-- 教师信息 -->

				<table id="example2" class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>教研室</th>
							<th>修改|保存</th>
							<th>删除</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${pagination.list }" var="trsection">
							<tr>
								<td><input type="text" class="form-control"
									id="m${trsection.trsectionId }" style="text-align:center;"
									value="${trsection.sectionName }" readonly required="required" /></td>
								<td><span class="label label-warning"><a
										href="javascript:updateTrsectionName(${trsection.trsectionId })">修改</a></span>
									|&nbsp;<span class="label label-success"> <a
										href="javascript:saveTrsectionName(${trsection.trsectionId })"
										class="success">保存</a></span></td>
								<td><span class="label label-danger"><a
										href="javascript:deleteTrsection(${trsection.trsectionId })">删除</a></span></td>
							</tr>
						</c:forEach>
						<tr>
							<td colspan="6" ><c:forEach
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
	//让选中行亮起来
	function updateTrsectionName(trsectionId) {
		$("#m" + trsectionId).attr("readonly", false);
	}
	//保存修改的教研室名字
	function saveTrsectionName(trsectionId) {

		var trsectionName = $("#m" + trsectionId).val();
		if (trsectionName == "") {
			alert("院系名字不能为空!");
			return;
		}
		var url = "/manage/trsection/update.do";
		var params = {
			"trsectionId" : trsectionId,
			"sectionName" : trsectionName
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.reload();
		}, "json");
	}
	function deleteTrsection(trsectionId) {
		var sectionName = $("#m" + trsectionId).val();
		if (!confirm("你确定删除吗?")) {
			return;
		}
		var url = "/manage/trsection/delete.do";
		var params = {
			"trsectionId" : trsectionId,
			"sectionName" : sectionName
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.reload();
		}, "json");
	}
</script>
</html>

