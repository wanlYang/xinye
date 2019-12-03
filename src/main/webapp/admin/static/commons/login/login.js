layui.use(['form', 'layer', 'jquery'], function () {
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        		$ = layui.jquery;

//    if (imunityCookie.get("adminHeadImg")) {
//        getUrlBase64(getRealPath() + imunityCookie.get("adminHeadImg"), "jpg", function (base64) {
//            $("#loginHeadImg").attr("src", base64);
//        });
//    }
//
//    if (imunityCookie.get("QQ_OAUTH2_SUCCESS")) {
//        $("#moreLandings").html("<a href=\"javascript:;\" id=\"qqOauth2LoginBtn\" class=\"seraph icon-qq layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4\"></a>" +
//            "<a href=\"javascript:;\" class=\"seraph icon-wechat layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4\"></a>" +
//            "<a href=\"javascript:;\" class=\"seraph icon-sina layui-col-xs4 layui-col-sm4 layui-col-md4 layui-col-lg4\"></a>");
//    }else{
//        $("#moreLandings").html("");
//    }
//    $(".loginBody .seraph:not(':first')").click(function () {
//        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的?还是老老实实的找管理员去注册吧", {
//            time: 5000
//        });
//    })
//    // qq登陆
//    $("#qqOauth2LoginBtn").click(function () {
//        $("#qqOauth2LoginForm").submit();
//    })

    // 获取验证码
//    $("#getcode").click(function () {
//        var url = getRealPath() + "/get/kaptcha/image";
//        $(this).attr("src", url);
//    })

    //登录按钮
    form.on("submit(login)", function (data) {
        $(this).text("登录中...").attr("disabled", "disabled").addClass("layui-disabled");
        var index = top.layer.msg('登录中,请稍候', {
            icon: 16,
            time: false,
            shade: 0.8
        });
        $.ajax({
            url: getRealPath() + "/admin/login/submit",
            type: 'POST',
            data: data.field,
            complete: function (XMLHttpRequest, textStatus) {
                layer.close(index);
                $("#admin_login").text("登陆").removeAttr("disabled").removeClass("layui-disabled");
            },
            success: function (data) {
            	console.log(data);
                if (data.status == 200) {
//                    imunityCookie.set("adminHeadImg", data.headImg, "24");
                    window.location.href = getRealPath() + "/admin/index";
                } else if (data.status == -2) {
                    layer.msg("账号或密码错误!", {
                        time: 1500,
                        anim: 6
                    });
//                    var url = getRealPath() + "/get/kaptcha/image";
//                    $("#getcode").attr("src", url);

                } else {
                    layer.msg(data.message, {
                        time: 1500,
                        anim: 6
                    })
//                    var url = getRealPath() + "/get/kaptcha/image";
//                    $("#getcode").attr("src", url);
                }
            },
            error: function () {
                layer.msg("出现错误,请尝试刷新页面!");
            }
        });
        return false;
    })
    //表单输入效果
    $(".loginBody .input-item").click(function (e) {
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function () {
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function () {
        $(this).parent().removeClass("layui-input-focus");
        if ($(this).val() != '') {
            $(this).parent().addClass("layui-input-active");
        } else {
            $(this).parent().removeClass("layui-input-active");
        }
    })
    // 判断身份失效跳转
    $(document).ready(function () {
        if (window != top) {
            top.location.href = location.href;
        }
    })
})
