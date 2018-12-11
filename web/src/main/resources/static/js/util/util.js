// 客户端直接跳转作业预习的地址
var homework = "http://47.93.23.219:8026/work/homeWork/list";
var preview = "http://47.93.23.219:8026/work/preview/list";
//var homework = "http://www.baidu.com";
//var preview = "http://www.hao123.com";

function getLogin(){
    window.location.href=getRootPath()+"/system";
}

/**
 * 获得web根路径
 */
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	//return(prePath+postPath);
	return prePath;
}

/**
 * js数字相加  包含小数点
 * 
 * @param {string} value1 相加的数字1
 * @param {string} value2 相加的数字2
 */
function addValue(value1, value2){
    if (value1 == "") value1 = "0";
    if (value2 == "") value2 = "0";
    var temp1 = 0;
    var temp2 = 0;
    if (value1.indexOf(".") != -1) temp1 = value1.length - value1.indexOf(".")-1;
    if (value2.indexOf(".") != -1) temp2 = value2.length - value2.indexOf(".")-1;
    var temp = 0;
    if (temp1 > temp2) temp = (parseFloat(value1) + parseFloat(value2)).toFixed(temp1);
    else temp = (parseFloat(value1) + parseFloat(value2)).toFixed(temp2);
    return temp;
}

/**
 * 时间兼容浏览器 用法 if(NewDate($(dq).val()) < NewDate($("#filterTimeStart").val())) alert("结束日期不能小于开始日期")
 *
 * @param {string} str 需要转换的时间
 */
function NewDate(str) {
	str = str.split('-');
	var date = new Date();
	date.setUTCFullYear(str[0], str[1] - 1, str[2]);
	date.setUTCHours(0, 0, 0, 0);
	return date;
} 

/**
 * 是undefined true是
 * 
 * @param {string} value 需要判断的值
 */
function isUndefined(value){
	return typeof(value) == "undefined";
}

/**
 * 不是undefined true不是
 * 
 * @param {string} value 需要判断的值
 */
function isNotUndefined(value){
	return !isUndefined(value);
}

/**
 * 校验上传文件的大小
 * @param id  fileid
 * @param size  大小
 * @returns {Boolean}
 */
function getSize(id,size){
	var fileSize = 0;
    var isIE = /msie/i.test(navigator.userAgent) && !window.opera;          
    if (isIE && !obj.files) {
    	var filePath = $("#"+id).value;      
    	var fileSystem = new ActiveXObject("Scripting.FileSystemObject");
    	var file = fileSystem.GetFile (filePath);               
    	fileSize = file.Size;    
    } else {
    	fileSize = $("#"+id).get(0).files[0].size;  
    }
    fileSize=Math.round(fileSize/1024/1024)*size; //单位为MB
    if(fileSize>=size){
        alert("照片最大尺寸为4MB，请重新上传!");
        return false;
    }
}

/**
 * 获取地址栏的参数值 
 * @params name(key)  alert(GetQueryString("参数名1"));
 */
function getUrlParams(name){
	var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 模拟java的stringBuild S
 */
function StringBuffer() {
	this.strings = new Array();
}

StringBuffer.prototype.append = function (str) {
	this.strings.push(str);
	return this;    //方便链式操作
};

StringBuffer.prototype.toString = function () {
	return this.strings.join("");
};

/**
 * 横线后面首字母转成大写去掉横线
 */
function ucfirst(str) {
	var ds = str.split("_");
	var str = ds[1].toLowerCase();
	str = str.replace(/\b\w+\b/g, function(word){
	    return word.substring(0,1).toUpperCase()+word.substring(1);
	});
	return ds[0]+str;
}

//发送POST请求跳转到指定页面
function httpPost(URL, PARAMS) {
    var temp = document.createElement("form");
    temp.action = URL;
    temp.method = "post";
    temp.style.display = "none";
    for (var x in PARAMS) {
        var opt = document.createElement("input");
        opt.name = x;
        opt.value = PARAMS[x];
        temp.appendChild(opt);
    }
    document.body.appendChild(temp);
    temp.submit();
    return temp;
}

//时间比较（yyyy-MM-dd）
function compareDate(startDate, endDate) {
    var arrStart = startDate.split("-");
    var startTime = new Date(arrStart[0], arrStart[1], arrStart[2]);
    var startTimes = startTime.getTime();
    var arrEnd = endDate.split("-");
    var endTime = new Date(arrEnd[0], arrEnd[1], arrEnd[2]);
    var endTimes = endTime.getTime();
    if (endTimes < startTimes) {
        return false;
    }
    return true;
}


/**
 * 错误信息提示
 *
 * @param accountId id
 * @param type 提示的字
 * @param flag true 不校验是如果选择多个  false校验是否选择多个
 */
function ts(id, type, flag) {
    if (id == "") {
        return "请选择要" + type + "的数据";
    }
    if (!flag){
        if (id.split(",").length > 1) {
            return "只能" + type + "一条数据";
        }
    }
    return "";
}

/**
 * 错误信息提示优化
 *
 * @param accountId id
 * @param type 提示的字
 * @param flag true 不校验是如果选择多个  false校验是否选择多个
 * @return true 校验通过 flase 校验不通过
 */
function errorShow(id, type, flag) {
    // 默认通过
    var adopt = true;
    if (id == "") {
        $.ts({"content":"请选择要" + type + "的数据","direction":"around","code":"2"});
        adopt = false;
    }
    if (!flag){
        if (id.split(",").length > 1) {
            $.ts({"content":"只能" + type + "一条数据","direction":"around","code":"2"});
            adopt = false;
        }
    }
    return adopt;
}

/**
 * 通过name翻译图标
 * @param name
 */
function ico(name){
    var tubiao = ""
    switch (name) {
        case "新增" :
            tubiao = "<i class=\"fa fa-plus\"></i>"
            break;
        case "编辑" :
            tubiao = "<i class=\"fa fa-pencil fa-fw\"></i>"
            break;
        case "删除" :
            tubiao = "<i class=\"fa fa-trash-o fa-fw\"></i>"
            break;
        case "批量删除" :
            tubiao = "<i class=\"fa fa-trash-o fa-fw\"></i>"
            break;
        case "导入" :
            tubiao = "<i class=\"fa fa-upload\"></i>"
            break;
        case "批量通过" :
            tubiao = "<i class=\"fa fa-check\"></i>"
            break;
        case "批量不通过" :
            tubiao = "<i class=\"fa fa-close\"></i>"
            break;
        case "审核" :
            tubiao = "<i class=\"fa fa-pencil-square-o\"></i>"
            break;
        case "重置密码" :
            tubiao = "<i class=\"fa fa-key fa-fw\"></i>"
            break;
        case "续费" :
            tubiao = "<i class=\"fa fa-key fa-fw\"></i>"
            break;
        /**case "授权" :
            tubiao = "<i class=\"fa fa-laptop\"></i>"
            break;
        case "角色授权" :
            tubiao = "<i class=\"fa fa-laptop\"></i>"
            break;
        case "菜单授权" :
            tubiao = "<i class=\"fa fa-laptop\"></i>"
            break;*/
        default :
            tubiao = "<i class=\"fa fa-laptop\"></i>"
            break;
    }
    return tubiao;
}

/**获取问号后面的所有的值*/
function getWhVal() {
    return window.location.search.substring(1);
}

/**获取左边的选中的url地址*/
function leftMenuUrl(){
    var url = "";
    $(window.parent.document).find("#menuU li").each(function () {
        if ($(this).hasClass("xz")){
            url = $(this).data("url");
            return false;
        }
    });
    return url;
}

/** 加密*/
function compile(code){
    var c=String.fromCharCode(code.charCodeAt(0)+code.length);
    for(var i=1;i<code.length;i++){
        c+=String.fromCharCode(code.charCodeAt(i)+code.charCodeAt(i-1));
    }
    return c;
}

/** 解密*/
function uncompile(code){
    code=unescape(code);
    var c=String.fromCharCode(code.charCodeAt(0)-code.length);
    for(var i=1;i<code.length;i++){
        c+=String.fromCharCode(code.charCodeAt(i)-c.charCodeAt(i-1));
    }
    return c;
}

//cookie.set("uesr","sss",24);//设置为24天过期
//alert(cookie.get("uesr"));//获取cookie
var cookie = {
    set:function(key,val,time){//设置cookie方法
        var date=new Date(); //获取当前时间
        var expiresDays=time;  //将date设置为n天以后的时间
        date.setTime(date.getTime()+expiresDays*24*3600*1000); //格式化为cookie识别的时间
        document.cookie=key + "=" + val +";expires="+date.toGMTString();  //设置cookie
    },
    get:function(key){//获取cookie方法
        /*获取cookie参数*/
        var getCookie = document.cookie.replace(/[ ]/g,"");  //获取cookie，并且将获得的cookie格式化，去掉空格字符
        var arrCookie = getCookie.split(";")  //将获得的cookie以"分号"为标识 将cookie保存到arrCookie的数组中
        var tips;  //声明变量tips
        for(var i=0;i<arrCookie.length;i++){   //使用for循环查找cookie中的tips变量
            var arr=arrCookie[i].split("=");   //将单条cookie用"等号"为标识，将单条cookie保存为arr数组
            if(key==arr[0]){  //匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
                tips=arr[1];   //将cookie的值赋给变量tips
                break;   //终止for循环遍历
            }
        }
        return tips;
    },
    delete:function(key){ //删除cookie方法
        var date = new Date(); //获取当前时间
        date.setTime(date.getTime()-10000); //将date设置为过去的时间
        document.cookie = key + "=v; expires =" +date.toGMTString();//设置cookie
    }
}

function huanfu(cssname){
    // 主页的
    var link = document.getElementsByTagName('link')[0];
    link.href = getRootPath()+"/css/admin/index"+cssname+".css";
    link.rel = 'stylesheet';
    link.type = 'text/css';
    // 放cookie  主要是记住下次进来的颜色
    cookie.set("cssName",cssname,365);
}

/*
 * 替换空格
 */
function replaceTrim(str){
    return str.replace(/\s+/g,"");
}

/**
 * 取消弹出框  内容的
 */
function closealert() {
    setTimeout(function () {
        $(window.parent.document.body).find(".zhezhao,.popup").remove();
    },1000);
}

$.ajaxSetup({
    //设置ajax请求结束后的执行动作
    complete : function(XMLHttpRequest, textStatus) {
        // 通过XMLHttpRequest取得响应头，REDIRECT
        var redirect = XMLHttpRequest.getResponseHeader("REDIRECT");//若HEADER中含有REDIRECT说明后端想重定向
        if (redirect == "REDIRECT") {
            var win = window;
            while (win != win.top){
                win = win.top;
            }
            //将后端重定向的地址取出来,使用win.location.href去实现重定向的要求
            win.location.href= XMLHttpRequest.getResponseHeader("CONTEXTPATH");
        }
    }
});

/**var buffer = new StringBuffer();
buffer.append("Hello ").append("javascript");
alert(buffer.toString());*/
 /**
  * 模拟java的stringBuild E
  */