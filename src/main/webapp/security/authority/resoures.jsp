<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="../fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/zeroModal.css">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../js/zeroModal.min.js"></script>
<title>资源管理</title>
<base href="<%=basePath%>">

    <style type="text/css">
        *{font-family: 微软雅黑;}
        table{border: 1px solid #ccc;margin: 50px auto;}
        td{border: 1px solid #fff;}
        thead{color: #fff;background-color: #6F93FF;}
        thead tr{height: 30px;}
        tbody{font-size: 1rem;}
        tbody tr:nth-child(odd){background-color: #e8e8e8;height: 24px;}
        tbody tr:nth-child(even){background-color: #ffffff;height: 24px;}
        tbody tr:nth-child(odd) td:hover{background-color: #ffffff;}
        tbody tr:nth-child(even) td:hover{background-color: #e8e8e8;}
        ul{list-style: none;}
        td a:nth-child(1) i{color:#E5974A;}
        td a:nth-child(2) i{color:#4144C5}
        td a:nth-child(3) i{color:#C91417;}
    </style>
</head>
<body>
<center><a href="javascript:void(0);" onclick="addResoures()">添加资源</a></center>
    <table cellspacing="0">
        <thead>
            <tr>
                <th width="150px">资源名称</th>
                <th width="300px">资源路径</th>
                <th width="150px">资源描述</th>
                <th width="150px">操作</th>
            </tr>
        </thead>
        <tbody id="resouresList">
           
        </tbody>
        
    </table>
<script>

    function _exit(id,name,url,de){

        zeroModal.show({
            title:'编辑',
            content:"<ul>"+
                       "<li>资源名称：<input id='name'  type='text' value="+name+"></li><br>"+
                        "<li>资源路径：<input id='url'  type='text' value="+url+"></li><br>"+
                       "<li>资源描述：<input id='de' type='text' value="+de+"></li><br>"+
                    "</ul>",
            left: "100px",
            top: "100px",
            ok: true,
            okFn: function(){
            	var data={
            	authId:id,
            	authName:$("#name").val(),
            	resourceUrl:$("#url").val(),
            	authDescribe:$("#de").val()
            	}
            	$.post("authority/update_authority", data, function(result) {
            		if(!result.success){
            		alert("更新失败");
            		}else{
            			getResoures();
            		}
            	})
            }
        });
    }
    function _delete(id){
        zeroModal.alert({
            content: '操作提示!',
            left: "100px",
            top: "100px",
            contentDetail: '您确定要永久删除该项功能',
            okFn: function() {
                $.getJSON("authority/delete_auh?authId="+id,function(result){
                if(result.success){
                getResoures();
                }else{
                alert("删除失败");
                }
                })
            }
        });
    }
    function addResoures(){
        zeroModal.show({
            title:'添加资源',
            content:"<ul>"+
                       "<li>资源名称：<input id='addname'  type='text' ></li><br>"+
                        "<li>资源路径：<input id='addurl'  type='text' ></li><br>"+
                       "<li>资源描述：<input id='addde' type='text' ></li><br>"+
                    "</ul>",
            left: "100px",
            top: "100px",
            ok: true,
            okFn: function(){
            	var data={
            	authName:$("#addname").val(),
            	resourceUrl:$("#addurl").val(),
            	authDescribe:$("#addde").val()
            	}
            	$.post("authority/add_authority", data, function(result) {
            		if(!result.success){
            		alert("添加失败");
            		}else{
            			getResoures();
            		}
            	})
            }
        });
    }
    function getResoures(){
$.getJSON("authority/getAuthority",function(result){
	//resouresList
		if(result!=null&&result!="[]"){
			$("#resouresList").html("");
			$.each(result, function(i, item) {
				$("#resouresList").append(" <tr align='center'><td>"+item.authName+"</td><td>"+item.resourceUrl+"</td><td>"+item.authDescribe+"</td><td><a href='javascript:void(0);'><i class='fa fa-pencil-square-o' aria-hidden='true' onclick='_exit(\""+item.authId+"\",\""+item.authName+"\",\""+item.resourceUrl+"\",\""+item.authDescribe+"\")'></i></a>&nbsp;&nbsp;<a href='javascript:void(0);'><i class='fa fa-minus-square-o' aria-hidden='true' onclick='_delete(\""+item.authId+"\")'></i></a></td></tr>");
			})
		}
	})
    }
$(function(){
	getResoures()
})
</script>
</body>
</html>
