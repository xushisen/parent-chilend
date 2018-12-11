<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="shortcut icon" href="${ctx.contextPath}/images/ico/favicon.ico" type="image/x-icon">
    <title>500错误页面</title>
    <style type="text/css">
        *{margin:0;padding:0;}
        html,body{height:100%;overflow:hidden;}
        .title{height:45%;text-align:center;position:relative;}
        .title img{margin-top:39px;position:absolute;top:50%;left:50%;margin-left:-221px;margin-top:-87px;}

        .zi{height:17%;background:#474A4F;text-align:center;}
        .zi .ayy{display:block;color:#fff;font-size:30px;line-height:60px;}
        .zi .sy a{color:yellow;text-decoration:none;}

        .butt{height:38%;position:relative;}
        .butt_cen{width:500px;height:200px;position:absolute;top:50%;left:50%;margin-left:-221px;margin-top:-87px;}
        .butt_cen img{display:block;float:left;}
        .butt_cen_con{margin:15px 0 0 20px;float:left;}
        .butt_cen_con ul li{color:#605F62;margin:10px 0 0 20px;font-size:14px;}
    </style>
</head>
<body>
<div class="title">
    <img src="${ctx.contextPath}/images/error/500.png" width="442" height="174" alt="404" />
</div>
<div class="zi">
    <span class="ayy">哎呀呀!&nbsp;&nbsp;系统出错了...</span>
    <span class="sy"><a href="javascript:;">【刷新页面】</a><a href="javascript:;" id="back">【返回网站首页】</a></span>
</div>
<div class="butt">
    <div class="butt_cen">
        <img src="${ctx.contextPath}/images/error/jiong.png" width="73" height="75" alt="jiong" />
        <div class="butt_cen_con">
            <span>可能出现了以下问题:</span>
            <ul>
                <li>您没有访问该系统的权限,请联系管理员开通权限</li>
                <li>如果已经开通权限,刷新页面或者重新登录</li>
                <li>该系统已经被删除</li>
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