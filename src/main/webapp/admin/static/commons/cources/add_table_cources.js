layui.use(['form', 'layer','laydate','rate'], function() {
		var form = layui.form
		layer = parent.layer === undefined ? layui.layer : top.layer,
			$ = layui.jquery,
			rate = layui.rate,
			laydate = layui.laydate;
		var date = new Date();
		//渲染
	    var rateOne = rate.render({
	      elem: '#difficulty'  //绑定元素
	      ,choose: function(value){
		     $("#star_class").val(value);
		  },
		  value: 1
	    });
		form.verify({});
		var tempImg = false;
		// 监听表单
		//时间范围
		laydate.render({
			elem: '#start_time'
			,type: 'time',
			trigger: 'click'
		});
		laydate.render({
			elem: '#end_time'
			,type: 'time'
			,trigger: 'click'
		});
		form.on("submit(addTableCources)", function(data) {
			var index = top.layer.msg('数据提交中,请稍候', {
				icon: 16,
				time: false,
				shade: 0.8
			});
			// 实际使用时的提交信息
			$.ajax({
				type: "POST",
				url: getRealPath() + "/admin/cources/table/add/submit",
				data: data.field,
				success: function(result) {
					if(result.status == 200) {
						setTimeout(function() {
							layer.close(index);
							var index_p=parent.layer.getFrameIndex(window.name); //获取当前窗口的name
				            parent.layer.close(index_p);
							window.sessionStorage.setItem("cources", JSON.stringify(result.data));
							layer.msg("添加成功！");
						}, 500);
					} else {
						layer.close(index);
						layer.msg("添加失败！");
					}
				}
			});
			return false;
		})
	})