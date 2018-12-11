var div = "<div class=\"container\">"+
		 		    	"<div id=\"dateJs\">"+
		 		        	"<div class=\"dataTitle\">"+
		 		        		"<div class=\"t_data\" title=\"选择年月\"><a href=\"javascript:;\"></a></div>"+
		 		        		"<div class=\"t_caozuo\">"+
		 		        			"<a href=\"javascript:;\" id=\"prev\" title=\"上一月\">&lt;</a>"+
		 		        			"<a href=\"javascript:;\" id=\"next\" title=\"下一月\">&gt;</a>"+
		 		        		"</div>"+
		 		        	"</div>"+
		 		        	"<div class=\"riqi\">"+
		 		        		"<div class=\"qi_title\">"+
		 		        			"<ul><li>日</li><li>一</li><li>二</li><li>三</li><li>四</li><li>五</li><li>六</li></ul>"+
		 		        		"</div>"+
		 		        		"<div class=\"qi_con\">"+
		 		        			"<ul></ul>"+
		 		        		"</div>"+
		 		        	"</div>"+
		 		        "</div>"+
		 		        "<div id=\"yearJs\">"+
		 		        	"<div class=\"dataTitle\">"+
		 		        		"<div class=\"t_data\" title=\"选择年月\"><a href=\"javascript:;\"></a></div>"+
		 		        		"<div class=\"t_caozuo\">"+
		 		        			"<a href=\"javascript:;\" id=\"prev\" title=\"上一年\">&lt;</a>"+
		 		        			"<a href=\"javascript:;\" id=\"next\" title=\"下一年\">&gt;</a>"+
		 		        		"</div>"+
		 		        	"</div>"+
	 		        		"<div class=\"qi_con\">"+
	 		        			"<ul><li>1月</li><li>2月</li><li>3月</li><li>4月</li><li>5月</li><li>6月</li><li>7月</li><li>8月</li><li>9月</li><li>10月</li><li>11月</li><li>12月</li></ul>"+
	 		        		"</div>"+
	 		        	"</div>"+
	 		        "</div>";
	 		        
 $.date = function(options){
	 /**var today = new Date();       //获取当前日期
	 var y = today.getFullYear();     //获取日期中的年份
	 var m = today.getMonth();      //获取日期中的月份(需要注意的是：月份是从0开始计算，获取的值比正常月份的值少1)
	 var d = today.getDate();*/
	 var opts = $.extend({},$.date.method,options);
	 opts.inputClick(opts.id); //点击input触发的事件
	 //opts.riQiClick(opts.id);//点击日历li给传入的input赋值
	 //opts.appendRiqi(y,m+1);  //加载日期
	 //opts.clickLeftRightRl();//日历点击左右
	 //opts.clickFz(); //3d翻转
	 //opts.clickLeftRightYear();//年点击左右
	 //opts.yearLiClick(); //点击年里面的月
	 opts.clickHide();
 };

 $.date.method = {
     riQiClick:function(inputId){
		$("#dateJs .riqi .qi_con ul li").not("[class]").click(function (){
		    var nianyue =  $("#dateJs .dataTitle .t_data a").text();
			$("#"+inputId).val(nianyue.replace("/","-")+"-"+$(this).text());
			$(".container").remove();
		});
     },
	 //绑定input的点击事件 并给对应的时间div赋值top left
	 inputClick:function (inputId){
		 var $this = this;
		 $("#"+inputId).click(function (){
			 $("body").append(div); //拼接div
			 var today = new Date();       //获取当前日期
			 var y = today.getFullYear();     //获取日期中的年份
			 var m = today.getMonth();      //获取日期中的月份(需要注意的是：月份是从0开始计算，获取的值比正常月份的值少1)
			 //var d = today.getDate();
			 $this.appendRiqi(y,m+1,inputId);  //加载日期
			 $this.clickLeftRightRl(inputId);//日历点击左右
			 $this.clickFz(); //3d翻转
			 $this.clickLeftRightYear();//年点击左右
			 $this.yearLiClick(inputId); //点击年里面的月
			 //$this.riQiClick(inputId);//赋值
			 var top = $(this).offset().top+20;  //文本框距离浏览器上面距离
			 var left = $(this).offset().left;  //文本框距离浏览器左面距离
			 $(".container").css({"display":"block","top":top+"px","left":left+"px"});
		 });
	 },
     //点击向左或向右按钮
     clickLeftRightRl:function(inputId){
    	 var $this = this;
    	 $("#dateJs .dataTitle .t_caozuo #prev,#dateJs .dataTitle .t_caozuo #next").click(function (){
	    	 var nianyue =  $("#dateJs .dataTitle .t_data a").text();
			 var year = nianyue.split("/")[0];
			 var mon = nianyue.split("/")[1];
			 var id = $(this).attr("id");
			 if(id == "prev"){  //上月
				mon = mon-1;
			 }else{  //下月
				mon = parseInt(mon)+1;
			 }
			 if(mon == 13){  //下一年1月
				 year = parseInt(year)+1;
			 	 mon = "1";
			 }else if(mon == 0){  //上年12月
				 year = year-1;
			 	 mon = "12";
			 }
			 $this.appendRiqi(year,mon,inputId);
    	 });
     },
     //点击向左向右的按钮年
     clickLeftRightYear:function (){
    	 $("#yearJs .dataTitle .t_caozuo #prev,#yearJs .dataTitle .t_caozuo #next").click(function (){
    		 var nian =  $("#yearJs .dataTitle .t_data a").text();
    		 var id = $(this).attr("id");
    		 if(id == "next"){  //向右  年加1
    			 $("#yearJs .dataTitle .t_data a").text(parseInt(nian)+1);
    		 }else{  //向左 年减一
    			 $("#yearJs .dataTitle .t_data a").text(nian-1);
    		 }
    	 });
     },
     //点击年里面的月费翻转3d然后把相应的年月调用appendRiqi()方法
     yearLiClick:function (inputId){
    	$this = this;
    	$("#yearJs .qi_con ul li").click(function (){
	    	var nian =  $("#yearJs .dataTitle .t_data a").text();
	    	var yue = "";
    		yue = $(this).text().replace("月","");
    		$(".container #dateJs").css({"-moz-transform":"rotateY(0deg)","-webkit-transform":"rotateY(0deg)","transform":"rotateY(0deg)"});
    		$(".container #yearJs").css({"-moz-transform":"rotateY(180deg)","-webkit-transform":"rotateY(180deg)","transform":"rotateY(180deg)"});
    		$this.appendRiqi(nian,yue,inputId);
    	});
     },
     //3d效果点击事件翻转事件
     clickFz:function (){
    	 $("#dateJs .dataTitle .t_data a").click(function (){
    		 $(".container #dateJs").css({"-moz-transform":"rotateY(180deg)","-webkit-transform":"rotateY(180deg)","transform":"rotateY(180deg)"});
    		 $(".container #yearJs").css({"-moz-transform":"rotateY(0deg)","-webkit-transform":"rotateY(0deg)","transform":"rotateY(0deg)"});
    		 $("#yearJs .dataTitle .t_data a").text($("#dateJs .dataTitle .t_data a").text().split("/")[0]);  //吧当前的年赋值到yearJs里面
    	 });
    	 $("#yearJs .dataTitle .t_data a").click(function (){
    		 $(".container #dateJs").css({"-moz-transform":"rotateY(0deg)","-webkit-transform":"rotateY(0deg)","transform":"rotateY(0deg)"});
    		 $(".container #yearJs").css({"-moz-transform":"rotateY(180deg)","-webkit-transform":"rotateY(180deg)","transform":"rotateY(180deg)"});
    	 });
     },
	 //如果当前年份能被4整除但是不能被100整除或者能被400整除，即可确定为闰年，返回1，否则返回0
	 isLeap:function(year){
		 return year % 4 == 0 ? (year % 100 != 0 ? 1 : (year % 400 == 0 ? 1 : 0)) : 0;
	 },
	 //返回当月的前面灰色的天数
 	shangYueNum:function(str){
 		str = str.split('-'); 
 		var date = new Date(); 
 		date.setUTCFullYear(str[0], str[1] - 1, str[2]); 
 		date.setUTCHours(0, 0, 0, 0);  //此时的date兼容各种浏览器
 		return date.getDay();
 	},
 	//重新计算日期 year 年 mon 月
 	appendRiqi:function(year,mon,inputId){
 		var $this = this;
 		var days_per_month = new Array(31, 28 + $this.isLeap(year), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);  //每年的12个月天数
		$("#dateJs .riqi .qi_con ul").css({"display":"none"}).find("li").remove();//设置display是为了下次的动画 并且移出所以的li
		var shangyue = $this.shangYueNum(""+year+"-"+mon+"-1"); //当月前面灰色的天数
		shangyue = shangyue == 0 ? 7 : shangyue;
		var symon = mon == 1 ? 13 : mon;
		var syzts = days_per_month[symon-2];  //上月的总天数
		for(var i=shangyue-1;i>=0;i--){  //当月前面的灰色
		    $("#dateJs .riqi .qi_con ul").append("<li class='hui'>"+(syzts-i)+"</li>");
		}
		
		var day = new Date();       //获取当前日期
	    var y = day.getFullYear();     //获取日期中的年份
	    var m = day.getMonth()+1;      //获取日期中的月份(需要注意的是：月份是从0开始计算，获取的值比正常月份的值少1)
	    if(y==year&&m==mon){  //需要把当日变红
	    	for(var i=1;i<=days_per_month[mon-1];i++){
	    		if(i==day.getDate()){
	    			$("#dateJs .riqi .qi_con ul").append("<li><div class=\"hong\">"+i+"</div></li>");
	    		}else{
	    			$("#dateJs .riqi .qi_con ul").append("<li>"+i+"</li>");
	    		}
	    	}
	    }else{
	    	for(var i=1;i<=days_per_month[mon-1];i++){
	    		$("#dateJs .riqi .qi_con ul").append("<li>"+i+"</li>");
	    	}
	    }
		var length = $("#dateJs .riqi .qi_con ul li").length; //已经拼的个数 总共42个 剩余的从0开始全是灰色
		for(var i=1;i<=42-length;i++){  //当月后面的灰色
			$("#dateJs .riqi .qi_con ul").append("<li class='hui'>"+i+"</li>");
		};
		$("#dateJs .riqi .qi_con ul").fadeIn();
		//当前月显示
		$("#dateJs .dataTitle .t_data a").text(year+"/"+mon);
		$this.riQiClick(inputId);//重新绑定li的点击事件
 	},
 	//点击空白处隐藏
 	clickHide:function (){
 		/**点击空白区域图片隐藏*/
     	$("body").click(function (event){
     		var event=event||window.event;
            var nodeName = event.target ? event.target.nodeName : event.srcElement.nodeName;
     		if(nodeName == "BODY" || nodeName == "DIV" || nodeName == "TD" ){
     			$(".container").remove();
     		}
     	});
 	}
 };