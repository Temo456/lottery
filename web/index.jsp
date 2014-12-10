<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<script language="JavaScript" src="<%=basePath%>js/jquery-2.1.0.min.js"></script>
<title>lottery</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
	$(document).ready(function() {
		$.get("<%=basePath%>q",function(data,status){
			console.log(data.q);
			for(o in data.q){
				console.log(data.q[o].title);
				$("#questions").append("<h3>" + data.q[o].title + "</h3>");
				ch = 'A';
				for(a in data.q[o].answers){
					$("#questions").append("<h4>" + ch + "." + data.q[o].answers[a] + "</h4>");
					ch = String.fromCharCode(ch.charCodeAt(0) + 1);
				}
			}
		  });
	});
</script>
</head>

<body>
	<p>This is my JSP page.</p>
	<div id="questions"></div>
	<br>
</body>
</html>
