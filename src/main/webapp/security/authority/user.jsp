<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet"
	href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/zeroModal.css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../js/zeroModal.min.js"></script>
<title>用户管理</title>
<base href="<%=basePath%>">
<style>
* {
	font-family: 微软雅黑;
	padding: 0;
	margin: 0;
}

table {
	border: 1px solid #ccc;
	margin: 50px auto;
}

td {
	border: 1px solid #fff;
}

thead {
	color: #fff;
	background-color: #6F93FF;
}

thead tr {
	height: 30px;
}

tbody {
	font-size: 1rem;
}

tbody tr:nth-child(odd) {
	background-color: #e8e8e8;
	height: 24px;
}

tbody tr:nth-child(even) {
	background-color: #ffffff;
	height: 24px;
}

tbody tr:nth-child(odd) td:hover {
	background-color: #ffffff;
}

tbody tr:nth-child(even) td:hover {
	background-color: #e8e8e8;
}

ul {
	list-style: none;
}

a:nth-child(1) i {
	color: #E5974A;
}

a:nth-child(2) i {
	color: #4144C5
}

a:nth-child(3) i {
	color: #C91417;
}
</style>
</head>
<body>
	<table cellspacing="0">
		<thead>
			<tr>
				<th width="150px">用户名</th>
				<th width="150px">账号</th>
				<th width="150px">邮箱</th>
				<th width="150px">手机号</th>
				<th width="150px">操作</th>
			</tr>
		</thead>
		<tbody align="center" id="userList">
		</tbody>

	</table>
	<script>
		$(function() {
			var roles;
			$.getJSON("roles/get_roles", function(data) {
/*  				if(!data.success){
					window.location.href="/stms/unauthorized.jsp";
				}else{
					roles = data;
				} */
				roles = data;
			})
			$.ajax({
				type : "GET",
				url : "getUserDateRoles?page=1&size=10",
				dataType : "json",
				success : function(data) {
					$("#userList").html("");
						$.each(data.list, function(i, item) {
							$("#userList").append("<tr><td>" + item.userName + "</td><td>" + item.userNumber + "</td><td>" + item.userEmail + "</td><td>" + item.contactPhone + "</td><td><a href='javascript:void(0);'><i id='user" + item.userId + "' class='fa fa-handshake-o' aria-hidden='true' ></i></a></td></tr>");
							$("#user" + item.userId + "").click(function() {
								//加载内容
								getContent(item.userId);
							})
		
						})
				}
			});
			//加载内容
			function getContent(userId) {
				$.getJSON("roles/get_roles_userId?userId=" + userId, function(data) {
					if (roles != null) {
						var html = "<ul>";
						var i = 0;
						var j = 0;
						for (i = 0; i < roles.length; i++) {
							for (j = 0; j < data.length; j++) {
								if (roles[i].roleId == data[j].roleId) {
									html += "<li><input id='roles" + roles[i].roleId + "' name='" + userId + "' type='checkbox' checked='true'>" + roles[i].roleName + "</li>";
									break;
								}
							}
							if (j == data.length) {
								html += "<li><input id='roles" + roles[i].roleId + "' name='" + userId + "' type='checkbox'>" + roles[i].roleName + "</li>";
							}
						}
						html += "</ul>";
						//显示modal
						showZeroModal(html);
						//编辑角色
						editRoles(userId);
					}
				})
			}
			//显示modal
			function showZeroModal(html) {
				zeroModal.show({
					title : '用户类型',
					content : html,
					left : "100px",
					top : "100px"
				});
			}
			//点击添加或者删除角色
			function editRoles(userId) {
				$('input[id^="roles"]').change(function() {
					var id = $(this).attr("id");
					id = id.substring(5, id.length);
					//tyep 0 为删除  1为添加
					if ($(this).is(':checked')) {
						var data = {
							roleId : id,
							userId : userId,
							type : 1
						}
						$.post("loginRole/operate", data, function(result) {
							if (!result.success) {
								alert("添加失败");
							}
						})
					} else {
						var data = {
							roleId : id,
							userId : userId,
							type : 0
						}
						$.post("loginRole/operate", data, function(data) {
							if (!result.success) {
								alert("删除失败");
							}
						})
					}
				});
			}
	
		})
	</script>
</body>
</html>
