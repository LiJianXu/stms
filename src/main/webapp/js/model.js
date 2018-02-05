// JavaScript Document

//弹出模态框---修改------------------------
			$('#upt').click(function(){

				$('#modelkuang').show()
				$('#uptpanel').show()
			});
			//弹出模态框---添加------------------------
			$('#add').click(function(){

				$('#modelkuang').show()
				$('#addpanel').show()
				
			});
			//关闭按钮--------------
			$('#closebtn,#closebtn2').click(function(){
				$('#modelkuang').hide()
				$('this').hide()
				$("#addpanel,#uptpanel").hide()
				
			});
			//----------------------------------

//判断是否勾中------------------------------
		$(function(){
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
		})	
				
		
			
			//----------------------------------------