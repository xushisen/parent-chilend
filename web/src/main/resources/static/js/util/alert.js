var type = false, startTop = 0, startLeft = 0, divTop = 0, divLeft = 0;
$(function () {
    jQuery.extend({
        jAlert: function (content) {
            var zhi = {
                qd: function () {
                }
            };
            var tck = "<div class='mask'></div><div class='alert'><div class='alertTitle'><p>" + content.title + "</p><span id='cha' title=\"关闭\"></span></div><div class='alertContent'><div class='content'>" + content.content + "</div><div class='contentBu'><a href='javascript:;' id='qd'>确定</a></div></div></div>";
            if (content.sfqp == false) {
                $("body").append(tck);
                divCenter(content.direction);
                mouseDown();
                $("#qd,#cha").click(function () {
                    removeDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.qd)();
                    return true
                })
            } else {
                $(parent.window.document.body).append(tck);
                divCenterIframe(content.direction);
                mouseDownifame();
                $(parent.window.document.body).find("#qd,#cha").click(function () {
                    removeQpDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.qd)();
                    return true
                })
            }
        }, tConfirm: function (content) {
            var zhi = {
                yes: function () {
                }, no: function () {
                }
            };
            var tsk = "<div class='mask'></div><div class='alert'><div class='alertTitle'><p>" + content.title + "</p><span id='cha'></span></div><div class='alertContent'><div class='content'>" + content.content + "</div><div class='contentBuTs'><a href='javascript:;' id='qd'>确定</a><a href='javascript:;' id='qx'>点错了</a></div></div></div>";
            if (content.sfqp == false) {
                $("body").append(tsk);
                divCenter(content.direction);
                mouseDown();
                $("#qd").click(function () {
                    removeDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.yes)()
                });
                $("#qx,#cha").click(function () {
                    removeDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.no)()
                })
            } else {
                $(parent.window.document.body).append(tsk);
                divCenterIframe(content.direction);
                mouseDownifame();
                $(parent.window.document.body).find("#qd").click(function () {
                    removeQpDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.yes)()
                });
                $(parent.window.document.body).find("#qx,#cha").click(function () {
                    removeQpDhk();
                    this.content = $.extend({}, zhi, content);
                    (this.content.no)()
                })
            }
        }, tContent: function (options) {
            var tcContentDiv = '<div class="zhezhao"></div>' + '<div class="popup">' + '	<div class="popup-title">' + '		<div class="title-left">' + options.title + "</div>" + '		<div class="title-right" id="sen" title=\"关闭\">×</div>' + "	</div>" + '	<div class="popup-content"><iframe></iframe></div>' + "</div>";
            $("body").append(tcContentDiv);
            $(".popup").width(options.width).height(options.height);
            $(".popup .popup-content").height(options.height - 35);
            $(".popup .popup-content iframe").attr("src", options.url);
            $(".popup .popup-title .title-right").click(function () {
                $("body").find(".zhezhao,.popup").remove()
            })
        }
    })
});

function mouseDown() {
    $(".alert .alertTitle").mousedown(function (e) {
        type = true;
        var e = e || window.event;
        startTop = e.clientY;
        startLeft = e.clientX;
        divTop = $(".alert").offset().top + 90;
        divLeft = $(".alert").offset().left + 180
    });
    $(document).mouseup(function () {
        type = false
    });
    $(document).mousemove(function (e) {
        if (type) {
            var e = e || window.event;
            var left = divLeft + (e.clientX - startLeft);
            var top = divTop + (e.clientY - startTop);
            if (left < 180) {
                left = 180
            }
            if (top < 90) {
                top = 90
            }
            if (left + 180 > $(window).width()) {
                left = $(window).width() - 180
            }
            if (top + 90 > $(window).height()) {
                top = $(window).height() - 90
            }
            $(".alert").css({"left": left + "px", "top": top + "px"})
        }
    })
}

function mouseDownifame() {
    $(parent.window.document.body).find(".alert .alertTitle").mousedown(function (e) {
        type = true;
        var e = e || window.event;
        startTop = e.clientY;
        startLeft = e.clientX;
        divTop = $(parent.window.document.body).find(".alert").offset().top + 90;
        divLeft = $(parent.window.document.body).find(".alert").offset().left
    });
    $(parent.window.document).mouseup(function () {
        type = false
    });
    $(parent.window.document).mousemove(function (e) {
        if (type) {
            var e = e || window.event;
            var left = divLeft + (e.clientX - startLeft);
            var top = divTop + (e.clientY - startTop);
            if (left < 180) {
                left = 180
            }
            if (top < 90) {
                top = 90
            }
            if (left + 180 > $(parent.window).width()) {
                left = $(parent.window).width() - 180
            }
            if (top + 90 > $(parent.window).height()) {
                top = $(parent.window).height() - 90
            }
            $(parent.window.document.body).find(".alert").css({"left": left + "px", "top": top + "px"})
        }
    })
}

function resize() {
    $(window).resize(function () {
        if ($(".alert").length > 0) {
            $(".alert").animate({"top": $(window).height() / 2 + "px"})
        }
    })
}

function divCenter(type) {
    var left = $(window).width() / 2;
    var top = $(window).height() / 2;
    if (type == "left") {
        $(".alert").css({"left": "0", "top": "50%"}).animate({"left": left + "px"})
    } else {
        if (type == "top") {
            $(".alert").css({"top": "0", "left": "50%"}).animate({"top": top + "px"})
        } else {
            if (type == "right") {
                $(".alert").css({"right": "0", "top": "50%"}).animate({"right": left - 180 + "px"})
            } else {
                $(".alert").css({"bottom": "0", "left": "50%"}).animate({"bottom": top - 90 + "px"})
            }
        }
    }
    resize()
}

function divCenterIframe(type) {
    var left = $(parent.window).width() / 2;
    var top = $(parent.window).height() / 2;
    if (type == "left") {
        $(parent.window.document.body).find(".alert").css({"left": "0", "top": "50%"}).animate({"left": left + "px"})
    } else {
        if (type == "top") {
            $(parent.window.document.body).find(".alert").css({"top": "0", "left": "50%"}).animate({"top": top + "px"})
        } else {
            if (type == "right") {
                $(parent.window.document.body).find(".alert").css({
                    "right": "0",
                    "top": "50%"
                }).animate({"right": left - 180 + "px"})
            } else {
                $(parent.window.document.body).find(".alert").css({
                    "bottom": "0",
                    "left": "50%"
                }).animate({"bottom": top - 90 + "px"})
            }
        }
    }
    resize()
}

function removeDhk() {
    $(".alert").animate({"height": "0px"}, function () {
        $(".mask").remove();
        $(".alert").remove()
    })
}

function removeQpDhk() {
    $(parent.window.document.body).find(".alert").animate({"height": "0px"}, function () {
        $(parent.window.document.body).find(".mask").remove();
        $(parent.window.document.body).find(".alert").remove()
    })
};
