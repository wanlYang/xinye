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
	var tempImg_m = true;
	var tempImg_p = true;
	// 监听表单
	form.on("submit(addTeacher)", function(data) {
		if(tempImg_m && tempImg_p){
			top.layer.msg("请上传简图!!", {
				icon:5,
				time: 1500,
				anim: 6,
				shade: 0,
				shadeClose: true //开启遮罩关闭
			});
			return false;
		}
		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		// 实际使用时的提交信息
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/teacher/insert",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.close(index);
						top.layer.msg("导师添加成功！");
						layer.closeAll("iframe");
						parent.location.reload();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("导师添加失败！" + result.message);
				}
			}
		});
		return false;
	})
	upload.render({
		elem: '#teacher_m'
		, url: getRealPath() + "/admin/teacher/upload/img" //必填项
		, method: ''  //可选项。HTTP类型，默认post
		, accept: 'images'
		, size:3000
		, acceptMime: 'image/*'
		, before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			//预读本地文件示例，不支持ie8
		}
		,choose:function (obj) {

		}
		,done: function(result){
			layer.msg(result.msg,{icon: 1});
			tempImg_m = false;
			$("#teacherImg_m").val(result.data.src);
			$("#mol")[0].src = result.data.src;
		}
		,error: function(){
			layer.msg("上传失败");
		}
	});
	upload.render({
		elem: '#teacher_p'
		, url: getRealPath() + "/admin/teacher/upload/img" //必填项
		, method: ''  //可选项。HTTP类型，默认post
		, accept: 'images'
		, size:3000
		, acceptMime: 'image/*'
		, before: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
			//预读本地文件示例，不支持ie8
		}
		,choose:function (obj) {
		}
		,done: function(result){
			layer.msg(result.msg,{icon: 1});
			tempImg_m = false;
			$("#teacherImg_p").val(result.data.src);
			$("#pc")[0].src = result.data.src
		}
		,error: function(){
			layer.msg("上传失败");
		}
	});
})