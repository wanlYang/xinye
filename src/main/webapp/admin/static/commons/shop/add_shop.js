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
	form.on("submit(addShop)", function(data) {
		 if(!tempImg){
		        top.layer.msg("请上传简图!!", {
		        icon:5,
		        time: 1500,
		        anim: 6,
		        shade: 0,
		        shadeClose: false //开启遮罩关闭
		        });
		        return false;
		 }
		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		 console.log(data.field)
		// 实际使用时的提交信息
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/shop/add/submit",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.close(index);
						top.layer.msg("添加成功！");
						layer.closeAll("iframe");
						parent.location.reload();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("添加失败！" + result.message);
				}
			}
		});
		return false;
	})
	upload.render({
		elem: '#shop_img'
		, url: getRealPath() + "/admin/shop/upload/img" //必填项
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
			tempImg = true;
			$("#shopImgVal").val(result.data.src);
			$("#shop_img_view")[0].src = result.data.src
		}
		,error: function(){
			layer.msg("上传失败");
		}
	});
})