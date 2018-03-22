<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/system/jquery-3.3.1.min.js"></script>
<title>用户管理-更新</title>
</head>
<body>
	<form id="updateForm" action="UserController?action=update" method="post">
		<input type="hidden" id="uid" name="uid" value="${userMsg.id }"/>
		<table>
			<tr>
				<td>密码</td>
				<td colspan="2"><input id="password" type="password" name="password" ></td>
			</tr>
			<tr>
				<td>重新密码</td>
				<td colspan="2"><input id="rpassword" type="password" name="rpassword" ></td>
			</tr>
			<tr>
				<td><input type="button" onclick="updateUser();" value="更新"/></td>
				<td><input type="reset" value="重置"/></td>
				<td><input type="button" onclick="javascript:history.back(-1)" value="返回"/></td>
			</tr>
		</table>
	</form>
	<script type="text/javascript">
		function updateUser(){
			var password  = $("#password").val(); 
			var rpassword = $("#rpassword").val(); 
			if(password == "" || password == null){
				alert("密码不能为空"); 
				return false; 
			}
			if(password != rpassword){
				alert("密码不一致"); 
				return false; 
			}
			$("#updateForm").submit(); 
		}
	</script>
</body>
</html>