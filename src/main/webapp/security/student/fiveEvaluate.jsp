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
<meta charset="UTF-8">
<title>五项评估</title>
<style>
.student_table {
	width: 100%;
}

td {
	text-align: center;
}

.tr_title {
	background-color: #2c7fcd;
	height: 60px;
}

.tr_title td {
	width: 100px;
}
</style>
</head>
<script type="text/javascript">
$(function(){
	$.getJSON("countAll", function(data) {
		if(data.success){
		$("#all").html(data.obj.totalPoints);
		$("#countsHonest").html(data.obj.honestPoints);
		$("#countsExecution").html(data.obj.executePoints);
		}
	})
})
</script>
<body>
	<table class="student_table" border="1" cellspacing="0"
		bordercolor="#CCCCCC">
		<tr class="tr_title">
			<td>五项总分</td>
			<td>诚信总分</td>
			<td>执行力总分</td>
			<td>技能总分</td>
			<td>团队总分</td>
			<td>健康总分</td>
			<td>备注</td>
			<td>年级排名（名次/总人数）</td>
			<td>专业排名（名次/总人数）</td>
			<td>班级排名（名次/总人数）</td>
		</tr>
		<tr style="height: 30px;">
			<td id="all">0</td>
			<td id="countsHonest">0</td>
			<td id="countsExecution">0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td>0</td>
			<td id="departmentRanking">0</td>
			<td id="majorRanking">0</td>
			<td id="classRanking">0</td>
		</tr>
	</table>
</body>
</html>
