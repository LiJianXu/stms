// JavaScript Document
//点击切换页面----------------------------------------
		function src(url,navtitle){
			var navtitle2 = document.getElementById("navtitle");
			navtitle2.textContent=navtitle2.textContent.substring(0,navtitle2.textContent.indexOf('>'))
			navtitle2.innerHTML+='>'+navtitle//标题添加到导航
			var iframe=document.getElementById("contentaframe");
			iframe.src=url;
/*			 try{
					
					if(iframe.attachEvent){
						iframe.attachEvent("onload", function(){
							iframe.height =  iframe.contentWindow.document.documentElement.scrollHeight;
						});
						return;
					}else{
						iframe.onload = function(){
//							iframe.height = iframe.contentDocument.body.scrollHeight;
							iframe.height = document.documentElement.scrollHeight 
							
						};
						return;                 
					}     
				}catch(e){
					throw new Error('nonono');
				}*/
			
		}
		
		//--------------------------------------

	//变更导航名---------------------------------
	function navhead(t){
		
		document.getElementById("navtitle").innerHTML=""
		document.getElementById("navtitle").innerHTML=t.textContent+">"
	}
	function systitle(){
		document.getElementById("navtitle").innerHTML=""
	}
	//-----------------------------------------------

	//下拉列表框----------------------
		var indexb=0;
		var a=document.getElementById("aaa");
		var b=document.getElementById("bbb");
		var ddm=document.getElementById("ddm");
		function abd (){
			if(indexb==0){
				b.style.opacity=1;
				ddm.style.opacity=1;
				ddm.style.zIndex=50;
				indexb=1;
			}else{
				b.style.opacity=0;
				ddm.style.opacity=0;
				ddm.style.zIndex=0;
				indexb=0;
			}
			
		}
		//---------------------------------------
		
		
		
		$(function(){
			//获得菜单信息
			$.getJSON("/stms/menu/getMenus", function(data) {
				if (data != null && data != "[]") {
				var html = '';
					for (var i = 0; i < data.length; i++) {
						var afd = ""
						html += "<li><span onClick='navhead(this)' class='tree2'><i class='fa fa-file-text-o' aria-hidden='true'></i><b class='Off'>"+data[i].menuName+"</b></span><ul style='display:none;'>";
						for (var j = 0; j < data[i].childmenus.length; j++) {
							afd = data[i].childmenus[j].menuName;
						html+="<li onClick='src(\""+data[i].childmenus[j].menuUrl+"\",\""+afd+"\")'><span class='tree3'><b>"+afd+"</b><i class='fa fa-caret-left fa-2x din' aria-hidden='true'></i></span></li>";
						}
						html+="</ul></li>";
					}
					$("#tree_root").html(html);
				}
				//设置动画消失
				document.getElementById("aa").style.display = "none";
				$("#tree_root").find("li").children("span").click(function(){
					setTreeStyle($(this));
				});
				$(".ul2").find("li").children("span").click(function(){
					setTreeStyle($(this));
				});
			}) 
			//目录树折叠按钮 -------------------------------
			function setTreeStyle(obj){
				var objStyle = obj.children("b");
				var objList = obj.siblings("ul");
				if(objList.length == 1){
					var style = objStyle.attr("class");
					objStyle.attr("class","On2Off");
					setTimeout(
						function(){
							
							if(style == "Off"){
								objList.parent().siblings("li").children("span").children(".On").each(function(){
									setTreeStyle($(this).parent());	
								});
								var H = objList.innerHeight()
								objList.css({display:"block",height:"0"});
								objList.animate({height:H},300,function(){$(this).css({height:"auto"});});
								objStyle.attr("class","On");
							}
							else if(style == "On"){
								objList.find("li").children("span").children(".On").each(function(){
									setTreeStyle($(this).parent());	
								});
								var H = objList.innerHeight()
								objList.animate({height:0},300,function(){$(this).css({height:"auto",display:"none"})});
								objStyle.attr("class","Off");
							}
						},
						42
					);
				}
			}
			
			// -----------------------------------------	
		});