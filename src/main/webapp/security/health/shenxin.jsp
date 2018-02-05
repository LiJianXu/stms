<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<link rel="icon" href="../img/titleimg.png" type="image/png">
<link rel="stylesheet" href="../easyui/themes/default/easyui.css">
<link rel="stylesheet" href="../easyui/themes/icon.css">
<link rel="stylesheet" href="../easyui/demo/demo.css">
<link rel="stylesheet" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/flie.css">
<script type="text/javascript" src="../easyui/jquery.min.js"></script>
<script type="text/javascript" src="../easyui/jquery.easyui.min.js"></script>
<title>身心健康</title>
</head>

<body>
	<div style="margin-bottom: 20px;">
		<input id="xx" style="width:150px;float: left;"
			url="data/combobox_data.json"
			valueField="id" textField="text" data-options="prompt:'选择系名'">
		</input>
		
		<script type="text/javascript">
			$('#xx').combobox({
				formatter:function(row){
					var imageFile = 'images/' + row.icon;
					return '<img class="item-img" src="'+imageFile+'"/><span class="item-text">'+row.text+'</span>';
				}
			});
		</script>
		<input id="nn" style="width:100px;float: left;"
			url="data/combobox_data.json"
			valueField="id" textField="text" data-options="prompt:'选择年级'">
		</input>
		<script type="text/javascript">
			$('#nn').combobox({
				formatter:function(row){
					var imageFile = 'images/' + row.icon;
					return '<img class="item-img" src="'+imageFile+'"/><span class="item-text">'+row.text+'</span>';
				}
			});
		</script>
		<input id="cc" style="width:80px;float: left;"
			url="data/combobox_data.json"
			valueField="id" textField="text" data-options="prompt:'选择班级'">
		</input>
		<script type="text/javascript">
			$('#cc').combobox({
				formatter:function(row){
					var imageFile = 'images/' + row.icon;
					return '<img class="item-img" src="'+imageFile+'"/><span class="item-text">'+row.text+'</span>';
				}
			});
		</script>
		<form id="searchff" action="#" method="post" style="width: 50px;float: left;height: auto;">
			<input type="submit" value="查询" style="background-color: #6FB3E0;border: 0;border-radius: 3px;height: 24px;color: #fff;"></input>
		</form>
<!--
		<script>
			$('#searchff').form({
					success:function(data){
						$.messager.alert('Info', data);
					}
			});
		</script>
-->
	</div>
	<table id="tt" class="easyui-datagrid" title="学生痕迹管理系统>身心健康" style="width:1040px;height:auto;"
				data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,align:'center'">ID</th>
					<th data-options="field:'gou',width:50,align:'center'"><i class="fa fa-square-o" id="gou" aria-hidden="true"></i></th>
					<th data-options="field:'stuid',width:100,align:'center'">学号</th>
					<th data-options="field:'name',width:80,align:'center'">姓名</th>
					<th data-options="field:'date',width:100,align:'center'">日期</th>
					<th data-options="field:'attr1',width:120,align:'center'">学院体测成绩</th>
					<th data-options="field:'attr1',width:120,align:'center'">学校体质测试成绩</th>
					<th data-options="field:'attr1',width:80,align:'center'">病假</th>
					<th data-options="field:'attr1',width:120,align:'center'">是否违背社会公德</th>
					<th data-options="field:'status',width:150,align:'center'">备注</th>
				</tr>
			</thead>
	</table>
	
	<div class="contentfilefooter">
		<div class="footerleftdiv">
			<span id="add" onClick="javascript:$('#p').panel('open')"><i class="fa fa-plus-circle" aria-hidden="true">增加</i></span>
			<span id="upt" onClick="javascript:$('#p2').panel('open')"><i class="fa fa-pencil" aria-hidden="true">修改</i></span>
			<span id="del"><i class="fa fa-trash-o" aria-hidden="true">删除</i></span>
		</div>
		<div class="footercenterdiv">
			
			<div class="easyui-pagination" data-options="total:50"></div>
			
		</div>
		<div class="footerrightdiv"></div>
	</div>
<!--	弹出层-->
	<div id="modelkuang" style="background-color: rgba(0,0,0,0.59);width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:none;">
<!--	(添加)-->
		<div id="addpanel" style="width: auto;height: auto;margin: 20px 100px;display: none;">
			<a id="closebtn"  href="#" class="easyui-linkbutton" onClick="javascript:$('#p').panel('close')" style="position: relative;left: 470px;top: 30px;z-index: 20;background-color: transparent;">
				<i class="fa fa-times fa-2x" aria-hidden="true"></i>
			</a>
		
			<div id="p" class="easyui-panel" title="增加" style="width:500px;height:auto;padding:10px;">
				<table style="width:100%;line-height: 50px;padding: 20px;font-size: 1rem;">
					<tr>
						<td>学号：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>姓名：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr>
						<td>日期：</td>
						<td><input class="easyui-datebox" style="width:200px;height: 32px;"></td>
						
					</tr>
					<tr style="background-color: #fff;">
						<td>学院体测成绩：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>学校体质测试成绩：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>病假：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>是否违背社会公德：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="easyui-textbox" type="text" style="width:150px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-add" style="width:100px;height:32px">添加</a></td>
					</tr>
				</table>
				
			</div>
		</div>
		<!--	（修改）-->
		<div id="uptpanel" style="width: auto;height: auto;margin: 20px 100px;display: none;">
			<a id="closebtn2"  href="#" class="easyui-linkbutton" onClick="javascript:$('#p').panel('close')" style="position: relative;left: 470px;top: 30px;z-index: 20;background-color: transparent;">
				<i class="fa fa-times fa-2x" aria-hidden="true"></i>
			</a>
		
			<div id="p2" class="easyui-panel" title="修改" style="width:500px;height:auto;padding:10px;">
				<table style="width:100%;line-height: 50px;padding: 20px;font-size: 1rem;">
					<tr>
						<td>学号：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>姓名：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr>
						<td>日期：</td>
						<td><input class="easyui-datebox" style="width:200px;height: 32px;"></td>
						
					</tr>
					<tr style="background-color: #fff;">
						<td>学院体测成绩：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>学校体质测试成绩：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>病假：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td>是否违背社会公德：</td>
						<td><input class="easyui-textbox" type="text" style="width:100px;height:32px;"></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="easyui-textbox" type="text" style="width:150px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-save" style="width:100px;height:32px">修改</a></td>
					</tr>
				</table>
				
			</div>
		</div>
	</div>
<script type="text/javascript" src="../js/model.js"></script>

	
</body>
</html>
