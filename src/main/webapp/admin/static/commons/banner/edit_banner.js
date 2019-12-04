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
	// 监听表单
	form.on("submit(editBanner)", function(data) {
		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		// 实际使用时的提交信息
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/banner/edit/submit",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.close(index);
						top.layer.msg("编辑成功！");
						layer.closeAll("iframe");
						parent.location.reload();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("编辑失败！" + result.message);
				}
			}
		});
		return false;
	})
    upload.render({
    	 elem: '#banner_m'
         , url: getRealPath() + "/admin/banner/upload/img" //必填项
         , method: ''  //可选项。HTTP类型，默认post
         , accept: 'images'
         , size:300
        ,data: {"flag":"banner_m"}
         , acceptMime: 'image/*'
         , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
             //预读本地文件示例，不支持ie8
         }
         ,choose:function (obj) {

        }
         ,done: function(result){
             layer.msg(result.msg,{icon: 1});
             $("#bannerImg_m").val(result.data.src);
			$("#mol")[0].src = result.data.src;
          }
          ,error: function(){
               layer.msg("上传失败");
         }
    });
    upload.render({
        elem: '#banner_p'
        , url: getRealPath() + "/admin/banner/upload/img" //必填项
        , method: ''  //可选项。HTTP类型，默认post
        , accept: 'images'
        , size:300
        ,data: {"flag":"banner_p"}
        , acceptMime: 'image/*'
        , before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
            //预读本地文件示例，不支持ie8
        }
        ,choose:function (obj) {
        }
        ,done: function(result){
            layer.msg(result.msg,{icon: 1});
			$("#bannerImg_p").val(result.data.src);

			$("#pc")[0].src = result.data.src
        }
        ,error: function(){
            layer.msg("上传失败");
        }
    });
})