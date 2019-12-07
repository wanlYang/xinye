layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#adminList',
		url: getRealPath() + '/admin/select',
		cellMinWidth: 95,
		method: "POST",
		height: "full-125",
		id: "adminListTable",
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
					title: '账号',
					align: 'center',
					templet: function(d) {
						return d.name;
					}
				},
				{
					field: 'password',
					title: '密码',
					align: 'center',
					templet: function(d) {
						return d.password;
					}
				},
				{
					title: '操作',
					templet: '#adminListBar',
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
	function addAdmin() {
		var index = layui.layer.open({
			title: "添加管理员",
			type: 2,
			content: getRealPath() + "/admin/add",
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
		addAdmin();
	})
	// 列表操作
	table.on('tool(adminList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
        if(layEvent === "edit"){
            var videoIndex = layui.layer.open({
                title: "编辑管理",
                type: 2,
                content: getRealPath() + "/admin/edit",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var iframeWindow = window[layero.find('iframe')[0]['name']];
                    body.find(".id").val(data.id);
                    body.find(".name").val(data.name);
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
			layer.confirm('确定删除?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/delete",{id:data.id},function(result){
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