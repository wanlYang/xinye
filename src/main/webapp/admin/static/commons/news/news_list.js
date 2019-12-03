layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#newsList',
		url: getRealPath() + '/admin/news/get/list',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "newsListTable",
		cols: [
				[	
//				{
//					type: "checkbox",
//					fixed: "left",
//					width: 50
//				},
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
				},
				{
					field: 'title',
					title: '新闻标题',
					align: "center"
				},
				{
					field: 'release_time',
					title: '发布时间',
					align: 'center',
					templet: function(d) {
						return Format(d.release_time,"yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					field: 'img',
					title: '新闻简图',
					event: 'preview',
                    style: 'cursor: pointer;',
					align: 'center',
					templet: function(d) {
						return "<img title='点击预览' src='"+ getRealPath() + '/'+d.img+"' class='cover'/>";
					}
				},
				{
					field: 'browse_volume',
					title: '浏览量',
					align: 'center',
					templet: function(d) {
						return d.browse_volume;
					}
				},
				{
					title: '操作',
					templet: '#newsListBar',
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
	// 添加培训项目
	function addNews() {
		var index = layui.layer.open({
			title: "添加新闻",
			type: 2,
			content: getRealPath() + "/admin/news/add",
			success: function(layero, index) {
				setTimeout(function() {
					layui.layer.tips('点击此处返回培训项目列表', '.layui-layer-setwin .layui-layer-close', {
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
		addNews();
	})
	// 列表操作
	table.on('tool(newsList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === "editinfo"){
			var newsIndex = layui.layer.open({
				title: "编辑新闻",
				type: 2,
				content: getRealPath() + "/admin/news/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					body.find("#news_content").val(data.content);
					body.find("#saveimg").val(data.img);
					body.find(".browse_volume").val(data.browse_volume);
					body.find(".title").val(data.title);
					body.find("#newsImg")[0].src = getRealPath() + data.img;
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
		}else if(layEvent === 'showinfo') { // 详情
			window.open(getRealPath() + "/news/detail/"+data.id);
		} else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将此新闻的所有信息清空!<br/>确定删除此新闻?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/news/del/submit",{id:data.id},function(result){
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
            preview_img(getRealPath() + "/"+data.img);
        }
	});
})