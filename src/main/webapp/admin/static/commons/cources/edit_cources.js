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
	form.on("submit(editCources)", function(data) {

		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		// 实际使用时的提交信息
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/cources/update",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.close(index);
						top.layer.msg("修改成功！");
						layer.closeAll("iframe");
						parent.location.reload();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("修改失败！" + result.message);
				}
			}
		});
		return false;
	})
	upload.render({
		elem: '#cources_img'
		, url: getRealPath() + "/admin/cources/upload/img" //必填项
		, method: ''  //可选项。HTTP类型，默认post
		, accept: 'images'
		, size:300
		, acceptMime: 'image/*'
		, before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			//预读本地文件示例，不支持ie8
		}
		,choose:function (obj) {
		}
		,done: function(result){
			layer.msg(result.msg,{icon: 1});
			$("#courcesImgVal").val(result.data.src);
			$("#cources_img_view")[0].src = result.data.src
		}
		,error: function(){
			layer.msg("上传失败");
		}
	});
})