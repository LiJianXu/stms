<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" href="../easyui/themes/icon.css">
<link rel="stylesheet" href="../easyui/demo/demo.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<!-- 引入jquery  form 插件 -->
<script type="text/javascript" src="../js/jquery-form.js"></script>
<base href="<%=basePath%>">

<title>学生信息界面</title>
<style type="text/css">
.header {
	margin: 30px;
	text-align: center;
}

.header img {
	width: 100px;
	height: 100px;
	border-radius: 50%;
}

.fileInput {
	height: 100px;
	font-size: 100px;
	position: absolute;
	right: 0;
	top: 30px;
	opacity: 0;
	filter: alpha(opacity = 0);
	cursor: pointer;
}

.content {
	width: 100%;
	margin: 10px;
	text-align: center;
}

.delatils-student {
	border: 1px solid #95B8E7;
	border-radius: 5px 5px 5px 5px;
	padding-left: 5px;
}
</style>
</head>
<script type="text/javascript">
	$(function() {
		$.getJSON("student/getStudent/", function(data) {
			if (Number(data.studentId) > 0) {
				//alert(data.studentNumber)
				//$("#studentNumber").val(data.studentNumber);
				$("#studentNumber").val(data.studentNumber)
				$("#studentId").val(data.studentId)
				//$("#_easyui_textbox_input1").val(data.studentNumber);
				$("#studentSex").val(data.studentSex);
				$("#studentNation").val(data.studentNation);
				$("#studentEmail").val(data.studentEmail);
				$("#contactPhone").val(data.contactPhone);
				$("#studentCard").val(data.studentCard);
				$("#studentHometown").val(data.studentHometown);
				$("#departmentName").val(data.studentDepartment.name);
				$("#studentDepartmentId").val(data.studentDepartment.id);
				$("#studentClassSpecialty").val(data.studentClass.specialty);
				$("#studentClassName").val(data.studentClass.name);
				$("#studentClassId").val(data.studentClass.id);
				$("#entranceYear").val(data.entranceYear);
				$("#onlineYear").val(data.onlineYear);
				$("#graduateYear").val(data.graduateYear);
			}
		})
		$("#imgFile").on("change", function() {
			var file = $(this);
			//alert(file[0].files[0])

			var filepath = $(this).val();
			var extStart = filepath.lastIndexOf(".");
			var ext = filepath.substring(extStart, filepath.length).toUpperCase();
			if (ext != ".PNG" && ext != ".JPG" && ext != ".JPEG") {
				alert("图片限于png,jpg,JPEG格式");
			}else if(file[0].files[0].size > 1048576){
			alert("请上传小于1M的图片");
			}else {
				var formdata = new FormData();
				formdata.append("file", file[0].files[0]);
				$.ajax({
					url : "student/upload_file",
					type : 'POST',
					cache : false,
					data : formdata,
					processData : false,
					contentType : false,
					dataType : "json",
					success : function(data) {
						if(data.success){
						//alert(data.obj);
						$("#userImg").attr("src","img/"+data.obj);
						}else{
						alert("上传失败");
						}
					},
					error : function(e){
					alert("上传异常");
					}
				});
			}
		})
		
 		$("#saveStudent").click(function(){
 			$("#submitStudent").ajaxSubmit(function(data){    
		        if(data.success){
		        window.location.reload();
		        }else{
		        alert("保存失败");
		        }
			})
    	});  
	})
</script>
<body>
	<div class="header">
		<img alt="头像" id="userImg" src="img/${userDetailed.userImg}"><input
			class="fileInput" type="file" id="imgFile" />
		<p>
			<b>点击图片上传头像</b>
		</p>
		<p>
			<b style="color: red;font-size:20px">${userDetailed.userName}</b>
		</p>
	</div>
	<div>
	<form action="student/upload_student" method="post" id="submitStudent">
		<div>
			<table class="content">
				<tr>
					<td>学号：</td>
					<td><input id="studentNumber" class="delatils-student"
						style="width:90%;height:32px" name="studentNumber"></td>
					<td>性别：</td>
					<td><input id="studentSex" class="delatils-student"
						style="width:90%;height:32px" name="studentSex"></td>
					<td>名族：</td>
					<td><input id="studentNation" class="delatils-student"
						style="width:90%;height:32px" name="studentNation"></td>
				</tr>
				<tr>
					<td>邮箱：</td>
					<td><input id="studentEmail" class="delatils-student"
						style="width:90%;height:32px" name="studentEmail"></td>
					<td>电话：</td>
					<td><input id="contactPhone" class="delatils-student"
						style="width:90%;height:32px" name="contactPhone"></td>
					<td>身份证号码：</td>
					<td><input id="studentCard" class="delatils-student"
						style="width:90%;height:32px" name="studentCard"></td>
				</tr>
				<tr>
					<td>系：</td>
					<td><input id="departmentName" class="delatils-student"
						style="width:90%;height:32px" name="studentDepartment.name"></td>
					<td>专业：</td>
					<td><input id="studentClassSpecialty" class="delatils-student"
						style="width:90%;height:32px" name="studentClass.specialty"></td>
					<td>班级：</td>
					<td><input id="studentClassName" class="delatils-student"
						style="width:90%;height:32px" name="studentClass.name"></td>
				</tr>
				<tr>
					<td>入学年份：</td>
					<td><input id="entranceYear" class="delatils-student"
						style="width:90%;height:32px"  name="entranceYear"></td>
					<td>学制：</td>
					<td><input id="onlineYear" class="delatils-student"
						style="width:90%;height:32px"  name="onlineYear"></td>
					<td>毕业年份：</td>
					<td><input id="graduateYear" class="delatils-student"
						style="width:90%;height:32px"  name="graduateYear"></td>
				</tr>
				<tr><td>地址：</td><td><input id="studentHometown" name="studentHometown" class="delatils-student" style="width:90%;height:32px"></td></tr>
				<tr>
				<td><input type="text" style="display: none;" id="studentId" name="studentId"></td><td><input type="text" style="display: none;" id="studentClassId" name="studentClass.id"></td><td><input type="text" style="display: none;" id="studentDepartmentId" name="studentDepartment.id"></td>
				</tr>
			</table>
			
		</div>
		<div class="content">
			<span class="easyui-linkbutton" data-options="iconCls:'icon-save'"
				id="saveStudent">保存</span>
		</div>
		</form>
	</div>

</body>
</html>
