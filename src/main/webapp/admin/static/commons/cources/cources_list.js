layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#courcesList',
		url: getRealPath() + '/admin/cources/select',
		cellMinWidth: 95,
		method: "POST",
		height: "full-125",
		id: "courcesListTable",
		cols: [
				[	
				{
					sort: true,
					field: "id",
					title: "ID",
					align: "center",
				},
				{
					field: 'classPhoto',
					title: '课程表图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath() + '/'+d.classPhoto+"' class='cover'/>";
					}
				},
				{
					field: 'className',
					title: '标题',
					align: 'center',
					templet: function(d) {
						return d.className;
					}
				},
				{
					field: 'classTime',
					title: '时间',
					align: 'center',
					templet: function(d) {
						return Format(d.classTime,"yyyy-MM-dd hh:mm:ss");
					}
				},
				{
					title: '操作',
					templet: '#courcesListBar',
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
	function addCources() {
		var index = layui.layer.open({
			title: "添加课程表",
			type: 2,
			content: getRealPath() + "/admin/cources/add",
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
		addCources();
	})
	// 列表操作
	table.on('tool(courcesList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
        if(layEvent === "edit"){
            var videoIndex = layui.layer.open({
                title: "编辑视频",
                type: 2,
                content: getRealPath() + "/admin/cources/edit",
                success: function(layero, index) {
                    var body = layui.layer.getChildFrame('body', index);
                    var iframeWindow = window[layero.find('iframe')[0]['name']];
                    body.find(".id").val(data.id);
                    body.find("#courcesImgVal").val(data.classPhoto);
                    body.find(".className").val(data.className);
                    body.find("#cources_img_view")[0].src = getRealPath() + data.classPhoto;
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
			layer.confirm('确定删除此课程表?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/cources/delete",{id:data.id},function(result){
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