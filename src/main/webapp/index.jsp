<html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="UTF-8">
<body>
<h2>Hello World!</h2>
<a href="${pageContext.request.contextPath}/login"> 登陆</a>
</body>
</html>
