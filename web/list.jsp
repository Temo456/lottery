<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="header.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link href="<%=basePath%>css/master.css" rel="stylesheet"
	type="text/css" />
<link href="<%=basePath%>css/layout.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript" src="<%=basePath%>js/jquery-2.1.0.min.js"></script>
<script src="<%=basePath%>js/slides.js" type="text/javascript"></script>
</head>
<body>
	<!--20141125 start-->
	<div class="flexbg disblock" id="flex">
		<div class="choujiang">
			<div class="choujiang_head">
				<div class="close_button" id="close"></div>
			</div>
			<div class="cjins">
				<div class="jpbox jpboxnobg">
					<span><img src="img/sad.jpg" alt="" /></span>
					<h4>很遗憾，您的问题回答错误。</h4>
				</div>

				<div class="lnebox">Line</div>
				<div class="buttonbox">
					<a class="floatrgt" id="close"><img src="img/likai.gif" alt="" /></a><a
						id="restart"><img src="img/restart.gif" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/flex.js"></script>
</body>
</html>
