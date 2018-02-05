<html>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@  taglib uri="http://shiro.apache.org/tags" prefix="shiro"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<head>
<meta charset="utf-8">
<link rel="icon" href="${path}/stms/img/titleimg.png" type="image/png" sizes="20*20">
<link rel="stylesheet" href="${path}/stms/easyui/themes/default/easyui.css">
<link rel="stylesheet" href="${path}/stms/easyui/themes/icon.css">
<link rel="stylesheet" href="${path}/stms/easyui/demo/demo.css">
<link rel="stylesheet"
	href="${path}/stms/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${path}/stms/css/index.css">
<script type="text/javascript" src="${path}/stms/easyui/jquery.min.js"></script>
<script type="text/javascript" src="${path}/stms/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/stms/js/index.js"></script>
<script type="text/javascript">
	$(function() {
		//下拉列表框----------------------
		var indexb = 0;
		var b = document.getElementById("bbb");
		var ddm = document.getElementById("ddm");
		$("#aaa").click(function() {
			if (indexb == 0) {
				b.style.opacity = 1;
				ddm.style.opacity = 1;
				ddm.style.zIndex = 50;
				indexb = 1;
			} else {
				b.style.opacity = 0;
				ddm.style.opacity = 0;
				ddm.style.zIndex = 0;
				indexb = 0;
			}
		})
	})
</script>
<style type="text/css">
	.zong{width: 150px;height: 150px;padding: 50px 20px;margin: 0 auto;}
	.zong .jx{height: 50px;width: 15px;float: left;margin-right: 10px;background-color: #4B69DB;transition: 1s infinite;}	
	.dong{transform: scaleY(0.4);animation: donghua 1s infinite;}
	.dong:nth-child(1){}
	.dong:nth-child(2){animation-delay: 0.1s;}
	.dong:nth-child(3){animation-delay: 0.2s;}
	.dong:nth-child(4){animation-delay: 0.3s;}
	.dong:nth-child(5){animation-delay: 0.4s;}
	@keyframes donghua{
		0%,40%,80%,100%{
			transform: scaleY(0.4)
		}
		20%{
		transform: 
		scaleY(1)
		}
	}
.divcss5{
	float: left;
}
.divcss5 img{
border-radius:50%;
width: 60px;height: 60px;

}
.dropdownimg{
border-radius:50%;
width: 100px;height: 100px;
margin:30px auto;
}
</style>
<title>痕迹管理系统-首页</title>

</head>
<body>

	<div class="sys">
		<div class="header">
			<div class="log">
				<img src="img/1-1.png" height="80px">
			</div>
			<div class="user-infom">
				<%-- 				<p>
					<a href="${pageContext.request.contextPath}/getUserData">得到用户信息</a>
				</p> --%>

				<!-- <div class="user-p"></div> -->
				<p class="divcss5"><img id="userImage" src="img/${userDetailed.userImg}" /></p> 
				<p id="uuu">${userDetailed.userName}</p>
				<i id="aaa" class="fa fa-caret-down fa-2x" aria-hidden="true"></i> <i
					class="fa fa-envelope fa-2x" aria-hidden="true"></i> <i id="bbb"
					class="fa fa-caret-up fa-4x bbb" aria-hidden="true"></i>

				<div id="ddm" class="drop-down-m">
					<div style="text-align: center;">
					<img class="dropdownimg" src="img/${userDetailed.userImg}" />
					</div>
					<p>${userDetailed.userName}</p>
					<p>${userDetailed.userNumber}</p>
					<p><i class="fa fa-phone"></i>${userDetailed.contactPhone}</p>
					<hr>
					<p>
						<i class="fa fa-sign-out" aria-hidden="true"></i><a
							href="${pageContext.request.contextPath}/logout">退出登录</a>
					</p>
				</div>
			</div>
		</div>
		<div id="menu">
			<h1 id="systitle" class="tree1" onClick="src('welcome'),systitle()">痕迹管理系统</h1>
			<div class="zong" id="aa">
				<div class="jx dong"></div>
				<div class="jx dong"></div>
				<div class="jx dong"></div>
				<div class="jx dong"></div>
				<div class="jx dong"></div>
			</div>
			<ul id="tree_root">

			</ul>
		</div>
		<div class="contenta">
			<div class="contentanav">
				<div class="navbar">
					<span id="navtitle"></span>
				</div>
				
			</div>
			<div class="contentfile">
				<iframe id="contentaframe" height="100%" width="100%" src="welcome"
					scrolling="auto" frameborder="0"> </iframe>
			</div>
		</div>
	</div>

</body>
</html>
