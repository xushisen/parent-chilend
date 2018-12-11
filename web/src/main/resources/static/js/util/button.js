/**
 * admin 按钮级别的权限
 *
 * @param operation 是否有表格后面的操作按钮 默认true 有操作 填写了就是false 没有表格后面的操作
 * @returns 右键的链接名字
 */
$.admin = function(operation){
    var param = "";
    // !getUrlParams("param") false 有值 true 没值 需要动态拿到点击的菜单的param
    // false 是左边直接点击的  true就是列表的crud刷新需要动态拿js拿params的
    if(!getUrlParams("param") == false){
        param = getUrlParams("param");
    } else {
        var url = leftMenuUrl();
        param = url.substring(url.indexOf("=") + 1);
    }
    var rightVal = "";
    $.ajax({
        url:getRootPath() + "/pmdRoleApplicationAuth/getAdminButton",
        type: 'post',
        async : false,
        dataType: 'json',
        data : {"param" : param},
        success: function (data) {
            // 返回的数据
            var len = data.data.length;
            // 是否有表格
            var tablelen = $(".table").length;
            // 是否有查询框 长度是2的话text:eq(1) 否则eq(0)
            var textlen = $(".text").length;
            if (len > 0){
                $.each(data.data,function (i, obj) {
                    var name = obj.applicationName,url=obj.applicationUrl;
                    var tb = ico(name);
                    if (tablelen > 0){
                        // 表格的处理
                        // 最上面的拼接
                        if (name == "新增" || name == "删除"){
                            var urls = name != "删除" ? url : url.replace("()","(hiddenVal())");
                            var a = "<a href=\"javascript:;\" onclick=\""+urls+"\">"+tb+name+"</a>";
                            var eq = textlen == 2 ? 1 : 0;
                            $(".text:eq('"+eq+"'),.texts:eq('"+eq+"')").append(a);
                        }
                        // 右键拼接
                        rightVal += name + ",";
                        if (isUndefined(operation)){
                            // 表格操作权限
                            var xiegang = i == len - 1 ? "" : "&nbsp;/&nbsp;";
                            if (name != "新增"){
                                $(".table tbody tr").each(function (i, obj) {
                                    var id = $(obj).find("td:eq(0) div").data("num"),urls;
                                    // 需要点击事件拼接id
                                    urls = url.replace("()", "('"+id+"')");
                                    $(obj).find("td:last").append("<a href=\"javascript:;\" onclick=\""+urls+"\">"+name+"</a>"+xiegang+"");
                                })
                            }
                        }
                    } else {
                        // 不是表格的处理
                        var a = "<a href=\"javascript:;\" onclick=\""+url+"\">"+tb+name+"</a>";
                        $(".text").append(a);
                    }
                })
            }
        },
        error: function () {
            $.ts({"content":"网络异常,admin菜单权限获取失败","direction":"around","code":"0"});
        }
    });
    return rightVal;
}