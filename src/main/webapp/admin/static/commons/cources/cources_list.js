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
		url: getRealPath() + '/admin/cources/get/list',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
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
					field: 'tableImg',
					title: '课程表图',
					event: 'preview',
                    style: 'cursor: pointer;',
					align: 'center',
					templet: function(d) {
						return "<img title='点击预览' src='"+ getRealPath() + '/'+d.tableImg+"' class='cover'/>";
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
		// 监听操作
		if(layEvent === 'del') { // 删除
			layer.confirm('确定删除此课程表?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/cources/delete/submit",{id:data.id},function(result){
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
		} else if(layEvent === 'preview') {//显示大图
            preview_img(getRealPath() + "/"+data.tableImg);
        } else if(layEvent === 'showinfo') {
        	window.open(getRealPath() + "/cources");
        }
	});
})