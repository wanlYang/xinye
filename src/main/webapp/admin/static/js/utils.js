layui.use(['jquery','layer'], function () {
    var $ = layui.jquery,
    	layer = layui.layer;
    function checkHtml(htmlStr) {
    	var reg = /<[^>]+>/g;
    	return reg.test(htmlStr);
    }
    $(document).ajaxComplete(function (event, obj, settings) {
    	if (checkHtml(obj.responseText)) {
			return false;
		}
        var resultCom = eval("(" + obj.responseText + ")");
        if (resultCom.status == -1001) {
        	var index = layer.open({
                type: 1
                ,title: '提示'
                ,shade: 0
                ,content: '<div style="padding:20px;line-height: 20px;font-weight:600;background: #c3fbcf;"><div style="font-size: 16px;">对不起出现了异常!<br>异常信息:</div><div style="word-wrap: break-word;word-break: normal;word-break:break-all;">'+resultCom.message+'</div></div>'
                ,btn: ['关闭全部'] // 只是为了演示
                ,yes: function(){
                	layer.close(index);
                	layer.closeAll();
                }
                ,zIndex: layer.zIndex // 重点1
                ,success: function(layero){
                  layer.setTop(layero); // 重点2
                }
            });
        }
        if (resultCom.message == 'timeout') { // 超时标识
            parent.location.href = getRealPath() + '/admin/login'; // 跳转到登录页面
        }
        if (resultCom.status == 401) { // 超时标识
        	layer.msg(resultCom.message)
        	setTimeout(function() {
        		parent.location.href = getRealPath() + '/admin/login'; // 跳转到登录页面
			}, 500);
            
        }
    });
})
//显示大图方法
function preview_img(url) {
    var img = new Image();
    img.src = url;
    var height = img.height + 44; //获取图片高度
    var width = img.width; //获取图片宽度
    var imgHtml = "<img src='" + url + "' />";
    //弹出层
    top.layer.msg("稍后会自动消失!");
    layer.open({
        type: 1,
        shade: 0.8,
        offset: 'auto',
        time:3000,
        area: [width + 'px',height+'px'],
        shadeClose:true,//点击外围关闭弹窗
        scrollbar: false,//不现实滚动条
        title: "图片预览", //不显示标题
        content: imgHtml, //捕获的元素，注意：最好该指定的元素要存放在body最外层，否则可能被其它的相对元素所影响

    });
}
function getRealPath() {
    // 获取当前网址，如： http://localhost:8083/myproj/view/my.jsp
    var curWwwPath = window.document.location.href;
    // 获取主机地址之后的目录，如： myproj/view/my.jsp
    var pathName = window.document.location.pathname;
    var pos = curWwwPath.indexOf(pathName);
    // 获取主机地址，如： http://localhost:8083
    var localhostPaht = curWwwPath.substring(0, pos);
    // 获取带"/"的项目名，如：/myproj
    var projectName = pathName
        .substring(0, pathName.substr(1).indexOf('/') + 1);
    // 得到了 http://localhost:8083/myproj
    var realPath = localhostPaht;
    //var realPath = localhostPaht;
    return realPath;
}

/**
 * 
 * @param url
 *            图片路径
 * @param ext
 *            图片格式
 * @param callback
 *            结果回调
 */
function getUrlBase64(url, ext, callback) {
    var canvas = document.createElement("canvas");   // 创建canvas DOM元素
    var ctx = canvas.getContext("2d");
    var img = new Image;
    img.crossOrigin = 'Anonymous';
    img.src = url;
    img.onload = function () {
        canvas.height = 500; // 指定画板的高度,自定义
        canvas.width = 500; // 指定画板的宽度，自定义
        ctx.drawImage(img, 0, 0, 500, 500); // 参数可自定义
        var dataURL = canvas.toDataURL("image/" + ext);
        callback.call(this, dataURL); // 回掉函数获取Base64编码
        canvas = null;
    };
}

var imunityCookie = {
    set: function (key, val, time) {// 设置cookie方法
        var date = new Date(); // 获取当前时间
        var expiresDays = time;  // 将date设置为n天以后的时间
        date.setTime(date.getTime() + expiresDays * 24 * 3600 * 1000); // 格式化为cookie识别的时间
        document.cookie = key + "=" + val + ";expires=" + date.toGMTString() + ";path=/";  // 设置cookie
    },
    get: function (key) {// 获取cookie方法
        /* 获取cookie参数 */
        var getCookie = document.cookie.replace(/[ ]/g, "");  // 获取cookie，并且将获得的cookie格式化，去掉空格字符
        var arrCookie = getCookie.split(";")  // 将获得的cookie以"分号"为标识
												// 将cookie保存到arrCookie的数组中
        var tips;  // 声明变量tips
        for (var i = 0; i < arrCookie.length; i++) {   // 使用for循环查找cookie中的tips变量
            var arr = arrCookie[i].split("=");   // 将单条cookie用"等号"为标识，将单条cookie保存为arr数组
            if (key == arr[0]) {  // 匹配变量名称，其中arr[0]是指的cookie名称，如果该条变量为tips则执行判断语句中的赋值操作
                tips = arr[1];   // 将cookie的值赋给变量tips
                break;   // 终止for循环遍历
            }
        }
        return tips;
    },
    delete: function (key) { // 删除cookie方法
        var date = new Date(); // 获取当前时间
        date.setTime(date.getTime() - 10000); // 将date设置为过去的时间
        document.cookie = key + "=v; expires =" + date.toGMTString() + ";path=/";// 设置cookie
    }
}

function Format(datetime, fmt) {
    if (parseInt(datetime) == datetime) {
        if (datetime.length == 10) {
            datetime = parseInt(datetime) * 1000;
        } else if (datetime.length == 13) {
            datetime = parseInt(datetime);
        }
    }
    datetime = new Date(datetime);
    var o = {
        "M+": datetime.getMonth() + 1, // 月份
        "d+": datetime.getDate(), // 日
        "h+": datetime.getHours(), // 小时
        "m+": datetime.getMinutes(), // 分
        "s+": datetime.getSeconds(), // 秒
        "q+": Math.floor((datetime.getMonth() + 3) / 3), // 季度
        "S": datetime.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(fmt))
        fmt = fmt.replace(RegExp.$1, (datetime.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(fmt))
            fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function checkIsAdmin(roles) {
    for (var i = 0;i <roles.length;i++){
        if (roles[i].name == "ROLE_ADMIN"){
            return true;
            break;
        }
    }
    return false;
}
function checkIsSuperAdmin(roles) {
    for (var i = 0;i <roles.length;i++){
        if (roles[i].name == "ROLE_SUPER_ADMIN"){
            return true;
            break;
        }
    }
    return false;
}
var HtmlUtil = {
    /* 1.用浏览器内部转换器实现html转码 */
    htmlEncode:function (html){
        // 1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement ("div");
        // 2.然后将要转换的字符串设置为这个元素的innerText(ie支持)或者textContent(火狐，google支持)
        (temp.textContent != undefined ) ? (temp.textContent = html) : (temp.innerText = html);
        // 3.最后返回这个元素的innerHTML，即得到经过HTML编码转换的字符串了
        var output = temp.innerHTML;
        temp = null;
        return output;
    },
    /* 2.用浏览器内部转换器实现html解码 */
    htmlDecode:function (text){
        // 1.首先动态创建一个容器标签元素，如DIV
        var temp = document.createElement("div");
        // 2.然后将要转换的字符串设置为这个元素的innerHTML(ie，火狐，google都支持)
        temp.innerHTML = text;
        // 3.最后返回这个元素的innerText(ie支持)或者textContent(火狐，google支持)，即得到经过HTML解码的字符串了。
        var output = temp.innerText || temp.textContent;
        temp = null;
        return output;
    },
    /* 3.用正则表达式实现html转码 */
    htmlEncodeByRegExp:function (str){  
         var s = "";
         if(str.length == 0) return "";
         s = str.replace(/&/g,"&amp;");
         s = s.replace(/</g,"&lt;");
         s = s.replace(/>/g,"&gt;");
         s = s.replace(/ /g,"&nbsp;");
         s = s.replace(/\'/g,"&#39;");
         s = s.replace(/\"/g,"&quot;");
         return s;  
   },
   /* 4.用正则表达式实现html解码 */
   htmlDecodeByRegExp:function (str){  
         var s = "";
         if(str.length == 0) return "";
         s = str.replace(/&amp;/g,"&");
         s = s.replace(/&lt;/g,"<");
         s = s.replace(/&gt;/g,">");
         s = s.replace(/&nbsp;/g," ");
         s = s.replace(/&#39;/g,"\'");
         s = s.replace(/&quot;/g,"\"");
         return s;  
   }
};