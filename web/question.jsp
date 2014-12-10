<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="header.jsp"%>
<script type="text/javascript">
    $(function() {
	var uid = $.cookie("uid");
	$
		.post(
			basePath + "q/q",
			{
			    uid : uid
			},
			function(data) {
			    $("#title").text(data.index);
			    $("#desc").text(data.title);
			    ch = 'A';

			    for ( var i = 0; i < 4; i++) {
				$(".wentibox")
					.append(
						"<dd><input type=\"radio\" name=\"wenti\" id=\""+ch+"\"  value=\""+ch+"\"/><span>"
							+ ch
							+ ". "
							+ data.answers[i]
							+ "</span></dd>");
				ch = String.fromCharCode(ch.charCodeAt(0) + 1);
			    }
			});

	$("#next_quest_goal").click(
		function() {
		    var uid = $.cookie("uid");
		    var url = basePath + "q/a/" + uid + "/"
			    + $('input:radio[name=wenti]:checked').val();
		    $.get(url, function(data, status) {
			if (data == "next") {
			    $("#flex").load(basePath + "question.jsp",function(){
				$("#smins").fadeIn();
			    });
			} else if (data == "success") {
			    $("#flex").load(basePath + "info.jsp");
			} else if (data == "error") {
			    $("#flex").load(basePath + "error.jsp");
			}
		    });
		});
    });
</script>
<div class="choujiang">
	<div class="choujiang_head">
		<div class="close_button" id="close"></div>
	</div>
	<div class="cjins" >
		<div class="smins" >
			<h4 class="stit scenter">
				<span id="title"></span>
			</h4>
			<p class="sdesc02">
				<span id="desc"></span>
			</p>
		</div>
		<dl class="wentibox">
		</dl>
		<div class="lnebox lneboxnomar">Line</div>
		<div class="buttonbox buttonbox2">
			<a id="next_quest_goal"><img src="<%=basePath%>img/nxtbutton.gif" alt="" /></a>
		</div>
	</div>
</div>
<script src="<%=basePath%>js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/flex.js"></script>