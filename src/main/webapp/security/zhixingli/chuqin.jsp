<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<link rel="icon" href="${path}/stms/img/titleimg.png" type="image/png">
<link rel="stylesheet"
	href="${path}/stms/easyui/themes/default/easyui.css">
<link rel="stylesheet" href="${path}/stms/easyui/themes/icon.css">
<link rel="stylesheet" href="${path}/stms/easyui/demo/demo.css">
<link rel="stylesheet"
	href="${path}/stms/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="${path}/stms/css/flie.css">
<script type="text/javascript" src="${path}/stms/easyui/jquery.min.js"></script>
<script type="text/javascript"
	src="${path}/stms/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${path}/stms/easyui/locale/easyui-lang-zh_CN.js"></script>
<title>出勤情况</title>
</head>
<style>
</style>
<body>
	<div style="margin-bottom: 20px;">
		<input id="department" style="width: 150px; float: left;"
			 data-options="prompt:'选择系名'">
		</input>

		<script type="text/javascript">
			$.ajax({
				url : '/stms/getDepartment',
				dataType : "json",
				type : "GET",
				success : function(data) {
					//绑定第一个下拉框
					$('#department').combobox({
						data : data,
						valueField : 'id',
						textField : 'name',
						onSelect : function(rec) {
							var url = "/stms/get_majors?dpId=" + rec.id;
							$("#major").combobox("reload", url);
						}
					})
				}

			})
		</script>
		<input id="major" style="width: 100px; float: left;" data-options="prompt:'专业'"> </input>
		<script type="text/javascript">
			$("#major").combobox({
				valueField : 'id',
				textField : 'name',
				onSelect : function(rec) {
					var url = "/stms/getClass?majorId=" + rec.id;
					$("#class").combobox("reload", url);
				}
			})
		</script>
		<input id="class" style="width: 80px; float: left;" valueField="id" textField="text"
			data-options="prompt:'选择班级'"> </input>
		<script type="text/javascript">
			$('#class').combobox({
				valueField : 'id',
				textField : 'name'	
			});
		</script>
		<form id="searchff" style="width: 50px; float: left; height: auto;">
			<input id="search" type="button" value="查询"
				style="background-color: #6FB3E0; border: 0; border-radius: 3px; height: 24px; color: #fff;"></input>
		</form>
		<script>
			$('#search').click(function(){
				var classId = $('#class').combobox('getValue');
				getData(classId, 1, 10);
			});
		</script>
	</div>
	<table id="dataGrid" class="easyui-datagrid" title="学生痕迹管理系统>出勤情况"
		style="width: 1040px; height: auto;" data-options="rownumbers:true">
		<thead>
			<tr>
				<th data-options="field:'ck',checkbox:true"></th>
				<th data-options="field:'id',width:100,align:'center'"></th>
				<th data-options="field:'studentNumber',width:100,align:'center'">学号</th>
				<th data-options="field:'studentName',width:80,align:'center'">姓名</th>
				<th data-options="field:'recordTime',width:200,align:'center'">日期</th>
				<th data-options="field:'rulesName',width:150,align:'center'">违规</th>
				<th data-options="field:'descript',width:350,align:'center'">详情</th>
				<th data-options="field:'rulesScore',width:70,align:'center'">扣分</th>
			</tr>
		</thead>
	</table>
	<div class="contentfilefooter">
		<div class="footerleftdiv">
			<span id="add" onClick="javascript:$('#p').panel('open')"><a
				class="fa fa-plus-circle" aria-hidden="true"
				style="cursor: pointer;">增加</a></span> <span id="upt"><a
				class="fa fa-pencil" aria-hidden="true" style="cursor: pointer;">修改</a></span>
			<span id="del"><a class="fa fa-trash-o" aria-hidden="true"
				style="cursor: pointer;">删除</a></span>
		</div>
		<div class="footercenterdiv">
			<div id="pagination" class="easyui-pagination"
				style="border: 1px solid #ccc"></div>
			<div class="footerrightdiv"></div>
		</div>


		<!--	弹出层-->
		<div id="modelkuang"
			style="background-color: rgba(0, 0, 0, 0.59); width: 100%; height: 100%; position: absolute; left: 0; top: 0; display: none;">
			<!--	(添加)-->
			<div id="addpanel"
				style="width: auto; height: auto; margin: 20px 100px; display: none;">
				<a id="closebtn" href="#" class="easyui-linkbutton"
					onClick="javascript:$('#p').panel('close')"
					style="position: relative; left: 470px; top: 30px; z-index: 20; background-color: transparent;">
					<i class="fa fa-times fa-2x" aria-hidden="true"></i>
				</a>

				<div id="p" class="easyui-panel" title="增加"
					style="width: 500px; height: auto; padding: 10px;">
					<table
						style="width: 100%; line-height: 50px; padding: 20px; font-size: 1rem;">
						<tr>
							<td>学号：</td>
							<td><input id="studentNumber" class="easyui-textbox"
								type="text" style="width: 100px; height: 32px;"></td>
						</tr>
						<tr style="background-color: #fff;">
							<td>姓名：</td>
							<td><input id="studentName" class="easyui-textbox"
								type="text" style="width: 100px; height: 32px;"></td>
						</tr>
						<tr>
							<td>日期：</td>
							<td><input id="date" class="easyui-datebox"
								style="width: 200px; height: 32px;"></td>

						</tr>
						<tr style="background-color: #fff;">
							<td>违规：</td>
							<td><select id="ruleId" class="easyui-combobox"
								style="width: 200px; height: 32px;">
									<option value="1">迟到</option>
									<option value="2">旷课</option>
									<option value="3">请假</option>
									<option value="4">早退</option>
							</select></td>
						</tr>
						<tr>
							<td>详情：</td>
							<td><input id="describe" class="easyui-textbox" type="text"
								style="width: 350px; height: 32px;"></td>
						</tr>
						<tr style="background-color: #fff;">
							<td colspan="2"><a id="btn_add" class="easyui-linkbutton"
								iconCls="icon-add" style="width: 100px; height: 32px">添加</a></td>
						</tr>
					</table>

				</div>
			</div>
			<!--	（修改）-->
			<div id="uptpanel"
				style="width: auto; height: auto; margin: 20px 100px; display: none;">
				<a id="closebtn2" href="#" class="easyui-linkbutton"
					onClick="javascript:$('#p').panel('close')"
					style="position: relative; left: 470px; top: 30px; z-index: 20; background-color: transparent;">
					<i class="fa fa-times fa-2x" aria-hidden="true"></i>
				</a>

				<div id="p2" class="easyui-panel" title="修改"
					style="width: 500px; height: auto; padding: 10px;">
					<table
						style="width: 100%; line-height: 50px; padding: 20px; font-size: 1rem;">
						<tr>
							<td>学号：</td>
							<td><input id="upstudentNumber" type="text"
								style="width: 100px; height: 30px; border-radius: 5px;"></td>
						</tr>
						<tr style="background-color: #fff;">
							<td>姓名：</td>
							<td><input id="upstudentName" type="text"
								style="width: 100px; height: 30px; border-radius: 5px;"></td>
						</tr>
						<tr>
							<td>日期：</td>
							<td><input id="update" style="width: 200px; height: 30px; border-radius: 5px;" /></td>
						</tr>
						<tr style="background-color: #fff;">
							<td>违规：</td>
							<td><select id="upruleId" class="easyui-combobox"
								style="width: 200px; height: 32px;">
									<option value="1">迟到</option>
									<option value="2">旷课</option>
									<option value="3">请假</option>
									<option value="4">早退</option>
							</select></td>
						</tr>
						<tr>
							<td>详情：</td>
							<td><input id="updescribe" type="text"
								style="width: 350px; height: 30px; border-radius: 5px;"></td>
						</tr>
						<tr style="background-color: #fff;">
							<td colspan="2" ><a id="up-btn" class="easyui-linkbutton"
								iconCls="icon-save" style="width: 100px; height: 32px">修改</a></td>
						</tr>
					</table>

				</div>
			</div>

		</div>
	</div>
	<script type="text/javascript" src="${path}/stms/js/model.js"></script>
	<script type="text/javascript">
		//获取数据
		var class_Id;
		function getData(classId, page, size) {
			class_Id = classId;
			$.get("/stms/get_executes?menuId=10&classId=" + classId + "&page="
					+ page + "&size=" + size, function(result) {
				var list = result.list;
				var dataList = new Array();
				for ( var i in list) {
					var row_data = {
						id : list[i].id,
						studentNumber : list[i].studentNumber,
						studentName : list[i].studentName,
						recordTime : list[i].recordTime,
						rulesName : list[i].rules.rulesName,
						rulesScore : -list[i].rules.rulesScore,
						descript : list[i].descript
					};
					dataList[i] = row_data;
				}
				$('#dataGrid').datagrid('loadData', dataList);
				$("#dataGrid").datagrid('hideColumn', 'id');
				$('#pagination').pagination({
					total : result.rowCount,
					onSelectPage : function(pageNumber, pageSize) {
						getData(classId, pageNumber, pageSize);
					}
				})
			})
		}

		//添加执行纪录
		$("#btn_add").click(
				function() {
					var studentNumber = $("#studentNumber").val();
					var studentName = $("#studentName").val();
					var date = $("#date").val();
					var describe = $("#describe").val();
					var ruleId = $("#ruleId").val();
					var menuId = 10;
					if (studentName == "" || studentNumber == "" || date == ""
							|| ruleId == "") {
						alert("请确认信息是否填写完整");
						return;
					}
					$.post("/stms/add_executes", {
						"studentNumber" : studentNumber,
						"studentName" : studentName,
						"describe" : describe,
						"date" : date,
						"ruleId" : ruleId,
						"menuId" : menuId,
						"id":null
					}, function(data) {
						var result = JSON.parse(data);
						if (result.success) {
							alert("添加成功");
							getData(class_Id, 1, 10);
						} else {
							alert(result.msg);
						}
					})
				})

		//修改记录
		var upId;
		$("#upt").click(function() {
							var node = $(".datagrid-btable").eq(1).find(
									".datagrid-row-checked").eq(0);
							if (node.length < 1) {
								alert("请选择你要修改的数据");
							} else {
								upId= node.find("td").eq(1).text();
								$("#upstudentNumber").val(node.find("td").eq(2).text());
								$("#upstudentName").val(node.find("td").eq(3).text());
								$("#update").val(node.find("td").eq(4).text());
								$("#upruleId").val(node.find("td").eq(5).text());
								$("#updescribe").val(node.find("td").eq(6).text());
							}

						})

		$("#up-btn").click(function(){
					var studentNumber = $("#upstudentNumber").val();
					var studentName = $("#upstudentName").val();
					var date = $("#update").val();
					var describe = $("#updescribe").val();
					var ruleId = $("#upruleId").val();
					var menuId = 10;
					if (studentName == "" || studentNumber == "" || date == ""|| ruleId == "") {
						alert("请确认信息是否填写完整");
						return;
					}
					$.post("/stms/add_executes", {
						"studentNumber" : studentNumber,
						"studentName" : studentName,
						"describe" : describe,
						"date" : date,
						"ruleId" : ruleId,
						"menuId" : menuId,
						"id":upId,
					}, function(data) {
						var result = JSON.parse(data);
						if(result.success){
							alert("修改成功");
							getData(class_Id, 1, 10);
						}else{
							alert(result.msg);
						}
					})
				})

		//删除记录
		$("#del").click(
				function() {
					var node = $(".datagrid-btable").eq(1).find(
							".datagrid-row-checked");
					if (node.length < 1) {
						alert("请选择你要删除的数据");
						return;
					}
					for (var i = 0; i < node.length; i++) {
						var id = node.eq(i).find("td").eq(1).text();
						$.ajax({
							url : "/stms/delete_excutes_byId",
							data : {
								"id" : id
							},
							dataType : "json",
							type : "get",
							async : false,
							success : function(data) {
									getData(class_Id, 1, 10)
							}
						})
					}

				})
	</script>
</body>
</html>
