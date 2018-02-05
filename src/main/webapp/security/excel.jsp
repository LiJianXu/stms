<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh">
<head>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>HTML5文件上传组件美化jQuery插件</title>
	<link rel="stylesheet" type="text/css" href="upload/css/reset.css">
    <link rel="stylesheet" type="text/css" href="upload/css/jquery.filer.css">
    <link rel="stylesheet" type="text/css" href="upload/css/jquery.filer-dragdropbox-theme.css">
    <link rel="stylesheet" type="text/css" href="upload/css/tomorrow.css">
    <link rel="stylesheet" type="text/css" href="upload/css/custom.css">
<script src="http://www.jq22.com/jquery/jquery-1.10.2.js"></script>
    <script src="upload/js/jquery.filer.min.js" type="text/javascript"></script>
    <script src="upload/js/prettify.js" type="text/javascript"></script>
    <script src="upload/js/custom.js" type="text/javascript"></script>
</head>
<body>
		<section id="section4" class="section-white">                       
               <div>                         
                  <br><br>
                       <form action="" method="post" enctype="multipart/form-data" class="text-center">
                       <input type="file" name="files[]" id="demo-fileInput-4" multiple>
                              <input type="submit" class="btn-custom green" value="上传">
                       </form>
               </div>
 
        </section>
	
</body>
</html>

