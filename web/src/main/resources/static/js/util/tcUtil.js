$.ts = function (options) {
    var opts = $.extend({}, $.ts.method, options);
    var content = opts.content;
    var direction = opts.direction;
    // 成功失败的状态  1成功 0失败 2提示
    var code = opts.code;
    opts.create(content,direction,code)
};
$.ts.method = {
    create: function (content,direction,code) {
        var background = "",color ="",tubiao="";
        if (code == "2"){
            background = "#FDF7ED";
            color = "#EBB057";
            tubiao = "<i style='font-size:14px;' class=\"fa fa-exclamation-circle\" aria-hidden=\"true\"></i>";
        } else if(code == "1"){
            background = "#F1F9EB";
            color = "#73C84A";
            tubiao = "<i style='font-size:14px;' class=\"fa fa-check-circle\" aria-hidden=\"true\"></i>";
        } else if(code == "0"){
            background = "#FFF1F1";
            color = "#F77A7A";
            tubiao = "<i style='font-size:14px;' class=\"fa fa-times-circle\" aria-hidden=\"true\"></i>";
        }
        if (direction == "around") {
            var divTskdh = "<div class='tskdh'>" + tubiao + "&nbsp;&nbsp;" + content + "</div>";
            $("body").append(divTskdh);
            $(".tskdh").css({
                "text-align": "center",
                "line-height": "30px",
                "color": ""+color+"",
                "background": ""+background+"",
                "position": "fixed",
                "left": "50%",
                "top": "-20px",
                //"transform":"translateX(-50%)",
                "padding":"5px 26px",
                /**"-webkit-transition":"all .5s ease-in",
                "-moz-transition":"all .5s ease-in",
                "transition":"all .5s ease-in",*/
                //"bottom": "0",
                //"right": "0",
                "margin": "auto",
                "border-radius": "4px",
                //"width": "" + width + "",
                //"height": "30px",
                "-webkit-animation": "messageBoxDown .5s 1 ease-in-out forwards",
                "animation": "messageBoxDown .5s 1 ease-in-out forwards",
            });
            setTimeout(function () {
                $(".tskdh").css({
                    "-webkit-animation": "messageBoxUp 1s 1 ease-in-out forwards",
                    "animation": "messageBoxUp 1s 1 ease-in-out forwards"
                });
                setTimeout(function (args) {$(".tskdh").remove(); },1000);
            }, 2000)
        } else {
            if (direction == "among") {
                var divZhong = "<div class='yuan'>" + content + "</div>";
                $("body").append(divZhong);
                $(".yuan").css({
                    "position": "fixed",
                    "top": "50%",
                    "left": "50%",
                    "width": "" + width + "",
                    "height": "" + height + "",
                    "background": "" + backgroundColor + "",
                    "color": "" + color + "",
                    "text-align": "center",
                    "line-height": "" + height + "",
                    "border-radius": "4px",
                    "-webkit-animation": "tckZhong 1s 1 ease-in-out forwards",
                    "animation": "tckZhong 1s 1 ease-in-out forwards;"
                });
                if (date != "") {
                    setTimeout(function () {
                        $(".yuan").remove()
                    }, date)
                }
            } else {
                if (direction == "load") {
                    var text = typeof(content) == "undefined" ? "数据加载中..." : content;
                    var loadDiv = "<div class=\"loading\"><span><i style=\"font-size:18px;margin-right:8px;\" class=\"fa fa-spinner fa-pulse\"></i>"+text+"</span></div>";
                    $("body").append(loadDiv);
                    $(".loading").css({
                        "position": "fixed",
                        "top": "50%",
                        "left": "50%",
                        "margin-left": "-68px",
                        "margin-top": "-25px",
                        "width": "142px",
                        "background": "rgb(62,65,70)",
                        "color": "rgb(255,255,255)",
                        "border": "1px solid rgb(85,85,85)",
                        "border-radius": "4px",
                        "box-shadow": "2px 2px 1em #333"
                    });
                    $(".loading span").css({
                        "width": "130px",
                        "height": "28px",
                        "display": "block",
                        "font-size": "14px",
                        "margin": "5px 20px",
                        "line-height": "28px"
                    })
                }
            }
        }
    }
};
var div = "<div class='mask'><div class='spinner'><div class='rect1'></div><div class='rect2'></div><div class='rect3'></div><div class='rect4'></div><div class='rect5'></div></div></div>";
var mask = "";
$.jzjd = function (options) {
    var opts = $.extend({}, $.jzjd.method, options);
    var divId = opts.divId;
    opts.szjd(divId)
};
$.jzjd.method = {
    szjd: function (divId) {
        if (typeof(divId) == "undefined") {
            $("body").append(div);
            $(".mask").css({
                "width": "100%",
                "height": "100%",
                "position": "fixed",
                "top": "0px",
                "filter": "alpha(opacity=60)",
                "background-color": "#777",
                "left": "0px",
                "opacity": "0.5",
                "-moz-opacity": "0.5"
            })
        } else {
            $("#" + divId).append(div);
            $(".mask").css({
                "width": $("#Conframe").width(),
                "height": $("#Conframe").height(),
                "top": "60px",
                "position": "absolute",
                "filter": "alpha(opacity=60)",
                "background-color": "#777",
                "opacity": "0.5",
                "-moz-opacity": "0.5"
            })
        }
        $(".spinner").css({
            "position": "fixed",
            "left": "0",
            "top": "0",
            "bottom": "0",
            "right": "0",
            "margin": "auto",
            "width": "60px",
            "height": "60px",
            "text-align": "center",
            "font-size": "10px"
        });
        $(".spinner > div").css({
            "background-color": "#67CF22",
            "height": "100%",
            "width": "3px",
            "display": "inline-block",
            "-webkit-animation": "stretchdelay 1.2s infinite ease-in-out",
            "animation": "stretchdelay 1.2s infinite ease-in-out",
            "margin-right": "3px"
        });
        $(".spinner .rect2").css({"-webkit-animation-delay": "-1.1s", "animation-delay": "-1.1s"});
        $(".spinner .rect3").css({"-webkit-animation-delay": "-1s", "animation-delay": "-1s"});
        $(".spinner .rect4").css({"-webkit-animation-delay": "-0.9s", "animation-delay": "-0.9s"});
        $(".spinner .rect5").css({"-webkit-animation-delay": "-0.8s", "animation-delay": "-0.8s"})
    }
};