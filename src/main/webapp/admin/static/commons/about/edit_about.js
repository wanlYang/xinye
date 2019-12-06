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

	Simditor.locale = 'zh-CN'; //设置中文
	var editor = new Simditor({
		textarea: $('#news_content'), //textarea的id
		placeholder: '这里输入内容...',
		toolbar: [
			'title',
			'bold',
			'italic',
			'underline',
			'strikethrough',
			'fontScale',
			'color',
			'|',
			'ol',
			'ul',
			'blockquote',
			'code',
			'table',
			'|',
			'link',
			'image',
			'hr',
			'|',
			'indent',
			'outdent',
			'alignment'
		], //工具条都包含哪些内容
		pasteImage: true, //允许粘贴图片
		defaultImage: getRealPath() + '/admin/static/simditor/images/image.png', //编辑器插入的默认图片，此处可以删除
		upload: {
			url: getRealPath() + '/admin/about/upload/content/img', //文件上传的接口地址
			params: null, //键值对,指定文件上传接口的额外参数,上传的时候随文件一起提交
			fileKey: 'upload_file', //服务器端获取文件数据的参数名
			connectionCount: 3,
			leaveConfirm: '正在上传文件',
		}
	});
	editor.uploader.on('uploadsuccess', (function(_this) {
		return function(e, file, result) {
			$img = file.img;
			$img.attr("alt",result.title);
		};
	})(this));
	form.verify({
		content : function(value){
			if(value.match(/^[ ]*$/)) {
				return "内容不能空!";
			}
		}
	})
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
						layer.closeAll("iframe");
						parent.location.reload();
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("修改失败！" + result.message);
				}

				layui.form.render();

			}
		});
		return false;
	})
})