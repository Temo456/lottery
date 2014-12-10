
// JavaScript Document

 $(document).ready(function (){
     $(".menu>li:gt(0)").hover(
	 function () {
		$(this).siblings().children("ul").stop(true,true).hide()
		$(this).children("ul").slideDown()
		$(this).children("a").addClass("current")
     },
	 function () {
		
		$(this).children("a").removeClass("current")
		$(this).children("ul").slideUp()
     }
	    
	 )
	   $(".menu>li").hover(
    function (){
	   $(".menu>li>a").removeClass("mhover")
	   $(this).children("a").addClass("mhover")
	   
	},
	function (){
	    $(".menu>li>a").removeClass("mhover")	
	}
	)

 })
