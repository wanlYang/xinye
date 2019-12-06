layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	var tableIns = table.render({
		elem: '#aboutList',
		url: getRealPath() + '/admin/about/list/get',
		cellMinWidth: 95,
		method: "POST",
		height: "full-125",
		id: "aboutListTable",
		cols: [
				[
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
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
					templet: '#aboutListBar',
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
	// 列表操作
	table.on('tool(aboutList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "edit"){
			var trainingIndex = layui.layer.open({
				title: "编辑简介",
				type: 2,
				content: getRealPath() + "/admin/about/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					$.ajax({
						type: "POST",
						async:false,
						url: getRealPath() + "/admin/about/get",
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
					if (typeof(iframeWindow.layui.form) != "undefined") {
						iframeWindow.layui.form.render();
					}
					setTimeout(function() {
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3,
							tipsMore: true
						});
					}, 500)
				},
				end: function() {
					$(window).unbind("resize");
					
				}
			})
			layui.layer.full(trainingIndex);
			window.sessionStorage.setItem("trainingIndex", trainingIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("trainingIndex"));
			})
		}else if(layEvent === 'showinfo') { // 详情
			window.open(getRealPath() + "/training/detail/"+data.id);
		}
	});
})