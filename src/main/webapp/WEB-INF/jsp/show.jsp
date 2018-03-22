<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页面</title>
<script src="js/system/jquery-3.3.1.min.js"></script>
</head>
<body>
	当前用户:${user.username}
	<form action="UserController?action=search" method="post">
		<table>
			<thead>
				<th><input type="text" name="username"/></th>
				<th>
					<input type="submit" value="查询"/>
				</th>
				<th><a href="UserController?action=logout">退出</a></th>
			</thead>
		</table>
	</form>
	<table>
		<thead>
			<tr>
				<td>账号</td>
				<td>密码</td>
				<td>操作</td>
				<td><a href="UserController?action=addForm">新增</a></td>
			</tr>
		</thead>
		<c:forEach items="${userList}" var="item">
			<tr>
				<td>${item.username}</td>
				<td>${item.password}</td>
				<td>
					<a href="UserController?action=updateForm&id=${item.id}">编辑</a>
					<a href="UserController?action=delete&id=${item.id}" onclick="deleteUser();">删除</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<script type="text/javascript">
		function deleteUser(){
			if (!confirm("确认要删除？")) {
	            window.event.returnValue = false;
	        }
		}
		
/* 		function search(){
			var username = $("#search").val();
			$.ajax({
				url : 'UserController?action=search&username='+username,
				type: 'post',
				success : function(data){
					
				}
			});
		} 
*/
	</script>
</body>
</html>