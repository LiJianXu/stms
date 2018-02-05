<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" href="easyui/themes/icon.css">
<link rel="stylesheet" href="easyui/demo/demo.css">
<link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<title>登录界面</title>
<style type="text/css">
	body{margin: 0;padding: 0;background: url(img/back2.jpg) no-repeat;}
	#content{width: 980px;height: 500px;background-color:rgba(8,10,115,0.47);margin: 10% auto;}	
	@media screen and (max-width: 980px) {
		#content{width: 900px;}
	}
	#content .left-content{width:50%;height: 100%;float: left;}
	@media screen and (max-width: 768px){
		.left-content{width:100%;float: none;}
	}
	.left-content>img{margin-top:50px;}
	.left-content p{font-size:1.6rem;color: #fff;text-align: center;}
	
	#content .right-content{width:50%;height: 100%;float: left;}
	@media screen and (max-width: 768px){
		.right-content{width:100%;float: none;}
	}
	.fromdiv{margin:100px 30px;width: auto;height: auto;background-color:#fff;padding: 10px;}
	.fromdiv>p{color: #686767;font-size: 1.2rem;text-align: center;}
	.fromdiv .inputdome{margin-top: 20px;height: 32px;}
	.fromdiv .inputdome i{line-height: 32px;text-align: center;color:rgba(54,54,54,0.89);}
	.fromdiv .inputdome a:link{text-decoration: none;color: #292828;}
	.fromdiv .inputdome .button1{background-color: #00923F;width: 100%;height: 100%;border: 0;color: #FFFFFF;font-size: 1.2rem;transition: all .5s;}
	.fromdiv .inputdome .button1:hover{background-color: #16C461;transition: all .5s;}
</style>
</head>

<body>
<div id="content">
	<div class="left-content">
		<img src="img/2.png" width="100%" height="150px">
		<p>学生痕迹管理系统</p>
	</div>
	<div class="right-content">
	<form id="ff" method="post">
		<div class="fromdiv">
			<p>同意身份认证</p>
			<div class="inputdome">
				<div style="width: 10%;height:32px;float: left;"><i class="fa fa-user-circle fa-2x" id="f1" aria-hidden="true"></i></div>
				<input id="name" class="easyui-textbox input1" type="text" style="width:90%;height:32px;float: left;opacity: 0;">
			</div>
			<div class="inputdome">
				<div style="width: 10%;height:32px;float: left;"><i class="fa fa-lock fa-2x" id="f2" aria-hidden="true"></i></div>
				<input id="password" class="easyui-textbox input2" type="password" style="width:90%;height:32px;float: left;">
			</div>
			<div class="inputdome">
				<div style="width: 10%;height:32px;float: left;"><i class="fa fa-square-o fa-2x" id="f3" aria-hidden="true"></i></div>
				<div style="width:90%;height:32px;float: left;font-size: 1rem;color: #838181;line-height: 32px;">记住密码</div>
			</div>
			<div class="inputdome">
				<button type="button" class="button1">登陆</button>
			</div>
			<div class="inputdome">
				<div style="width: 50%;font-size: 0.8rem;float: left;"><a href="#">登陆说明？</a></div>
				<div style="width: 50%;font-size: 0.8rem;float: right;text-align: right;"><a href="#">忘记密码</a></div>
			</div>
		</div>
	</form>
</div>
<script>
$(document).ready(function(){
	var indexbs=0;
	$('#f3').click(function(){
		if(indexbs==0){
			$('#f3').removeClass('fa-square-o');
			$('#f3').addClass('fa-check-square-o');
			indexbs=1;
		}else{
			$('#f3').removeClass('fa-check-square-o');
			$('#f3').addClass('fa-square-o');
			indexbs=0;
		}
	});
	$(".inputdome button").click(function(){
		var name = $("#name").val();
		var password = $("#password").val();
		$.ajax({
			url:"${pageContext.request.contextPath}/login",
			data:{"username":name,"password":password},
			dataType:"text",
			type:"post",
			success:function(data){
			if(data!=null){
				data = JSON.parse(data)
				if(data.success){
					alert("登陆成功");
					window.location.href="${pageContext.request.contextPath}/index";
				}else{
					alert(data.msg);
					console.log(data.msg);
				}
			}
			}
			
		})
	})
});
		
</script>
</body>
</html>