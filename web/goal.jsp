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
	<script type="text/javascript" src="js/jquery-2.1.0.min.js"></script>
<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath%>js/slides.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {
	uid = $.cookie("uid");
	$.get(basePath + "q/l/" + uid, function(data, status) {
	    if (data != "error") {
		$("#lottery").text(data);
	    }
	});
    })
</script>
</head>
<body>
	<!--20141125 start-->
	<div class="flexbg disblock" id="flex">
		<div class="choujiang">
				<div class="choujiang_head">
		<div class="close_button" id="close"></div>
	</div>
			<div class="cjins">
				<div class="jpbox">
					<span><img src="img/jiang01.png" alt="" /></span>
					<!--<span><img src="img/jiang02.png" alt="" /></span>-->
					<!--<span><img src="img/jiang03.png" alt="" /></span>-->
					<!--<span><img src="img/jiang04.png" alt="" /></span>-->
					<h4 id="lottery"></h4>
				</div>
				<div class="jptip">恭喜您，我们将在活动结束后为您充值。</div>
				<div class="lnebox">Line</div>
				<div class="buttonbox" id="confirm_fail">
					<a> <img src="img/queding.gif" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/flex.js"></script>
</body>
</html>
