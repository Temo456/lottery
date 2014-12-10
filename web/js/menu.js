

$(function (){
   $(".subMenu").hide()
   $("ul.subMenu").parent().append("<span></span>");
   $(".menu>li>a").mouseover(function () {
      $(".subMenu").stop(true,true).hide(0)
	
	  $(this).parent().find("ul.subMenu").slideDown("slow")
	  $(this).parent().find("a").addClass("mainHover")
   })
   $(".menu>li").hover(
        function () {},
        function () {
		  $(this).find("ul.subMenu").hide();
		  $(this).find("a").removeClass("mainHover")
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
// JavaScript Document