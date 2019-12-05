layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#informationList',
		url: getRealPath() + '/admin/information/list/get',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "informationListTable",
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
					title: '资讯标题',
					align: "center"
				},
				{
					field: 'photo',
					title: '资讯简图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath() + '/'+d.photo+"' class='cover' alt=''/>";
					}
				},
				{
					field: 'time',
					title: '发布时间',
					align: 'center',
					templet: function(d) {
						return Format(d.time,"yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					field: 'content',
					title: '大致内容',
					align: 'center',
					templet: function(d) {
						return d.content;
					}
				},
				{
					field: 'person',
					title: '资讯所属',
					align: 'center',
					templet: function(d) {
						if (d.person == 1){
							return "行业动态"
						}
						if (d.person == 2){
							return "公司新闻"
						}
						if (d.person == 3){
							return "健康知识"
						}
						return "异常";
					}
				},
				{
					title: '操作',
					templet: '#informationListBar',
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
	function addInformation() {
		var index = layui.layer.open({
			title: "添加资讯",
			type: 2,
			content: getRealPath() + "/admin/topshow/information/add",
			success: function(layero, index) {
				setTimeout(function() {
					layui.layer.tips('点击此处返回资讯列表', '.layui-layer-setwin .layui-layer-close', {
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
		addInformation();
	})
	// 列表操作
	table.on('tool(informationList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "edit"){
			var newsIndex = layui.layer.open({
				title: "编辑资讯",
				type: 2,
				content: getRealPath() + "/admin/topshow/information/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					$.ajax({
						type: "POST",
						async:false,
						url: getRealPath() + "/admin/information/get",
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
			layui.layer.full(newsIndex);
			window.sessionStorage.setItem("newsIndex", newsIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("newsIndex"));
			})
		} else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将此资讯的所有信息清空!<br/>确定删除此资讯?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/information/del/submit",{id:data.id},function(result){
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