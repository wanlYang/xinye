layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#videoList',
		url: getRealPath() + '/admin/video/select',
		cellMinWidth: 95,
		method: "POST",
		height: "full-125",
		id: "videoListTable",
		cols: [
				[	
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
				},
				{
					field: 'videoName',
					title: '标题',
					align: 'center',
					templet: function(d) {
						return d.videoName;
					}
				},
				{
					field: 'videoLink',
					title: '链接',
					align: 'center',
					templet: function(d) {
						return d.videoName;
					}
				},
				{
					field: 'videoPhoto',
					title: '缩略图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath()  +d.videoPhoto+"' class='cover'/>";
					}
				},
				{
					field: 'teacherName',
					title: '老师',
					align: 'center',
					templet: function(d) {
						return d.teacherName;
					}
				},
				{
					field: 'videoTime',
					title: '时间',
					align: 'center',
					templet: function(d) {
						return Format(d.videoTime,"yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					field: 'person',
					title: '所属',
					align: 'center',
					templet: function(d) {
						if (d.person == 1){
							return "外教视频";
						}
						if (d.person == 2){
							return "教师视频";
						}
						if (d.person == 3){
							return "学员视频";
						}
						return "未知";
					}
				},
				{
					title: '操作',
					templet: '#videoListBar',
					fixed: "right",
					align: "center"
				}
			]
		],
		text: {
			none: '暂无相关数据' // 默认：无数据。注：该属性为 layui 2.2.5 开始新增
		},
		response: {
			statusName: 'status', // 规定数据状态的字段名称，默认：code
			statusCode: 200, // 规定成功的状态码，默认：0
			msgName: 'message', // 规定状态信息的字段名称，默认：msg
			countName: 'count', // 规定数据总数的字段名称，默认：count
			dataName: 'data' // 规定数据列表的字段名称，默认：data
		},
		toolbar: true
	});
	// 添加
	function addVideo() {
		var index = layui.layer.open({
			title: "添加视频",
			type: 2,
			content: getRealPath() + "/admin/video/add",
			success: function(layero, index) {
				setTimeout(function() {
					layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3
					});
				}, 500)
			},
			end: function() {
				$(window).unbind("resize");
			}
		})
		layui.layer.full(index);
		window.sessionStorage.setItem("index", index);
		// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
		$(window).on("resize", function() {
			layui.layer.full(window.sessionStorage.getItem("index"));
		})
	}
	$(".addNews_btn").click(function() {
		addVideo();
	})
	// 列表操作
	table.on('tool(videoList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		if(layEvent === "edit"){
			var videoIndex = layui.layer.open({
				title: "编辑视频",
				type: 2,
				content: getRealPath() + "/admin/video/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find(".id").val(data.id);
					body.find(".showVideoImg_val").val(data.videoPhoto);
					body.find("#videoLink").val(data.videoLink);
					body.find(".videoName").val(data.videoName);
					body.find(".teacherName").val(data.teacherName);
					body.find("#showVideoImg")[0].src = getRealPath() + data.videoPhoto;
					body.find("#person").each(function() {
						// this代表的是<option></option>，对option再进行遍历
						$(this).children("option").each(function() {
							// 判断需要对那个选项进行回显
							if (this.value == data.person) {
								// 进行回显
								$(this).attr("selected","selected");
							}
						});
					})
					if (typeof(iframeWindow.layui.form) != "undefined") {
						iframeWindow.layui.form.render();
					}
					setTimeout(function() {
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					}, 500)
				},
				end: function() {
					$(window).unbind("resize");

				}
			})
			layui.layer.full(videoIndex);
			window.sessionStorage.setItem("videoIndex", videoIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("videoIndex"));
			})
		}else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将视频的所有信息清空!<br/>确定删除此视频?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/video/delete",{id:data.id},function(result){
					if(result.status == 200){
						obj.del();// 删除缓存
						location.reload();
						top.layer.msg(result.message);
					}else{
						top.layer.msg("删除失败!");
					}
				},"json");
				layer.close(index);
			});
		}
	});
})