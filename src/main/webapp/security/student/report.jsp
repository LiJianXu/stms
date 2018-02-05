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
<meta charset="UTF-8">
<base href="<%=basePath%>">
<title>轨迹报告</title>
<style>
.student_table {
	width: 100%;
}

.student_table_chengxin {
	width: 70%;
}

.print {
	margin-top: 10px;
	width: 50px;
	height: 30px; border : none;
	background: aqua;
	cursor: pointer;
	border: none;
}

.print:hover {
	color: black;
	background: #2c7fcd;
}

td {
	text-align: center;
}

.tr_title {
	background-color: #2c7fcd;
}
</style>
</head>
<script type="text/javascript">
$(function(){
$.getJSON("student/getStudent/", function(data) {
			if (Number(data.studentId) > 0) {
				$("#studentName").text(data.studentName);
				$("#departmentName").text(data.studentDepartment.name);
				$("#studentClassSpecialty").text(data.studentClass.specialty);
				$("#studentClassName").text(data.studentClass.name);

			}
		})
//添加执行力信息
$.getJSON("counts_execution",function(data) {
	$("#execution").html("");
	$("#execution").append("<tr class='tr_title'><td width='25%'>日期</td><td width='25%'>备注(加分情况)</td><td width='25%'>备注(减分情况)</td><td width='25%'>诚信总分</td></tr>");
	if(data!=null && data!="[]"){
		$.each(data,function(i,item){
			$("#execution").append("<tr id='executionrole"+item.id+"'><td>"+item.date+"</td><td>"+item.addScore+"</td><td>"+item.subScore+"</td><td>"+item.allScore+"</td></tr>");
		})
	}
})
//添加诚信信息
$.getJSON("honest/counts_honest",function(data) {
	$("#honest").html("");
	$("#honest").append("<tr class='tr_title'><td width='25%'>日期</td><td width='25%'>备注(加分情况)</td><td width='25%'>备注(减分情况)</td><td width='25%'>诚信总分</td></tr>");
	if(data!=null && data!="[]"){
		$.each(data,function(i,item){
			$("#honest").append("<tr id='executionrole"+item.id+"'><td>"+item.date+"</td><td>"+item.addScore+"</td><td>"+item.subScore+"</td><td>"+item.allScore+"</td></tr>");
		})
	}
})

	$("#print").click(function(){
	window.print();
	})
	$.getJSON("countAll", function(data) {
		if(data.success){
		$("#all").html(data.obj.totalPoints);
		}
	})
})
</script>
<body>
	<table class="student_table" border="1" cellspacing="0"
		bordercolor="#CCCCCC">
		<tbody>
			<tr style="height: 60px;">
				<th colspan="4">四川交通职业技术学院痕迹报告</th>
			</tr>
			<tr style="height: 30px;">
				<td>姓名:<span id="studentName"></span></td>
				<td>班级:<span id="studentClassName"></span></td>
				<td>专业:<span id="studentClassSpecialty"></span></td>
				<td>系别:<span id="departmentName"></span></td>
			</tr>
			<tr style="height: 120px;">
				<th colspan="4">
					<div style="margin: 20px;">
						<p>诚信情况</p>
						<center>
							<table class="student_table_chengxin" border="1" cellspacing="0"
								bordercolor="#CCCCCC">
								<tbody id="honest">
								</tbody>
							</table>
						</center>
					</div>
					<div style="margin: 20px;">
						<p>执行力情况</p>
						<center>
							<table class="student_table_chengxin" border="1" cellspacing="0"
								bordercolor="#CCCCCC">
								<tbody id="execution">
								</tbody>
							</table>
						</center>
					</div>
					<div style="margin: 20px;">
						<p>团队情况</p>
						<center>
							<table class="student_table_chengxin" border="1" cellspacing="0"
								bordercolor="#CCCCCC">
								<tbody>
									<tr class="tr_title">
										<td width="25%">日期</td>
										<td width="25%">备注(加分情况)</td>
										<td width="25%">备注(减分情况)</td>
										<td width="25%">团队总分</td>
									</tr>
								</tbody>
							</table>
						</center>
					</div>
					<div style="margin: 20px;">
						<p>技能情况</p>
						<center>
							<table class="student_table_chengxin" border="1" cellspacing="0"
								bordercolor="#CCCCCC">
								<tbody>
									<tr class="tr_title">
										<td width="25%">日期</td>
										<td width="25%">备注(加分情况)</td>
										<td width="25%">备注(减分情况)</td>
										<td width="25%">技能总分</td>
									</tr>
								</tbody>
							</table>
						</center>
					</div>
					<div style="margin: 20px;">
						<p>健康情况</p>
						<center>
							<table class="student_table_chengxin" border="1" cellspacing="0"
								bordercolor="#CCCCCC">
								<tbody>
									<tr class="tr_title">
										<td width="25%">日期</td>
										<td width="25%">备注(加分情况)</td>
										<td width="25%">备注(减分情况)</td>
										<td width="25%">健康总分</td>
									</tr>
								</tbody>
							</table>
						</center>
					</div>
				</th>
			</tr>
			<tr style="height: 30px;">
				<td>五项总分<span id="all">0</span></td>
				<td>年级排名<span id="departmentRanking">0</span></td>
				<td>专业排名<span id="majorRanking">0</span></td>
				<td>班级排名<span id="classRanking">0</span></td>
			</tr>
		</tbody>
	</table>
	<div style="width: 100%;text-align: center;">
		<button class="print" id="print">打印</button>
	</div>
</body>

</html>