var userAgent = window.navigator.userAgent.toLowerCase();

$.browser.msie10 = $.browser.msie && /msie 10\.0/i.test(userAgent);
$.browser.msie9 = $.browser.msie && /msie 9\.0/i.test(userAgent);
$.browser.msie8 = $.browser.msie && /msie 8\.0/i.test(userAgent);
$.browser.msie7 = $.browser.msie && /msie 7\.0/i.test(userAgent);
$.browser.msie6 = !$.browser.msie8 && !$.browser.msie7 && $.browser.msie
		&& /msie 6\.0/i.test(userAgent);

function loadUrlFromUrl(url, func) {
	if ($.browser.msie8 || $.browser.msie7 || $.browser.msie6) {
		var xdr = new XDomainRequest();
		xdr.onload = function(e) {
			$("#flex").html(xdr.responseText);
			// set_innerHTML("flex",xdr.responseText);
			// document.getElementById("flex").innerHTML = xdr.responseText;
			// div.html(xdr.responseText);
			// success
			(func && typeof (func) === "function") && func(xdr.responseText);
		};
		xdr.onerror = function(e) {
			// error
		};
		xdr.open("GET", url);
		xdr.send();
	} else {
		$.get(url, function(data, status) {
			$.cookie("uid", data);
			(func && typeof (func) === "function") && func(data);
		});
	}
}
function loadUrlIntoDiv(div, url, func) {

	if ($.browser.msie8 || $.browser.msie7 || $.browser.msie6) {

		var xdr = new XDomainRequest();
		xdr.onload = function(e) {
			$(div).html(xdr.responseText);
			// document.getElementById("flex").innerHTML = xdr.responseText;
			// div.html(xdr.responseText);
			// success
			(func && typeof (func) === "function") && func();
		};
		xdr.onerror = function(e) {
			// error
		};
		xdr.open("GET", url);
		xdr.send();
	} else {
		$("#flex,.flexbackground").load(url,
				function(responseTxt, statusTxt, xhr) {
					$("#flex").fadeIn();
					(func && typeof (func) === "function") && func();
				});
	}
}
$(function() {
	$("#clickbox").click(function() {
		$("#flex,.flexbackground").html("");
		$("#flex,.flexbackground2").addClass("disblock");
		loadUrlIntoDiv($("#flex"), basePath + "intro.jsp");
		/*
		 * $("#flex,.flexbackground").load(basePath + "intro.jsp",
		 * function(responseTxt, statusTxt, xhr) { $("#flex").fadeIn(); });
		 */
		$.cookie("uid", null);
	});

	$("#restart").click(function() {
		loadUrlIntoDiv($("#flex"), basePath + "intro.jsp");
	});

	$("#close").click(function() {
		$("#flex,.flexbackground2").removeClass("disblock");
		$("#flex,.flexbackground").fadeOut();
	});
	$("#confirm_fail").click(function() {
		$("#flex,.flexbackground2").removeClass("disblock");
		$("#flex,.flexbackground").fadeOut();
	});
	$("#intro_confirm").click(function() {
		// load uuid to cookie
		$("#intro_confirm").unbind('click');
		loadUrlFromUrl(basePath + "q/u", function(data) {
			$.cookie("uid", data);

			loadUrlIntoDiv($("#flex"), basePath + "question.jsp", function() {
				$('html, body').animate({
					scrollTop : 0
				}, 'slow');
			});
		});
	});

	var whei = $("body").height();
	$(".flexbg2").css("height", "" + whei + "px");
});
