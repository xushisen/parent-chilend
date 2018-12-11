<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="shortcut icon" href="${ctx.contextPath}/images/ico/favicon.ico" type="image/x-icon">
    <title>404错误页面</title>
    <style type="text/css">
        *{margin:0;padding:0;}
        html,body{height:100%;overflow:hidden;}
        .title{height:45%;text-align:center;position:relative;}
        .title img{margin-top:39px;position:absolute;top:50%;left:50%;margin-left:-221px;margin-top:-87px;}

        .zi{height:17%;background:#474A4F;text-align:center;}
        .zi .ayy{display:block;color:#fff;font-size:30px;line-height:60px;}
        .zi .sy a{color:yellow;}

        .butt{height:38%;position:relative;}
        .butt_cen{width:500px;height:200px;position:absolute;top:50%;left:50%;margin-left:-221px;margin-top:-87px;}
        .butt_cen img{display:block;float:left;}
        .butt_cen_con{margin:15px 0 0 20px;float:left;}
        .butt_cen_con ul li{color:#605F62;margin:10px 0 0 20px;font-size:14px;}
    </style>
</head>
<body>
<div class="title">
    <img src="${ctx.contextPath}/images/error/404.png" width="442" height="174" alt="404" />
</div>
<div class="zi">
    <span class="ayy">哎呀呀!&nbsp;&nbsp;您访问的页面不知道"去哪了"</span>
    <span class="sy"><a href="javascript:;">【刷新页面】</a><a href="javascript:;" id="back">【返回网站首页】</a></span>
</div>
<div class="butt">
    <div class="butt_cen">
        <img src="${ctx.contextPath}/images/error/jiong.png" width="73" height="75" alt="jiong" />
        <div class="butt_cen_con">
            <span>可能出现了以下问题:</span>
            <ul>
                <li>您输入的网址不正确或者是不完整,请检查并重新输入</li>
                <li>该网站正在维护升级,请关注相关公告</li>
                <li>该页面已被删除或移出,请访问其他页面</li>
            </ul>
        </div>
    </div>
</div>
<script type="text/javascript" src="${ctx.contextPath}/js/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript" src="${ctx.contextPath}/js/util/util.js"></script>
<script type="text/javascript">
    if(window!=top){top.location.href=location.href;}
    $("#back").click(function (){
        window.location.href=getRootPath();
    });
</script>
</body>
</html>