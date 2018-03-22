<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/back_page/tag.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>失败提示信息</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="${baseurl}styles/body.css" type="text/css" />
<LINK rel="stylesheet" type="text/css"
	href="${baseurl}js/easyui/styles/default.css">
<%@ include file="/WEB-INF/back_page/common_css.jsp"%>
<%@ include file="/WEB-INF/back_page/common_js.jsp"%>
</head>
<body>
	<table class="submit_hint">
		<tr>
			<td>
				<div id="tabBtnContainer" width="100%">
					<ul id="tabBtnUi">
						<li><a href="#"></a>失败提示信息</li>
					</ul>
				</div>
			</td>
		</tr>
		<tr>
			<td style="font-size: 14px; color: #ff0000; padding-top: 2px;"
				align='center' valign="middle"><img alt=""
				src="/res/images/info.png" align="middle" height="30"
				width="30" /> <span style="vertical-align: middle">${message}</span>
			</td>
		</tr>
	</table>
	<table width='100%'>
		<tr>
			<td colspan=4 align=center class=category> <a id="closebtn"
				class="easyui-linkbutton" iconCls="icon-cancel" href="#"
				onclick="parent.closemodalwindow()">关闭</a></td>
		</tr>
	</table>
</body>
</html>