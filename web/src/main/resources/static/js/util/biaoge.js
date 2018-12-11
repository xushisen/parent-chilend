// 让分页select下拉框默认选中
$(".page select").find("option[value = '"+$("#pagesize").val()+"']").attr("selected","selected");

var rightDiv = "<div class='caidan'><ul></ul></div>";
var parentName = window.parent.document.getElementsByTagName('link')[0].href;

// 鼠标点击的颜色
var xzColor = "#d6f2db";
// 隔行换色的寒色
var geColor = "#eefff1";
if (parentName.indexOf("blue")!= -1){
    xzColor = "#E1F1F9";
    geColor = "#EBF8FF";
}
$.rightClick = function (options) {
    var opts = $.extend({}, options);
    var val = opts.val;
    var vals = val.split(",");
    $("body").append(rightDiv);
    for (var i = 0; i < vals.length; i++) {
        var tubiao = ico(vals[i]);
        $(".caidan ul").append("<li>" + tubiao + "<span>" + vals[i] + "</span>" + "</li>")
    }
    $(".caidan ul li").each(function () {
        $(this).click(function () {
            var vall = $(this).find("span").text();
            if (vall == "新增") {
                (opts.save)()
            } else if (vall == "编辑") {
                (opts.edit)(hiddenVal())
            } else if (vall == "删除") {
                (opts.dele)(hiddenVal())
            } else if (vall == "审核") {
                (opts.examine)(hiddenVal())
            } else if (vall == "批量通过") {
                (opts.batch)(hiddenVal())
            } else if (vall == "批量不通过") {
                (opts.batchNo)(hiddenVal())
            } else if (vall == "导入") {
                (opts.exports)()
            } else if (vall == "重置密码") {
                (opts.resetPwd)(hiddenVal())
            } else if (vall == "续费") {
                (opts.renew)(hiddenVal())
            } else if (vall.indexOf("授权") != -1){
                (opts.authorization)(hiddenVal())
            }
            $(".caidan").css({"display": "none"});
        })
    })
}

var crtlKey = false, shiftKey = false;
var firstIndex, lastIndex;
$(document).bind("contextmenu", function (e) {
    return false
});
$(".table tr:not(:first)").mousedown(function (e) {
    if (e.which == 3) {
        $(".caidan").css({"display": "block", "position": "fixed", "top": e.clientY + "px", "left": e.clientX + "px"})
    }
    if (e.which == 1) {
        $(".caidan").css({"display": "none"})
    }
});
$(document).keydown(function (e) {
    var e = e || event;
    if (e.keyCode == 17) {
        crtlKey = true
    } else if (e.keyCode == 16) {
        shiftKey = true
    }
}).keyup(function () {
    crtlKey = false;
    shiftKey = false;
    firstIndex = undefined;
    lastIndex = undefined
});
$(".table tr:not(:first)").each(function (e) {
    $(this).click(function () {
        if (crtlKey) {
            var len = $(this).find("td:first").find(".checkK").children().length;
            if (len == 0) {
                tjbackground($(this).index() + 1)
            } else {
                qxbackground($(this).index() + 1)
            }
        } else {
            if (shiftKey) {
                quanxiao();
                if (typeof(firstIndex) == "undefined") {
                    firstIndex = $(this).index();
                    $(".table tr:not(:first):eq(" + firstIndex + ")").find("td:first").find(".checkK").append("<div class='checkK_dian'></div>");
                    $(".table tr:not(:first):eq(" + firstIndex + ")").css("background", xzColor)
                } else {
                    lastIndex = $(this).index()
                }
                if (typeof(firstIndex) != "undefined" && typeof(lastIndex) != "undefined") {
                    shiftCheck(firstIndex, lastIndex)
                }
            } else {
                var len = $(this).find("td:first").find(".checkK").children().length;
                if (len == 0) {
                    quanxiao();
                    tjbackground($(this).index() + 1)
                } else {
                    var vals = hiddenVal().split(",");
                    if (vals.length > 2) {
                        quanxiao();
                        var div = "<div class='checkK_dian'></div>";
                        $(this).find("td:first").find(".checkK").append(div);
                        $(this).css("background", xzColor)
                    } else {
                        qxbackground($(this).index() + 1)
                    }
                }
            }
        }
    })
});

function shiftCheck(firstIndex, lastIndex) {
    var div = "<div class='checkK_dian'></div>";
    if (firstIndex < lastIndex) {
        $(".table tr:not(:first):lt(" + (lastIndex + 1) + ")").find("td:first").find(".checkK").append(div);
        $(".table tr:not(:first):lt(" + (lastIndex + 1) + ")").css("background", xzColor);
        $(".table tr:not(:first):lt(" + (firstIndex) + ")").find("td:first").find(".checkK").children(".checkK_dian").remove();
        $(".table tr:not(:first):lt(" + (firstIndex) + ")").each(function () {
            var index = $(this).index() + 1;
            index % 2 == 0 ? $(this).css("background", geColor) : $(this).css("background", "#fff")
        })
    } else {
        var eindex = lastIndex - 1;
        if (eindex == -1) {
            eindex = 0;
            $(".table tr:not(:first):eq(" + eindex + ")").find("td:first").find(".checkK").append(div);
            $(".table tr:not(:first):eq(" + eindex + ")").css("background", xzColor)
        }
        $(".table tr:not(:first):gt(" + (eindex) + ")").find("td:first").find(".checkK").append(div);
        $(".table tr:not(:first):gt(" + (eindex) + ")").css("background", xzColor);
        $(".table tr:not(:first):gt(" + (firstIndex) + ")").find("td:first").find(".checkK").children(".checkK_dian").remove();
        $(".table tr:not(:first):gt(" + (firstIndex) + ")").each(function () {
            var index = $(this).index() + 1;
            index % 2 == 0 ? $(this).css("background", geColor) : $(this).css("background", "#fff")
        })
    }
}

function xuan(obj) {
    var val = $(obj).text();
    if (val == "全选") {
        quanxuan();
        $(obj).text("全消")
    } else {
        quanxiao();
        $(obj).text("全选")
    }
}

function quanxuan() {
    $(".table .checkK").each(function () {
        var div = "<div class='checkK_dian'></div>";
        $(this).append(div)
    })
}

function quanxiao() {
    $(".table .checkK").each(function () {
        $(this).children(".checkK_dian").remove()
    });
    hf()
}

function hiddenVal() {
    var retu = "";
    $(".table .checkK").each(function () {
        if ($(this).children().length == 1) {
            retu += $(this).data("num") + ","
        }
    });
    return retu.substring(0, retu.length - 1)
}

hf();

function hf() {
    $(".table tr:not(:first)").css("background", "#fff");
    $(".table tr:not(:first):odd").css("background", geColor)
}

function tjbackground(index) {
    var div = "<div class='checkK_dian'></div>";
    $(".table tr:eq(" + index + ")").find("td:first").find(".checkK").append(div);
    $(".table tr:eq(" + index + ")").css("background", xzColor)
}

function qxbackground(index) {
    $(".table tr:eq(" + index + ")").find("td:first").find(".checkK").children(".checkK_dian").remove();
    index % 2 == 0 ? $(".table tr:eq(" + index + ")").css("background", geColor) : $(".table tr:eq(" + index + ")").css("background", "#fff")
};