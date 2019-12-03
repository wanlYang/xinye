layui.use(['form', 'layer', 'table', 'laytpl','laydate','jquery'], function() {
	var form = layui.form,
		layer = parent.layer === undefined ? layui.layer : top.layer,
		$ = layui.jquery,
		laytpl = layui.laytpl,
		table = layui.table,
		laydate = layui.laydate;
	// 导师列表
	var tableIns = table.render({
		elem: '#teacherList',
		url: getRealPath() + '/admin/teacher/get/list',
		cellMinWidth: 95,
		page: true,
		method: "POST",
		height: "full-125",
		limits: [10, 15, 20, 25],
		limit: 20,
		id: "teacherListTable",
		cols: [
				[	
				{
					type: "checkbox",
					fixed: "left",
					width: 50
				},
				{
					sort: true,
					field: "id",
					title: "ID",
					minWidth: 210,
					align: "center",
				},
				{
					field: 'name',
					title: '教师名称',
					minWidth: 100,
					align: "center"
				},
				{
					field: 'graduateSchool',
					title: '毕业学院',
					minWidth: 100,
					align: 'center',
					templet: function(d) {
						return '<a class="layui-blue" href="mailto:' + d.graduateSchool + '">' + d.graduateSchool + '</a>';
					}
				},
				{
					field: 'teachingQualification',
					title: '教学资质',
					align: 'center',
					templet: function(d) {
						return d.teachingQualification;
					}
				},
				{
					field: 'teachingScope',
					title: '教学范围',
					align: 'center',
					templet: function(d) {
						return d.teachingScope;
					}
				},
				{
					field: 'describe',
					title: '描述',
					align: 'center',
					templet: function(d) {
						return d.describe;
					}
				},
				{
					sort: true,
					field: 'age',
					title: '年龄',
					align: 'center',
					templet: function(d) {
						return d.age;
					}
				},
				{
					field: 'international',
					title: '国籍',
					align: 'center',
					templet: function(d) {
						return d.international;
					}
				},
				{
					field: 'img',
					title: '导师简图',
					event: 'preview',
                    style: 'cursor: pointer;',
					align: 'center',
					templet: function(d) {
						return "<img title='点击预览' src='"+ getRealPath() + '/'+d.img+"' class='cover'/>";
					}
				},
				{
					title: '操作',
					minWidth: 240,
					templet: '#teacherListBar',
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
	// 搜索
	var active = {
		reload: function(){
			var keyWord = "";
			console.log(keyWordType);
			if(keyWordType == "text"){
				keyWord = $('.searchVal').val();
			}
            var keyType = $('.keyType');
            table.reload('teacherListTable', {
            	page: {
					curr: 1 // 重新从第 1 页开始
				},
				method:'post',
                where: {
                    keyType: keyType.val(),
                    keyWord: keyWord
                }
            });
        }
    };
	$(".search_btn").on("click", function() {
		var type = $(this).data('type');
		active[type] ? active[type].call(this) : '';
	});
	// 添加导师
	function addTeacher() {
		var index = layui.layer.open({
			title: "添加导师",
			type: 2,
			content: getRealPath() + "/admin/danceteacher/add",
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
		addTeacher();
	})

	
	// 列表操作
	table.on('tool(teacherList)', function(obj) {
		var layEvent = obj.event,
			data = obj.data;
		// 监听操作
		//编辑导师页面
		if(layEvent === "editinfo"){
			var teacherIndex = layui.layer.open({
				title: "编辑导师",
				type: 2,
				content: getRealPath() + "/admin/danceteacher/edit",
				success: function(layero, index) {
					var body = layui.layer.getChildFrame('body', index);
					var iframeWindow = window[layero.find('iframe')[0]['name']];
					body.find("#id").val(data.id);
					body.find(".name").val(data.name);
					body.find("#imgtext").val(data.img);
					body.find(".graduateSchool").val(data.graduateSchool);
					body.find(".teachingQualification").val(data.teachingQualification);
					body.find(".teachingScope").val(data.teachingScope);
					body.find(".describe").val(data.describe);
					body.find(".age").val(data.age);
					body.find(".international").val(data.international);
					body.find("#teacherImg")[0].src = getRealPath() + data.img;
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
		}else if(layEvent === 'showinfo') { // 详情
			window.open(getRealPath() + "/teacher/detail/"+data.id);
			//window.location.href = ;
		} else if(layEvent === 'del') { // 删除
			layer.confirm('该操作会将导师的所有信息清空!<br/>确定删除此导师?', {
				icon: 3,
				title: '提示信息'
			}, function(index) {
				$.post(getRealPath() + "/admin/teacher/delete/submit",{id:data.id},function(result){
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