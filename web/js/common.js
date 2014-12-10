// JavaScript Document
//focus fashion
var focusImg = {
	init:function(){
		var self = this;
		self.autoSpeed = 3000 ;//自动播放速度
		self.animateSpeed = 50;//渐变速度
		self.div = document.getElementById("focus");
		self.liImg =self.div.getElementsByTagName("ul")[0].getElementsByTagName("li");
		self.textInfo = self.div.getElementsByTagName("div")[0];
		self.l = self.liImg.length;	 
		self.liImg[0].style.display = "block";
		self.old = 0;
		self.cur = 0;
		self.opacity = 0;
		var numHTML ='' ;
		self.ulNum = self.div.getElementsByTagName("ul")[1];
		for(i=1; i <= self.l;i++){
			oli = document.createElement("li");
			oli.innerHTML = i;
			oli.num = i;
			if(i==1){
				oli.className = "cur";
				//info = self.liImg[i-1].getElementsByTagName("img")[0].alt;
				//self.textInfo.innerHTML = info;
			};
			oli.onclick = function(){
				self.clickFn(this.num-1);
			};
			self.ulNum.appendChild(oli);
		};
		self.auto();
	},
	showImg:function(index){
		var self = this;
		var oli = self.ulNum.getElementsByTagName("li");
		for(var i = 0;i < self.l; i++){
			self.liImg[i].style.display ="none";
			self.liImg[i].style.filter = "";
			self.liImg[i].style.opacity = "";
			oli[i].className = "";
		};
		oli[index].className = "cur";
		self.liImg[index].style.filter = "alpha(opacity:0)";
		self.liImg[index].style.opacity = 0;
		self.liImg[index].style.display = "block";
		//info = self.liImg[index].getElementsByTagName("img")[0].alt;
		//self.textInfo.innerHTML = info;
		self.opacity = 0;
		self.autoTime = setInterval(function(){
			self.opacity += 10;
			if(self.opacity >= 100){
				self.opacity = 100;
				clearInterval(self.autoTime);
			};
			self.liImg[index].style.filter = "alpha(opacity:"+self.opacity+")";
			self.liImg[index].style.opacity = self.opacity/100;
		},self.animateSpeed);
		self.old = index ; 
		self.cur = index ;
		if(self.autoPlay == null){
			self.auto();
		};	
	},
	clickFn:function(index){
			var self = this;
			if(self.old!=index){
				if(self.autoPlay){
					clearInterval(self.autoPlay);
					self.autoPlay = null;
					self.showImg(index);
				};
			};
			
	},
	auto:function(){
		var self = this;
			self.autoPlay = setInterval(function(){
				self.cur < self.l-1 ? self.cur ++ : self.cur = 0;
				self.showImg(self.cur);
			},self.autoSpeed);
	}
};

//tabMenu
function tab(m,id,l){
	for(var i = 1;i<=l;i++){
		$("#"+m+i).removeClass("cur");
	};
	$("#"+m+id).addClass("cur");
}

// 滚动
function Scroll_x(obj){
	//obj = {id(对象id名称),v(显示数量),auto(是否自动轮播),b(往后按钮名称),p(往前按钮名称),t(轮播间隔时间)}
	var that = this; 
	var me = $(obj.id); 
	this.ul = me.find("ul");
	this.li = this.ul.find("li");
	this.l = this.li.length;
	var v = obj.v || 2; 
	if(obj.p){that.pre = obj.p;}
	if(obj.n){that.next = obj.n;}
	if(v>this.l){ 
		if(obj.p)$(that.pre).hide();
		if(obj.n)$(that.next).hide();
		return false;
	} 
	this.w = this.li.outerWidth(true);
	this.max_l = null;
	this.autoId = null;
	this.default_n = 0; 
	this.isAuto = obj.auto!=null ? false : true; 
	this.time = obj.t || 3000;
	init();//初始化
	function init(){
		var copy_li = ''; 
		for(var i = 0;i<v;i++){
			that.ul.append(that.li.eq(i).clone());	
		};	
		that.li = that.ul.find("li");
		var new_l = that.ul.find("li").length;
		that.ul.width(new_l*that.w);
		that.max_l = new_l - v;
		if(obj.p)$(that.pre).bind("click",function(){that.preFn(1);return false; });
		if(obj.n)$(that.next).bind("click",function(){that.nextFn(1);return false;});
		if(that.isAuto){ 
			that.autoPlay();
		};
	};
}
Scroll_x.prototype.autoPlay = function(){		
		var that = this;
		if(!this.autoId){ //如果轮播id为空
			this.autoId = setInterval(function(){that.nextFn();},that.time);
		};
};
Scroll_x.prototype.preFn = function(o){ 
		var that = this;
		if(o&&this.autoId){ 
			clearInterval(that.autoId);	
			that.autoId = null;
		};
		if(this.default_n>0){ 
			this.default_n--;
		}else{ 
				that.ul.css({"left":-this.l*this.w+"px"});
				this.default_n=this.l;
				this.default_n--;
		};
		that.move(o);
};
Scroll_x.prototype.nextFn = function(o){
	var that = this;
		if(o&&this.autoId){
			clearInterval(that.autoId);	
			this.autoId = null;
		};
		if(this.default_n<that.l){
			this.default_n++;
		}else{
			this.ul.css({"left":0});
			this.default_n=0;
			this.default_n++;
		};
		this.move(o);
}
Scroll_x.prototype.move = function(o){
		var that = this;
		var pos= -this.default_n*this.w;
		this.ul.stop();
		this.ul.animate({"left":pos+"px"},function(){if(o&&that.isAuto){that.autoPlay();};})
};

//娱乐焦点图
var focus_travel = {
	init:function(o){
		this.me = $(o);
		this.num  = this.me.find(".num");
		this.li = this.num.find("li");
		this.c = 0;
		
		this.l = this.li.length;
		for(var i = 0;i<this.l ;i++){
			this.li.eq(i).attr("n",i)
			var src = this.li.eq(i).attr("src");
			var _link =  this.li.eq(i).attr("link");
			this.me.find(".img").append("<li><a href='"+_link+"'><img src='"+src+"' /></a></li>");
		};
		var that = this;
		this.li.bind("click",function(){ that.clickFn(this); return false});
		this.autoId  =null;
		this.autoId = setInterval(function(){that.auto();},3000);
		this.showImg();
		
	},
	clickFn:function(o){	
		var that  = this;
		
		if(this.autoId)clearInterval(that.autoId);
		this.c = $(o).attr("n");
		this.showImg(1);
	},
	auto:function(){
		this.c < this.l-1 ? this.c ++:this.c = 0;
		this.showImg();
	},
	showImg:function(test){
		var that = this;
		this.me.find(".img li").hide();
		this.li.removeClass("cur");
		this.li.eq(this.c).addClass("cur");
		this.me.find(".img li").eq(this.c).fadeIn(200,function(){if(test){that.autoId = setInterval(function(){that.auto();},3000);}});	
	}
};

//时间
function getDateFn (){
	var myDate = new Date();
	var y = myDate.getFullYear();
	var m = myDate.getMonth()+1;
	var d = myDate.getDate();
	var day = myDate.getDay();
	var day_array = ["星期一","星期二","星期三","星期四","星期五","星期六","星期天"];
	document.getElementById("nowDeta").innerHTML = y+"-"+m+"-"+d+" "+day_array[day-1];
}