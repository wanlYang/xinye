//获取系统时间
var newDate = '';
getLangDate();
//值小于10时，在前面补0
function dateFilter(date){
    if(date < 10){return "0"+date;}
    return date;
}
function getLangDate(){
    var dateObj = new Date(); //表示当前系统时间的Date对象
    var year = dateObj.getFullYear(); //当前系统时间的完整年份值
    var month = dateObj.getMonth()+1; //当前系统时间的月份值
    var date = dateObj.getDate(); //当前系统时间的月份中的日
    var day = dateObj.getDay(); //当前系统时间中的星期值
    var weeks = ["星期日","星期一","星期二","星期三","星期四","星期五","星期六"];
    var week = weeks[day]; //根据星期值，从数组中获取对应的星期字符串
    var hour = dateObj.getHours(); //当前系统时间的小时值
    var minute = dateObj.getMinutes(); //当前系统时间的分钟值
    var second = dateObj.getSeconds(); //当前系统时间的秒钟值
    var timeValue = "" +((hour >= 12) ? (hour >= 18) ? "晚上" : "下午" : "上午" ); //当前时间属于上午、晚上还是下午
    newDate = dateFilter(year)+"年"+dateFilter(month)+"月"+dateFilter(date)+"日 "+" "+dateFilter(hour)+":"+dateFilter(minute)+":"+dateFilter(second);
    document.getElementById("nowTime").innerHTML = ""+timeValue+"好.当前时间为： "+newDate+"　"+week;
    setTimeout("getLangDate()",1000);
}

layui.use(['form','element','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        element = layui.element;
        $ = layui.jquery;
    //上次登录时间【此处应该从接口获取，实际使用中请自行更换】
    $(".loginTime").html(newDate.split("日")[0]+"日</br>"+newDate.split("日")[1]);
    //icon动画
    $(".panel a").hover(function(){
        $(this).find(".layui-anim").addClass("layui-anim-scaleSpring");
    },function(){
        $(this).find(".layui-anim").removeClass("layui-anim-scaleSpring");
    })
    $(".panel a").click(function(){
        parent.addTab($(this));
    })
    //系统基本参数
//    if(window.sessionStorage.getItem("systemParameter")){
//        var systemParameter = JSON.parse(window.sessionStorage.getItem("systemParameter"));
//        fillParameter(systemParameter);
//    }else{
//        $.ajax({
//            url : getRealPath() + "/admin/manager/system_setting/get/parameter",
//			type : "GET",
//			dataType : "json",
//			success : function(result){
//				var index = top.layer.msg('数据获取中,请稍候',{icon: 16,time:false,shade:0.8});
//		        setTimeout(function(){
//		        	top.layer.close(index);
//		        	top.layer.msg("数据获取成功!");
//					fillParameter(result.data);
//		        },500);
//			}
//        })
//    }
//    //填充数据方法
//    function fillParameter(data){
//        //判断字段数据是否存在
//        function nullData(data){
//            if(data == '' || data == "undefined"){
//                return "未定义";
//            }else{
//                return data;
//            }
//        }
//        var indexFooter = $('.footer p span', parent.document);
//        indexFooter.text(data.websitePowerby);
// 		$(".version").text(data.websiteVersion);      //当前版本
//		$(".author").text(data.websiteAuthor);        //开发作者
//		$(".homePage").text(data.websiteHomePage);    //网站首页
//		$(".server").text(data.websiteServer);        //服务器环境
//		$(".dataBase").text(data.websiteDataBase);    //数据库版本
//		$(".maxUpload").text(data.websiteMaxUpload);  //最大上传限制
//    }
//
//     //最新文章列表
//     $.get(getRealPath() +"/admin/news/get",function(data){
//         var hotNewsHtml = '';
//         for(var i=0;i<5;i++){
//             hotNewsHtml += '<tr>'
//                 +'<td align="left"><a target="_blank" href="'+getRealPath()+ "/news/detail/" +data.data[i].id+'"> '+data.data[i].title+'</a></td>'
//                 +'<td>'+Format(data.data[i].release_time,"yyyy-MM-dd")+'</td>'
//                 +'</tr>';
//         }
//         $(".hot_news").html(hotNewsHtml);
//         $(".userAll span").text(data.length);
//     })
// //
//     //导师
//     $.get(getRealPath() +"/admin/teacher/get/count",{data:""},function(data){
//         $(".teacher span").text(data.count);
//     },"json")
//
//     //视频数量
//     $.get(getRealPath() +"/admin/video/get/count",function(data){
//         $(".video span").text(data.count);
//     },"json")
//
//     //舞蹈数量
//     $.get(getRealPath() +"/admin/training/get/count",function(data){
//         $(".training span").text(data.count);
//     },"json")
//
//     //咨询数量
//     $.get(getRealPath() +"/admin/news/get/count",function(data){
//         $(".news span").text(data.count);
//     },"json")
//
//     //留言总数
//     $.get(getRealPath() +"/admin/suggestion/get/count",function(data){
//         $(".suggestion span").text(data.count);
//     },"json")
})
