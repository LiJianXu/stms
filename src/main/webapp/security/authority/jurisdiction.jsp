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
<title>角色管理</title>
<base href="<%=basePath%>">

    <style>
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
       a:nth-child(1) i{color:#E5974A;}
       a:nth-child(2) i{color:#4144C5}
       a:nth-child(3) i{color:#C91417;}
       .line-spin-fade-loader {
       	left:10px;
       	top:10px;
    	position: absolute;
		}
    </style>
</head>
<body>
    <table cellspacing="0">
        <thead>
            <tr>
                <th width="150px">角色id</th>
                <th width="150px">角色名字</th>
                <th width="150px">操作</th>
            </tr>
        </thead>
        <tbody align="center" id="roleList">

        </tbody>

    </table>
<script>
	var menus;
	var loadUniqe;
	var allresourse;
 	//得到所有的菜单
 	$.getJSON("menu/get_all_menus", function(data) {
		menus = data.obj;
	})
	//得到所有的资源
	$.getJSON("authority/getAuthority",function(result){
	    allresourse = result;
	})
	
    function _menu(roleId) {
	    if(menus==null){
		    $.getJSON("menu/get_all_menus", function(data) {
			    	menus = data.obj;
			    	showModal(parseResult(menus),roleId);
			})
    	}else{
    		showModal(parseResult(menus),roleId);
    	}
    }
    function parseResult(result){
	    var start ="<ul>";
	    	var html ="";
	    	
	    var end ="</ul>";
	    $.each(result,function(i,item){
	    	//解析一级菜单
		    if(item.menuFatherId=="1"&&item.menuGrade=="1"){
		     	html+="<li><input id='menu"+item.menuId+"' fatherId='menu1' type='checkbox'>"+item.menuName+"</li>";
		    }
		    var childmenus = item.childmenus;
		    html+=start;
		    //解析二级菜单
		    $.each(childmenus,function(i1,item1){
		    	html+="<li><input id='menu"+item1.menuId+"' fatherId='menu"+item.menuId+"' type='checkbox'>"+item1.menuName+"</li>";
		    })
		    html+=end;
	   	})
    	return start+html+end;
    }
    function showModal(html,roleId){
    	zeroModal.show({
            title: '菜单',
            content: html,
            left: "250px",
            top: "100px"
        });
        //开启加载动画
        loadUniqe = zeroModal.loading(5);
        //选中复选框事件
        $('input[id^="menu"]').change(function() {
        	//得到当前选中的fatherId
        	var fatherId = $(this).attr("fatherid");
        	//得到当前的id
        	var id=$(this).attr("id");
        	//得到当前选中的菜单id
        	var menuId = id.substring(4, id.length);
        	//如果选中的是一级目录  则选中改目录下的所有目录
        	if($(this).is(":checked") == true && fatherId == "menu1"){
        		$(this).attr("checked",true);
        		//添加当前的菜单
        		addMenuRole(roleId,menuId);
        		$("input[fatherid='"+id+"']").each(function(){
	        		if($(this).is(":checked") == false){
	        			var id = $(this).attr("id");
	        			//添加没有选中的菜单
	        			addMenuRole(roleId,id.substring(4, id.length));
	        		}
        		})
        		//设置该菜单下所有的子菜单为true
        		$("input[fatherid='"+id+"']").prop("checked",true);
        		//如果取消的是一级目录则取消该目录下的所有目录
        	}else if($(this).is(":checked") == false && fatherId == "menu1"){
        		$(this).attr("checked",false);
        		$("input[fatherid='"+id+"']").prop("checked",false);
        		//需要删除的id
        		var deleteIds = new Array();
        		deleteIds.push($(this).attr("menurole"));
        		//获取当前菜单下的子菜单
        		var menusIds = $("input[fatherid='"+id+"']");
        		menusIds.each(function(){
	        		if(menusIds.is(":checked") == false){
	        		deleteIds.push($(this).attr("menurole"));
	        		}
        		})
        		for(var i=0;i<deleteIds.length;i++){
        		//通过菜单角色id 删除记录(menuRoleId)
        		deleteMenuRoleId(deleteIds[i]);
        		}
        	}
        	if($(this).is(":checked") == false && fatherId != "menu1"){
        		//通过菜单角色id 删除记录(menuRoleId)
        		deleteMenuRoleId($(this).attr("menurole"));
        	}
        	if($(this).is(":checked") == true && fatherId != "menu1"){
        		if($("#"+fatherId+"").is(":checked")){
        			addMenuRole(roleId,menuId);
        		}else{
        			$("#"+fatherId+"").prop("checked",true);
        			addMenuRole(roleId,fatherId.substring(4, fatherId.length))
        			addMenuRole(roleId,menuId);
        		}
        		
        	}
        	
        })
        //通过角色得到菜单信息
        $.getJSON("menuRole/get_menu_roleId?roleId="+roleId, function(result) {
        	if(result.success){
        		$.each(result.obj, function(i, item) {
        			//alert("id"+item.id);
        			//alert("menuId"+item.menu.menuId);
         			var m = $("#menu"+item.menu.menuId+"");
        			m.prop("checked",true);
         			m.attr("menuRole",item.id);
        		})
        		zeroModal.close(loadUniqe);
        	}
        })
    }
    //通过菜单角色的id  删除菜单角色id
    function deleteMenuRoleId(id){
	    $.getJSON("menuRole/delete_menuRoleId?menuRoleId="+id,function(result){
	    	if(!result.success){
	    		alert("删除id为:"+id+"失败");
	    	}
	    })
    }
    //添加菜单角色记录  通过角色id和菜单id
    function addMenuRole(roleId,menuId){
    	$.getJSON("menuRole/add_menuRole?roleId="+roleId+"&menuId="+menuId,function(result){
	    	if(!result.success){
	    		alert("添加失败"+roleId+":"+menuId);
	    	}else{
	    		//添加新的菜单角色id
	    		var m = $("#menu"+menuId+"");
	    		m.attr("menuRole",result.obj);
	    	}
	    })
    }
    function _exit(name,id){
        zeroModal.show({
            title:'编辑',
            content:"<ul>"+
                       "<li>角色名称：<input id='editRoles"+id+"' type='text' value='"+name+"'></li><br>"+
                    "</ul>",
            left: "250px",
            top: "100px",
            ok: true,
            okFn: function(){
            var name = $("#editRoles"+id).val();
	            if(name.length>0){
		            var data = {
		            roleId:id,
		            roleName:name
		            }
		            $.post("roles/update_role", data, function(result) {
		            	if(result.success){
		            	window.location.reload();
		            	}else{
		            	alert("更新失败")
		            	}
		            })
	            }
            
            }
        });
    }
    function _resourse(roleId){
	    if(allresourse ==null){
	    	//得到所有的资源
	    	$.getJSON("authority/getAuthority",function(result){
		    	allresourse = result;
		    	parseResourse(allresourse,roleId);
		    })
	    }else{
			parseResourse(allresourse,roleId);
	    }
    }
    //解析资源
    function parseResourse(result,roleId){
    var start = "<ul>";
    var content ="";
    $.each(result, function(i, item) {
    	content+="<li><input id='resourse"+item.authId+"' type='checkbox'>"+item.authDescribe+"</li>"
    })
    var end = "</ul>";
    //显示框
   		zeroModal.show({
            title:'资源',
            content:start+content+end,
            left: "250px",
            top: "100px"
        });
        //添加权限记录
        addAuthority(roleId);
        $("input[id^='resourse']").change(function() {
        	if($(this).is(":checked")){
        	var id = $(this).attr("id");
        	var authId = id.substring(8,id.length);
        		var data = {
        		roleId:roleId,
        		authId:authId
        		}
        		$.post("roleAuthRelation/ad_roleAuth", data, function(result) {
        			if(result.success){
        			$("#resourse"+authId).attr("roleAuthorId",result.obj);
        			}else{
        			alert("添加失败");
        			}
        		})
        	}else{
        		$.getJSON("roleAuthRelation/delete_roleAuth_id?roleAuthId="+$(this).attr("roleAuthorId"), function(result) {
        			if(!result.success){
        			alert("删除失败");
        			}
        		})
        	}
        })
    }
    function  addAuthority(roleId){
    //开启加载动画
        loadUniqe = zeroModal.loading(5);
	    $.getJSON("roleAuthRelation/get_roleAuth?roleId="+roleId,function(result){
	    	$.each(result,function(i,item){
	    		$("#resourse"+item.authority.authId).attr("checked",true);
	    		$("#resourse"+item.authority.authId).attr("roleAuthorId",item.relationId);
	    	})
	    	zeroModal.close(loadUniqe);
	    })
    }
    
    $(function(){
    	$.ajax({
				type : "GET",
				url : "roles/get_roles",
				dataType : "json",
				success : function(result) {
					if (result != null && result != "[]") {
						$("#roleList").html("");
						$.each(result, function(i, item) {
							$("#roleList").append("<tr><td>" + item.roleId + "</td><td>" + item.roleName + "</td><td><a href='javascript:void(0);'><i class='fa fa-folder-open-o' aria-hidden='true' onclick='_resourse(\"" + item.roleId + "\")'></i></a>&nbsp;&nbsp;<a href='javascript:void(0);'><i class='fa fa-handshake-o' aria-hidden='true' onclick='_menu(\"" + item.roleId + "\")'></i></a>&nbsp;&nbsp;<a href='javascript:void(0);'><i class='fa fa-pencil-square-o' aria-hidden='true' onclick='_exit(\"" + item.roleName + "\",\"" + item.roleId + "\")'></i></a></td></tr>");
						})
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
						if(XMLHttpRequest.status==200&&textStatus=="parsererror"){
						window.location.href="/stms/unauthorized.jsp";
						}else{
						window.location.reload();
						}
				}
			}); 
    })
</script>
</body>
</html>
