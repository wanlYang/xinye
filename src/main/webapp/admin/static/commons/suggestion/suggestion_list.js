layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	var tableIns = table.render({
		elem: '#suggestionList',
		url: getRealPath() + '/admin/suggestion/get/list',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "suggestionListTable",
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
					field: 'username',
					title: '留言者',
					align: "center"
				},
				{
					field: 'phone',
					title: '手机号码',
					align: 'center',
					templet: function(d) {
						return d.phone;
					}
				},
				{
					field: 'content',
					title: '留言内容',
					align: 'center',
					templet: function(d) {
						return d.content;
					}
				},
				{
					title: '操作',
					templet: '#suggestionListBar',
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
	table.on('tool(suggestionList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑页面
		if(layEvent === 'del') { // 删除
			layer.confirm('确定删除此留言?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/suggestion/delete/submit",{id:data.id},function(result){
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