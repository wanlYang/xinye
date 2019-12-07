layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	var tableIns = table.render({
		elem: '#activityList',
		url: getRealPath() + '/admin/activity/select',
		cellMinWidth: 95,
		method: "POST",
		height: "full-125",
		id: "activityListTable",
		cols: [
				[
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
				},
				{
					field: 'activeName',
					title: '活动名称',
					align: "center"
				},
				{
					field: 'activePhoto',
					title: '活动简图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath() + '/'+d.activePhoto+"' class='cover'/>";
					}
				},
				{
					field: 'activeTime',
					title: '时间',
					align: 'center',
					templet: function(d) {
						return Format(d.activeTime,"yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					field: 'activeContent',
					title: '内容概述',
					align: 'center',
					templet: function(d) {
						return d.activeContent;
					}
				},
				{
					field: 'activeState',
					title: '是否发布',
					align: 'center',
					templet: function(d) {
						if (d.activeState=='1'){
							return "已发布";
						}
						if (d.activeState =='2'){
							return "未发布";
						}
						return "异常";
					}
				},
				{
					title: '操作',
					templet: '#activityListBar',
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
	function addActivity () {
		var index = layui.layer.open({
			title: "添加活动",
			type: 2,
			content: getRealPath() + "/admin/activity/add",
			success: function(layero, index) {
				var body = layui.layer.getChildFrame('body', index);
				var iframeWindow = window[layero.find('iframe')[0]['name']];

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
		layui.layer.full(index);
		window.sessionStorage.setItem("index", index);
		// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
		$(window).on("resize", function() {
			layui.layer.full(window.sessionStorage.getItem("index"));
		})
	}
	$(".addNews_btn").click(function() {
		addActivity();
	})
	// 列表操作
	table.on('tool(activityList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "edit"){
			var activityIndex = layui.layer.open({
				title: "编辑活动",
				type: 2,
				content: getRealPath() + "/admin/activity/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					body.find(".activeName").val(data.activeName);
					body.find("#activityImgVal").val(data.activePhoto);
					body.find("#test1").val(data.activeTime);
					$.ajax({
						type: "POST",
						async:false,
						url: getRealPath() + "/admin/activity/get",
						data: {"id":data.id},
						success: function(result) {
							if(result.status == 200) {
								body.find("#news_content").val(result.data.activeContent);
							} else {
								top.layer.close(index);
								top.layer.msg("获取失败！" + result.message);
							}
						}
					});
					body.find("#activeState").each(function() {
						// this代表的是<option></option>，对option再进行遍历
						$(this).children("option").each(function() {
							// 判断需要对那个选项进行回显
							if (this.value == data.activeState) {
								// 进行回显
								$(this).attr("selected","selected");
							}
						});
					})
					body.find("#activity_img_view")[0].src = getRealPath() + data.activePhoto;

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
			layui.layer.full(activityIndex);
			window.sessionStorage.setItem("activityIndex", activityIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("activityIndex"));
			})
		}else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将活动的所有信息清空!<br/>确定删除?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/activity/delete",{id:data.id},function(result){
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