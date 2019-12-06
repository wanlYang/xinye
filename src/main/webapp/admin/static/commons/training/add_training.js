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
			url: getRealPath() + '/admin/training/upload/content/img', //文件上传的接口地址
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
	form.on("submit(addNews)", function(data) {
		 if(!tempImg){
		        top.layer.msg("请上传简图!!", {
		        icon:5,
		        time: 1500,
		        anim: 6,
		        });
		        return false;
		 }
		var index = top.layer.msg('数据提交中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		// 实际使用时的提交信息
		data.field.content = editor.getValue();
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/training/add/submit",
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
   	 elem: '#editimg'
        , url: getRealPath() + "/admin/training/upload/img" //必填项
        , method: ''  //可选项。HTTP类型，默认post
        , size:300
        , accept: 'images'
        , acceptMime: 'image/*'
        , choose: function (obj) { //obj参数包含的信息，跟 choose回调完全一致，可参见上文。
        	//预读本地文件示例，不支持ie8
            obj.preview(function(index, file, result){
              $('#newsImg').attr('src', result); //图片链接（base64）
              $("#sub").show();
              tempImg = false;
				setTimeout(function() {

				}, 500)
            });
        },
		auto: false, //选择文件后不自动上传
	    bindAction: '#sub',
        done: function(result){
       	var newsImg = $("#newsImg");
       	newsImg[0].src = result.data.src;
            layer.msg(result.msg,{icon: 1});
        	tempImg = true;
            $("#saveimg").val(result.data.src_save);
			$("#sub").hide();
         }
         ,error: function(){
              layer.msg("上传失败");
        }
   });
})