<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="keywords"
	content="Flat Dark Web Login Form Responsive Templates, Iphone Widget Template, Smartphone login forms,Login form, Widget Template, Responsive Templates, a Ipad 404 Templates, Flat Responsive Templates" />

<link href="/res/weixin/css/style.css" rel='stylesheet' type='text/css' />

<script type="text/javascript" src="/res/weixin/js/jquery.min.js"></script>

</head>
<body>

	<div class="login-form">
		<div class="head-info">
			<label class="lbl-1"> </label> <label class="lbl-2"> </label> <label
				class="lbl-3"> </label>
		</div>
		<div class="clear"></div>
		<div class="avtar">
			<img src="/res/weixin/images/avtar.png" />
		</div>
		<form action="/wx/student/login.do" method="post">
			<input type="text" class="text" name="studentNo" required="required"
				placeholder="学号">
			<div>
				<input type="password" name="password" required="required"
					placeholder="密码">
			</div>
			<div style="font-size: 15px; color: red;">${message }</div>
			<div class="signin">
				<input type="submit" value="Login">
			</div>
		</form>

	</div>

</body>
</html>