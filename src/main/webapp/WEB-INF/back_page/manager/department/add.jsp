<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>院系添加</title>
</head>
<body>


<form action="/manage/department/add.do" class="form-horizontal" role="form" method="post">
  <div style="margin:100px auto;">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">院系名称</label>
    <div class="col-sm-10">
      <input type="text" class="form-control" name="departmentName" style="width:300px" placeholder="院系名称" value="${department.departmentName }">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-primary">添加</button>${message }
    </div>
  </div>
  </div>
</form>

</body>
</html>
