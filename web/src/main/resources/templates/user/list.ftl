<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <link rel="stylesheet" type="text/css" href="${path}/css/biaoge.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/zidingyi.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/css/texiao.css"/>
    <link rel="stylesheet" type="text/css" href="${path}/awesome/css/font-awesome.min.css">
    <link rel="shortcut icon" href="${path}/images/ico/favicon.ico" type="image/x-icon">
    <style type="text/css">
        body{overflow:auto;}
    </style>
</head>
  
<body leftmargin=0 topmargin=0  oncontextmenu='return false' ondragstart='return false' onselectstart ='return false' onselect='document.selection.empty()' oncopy='document.selection.empty()' onbeforecopy='return false'>
    <!-- 搜索框开始 -->
    <form action="${ctx.contextPath}/user/list" method="post" >
        <input type="hidden" id="pagebegin" name="pagebegin" >  <!-- 第几页 -->
        <input type="hidden" value="${list.page.pagesize?c}" id="pagesize" name="pagesize" >  <!-- 每页多少条 改变每页的数量只需要改变这个val就可以 -->

        <div class="text">
            <div class="searchD">
               <span>账号:</span>
               <input type="text" name="userLogin" autocomplete="off" placeholder="请输入姓名" class="inputText" value="${list.page.userLogin!''}" />
            </div>
            <div class="b-center">  <!-- 居中调整这个宽度 -->
                <a href="javascript:;"><i class="fa fa-search"></i>查询</a>
            </div>
        </div>
    </form>
    <!-- 搜索框结束 -->
    <div class="text">
        <a href="javascript:;" onclick="saveOrUpdatePage('');"><i class="fa fa-plus"></i>新增</a>
        <a href="javascript:;" onclick="del(hiddenVal());"><i class="fa fa-trash-o fa-fw"></i>批量删除</a>
    </div>
    <table class="table">
        <thead>
            <tr>
                <td><a href="javascript:;" onclick="xuan(this)">全选</a></td>
                <td>序号</td>
                <td>账号</td>
                <td>操作</td>
            </tr>
        </thead>
        <tbody>
            <#if list.list?? && (list.list?size > 0)>
                <#assign size = list.page.pagesize><#assign index = list.page.pagebegin><#assign cou = size * (index - 1)>
                <#list list.list as user>
                    <tr>
                        <td><div class="checkK" data-num="${user.userId}"></div></td>
                        <td>${user_index+1+cou}</td>
                        <td>${user.userLogin}</td>
                        <td>
                            <a href="javascript:;" onclick="saveOrUpdatePage('user.userId');">编辑</a>/
                            <a href="javascript:;" onclick="del('user.userId');">删除</a>
                        </td>
                    </tr>
                </#list>
            <#else>
                <tr>
                    <td colspan="20" style="text-align:center">没有查到数据</td>
                </tr>
            </#if>
        </tbody>
    </table>

    <@page count="${list.page.count?c}" page="${list.page.pagebegin?c}" pagesize="${list.page.pagesize?c}">${pages}</@page>
    ${js}

    <script type="text/javascript" src="${path}/js/jquery/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="${path}/js/util/biaoge.min.js"></script>
    <script type="text/javascript" src="${path}/js/util/alert.min.js"></script>
    <script type="text/javascript" src="${path}/js/util/tcUtil.min.js"></script>
    <script type="text/javascript" src="${path}/js/util/util.js"></script>

    <script type="text/javascript">
        $(parent.window.document.body).find(".mask").remove();//取消遮罩层

        /**
         * 回车事件
         */
        $(".inputText").keydown(function(event){
             if(event.keyCode ==13){
                document.forms[0].submit();
             }
         });

        /**
         * 查询
         */
        $(".b-center a").click(function (){
            document.forms[0].submit();
        });

        /**
         * 新增
         */
        function saveOrUpdatePage(id){
            var title = id == "" ? "新增" : "编辑";
            $.tContent({
                title:title,
                width:"910",
                height:"450",   //true 需要跳出ifame封住全屏
                url:getRootPath() + "/[lowerEntity]/saveOrUpdatePage?id="+id
            });
        }

        /**
         * 删除
         */
        function del(ids){
            if (errorShow(ids, "删除", true)){
                $.tConfirm({
                    content:"确定删除?",
                    title:"提示",
                    direction:"bottom",  //从哪个方向出来  top left right bottom
                    sfqp:false,   //true 需要跳出ifame封住全屏
                    yes:function(){
                        $.ajax({
                            url:getRootPath() + "[lowerEntity]/deleteByIds",
                            type: 'post',
                            dataType: 'json',
                            data : {ids : ids},
                            success: function (data) {
                                $.ts({"content":data.msg,"direction":"around","code":data.code});
                                if (data.code == 1){
                                    setTimeout(function (){document.forms[0].submit();},1000);
                                }
                            },
                            error: function () {
                                $.ts({"content":"网络异常,删除失败","direction":"around","code":"0"});
                            }
                        });
                    },
                    no:function(){
                        //$.jAlert({title:"提示",content:"点击了否",direction:"top",sfqp:false});
                        //$.ts({"width":"140px","height":"30px","color":"#fff","content":"点击了取消","backgroundColor":"orange","type":"Right","direction":"around","date":"3000"});
                    }
                });
            }
        }

        /**
         * 右键操作
         */
        $.rightClick({
            "val":"新增,编辑,删除",
            save:function (){
                saveOrUpdatePage('');
            },
            edit:function (id){
                if (errorShow(id, "编辑", false))
                saveOrUpdatePage(id);
            },
            dele:function (ids){
                del(ids);
            }
        });
    </script>
</body>
</html>