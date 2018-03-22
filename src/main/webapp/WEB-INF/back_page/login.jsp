<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/back_page/head.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>欢迎关临本小站</title>
<style type="text/css">
#logindev {
	position: absolute;
	top: 20%;
	-webkit-transform: translateY(-50%);
	-moz-transform: translateY(-50%);
	-ms-transform: translateY(-50%);
	-o-transform: translateY(-50%);
	transform: translateY(-50%);
	background-color: #B2DFEE;
	text-align: center;
}

body {
	background-color: #8DB6CD;
	background:url('/res/images/back2.png'); 
	background-position:center; 
	
}
</style>
</head>
<body>
	<div class="container" class="center-block">
		<div
			class="col-lg-3 col-lg-offset-2 col-sm-6 col-sm-offset-3 col-xs-8 col-xs-offset-2"
			id="logindev">
			<ul id="myTab" class="nav nav-tabs" class="navbar-btn"  >
				<li class="active"><a href="#stu" data-toggle="tab"> 学生登录 </a></li>
				<li><a href="#tech" data-toggle="tab">教师登录</a></li>
				<li><a href="#ejb" data-toggle="tab">管理员登陆</a></li>
				<li><a data-toggle="tab" class="text-danger">${error }</a></li>
			</ul>
			<div id="myTabContent" class="tab-content">
				<div class="tab-pane fade in active" id="stu">
					<div class="signin">
						<form action="/system/studentLogin.do" method="post"
							class="form-signin" role="form">
							<input type="text" class="form-control" placeholder="学号"
								value="${studentNo}" name="studentNo" required autofocus /> <input
								type="password" class="form-control" name="password"
								placeholder="密码" required /> <input type="text" id="captcha1"
								name="captcha" class="form-control" placeholder="验证码" /> <img
								src="/captcha.svl"
								onclick="this.src='/captcha.svl?d='+new Date()" class="code"
								alt="换一张" /><a href="javascript:void(0);"
								onclick="$('.code').attr('src','/captcha.svl?d='+new Date())"
								title="换一张"></a>
							<button class="btn btn-info btn-lg" type="submit">登录</button>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="tech">
					<div class="signin">
						<form action="/system/teacherLogin.do" method="post"
							class="form-signin" role="form">
							<input type="text" class="form-control" placeholder="工号"
								value="${employeeNum}" name="employeeNum" required autofocus />
							<input type="password" name="password" class="form-control"
								placeholder="密码" required /> <input type="text" id="captcha2"
								name="captcha" class="form-control" placeholder="验证码" /> <img
								src="/captcha.svl"
								onclick="this.src='/captcha.svl?d='+new Date()" class="code"
								alt="换一张" /><a href="javascript:void(0);"
								onclick="$('.code').attr('src','/captcha.svl?d='+new Date())"
								title="换一张"></a>
							<button class="btn btn-info btn-lg" type="submit">登录</button>
						</form>
					</div>
				</div>
				<div class="tab-pane fade" id="ejb">
					<div class="signin">
						<form action="/system/systemManagerLogin.do" method="post"
							class="form-signin" role="form" method="post">
							<input type="text" class="form-control" placeholder="用户名"
								name="name" value="${name}" required autofocus /> <input
								type="password" name="password" class="form-control"
								placeholder="密码" required /> <input type="text" id="captcha3"
								name="captcha" class="form-control" placeholder="验证码" /> <img
								src="/captcha.svl"
								onclick="this.src='/captcha.svl?d='+new Date()" class="code"
								alt="换一张" /><a href="javascript:void(0);"
								onclick="$('.code').attr('src','/captcha.svl?d='+new Date())"
								title="换一张"></a>
							<button class="btn btn-info btn-lg" type="submit">登录</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
