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
	var temp_data;
	var flag = false;
	// 监听表单
	form.on("submit(addVideo)", function(data) {
		temp_data = data;
		if (flag) {
			document.getElementById("ADD").click()
			document.getElementById("ADDI").click()
		}else{
			top.layer.msg("请选择图片或视频!");
		}
		return false;
	})
	//上传视频
	var videoindex;
	upload.render({
		size:512000,
	    elem: '#editvideo',
	    auto: false, //选择文件后不自动上传
	    bindAction: '#ADD',
	    method:"post",
	    choose:function(object){
	    	top.layer.msg("选择成功!")
	    	$("#addVideo").text("点击上传").removeAttr("disabled").removeClass("layui-disabled");
	    },
	    before:function(object){
	    	videoindex = top.layer.msg('视频图片上传中,请耐心等待', {
				icon: 16,
				time: false,
				shade: 0.8
			});
	    },
	    accept: 'video',
	    url: getRealPath() + '/upload/up',
	    done: function(result){
	    	$("#local").val(result.qiniuUrl);
	    	temp_data.field.local = result.qiniuUrl;
	    	top.layer.msg(result.info);
	    	submit_form(temp_data);
	    	setTimeout(function() {
	    		top.layer.close(videoindex);
		    	layer.closeAll("iframe");
				parent.location.reload();
			}, 500);
	    },
	    error: function(){
	    	top.layer.msg("上传失败!");
	    }
	});
	//缩略图上传
	upload.render({
		size:300,
	    elem: '#editimg',
	    auto: false, //选择文件后不自动上传
	    bindAction: '#ADDI',
	    method:"post",
	    url: getRealPath() + '/upload/img/up',
	    accept:"images",
	    choose:function(object){
	    	top.layer.msg("选择成功!");
	    	flag = true;
	    },
	    done: function(result){
	    	$("#video_img").val(result.qiniuUrl);
	    	temp_data.field.video_img = result.qiniuUrl;
	    },
	    error: function(){
	    	top.layer.msg("上传失败!");
	    }
	});
	//提交数据方法C2b8fn4-lRQHc0XgTy98wsTXcOoHUqowVukDT6WQ
	//1gMkVc99HlF9TDA1DJR9xjUm8D7uuswATml30m4Z
	//http://video.jytopshow.com/01de53a812eda5989ad7da8084bead42.mp4
	//jinyanwudao
	function submit_form(data){
		$.ajax({
			type: "POST",
			url: getRealPath() + "/admin/video/add/submit",
			data: data.field,
			success: function(result) {
				if(result.status == 200) {
					setTimeout(function() {
						top.layer.msg("添加成功！");
					}, 500);
				} else {
					top.layer.close(index);
					top.layer.msg("添加失败！" + result.message);
				}
			}
		});
	}
})