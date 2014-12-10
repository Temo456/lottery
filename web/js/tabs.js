$(document).ready(function(){
    $("#tabBar a").each(function(i){
       $(this).css("left",-i+"px"); //取消按钮之间双线
    });
	$("#tabBar a").hover(function (){
	    $("#tabBar a").removeClass("current");
		$(this).addClass("current");

		var n = $("#tabBar a").index(this)
		$("#item div").hide(0);
		$($("#item div")[n]).show();		
	})
    //==========第二个选项卡===========
	 $("#tabBar2 a").each(function(i){
       $(this).css("left",-i+"px"); //取消按钮之间双线
    });
	$("#tabBar2 a").hover(function (){
	    $("#tabBar2 a").removeClass("current");
		$(this).addClass("current");

		var n = $("#tabBar2 a").index(this)
		$("#item2 div").hide();
		$($("#item2 div")[n]).show();		
	})
	    //==========第三个选项卡===========
	 $("#tabBar3 a").each(function(i){
       $(this).css("left",-i+"px"); //取消按钮之间双线
    });
	$("#tabBar3 a").hover(function (){
	    $("#tabBar3 a").removeClass("current");
		$(this).addClass("current");

		var n = $("#tabBar3 a").index(this)
		$("#item3 div").hide();
		$($("#item3 div")[n]).show();		
	})
		    //==========第四个选项卡===========
	 $("#tabBar4 a").each(function(i){
       $(this).css("left",-i+"px"); //取消按钮之间双线
    });
	$("#tabBar4 a").hover(function (){
	    $("#tabBar4 a").removeClass("current");
		$(this).addClass("current");

		var n = $("#tabBar4 a").index(this)
		$("#item4 div").hide();
		$($("#item4 div")[n]).show();		
	})

});// JavaScript Document