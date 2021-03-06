﻿layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	var tableIns = table.render({
		elem: '#trainingList',
		url: getRealPath() + '/admin/training/list/get',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "trainingListTable",
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
					title: '舞蹈名称',
					align: "center"
				},
				{
					field: 'photo',
					title: '舞蹈简图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath() + '/'+d.photo+"' class='cover'/>";
					}
				},
				{
					field: 'describe',
					title: '内容概述',
					align: 'center',
					templet: function(d) {
						return d.content;
					}
				},
				{
					title: '操作',
					templet: '#trainingListBar',
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
	function addTraining() {
		var index = layui.layer.open({
			title: "添加舞种",
			type: 2,
			content: getRealPath() + "/admin/dancetraining/add",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				var iframeWindow = window[layero.find('iframe')[0]['name']];
				setTimeout(function() {
					layui.layer.tips('点击此处返回培训项目列表', '.layui-layer-setwin .layui-layer-close', {
						tips: 3,
						tipsMore: true
					});
					layui.layer.tips('选择图片后点击右边上传按钮上传!', body.find("#sub"),{tips:1,tipsMore:true});
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
		addTraining();
	})
	// 列表操作
	table.on('tool(trainingList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "edit"){
			var trainingIndex = layui.layer.open({
				title: "编辑舞蹈",
				type: 2,
				content: getRealPath() + "/admin/dancetraining/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					body.find(".name").val(data.name);
					body.find("#saveimg").val(data.photo);
					$.ajax({
						type: "POST",
						async:false,
						url: getRealPath() + "/admin/training/get",
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
					body.find("#newsImg")[0].src = getRealPath() + data.photo;
					if (typeof(iframeWindow.layui.form) != "undefined") {
						iframeWindow.layui.form.render();
					}
					setTimeout(function() {
						layui.layer.tips('点击此处返回列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3,
							tipsMore: true
						});
						layui.layer.tips('选择图片后点击右边上传按钮上传!', body.find("#sub"),{tips:1,tipsMore:true});
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
		} else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将舞蹈的所有信息清空!<br/>确定删除?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/training/del/submit",{id:data.id},function(result){
					if(result.status == 200){
						obj.del();// 删除缓存
						top.layer.msg(result.message);
					}else{
						top.layer.msg("删除失败!");
					}
				},"json");
				layer.close(index);
			});
		} else if(layEvent === 'preview') {//显示大图
            preview_img(getRealPath() + "/"+data.danceImg);
        }
	});
})