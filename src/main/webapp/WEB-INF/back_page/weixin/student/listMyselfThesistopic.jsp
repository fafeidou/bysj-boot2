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
	<form action="" class="form-horizontal" role="form" method="post">
		<div class="container-fluid">
			<div class="row">
				<!-- 查询条件 -->
				<div class="panel panel-primary" class="col-md-6">
					<table id="example2" class="table table-bordered table-hover">
						<thead>
							<tr>
								<th>教师姓名</th>
								<th>论文标题</th>
								<th>状态</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<c:forEach items="${list }" var="row">
									<td><input type="text" class="form-control"
										style="text-align: center;" value="${row.teacherName }"
										readonly /></td>
									<td><input type="text" class="form-control"
										style="text-align: center;"
										value="${row.thesistopic.thesisTitle }" readonly /></td>
									<c:if test="${row.thesistopic.topicState == 5 }">
										<td><input type="text" class="form-control"
											style="text-align: center;" value="等待分配" readonly /></td>
									</c:if>
									<c:if test="${row.thesistopic.topicState == 6 }">
										<td><input type="text" class="form-control"
											style="text-align: center;" value="完成" readonly /></td>
									</c:if>
								</c:forEach>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</form>
</body>
</html>
