<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>修改院系信息</title>
<style type="text/css">
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
</style>
</head>
<body>
	<form action="/manage/department/toEdit.do" class="form-horizontal"
		role="form" method="post">
		<div class="container-fluid">
			<div class="box" id="controllTable">
				<div class="box-tools">
					<div class="input-group input-group-lg">
						<input type="text" name="departmentName"
							class="form-control pull-right" placeholder="搜索一下"
							value="${departmentName }">
						<div class="input-group-btn">
							<button type="submit" class="btn btn-primary">
								<i class="fa fa-search"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- /.box-header -->
				<div class="box-body">
					<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>院系名称</th>
								<th>修改</th>
								<th>删除</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${pagination.list }" var="department">
								<tr>
									<td><input type="text" class="form-control"
										style="text-align: center;" id="m${department.departmentId }"
										value="${department.departmentName }" readonly /></td>
									<td><span class="label label-warning"><a
											href="javascript:updateDepartment(${department.departmentId })">修改</a></span>
										|&nbsp;<span class="label label-success"> <a
											href="javascript:saveDepartment(${department.departmentId })"
											class="success">保存</a></span></td>
									<td><span class="label label-danger"><a
											href="javascript:deleteDepartment(${department.departmentId })">删除</a></span></td>
								</tr>
							</c:forEach>
						</tbody>
						<tr>
							<td colspan="3"><c:forEach items="${pagination.pageView }"
									var="page">
							${page }
							</c:forEach></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
<script type="text/javascript">
	//修改按钮
	function updateDepartment(departmentId) {
		//让修改行亮起来
		$("#m" + departmentId).attr("readonly", false);
	}
	//保存按钮
	function saveDepartment(departmentId) {
		//让修改行暗
		var m = $("#m" + departmentId).attr("readonly", true).val();
		//保存
		var url = "/manage/department/update.do";
		var params = {
			"departmentId" : departmentId,
			"departmentName" : m
		};
		$.post(url, params, function(data) {
			alert(data.message);
		}, "json");

	}
	function deleteDepartment(departmentId) {
		var m = $("#m" + departmentId).val();
		if (!confirm("你确定删除吗?")) {
			return;
		}
		var url = "/manage/department/delete.do";
		var params = {
			"departmentId" : departmentId,
			"departmentName" : m
		};
		$.post(url, params, function(data) {
			alert(data.message);
			window.location.reload();
		}, "json");
	}
</script>
</html>
