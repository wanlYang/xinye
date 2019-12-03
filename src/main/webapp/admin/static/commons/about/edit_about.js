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
	form.on("submit(editCountent)", function(data) {
		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		// 实际使用时的提交信息
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/about/edit/submit",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.msg("修改成功！");
						update();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("修改失败！" + result.message);
				}
			}
		});
		return false;
	})
	update();
	function update() {
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/about/get",
			data: {'id':1},
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						$(".content").val(result.data.content);
						$("#id").val(result.data.id);
						top.layer.msg("获取成功！");
					}, 500);
				} else {
					top.layer.msg("修改失败！" + result.message);
				}
			}
		});
	}
})