$(function(){
	$("#formlist dt span,#formlist dd span").each(function(){
		var m=$(this).index();
		if(m==0){
			$(this).css("width","48px");
		}else if(m==1){
			$(this).css("width","116px");
		}else if(m==2){
			$(this).css("width","130px");
		}else if(m==3){
			$(this).css("width","268px");
		}else if(m==4){
			$(this).css("width","95px");
		}else if(m==5){
			$(this).css("width","242px");
		}else if(m==6){
			$(this).css("width","100px");
		} 
	});
	$("#formlist02 dt span,#formlist02 dd span").each(function(){
		var m=$(this).index();
		if(m==0){
			$(this).css("width","48px");
		}else if(m==1){
			$(this).css("width","116px");
		}else if(m==2){
			$(this).css("width","130px");
		}else if(m==3){
			$(this).css("width","237px");
		}else if(m==4){
			$(this).css("width","87px");
		}else if(m==5){
			$(this).css("width","209px");
		}else if(m==6){
			$(this).css("width","81px");
		}else if(m==7){
			$(this).css("width","90px");
		}  
	});
	var n=$(".formlist span").size();
	for (var a=0;a<n+1;a++){
		var h=$(".formlist dd span").eq(a).height();
		if(h>20){$(".formlist span").eq(a+7).parent("dd").find("span").css("height",""+h+"px")}
	}
});