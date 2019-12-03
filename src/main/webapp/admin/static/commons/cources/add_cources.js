layui.config({
    base : getRealPath() + "/admin/static/"
}).extend({
    "croppers" : "cropper/croppers"
})
layui.use(['form', 'layer','upload', 'laydate','croppers'], function() {
	var form = layui.form
	layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		croppers = layui.croppers,
		upload = layui.upload,
		laydate = layui.laydate;
	var date = new Date();
	
	form.verify({});
	var tempImg = false;
	// 监听表单
//	form.on("submit(addTraining)", function(data) {
//		 if(!tempImg){
//		        top.layer.msg("请上传简图!!", {
//		        icon:5,
//		        time: 1500,
//		        anim: 6,
//		        shade: 0.2,
//		        shadeClose: true //开启遮罩关闭
//		        });
//		        return false;
//		 }
//		var index = top.layer.msg('数据提交中,请稍候', {
//			icon: 16,
//			time: false,
//			shade: 0.8
//		});
//		// 实际使用时的提交信息
//		$.ajax({
//			type: "POST",
//			url: getRealPath() + "/admin/training/add/submit",
//			data: data.field,
//			success: function(result) {
//				if(result.status == 200) {
//					setTimeout(function() {
//						top.layer.close(index);
//						top.layer.msg("添加成功！");
//						layer.closeAll("iframe");
//						parent.location.reload();
//					}, 500);
//				} else {
//					top.layer.close(index);
//					top.layer.msg("添加失败！" + result.message);
//				}
//			}
//		});
//		return false;
//	})
	// 创建一个头像上传组件
//    upload.render({
//        elem: '#editimg',
//        saveW: 500,// 保存宽度
//        saveH: 375,
//        mark: 4/3,// 选取比例
//        area: ['900', '700px'],// 弹窗宽度
//        url: getRealPath() + "/admin/training/upload/img",// 图片上传接口返回和(layui 的upload 模块)返回的JOSN一样
//        done: function(result){// 上传完毕回调
//        	var trainingImg = $("#trainingImg");
//        	trainingImg[0].src = result.data.src;
//            tempImg = true;
//            layer.msg(result.msg,{icon: 1});
//        }
//    });
    upload.render({
    	 elem: '#tableimg'
         , url: getRealPath() + "/admin/cources/upload/img" //必填项
         , method: ''  //可选项。HTTP类型，默认post
         ,size:300
         , accept: 'images'
         , acceptMime: 'image/*'
         , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
             //预读本地文件示例，不支持ie8
         }
         ,done: function(result){
        	 var tableImg = $("#tableImg");
        	 tableImg[0].src = result.data.src;
             layer.msg(result.msg,{icon: 1});
             setTimeout(function() {
				top.layer.msg("添加成功！");
				var index=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
				console.log(index)
	            parent.layer.close(index);
	            parent.location.reload();
			}, 500);
          }
          ,error: function(){
               layer.msg("上传失败");
         }
    });
})