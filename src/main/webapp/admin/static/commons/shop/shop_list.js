layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 店面列表
	var tableIns = table.render({
		elem: '#shopList',
		url: getRealPath() + '/admin/shop/list/get',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "shopListTable",
		cols: [
				[
				{
					sort: true,
					field: "id",
					title: "ID",
					minWidth: 210,
					align: "center",
				},
				{
					field: 'shopName',
					title: '店面名称',
					align: "center"
				},
				{
					field: 'shopPhoto',
					title: '店面图',
					align: 'center',
					templet: function(d) {
						return "<img src='"+ getRealPath()  +d.shopPhoto+"' class='cover'/>";
					}
				},
				{
					field: 'shopAdd',
					title: '店面地址',
					align: 'center',
					templet: function(d) {
						return d.shopAdd;
					}
				},
				{
					title: '操作',
					minWidth: 240,
					templet: '#shopListBar',
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
	// 检测select
	var keyWordType = "";
	form.on('select(skeyType)', function(data){
		if(data.value == ""){
			$("#keyWordBox").html("");
		}else{
			$("#keyWordBox").html(
				"<input type='text' class='layui-input searchVal' placeholder='请输入关键字'>"
			);
			keyWordType = "text";
			form.render();
		}
	});
	function addShop() {
		var index = layui.layer.open({
			title: "添加店面图",
			type: 2,
			content: getRealPath() + "/admin/shop/add",
			success: function(layero, index) {
				setTimeout(function() {
					layui.layer.tips('点击此处返回导师列表', '.layui-layer-setwin .layui-layer-close', {
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
		addShop();
	})

	
	// 列表操作
	table.on('tool(shopList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑导师页面
		if(layEvent === "edit"){
			var teacherIndex = layui.layer.open({
				title: "编辑店面",
				type: 2,
				content: getRealPath() + "/admin/shop/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					body.find("#shopImgVal").val(data.shopPhoto);
					body.find("#shop_img_view")[0].src = getRealPath() + data.shopPhoto;
					body.find(".shopName").val(data.shopName);
					body.find(".shopAdd").val(data.shopAdd);
					if (typeof(iframeWindow.layui.form) != "undefined") {
						iframeWindow.layui.form.render();
					}
					setTimeout(function() {
						layui.layer.tips('点击此处返回导师列表', '.layui-layer-setwin .layui-layer-close', {
							tips: 3
						});
					}, 500)
				},
				end: function() {
					$(window).unbind("resize");
					
				}
			})
			layui.layer.full(teacherIndex);
			window.sessionStorage.setItem("teacherIndex", teacherIndex);
			// 改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
			$(window).on("resize", function() {
				layui.layer.full(window.sessionStorage.getItem("teacherIndex"));
			})
		}else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将店面的所有信息清空!<br/>确定删除此店面?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/shop/delete/submit",{id:data.id},function(result){
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