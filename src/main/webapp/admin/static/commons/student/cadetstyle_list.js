layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	var tableIns = table.render({
		elem: '#cadetstyleList',
		url: getRealPath() + '/admin/student/list/get',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "cadetstyleListTable",
		cols: [
				[
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
				},
				{
					field: 'name',
					title: '标题名称',
					align: 'center',
					templet: function(d) {
						return d.name;
					}
				},
				{
					field: 'photo',
					title: '封面简图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath() + '/'+d.photo+"' class='cover' alt=''/>";
					}
				},
				{
					field: 'content',
					title: '内容概述',
					align: 'center',
					templet: function(d) {
						return d.content;
					}
				},
				{
					title: '操作',
					templet: '#cadetstyleListBar',
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
	// 添加资讯
	function addStudent() {
		var index = layui.layer.open({
			title: "添加学员风采",
			type: 2,
			content: getRealPath() + "/admin/student/add",
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
		addStudent();
	})
	// 列表操作
	table.on('tool(cadetstyleList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "edit"){
			var newsIndex = layui.layer.open({
				title: "编辑",
				type: 2,
				content: getRealPath() + "/admin/student/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					$.ajax({
						type: "POST",
						async:false,
						url: getRealPath() + "/admin/student/get",
						data: {"id":data.id},
						success: function(result) {
							if(result.status == 200) {
                                body.find("#news_content").val(result.data.content);
							} else {
								top.layer.close(index);
								top.layer.msg("获取失败！" + result.message);
							}
						}
					});

					body.find(".name").val(data.name);
					body.find("#saveimg").val(data.photo);
					body.find("#newsImg")[0].src = getRealPath() + data.photo;


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
			layui.layer.full(newsIndex);
			window.sessionStorage.setItem("newsIndex", newsIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("newsIndex"));
			})
		} else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将此学员风采的所有信息清空!<br/>确定删除?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/student/del/submit",{id:data.id},function(result){
					if(result.status == 200){
						obj.del();// 删除缓存
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