<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<link rel="stylesheet" href="easyui/themes/default/easyui.css">
<link rel="stylesheet" href="easyui/themes/icon.css">
<link rel="stylesheet" href="easyui/demo/demo.css">
<link rel="stylesheet" href="fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<script type="text/javascript" src="easyui/jquery.min.js"></script>
<script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
<title>无标题文档</title>
<style type="text/css">
	tr:nth-child(2n){background-color:#E6E5E5;}	
	tr:hover{background-color:#FFFFFF;}
	.contentfilefooter{background-color: #E6F0FF;width: 100%;height: 60px;margin-top: 20px;width:720px;}
	.contentfilefooter .footerleftdiv{text-align:inherit;line-height: 60px;float: left;margin: 0 50px;}
	.contentfilefooter .footerleftdiv:hover{background-color:#FFFFFF;}
	.contentfilefooter .footerleftdiv>i:nth-child(1){color:#E5974A;}
	.contentfilefooter .footerleftdiv>i:nth-child(1):hover{color:#DBA675}
	.contentfilefooter .footerleftdiv>i:nth-child(2){color:#4144C5;}
	.contentfilefooter .footerleftdiv>i:nth-child(2):hover{color:#7B7DCF;}
	.contentfilefooter .footerleftdiv>i:nth-child(3){color:#C91417;}
	.contentfilefooter .footerleftdiv>i:nth-child(3):hover{color:#DE7D82;}
	
	.contentfilefooter .footercenterdiv{text-align:center;line-height: 60px;}
	
</style>
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
		<form id="searchff" action="#aaa" method="post" style="width: 50px;float: left;height: auto;">
			<input type="submit" value="查询" style="background-color: #6FB3E0;border: 0;border-radius: 3px;height: 24px;color: #fff;"></input>
		</form>
		<script>
			$('#searchff').form({
					success:function(data){
						$.messager.alert('Info', data, '查询到了');
					}
			});
		</script>
	</div>
	<table id="tt" class="easyui-datagrid" title="学生痕迹管理系统>课堂纪律" style="width:720px;height:auto;"
				data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
			<thead>
				<tr>
					<th data-options="field:'id',width:80,align:'center'">ID</th>
					<th data-options="field:'gou',width:50,align:'center'"></th>
					<th data-options="field:'stuid',width:100,align:'center'">学号</th>
					<th data-options="field:'name',width:80,align:'center'">姓名</th>
					<th data-options="field:'date',width:80,align:'center'">日期</th>
					<th data-options="field:'attr1',width:200,align:'center'">违规</th>
					<th data-options="field:'status',width:110,align:'center'">详细</th>
				</tr>
				<tr>
					<th data-options="field:'id',width:80,align:'center'">001</th>
					<th data-options="field:'gou',width:50,align:'center'"><i class="fa fa-square-o" id="gou" aria-hidden="true"></i></th>
					<th data-options="field:'stuid',width:100,align:'center'">20150424</th>
					<th data-options="field:'name',width:80,align:'center'">罗旭杨</th>
					<th data-options="field:'date',width:80,align:'center'">2015.10.20</th>
					<th data-options="field:'attr1',width:220,align:'center'">100分</th>
					<th data-options="field:'status',width:60,align:'center'">6666666</th>
				</tr>
			</thead>
	</table>
	
	<div class="contentfilefooter">
		<div class="footerleftdiv">
			<i id="add" class="fa fa-plus-circle" aria-hidden="true" onClick="javascrpt:$('#p').panel('open')">增加</i>
			<i id="upt" class="fa fa-pencil" aria-hidden="true">修改</i>
			<i id="del" class="fa fa-trash-o" aria-hidden="true">删除</i>
		</div>
		<div class="footercenterdiv">
			
			<div class="easyui-pagination" data-options="total:50"></div>
			
		</div>
		<div class="footerrightdiv"></div>
	</div>
<!--	弹出层-->
	<div id="modelkuang" style="background-color: rgba(0,0,0,0.59);width: 100%;height: 100%;position: absolute;left: 0;top: 0;display:none;">
		<div style="width: auto;height: auto;margin: 20px auto;">
			<a id="closebtn"  href="#" class="easyui-linkbutton" onclick="javascript:$('#p').panel('close')" style="position: relative;left: 470px;top: 30px;z-index: 20;background-color: transparent;">
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
						<td></td>
						
					</tr>
					<tr style="background-color: #fff;">
						<td>抄近道/缺席：</td>
						<td><input class="easyui-textbox" type="text" style="width:200px;height:32px;"></td>
					</tr>
					<tr>
						<td>备注：</td>
						<td><input class="easyui-textbox" type="text" style="width:150px;height:32px;"></td>
					</tr>
					<tr style="background-color: #fff;">
						<td colspan="2"><a href="#" class="easyui-linkbutton" iconCls="icon-ok" style="width:100px;height:32px">添加</a></td>
					</tr>
				</table>
				
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function(){
			//判断是否勾中------------------------------
			var indexbs=0;
			$('#gou').click(function(){
				if(indexbs==0){
					$('#gou').removeClass('fa-square-o');
					$('#gou').addClass('fa-check-square-o');
					indexbs=1;
				}else{
					$('#gou').removeClass('fa-check-square-o');
					$('#gou').addClass('fa-square-o');
					indexbs=0;
				}
				
			});
			//----------------------------------------
			//弹出模态框---------------------------
			$('.contentfilefooter .footerleftdiv>#add').click(function(){
				$('#modelkuang').css('display','block');
			});
			$('#closebtn').click(function(){
				$('#modelkuang').css('display','none');
				$('this').css('display','none');
			});
			//----------------------------------
		});
	</script>
</body>
</html>
