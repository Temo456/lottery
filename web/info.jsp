<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include  file="header.jsp"%>
<!--20141125 start-->
<style>
.state1 {
	color: #aaa !important;
}

.state2 {
	color: #000 !important;
}

.state3 {
	color: #f00 !important;
	padding-left: 20px;
	background: url(images/reg_icons.png) -80px -24px no-repeat;
}

.state4 {
	color: green !important;
	background: url(images/reg_icons.png) -80px 0 no-repeat;
}
</style>
<script type="text/javascript">
    $(function() {
	var ok1 = false;
	var ok2 = false;
	var ok3 = false;
	var ok4 = false;
	var username = "";
	var email = "";
	var tele = "";
	var corp = "";
	var suggest = "";
	// 验证用户名
	$('input[name="username"]').focus(
		function() {
		    $(this).next().text('请输入姓名').removeClass('state1')
			    .removeClass('state3').removeClass('state4')
			    .addClass('state2').fadeOut().fadeIn();
		}).blur(
		function() {
		    if ($(this).val().length >= 2 && $(this).val().length <= 10
			    && $(this).val() != '') {
			$(this).next().html(
				'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')
				.removeClass('state1').removeClass('state3')
				.removeClass('state2').addClass('state4')
				.fadeIn();
			ok1 = true;
			username = $(this).val();
		    } else {
			$(this).next().text('姓名输入错误').removeClass('state1')
				.removeClass('state4').addClass('state3')
				.fadeIn();
			ok1 = false;
		    }
		});

	//验证邮箱
	$('input[name="email"]')
		.focus(
			function() {
			    $(this).next().text('请输入E-Mail').removeClass(
				    'state1').removeClass('state3')
				    .removeClass('state4').addClass('state2')
				    .fadeOut().fadeIn();
			})
		.blur(
			function() {
			    if ($(this)
				    .val()
				    .search(
					    /\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*/) == -1) {
				$(this).next().text('E-Mail不正确').removeClass(
					'state1').removeClass('state3')
					.removeClass('state4').addClass(
						'state3').fadeIn();
				ok2 = false;
			    } else {
				$(this).next().html(
					'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')
					.removeClass('state1').removeClass(
						'state3').removeClass('state4')
					.addClass('state4').fadeIn();
				ok2 = true;
				email = $(this).val();
			    }

			});

	//验证手机号码
	var isMobile = /^(?:13\d|15\d|18\d)\d{5}(\d{3}|\*{3})$/;
	$('input[name="tele"]').focus(
		function() {
		    $(this).next().text('请输入手机号码').removeClass('state1')
			    .removeClass('state3').removeClass('state4')
			    .addClass('state2').fadeOut().fadeIn();
		}).blur(
		function() {
		    if ($(this).val().search(isMobile) == -1) {
			$(this).next().text('手机号码不正确').removeClass('state1')
				.removeClass('state3').removeClass('state4')
				.addClass('state3').fadeIn();
			ok3 = false;
		    } else {
			$(this).next().html(
				'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')
				.removeClass('state1').removeClass('state3')
				.removeClass('state4').addClass('state4')
				.fadeIn();
			ok3 = true;
			tele = $(this).val();
		    }
		});

	// 验证工作单位
	$('input[name="corp"]').focus(
		function() {
		    $(this).next().text('请输入工作单位').removeClass('state1')
			    .removeClass('state3').removeClass('state4')
			    .addClass('state2').fadeOut().fadeIn();
		}).blur(
		function() {
		    if ($(this).val().length >= 1 && $(this).val() != '') {
			$(this).next().html(
				'&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')
				.removeClass('state1').removeClass('state3')
				.removeClass('state2').addClass('state4')
				.fadeIn();
			ok4 = true;
			corp = $(this).val();
		    } else {
			$(this).next().text('工作单位输入错误').removeClass('state1')
				.removeClass('state4').addClass('state3')
				.fadeIn();
			ok4 = false;
		    }
		});


    //提交按钮,所有验证通过方可提交
	$("#fuck").click(function() {
	    if (ok1 && ok2 && ok3 && ok4) {
		suggest = $("#suggestion").val();
		//alert(suggest);
		uid = $.cookie("uid");
		$.post(basePath + "q/i/" + uid, {
		    uid : uid,
		    corp : corp,
		    suggest : suggest,
		    email : email,
		    tele : tele,
		    username : username
		}, function(data, status) {
		    if (data == "success") {
			$("#flex").load(basePath + "lottery.jsp");
		    } else if (data == "checked") {
			alert("您的手机号码已经抽过奖，请勿重复抽奖！");
		    } else {
			alert("提交失败！");
		    }
		});
	    } else {
		alert("请完整填写上面的信息。");
		return false;
	    }
	});
    });
</script>
<div class="flexbg2 disblock" id="flex">
	<div class="choujiang">
		<div class="choujiang_head">
			<div class="close_button" id="close"></div>
		</div>
		<div class="cjins">
			<div class="smins">
				<p class="sdesc02 lh30">
					<em>恭喜您</em><span>完成我们的问题问答，相信您对GTI组织有了更深的认识。请您填写以下资料以便我们为您派发奖品。</span>
				</p>
			</div>
			<form action="">
				<dl class="filecont">
					<dd>
						<span>姓名：</span><input type="text" name="username" /><em
							class='state1'>请输入姓名</em>
					</dd>
					<dd>
						<span>E-mail： </span><input type="text" name="email" /><em
							class='state1'>请输入E-mail</em>
					</dd>
					<dd>
						<span>领取奖品的手机号码：</span><input type="text" name="tele" /><em
							class='state1'>请输入手机号码</em>
					</dd>
					<dd>
						<span>工作单位：</span><input type="text" name="corp" /><em
							class='state1'>请输入工作单位</em>
					</dd>
					<dd>
						<span>您对GTI网站的意见 <br />与建议：
						</span>
						<textarea id="suggestion" cols="30" rows="10" name="suggestion"></textarea>
					</dd>
				</dl>
			</form>
			<div class="lnebox lneboxnomar2">Line</div>
			<div class="buttonbox buttonbox2">
				<a id="fuck"><img src="<%=basePath%>img/queding.gif" alt="" /></a>
			</div>
		</div>
	</div>
</div>

<script src="<%=basePath%>js/jquery.cookie.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>js/flex.js"></script>
<link href="<%=basePath%>css/layout.css" rel="stylesheet" type="text/css" />