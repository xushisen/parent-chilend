//模拟数据 "checked":"checked" 默认选中  "readonly":"readonly" 只读
/**var jsonData = [
    {"id":"1","name":"机动队","url":"www.baidu.com","pid":"0","checked":"checked"},
    {"id":"4","name":"男人","url":"","pid":"1","checked":"checked"},
    {"id":"13","name":"刘壮壮","url":"","pid":"4"},
    {"id":"14","name":"徐石森","url":"","pid":"4","checked":"chekced"},
    {"id":"15","name":"猴哥","url":"","pid":"4","checked":"chekced","readonly":"readonly"},
    {"id":"5","name":"女人","url":"","pid":"1"},
    {"id":"6","name":"赵海瑞","url":"","pid":"5"},
    {"id":"20","name":"哈哈","url":"","pid":"5"},
    {"id":"2","name":"云平台","url":"","pid":"0"},
    {"id":"7","name":"杨渊","url":"","pid":"2","readonly":"readonly"},
    {"id":"8","name":"李小猛","url":"","pid":"2"},
    {"id":"3","name":"移动端","url":"","pid":"0"},
    {"id":"10","name":"王朕","url":"","pid":"3"},
    {"id":"11","name":"贾斌","url":"","pid":"3"},
    {"id":"12","name":"高雪平","url":"","pid":"3"}
];*/


var menus = '';

//根据菜单主键id生成菜单列表html
// 第一个参数 最大的父id  一般都是空  第二个参数 json数据  !!!注意 返回的key必须是 id pid url name 格式 在sql别名就行
// 第三个参数 死的 第四个参数 是否显示复选框 true 显示  false不显示
// 第五个参数 点击的回掉函数

//控制是否显示复选框  写在外面是因为在getData的方法里面会迭代  第二次就拿不到了  写在外面可以一直拿到
var fxk;
function tree(id, arry, cla, fxks, callback) {
    fxk = fxks;
    getData(id, arry, cla);
    $(".treeView").append(menus);
    reloadTree();
    opens(); //默认展开一级菜单
    clicks(); // 加号减号点击事件
    //树的每个a绑定click事件
    $(".ztree a").each(function (i, obj) {
        $(obj).click(function () {
            $(".ztree span").removeClass("xz");
            $(this).find("span:last").addClass("xz");
            // 回掉函数的参数  1 当前的id  2 当前的text 3 父的id  4 type  5 父name
            callback($(this).find("span:last").attr("id"),
                     $(this).find("span:last").text(),
                     $(this).parent().parent().parent().find("a span:eq(1)").attr("id"),
                     $(this).find("span:last").data("type"),
                     $(this).parent().parent().parent().find("a span:eq(1)").text());
        });
    });
}

/**
 * 组装数据
 * @param id
 * @param arry
 * @param cla
 * @returns {string}
 */
function getData(id, arry, cla) {
    var childArry = GetParentArry(id, arry);
    if (childArry.length > 0) {
        menus += '<ul class="'+cla+'">';
        for (var i in childArry) {
            //console.log(i+"======"+childArry[i].pid+"====="+childArry[i].name);
            var checked = "checkbox_false_full";  //复选框的选中  只读  等等
            if (childArry[i].checked == true){  //选中
                checked = "checkbox_true_full";
            }
            if (childArry[i].readonly == true){ // 只读
                checked = "checkbox_readonly_notcheck";
            }
            if (childArry[i].checked == true && childArry[i].readonly == true){ //只读选中
                checked = "checkbox_readonly_check";
            }
            menus += "<li>";
            menus += "	<span class=\"button switch roots_close\"></span>";
            // 这一行是复选框
            if (fxk)
            menus += "	<span class=\"button chk "+checked+"\"></span>";  /**button chk 是否有复选框*/
            menus += "	<a title=\""+childArry[i].name+"\">";
            menus += "		<span class=\"button ico_close\"></span>";
            menus += "		<span id=\""+childArry[i].id+"\" data-type=\""+childArry[i].type+"\" class=\"node_name\">"+ childArry[i].name+"</span>";
            menus += "	</a>";
            getData(childArry[i].id, arry, "line none");
            menus += '</li>';
        }
        menus += '</ul>';
    }
    return menus;
}

//根据菜单主键id获取下级菜单
//id：菜单主键id
//arry：菜单数组信息
function GetParentArry(id, arry) {
    var newArry = new Array();
    for (var i in arry) {
        if (arry[i].pid == id)
            newArry.push(arry[i]);
    }
    return newArry;
}

/**重新组装树  主要是为了把最后一层的css改掉*/
function reloadTree(){
    $(".treeView ul li").each(function (){
        var ulobj = $(this).find("ul");
        if(ulobj.length == 0){
            //前面的文件夹图标
            $(this).find("a span:first").removeClass("ico_open").addClass("ico_docu");
            //去掉加号减号
            $(this).find("span:eq(0)").removeClass("roots_open").addClass("center_docu");
            //最后一个css又是不一样  这句代码应该是只执行一次的  但是因为是在循环里面  所以执行多次  可以优化
            $(this).parent().find("li:last span:first").removeClass("center_docu").addClass("bottom_docu");
        }
    });
    // 移出第一个的那个竖线
    $(".treeView > ul > li > ul:first").removeClass("line");
    //为每层的最后一个li做处理   比如每层的最后一个ul要去掉class 是line  还有就是最后一个的加号减号的图标和正常的也不一样
    $(".treeView ul").each(function (){
        if($(this).find("ul").length){  //只有下面有ul的情况才会进入  因为最后一层是没有ul的  查找也没意义
            //$(this).find("li:last").parent().parent().find("ul").removeClass("line");  //每个最后一个li的ul
            $(this).find("li:last").parent().parent().find("span:first").removeClass("roots_close").addClass("bottom_close");  //每个最后一个li的ul
        }
    });
}

function clicks(){
    /**每个a绑定click事件*/
    /**$(".ztree a").each(function (i,obj){
        $(obj).click(function (){
            alert($(this).prev().attr("id"));
        });
    });*/

    /**加号减号*/
    $(".switch").click(function (){
        var cla = $(this).attr("class");
        if(cla.indexOf("roots") != -1){ //不是点击的最后一个的加号减号  //因为2个的样式不一样
            $(this).toggleClass("roots_open roots_close");
        } else {
            $(this).toggleClass("bottom_open bottom_close");
        }
        $(this).nextAll("ul").toggleClass("none");
        $(this).siblings("a").find("span:first").toggleClass("ico_open ico_close");
    });
    /**复选框*/
    $(".chk").click(function (){
        var chkCla = $(this).attr("class");
        if (chkCla.indexOf("readonly") != -1){  //点击只读的  不做任何操作
            return;
        }
        //自己切换
        $(this).toggleClass("checkbox_false_full checkbox_true_full");

        //给父元素选中
        $(this).parents().children(".chk").not(this).removeClass("checkbox_false_full").addClass("checkbox_true_full");
        var cla = $(this).attr("class");

        //给所有的子元素选中
        if (cla.indexOf("checkbox_true_full") != -1){  //选中
            $(this).nextAll("ul").find("li .chk").each(function (i,obj){
                var objCla = $(obj).attr("class");
                if(objCla.indexOf("readonly") == -1){  //只读的不参与选择操作
                    $(obj).removeClass("checkbox_false_full").addClass("checkbox_true_full");
                }
            });
        } else {
            $(this).nextAll("ul").find("li .chk").each(function (i,obj){
                var objCla = $(obj).attr("class");
                if(objCla.indexOf("readonly") == -1){
                    $(obj).removeClass("checkbox_true_full").addClass("checkbox_false_full");
                }
            });
        }

        //判断当前的取消是否是最后一个  如果是的话 父节点也要跟随取消
        var ulall = $(this).parent().parent();
        var checkLen = true; //判断是否当前消除了就全部消除了 因为要同时去掉父节点的选中 false 不去 true 去掉
        ulall.children("li").each(function (){
            var checkCla = $(this).find("span:eq(1)").attr("class");
            if (checkCla.indexOf("checkbox_true_full") != -1){
                checkLen = false;
                return false;
            }
        });
        if (checkLen){  //去掉父节点的选中  这里存在如果第三级没了  第二级还有  这里直接就会把第一个也取消了
            $(this).parents().children(".chk").removeClass("checkbox_true_full").addClass("checkbox_false_full");
        }
    });
}
$(function(){
    /**GetData(0, jsonData, "ztree");
    $(".treeView").append(menus);
    reloadTree();
    opens(); //默认展开一级菜单
    clicks();*/
}) ;

/**获取选中的值 复选框*/
function checkVal(){
    var ids = new Array();
    $(".chk").each(function (i,obj){
        var cla = $(obj).attr("class");
        if (cla.indexOf("checkbox_true_full") != -1 || cla.indexOf("checkbox_readonly_check") !=-1){  //默认不选中的和选择选中的
            ids.push($(obj).next().find("span:last").attr("id"));
        }
    });
    return ids.join();
}

/**获取选中的值 没有复选框*/
function checkValNo(){
    var ids = new Array();
    $(".ztree a").each(function (i, obj) {
        if ($(obj).find("span:last").hasClass("xz")){
            console.log($(obj).text());
            ids.push($(this).find("span:last").attr("id"))
        }
    });
    return ids.join();
}

/**打开第几层的树*/
function opens(){
    var num = $("#num").val();
    if (num == ""){
        alert("请输入要展开的层数");
        return;
    }
    $(".treeView > ul > li > ul").each(function (){
        $(this).removeClass("none");
        $(this).prevAll("a").find("span:first").removeClass("ico_close").addClass("ico_open")  //图片变成展开
        $(this).prevAll(".roots_close").removeClass("roots_close").addClass("roots_open");  //加号 不是最后一个
        $(this).prevAll(".bottom_close").removeClass("bottom_close").addClass("bottom_open");  //加号 是最后一个
    });
}

