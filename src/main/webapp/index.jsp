<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/system/jquery-3.3.1.min.js"></script>
<title>首页-登录</title>
</head> 
<body>
<!-- 	
	<a href="IndexController?param=forward">forward跳转</a>
	<a href="IndexController?param=redirect">redirect跳转</a> 
-->
	
	<form id="loginForm" action="LoginController" method="post">
		<table>
			<tr>
				<td>账号</td>
				<td><input id="username" type="text" name="username"></td>
			</tr>
			<tr>
				<td>密码</td>
				<td><input id="password" type="password" name="password" ></td>
			</tr>
			<tr>
				<td><input type="button" onclick="login();" value="登录"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		function login(){
			var username = document.getElementById("username").value; 
			var password = document.getElementById("password").value; 
			if(password ==""){
				alert("密码不能为空"); 
				return false; 
			}
			document.getElementById("loginForm").submit(); 
		}
	</script>
</body>
</html>