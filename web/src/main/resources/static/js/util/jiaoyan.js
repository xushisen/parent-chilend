//数字
var num = new RegExp(/^[0-9]*$/);
//手机号
var phone = new RegExp(/^(0|86|17951)?(13[0-9]|15[012356789]|166|17[3678]|18[0-9]|14[57])[0-9]{8}$/);
//只能是英文字母和数字
var englishNum = new RegExp("^[0-9a-zA-Z]+$");
// 身份证
var carId = new RegExp(/(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/);
// 非0的正整数
var positive = new RegExp(/^\+?[1-9][0-9]*$/);
function jiaoyan() {
	var status = true;
	$("input").each(
			function() {
                var name = $(this).parent().prev().text().replace("*","");
				var ischeck = $(this).data("check");
				if (ischeck) {
					var check = ischeck.split(",");
					// 循环校验
					for ( var i = 0; i < check.length; i++) {
						//var required = "", number = "";
						if (check[i] == "required") {
							if (replaceTrim($(this).val()) == "") {
								showError(name.substring(0,name.length - 1)+ "不能为空");
								status = false;
								return false;
							}
						} else if (check[i] == "number") {
							if (!num.test($(this).val())) {
								showError(name.substring(0,name.length - 1)+ "格式不正确");
								status = false;
								return false;
							}
						} else if (check[i] == "phone") {
							if (!phone.test($(this).val())) {
								showError(name.substring(0,name.length - 1)+ "格式不正确");
								status = false;
								return false;
							}
						} else if (check[i] == "englishNum") {
							if (!englishNum.test($(this).val())) {
								showError(name.substring(0,name.length - 1)+ "只能输入数字和英文");
								status = false;
								return false;
							}
						} else if (check[i] == "carId") {
                            if (!carId.test($(this).val())) {
                                showError(name.substring(0,name.length - 1)+ "格式不正确");
                                status = false;
                                return false;
                            }
                        } else if (check[i] == "positive"){
                            if (!positive.test($(this).val())) {
                                showError(name.substring(0,name.length - 1)+ "只能输入非0的正整数");
                                status = false;
                                return false;
                            }
						}
					}
				}
                // 校验长度 如果有
                var dataLenght = $(this).data("length");
                // 有长度校验  先校验长度
                if(!isUndefined(dataLenght)){
                    if ($(this).val().length > dataLenght){
                        showError(name.substring(0,name.length - 1)+ "长度最长为"+dataLenght);
                        status = false;
                        return false;
                    }
                }
			});
	return status;
}

function showError(content){
	$.ts({
		"content" :content,
		"direction" : "around",
		"code" : "2"
	});
}