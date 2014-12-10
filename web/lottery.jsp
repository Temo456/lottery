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

<script src="js/jquery.cookie.js" type="text/javascript"></script>
<script src="<%=basePath%>js/slides.js" type="text/javascript"></script>
<script type="text/javascript">
    $(function() {

	$("#goal").click(function() {
	    var uid = $.cookie("uid");
	    $.get(basePath + "q/d/" + uid, function(data, status) {
		$(".smins").fadeOut("slow", function() {
		    if (data == "goal") {
			$("#flex").load(basePath + "goal.jsp");
		    } else {
			$("#flex").load(basePath + "fail.jsp");
		    }
		});
	    });
	});

    })
</script>
</head>
<body>
	<div class="flexbg2 disblock" id="flex">
		<div class="choujiang">
			<div class="choujiang_head">
				<div class="close_button" id="close"></div>
			</div>
			<div class="cjins">
				<div class="smins scenter">
					<object width="270" height="270"
						codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=4,0,2,0"
						classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000">
						<param value="<%=basePath%>img/zhuanpan.swf" name="movie">
						<param value="high" name="quality">
						<param value="transparent" name="wmode">
						<param value="exactfit" name="SCALE">
						<embed width="270" height="270" wmode="transparent"
							type="application/x-shockwave-flash"
							pluginspage="http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash"
							quality="high" src="<%=basePath%>img/zhuanpan.swf">
					</object>
				</div>

				<div class="lnebox lneboxnomar2">Line</div>
				<div class="buttonbox buttonbox2">
					<a id="goal"><img src="<%=basePath%>img/chouj.gif" alt="" /></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="<%=basePath%>js/flex.js"></script>
</body>
</html>
