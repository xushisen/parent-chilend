/**
 * 点击页数触发的事件
 */
function paging(pages){
    searchDate(pages);
}

/**
 *  校验只能是非0的正整数
 */
function isPositiveInteger(s){
    var re =/^\+?[1-9][0-9]*$/;
    return re.test(s);
}

/**
 * 校验输入的数字是否合法
 * flag true 回车 false 点击
 */
function tiao(page,zys,flag){
    if (! flag){
        if (page == ""){
            alert("请输入页数");
        } else {
            if(isPositiveInteger(page)){
                if(page<=zys){
                    searchDate(page);
                } else {
                    alert("输入的页数大于总页数")
                }
            } else {
                alert("只能输入非零的正整数");
            }
        }
    } else {
        var event = window.event || arguments.callee.caller.arguments[0];
        if(event.keyCode == 13){
            if(isPositiveInteger(page)){
                if(page<=zys){
                    searchDate(page);
                } else {
                    alert("输入的页数大于总页数")
                }
            } else {
                alert("只能输入非零的正整数");
            }
        }
    }
}

/**
 * 分页算法
 * @param count 总数
 * @param page 当前页
 * @param pagesize 每页多少条
 */
function getPage(count,page,pagesize){
    $(".page").empty();
    var pcys = 7;  //页面上要显示的页数的个数   可以改变
    //var page = pageIndex;   //当前页数   需要动态改
    //var count = 101; //总条数    需要动态改
    var zys = 0;  //总页数
    //var pagesize = pageSize;  //每页的数量 默认15条 改这里记得154行的15也要改

    zys = Math.ceil(count/pagesize);  //总共的页数
    page = page == 0 ? 7 : parseInt(page);  //第一次默认弟7页
    var buffer = "";
    buffer += "<ul>";
    if (zys>pcys) {  //总页数大于页面上要显示的分页的数量
        var shang = Math.ceil(pcys/2);  //向上取整
        if (page <= shang) {  //当前的页数小于中间的那个页数    也就是只有下一页的情况
            for (var i=1;i<=pcys;i++) {
                if (i == page) {
                    buffer += "<li class=\"bg\" onclick=\"paging("+i+")\">"+i+"</li>";
                } else {
                    buffer += "<li onclick=\"paging("+i+")\">"+i+"</li>";
                }
            }
            buffer += "<li onclick=\"paging("+(page+1)+")\">下一页</li>";
        } else {
            var xia = Math.floor(pcys/2);  //向下取整
            if (page+xia<zys) {  //当前页加上需要在页面显示页数数量的一半  如果大于总页数  证明后面还有  正常拼出  上页下页都有
                buffer += "<li onclick=\"paging("+(page-1)+")\">上一页</li>";
                for (var i=xia;i>=1;i--) {  //向左拼
                    buffer += "<li onclick=\"paging("+(page-i)+")\">"+(page-i)+"</li>";
                }
                buffer += "<li class=\"bg\" onclick=\"paging("+page+")\">"+page+"</li>";
                for (var i=1;i<=xia;i++) {   //向右拼
                    buffer += "<li onclick=\"paging("+(page+i)+")\">"+(page+i)+"</li>";
                }
                buffer += "<li onclick=\"paging("+(page+1)+")\">下一页</li>";
            } else {  //当前页数加上一半小于总页数  从最后往左拼显示在页面上页数数量的个数   只有上一页的情况
                buffer += "<li onclick=\"paging("+(page-1)+")\">上一页</li>";
                for (var i=pcys-1;i>=0;i--) {
                    if ((zys-i) == page) {
                        buffer += "<li class=\"bg\" onclick=\"paging("+(zys-i)+")\">"+(zys-i)+"</li>";
                    } else {
                        buffer += "<li onclick=\"paging("+(zys-i)+")\">"+(zys-i)+"</li>";
                    }
                }
            }
        }
    }else{  //拼出所有的页数   也就是没有上页下页
        for(var i=1;i<=zys;i++){
            if (i == page) {
                buffer += "<li class=\"bg\" onclick=\"paging("+i+")\">"+i+"</li>";
            } else {
                buffer += "<li onclick=\"paging("+i+")\">"+i+"</li>";
            }
        }
    }
    buffer += "&nbsp;跳至&nbsp;<input type=\"text\" id=\"yeshu\" onkeyup=tiao(this.value,"+zys+",true) />&nbsp;页";
    //  <button onclick=tiao(document.getElementById(\"yeshu\").value,"+zys+",false)>go</button>
    buffer += "</ul>";
    $(".page").append(buffer);
}
