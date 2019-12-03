layui.use(['form', 'layer','jquery'], function() {
		var form = layui.form,
			$ = layui.jquery,
			layer = parent.layer === undefined ? layui.layer : top.layer;
		var index_msg = layer.msg('获取数据中,请稍候', {
			icon: 16,
			time: false,
			shade: 0.8
		});
		var vm = new Vue({
	        el: '#app',
	        data(){
	        	return{
	        		timer:null,
	        		week: [],
		            monday:[],
		            tuesday:[],
		            wednesday:[],
		            thursday:[],
		            friday:[],
		            saturday:[],
		            sunday:[],
		            tempWeek:["monday","tuesday","wednesday","thursday","friday","saturday","sunday"]
	        	}
	        },
	        created: function () {
	　　　　　　	//为了在内部函数能使用外部函数的this对象，要给它赋值了一个名叫self的变量。
                var self = this;
	　　　　　　	//店面ID
	　　　　　　	var front = "10132947561574263774261";
	        	//实际使用时的提交信息
	        	//获取星期数据
	        	axios.post(getRealPath() + "/admin/cources/table/week/list")
				     .then(function (response) {
				    	 self.week = response.data.data;
				    	 layer.close(index_msg);
				    	 layer.msg("获取数据成功!")
				     })
				     .catch(function (error) { // 请求失败处理
				    	 layer.msg("获取数据失败!")
				       	 console.log(error);
				     });
	        	//获取星期一课程表
	        	var params_m = new URLSearchParams();
	        	params_m.append('front', front);
	        	params_m.append('week', '11976352101574247112256');
	        	axios.post(getRealPath() + "/admin/cources/table/list/monday",params_m)
			     .then(function (response) {
			    	 self.monday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	//获取星期二课程表
	        	var params_tue = new URLSearchParams();
	        	params_tue.append('front', front);
	        	params_tue.append('week', '21964327101574247282899');
	        	axios.post(getRealPath() + "/admin/cources/table/list/tuesday",params_tue)
			     .then(function (response) {
			    	 self.tuesday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	//获取星期三课程表
	        	var params_wed = new URLSearchParams();
	        	params_wed.append('front', front);
	        	params_wed.append('week', '39657101421574247168608');
	        	axios.post(getRealPath() + "/admin/cources/table/list/wednesday",params_wed)
			     .then(function (response) {
			    	 self.wednesday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	
	        	//获取星期四课程表
	        	var params_thu = new URLSearchParams();
	        	params_thu.append('front', front);
	        	params_thu.append('week', '47196108431574247199561');
	        	axios.post(getRealPath() + "/admin/cources/table/list/thursday",params_thu)
			     .then(function (response) {
			    	 self.thursday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	
	        	//获取星期五课程表
	        	var params_fri = new URLSearchParams();
	        	params_fri.append('front', front);
	        	params_fri.append('week', '51065329141574247214318');
	        	axios.post(getRealPath() + "/admin/cources/table/list/friday",params_fri)
			     .then(function (response) {
			    	 self.friday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	
	        	//获取星期六课程表
	        	var params_sat = new URLSearchParams();
	        	params_sat.append('front', front);
	        	params_sat.append('week', '67341681051574247226155');
	        	axios.post(getRealPath() + "/admin/cources/table/list/saturday",params_sat)
			     .then(function (response) {
			    	 self.saturday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        	//获取星期天课程表
	        	var params_sun = new URLSearchParams();
	        	params_sun.append('front', front);
	        	params_sun.append('week', '71046358971574247275310');
	        	axios.post(getRealPath() + "/admin/cources/table/list/sunday",params_sun)
			     .then(function (response) {
			    	 self.sunday = response.data.data;
			    	 layer.msg("获取数据成功!")
			     })
			     .catch(function (error) { // 请求失败处理
			    	 layer.msg("获取数据失败!")
			       	 console.log(error);
			     });
	        },
	        methods:{
	        	delCources:function(cources,index){
	        		timers = this.timer;
	        		if (timers) {
						window.clearTimeout(timers);
						this.timer = null;
					}
	        		this.timer = window.setTimeout(function(){
		        		layer.confirm('确定删除此课程表?', {
		    				icon: 3,
		    				title: '提示信息'
		    			}, function(index) {
		    				var params_del = new URLSearchParams();
			        		params_del.append('id', cources.id);
		    				axios.post(getRealPath() + "/admin/cources/table/delete/submit",params_del)
			   			     .then(function (response) {
			   			    	 layer.msg("删除成功!");
			   			    	 location.reload();
			   			     })
			   			     .catch(function (error) { // 请求失败处理
			   			    	 layer.msg("删除失败!")
			   			       	 console.log(error);
			   			     });
		    			});
	        		},300)
	        	},
	        	editCources:function(cources){
	        		timers = this.timer;
	        		if (timers) {
	        			window.clearTimeout(timers);
	        			this.timer = null;
					}
	        		window.sessionStorage.setItem("star_class",cources.star_class);
	        		var index = layui.layer.open({
	        			title: "编辑【"+cources.week.describe+"】课程",
	        			type: 2,
	        			area: ['500px','550px'],
	        			content: getRealPath() + "/admin/cources/table/edit/page",
	        			success:function(layero, index){
	        				var body = layui.layer.getChildFrame('body', index);
	    					var iframeWindow = window[layero.find('iframe')[0]['name']];
	    					body.find("#courcesId").val(cources.id);
	    					body.find(".name").val(cources.name);
	    					body.find(".star_class").val(cources.star_class);
	    					body.find(".type").val(cources.type);
	    					body.find(".effect").val(cources.effect);
	    					body.find(".start_time").val(cources.start_time);
	    					body.find(".end_time").val(cources.end_time);
	    					if (typeof(iframeWindow.layui.form) != "undefined") {
								iframeWindow.layui.form.render();
							}
	        			},
	        			end: function() {
	        				var cources = JSON.parse(window.sessionStorage.getItem("cources"));
	        				
	        			}
	        		})
	        	},
	        	addCourcesPage:function(data){
	        		var oldData = this.$data;
	        		var index = layui.layer.open({
	        			title: "添加【"+data.describe+"】课程",
	        			type: 2,
	        			area: ['500px','550px'],
	        			content: getRealPath() + "/admin/cources/table/add/page",
	        			success:function(layero, index){
	        				var body = layui.layer.getChildFrame('body', index);
	    					var iframeWindow = window[layero.find('iframe')[0]['name']];
	    					body.find("#weekId").val(data.id);
	    					body.find("#storeFrontId").val("10132947561574263774261");//东郊店    		
	    					
	        			},
	        			end: function() {
	        				var cources = JSON.parse(window.sessionStorage.getItem("cources"));
	        				if (!$.isEmptyObject(cources)) {
	        					if (data.describe == "星期一") {
		        					Vue.set(oldData.monday,oldData.monday.length,cources);
		        					window.sessionStorage.removeItem("cources");
								}
	        					if (data.describe == "星期二") {
		        					Vue.set(oldData.tuesday,oldData.tuesday.length,cources);
		        					window.sessionStorage.removeItem("cources");
								}
	        					if (data.describe == "星期三") {
	        						Vue.set(oldData.wednesday,oldData.wednesday.length,cources);
	        						window.sessionStorage.removeItem("cources");
	        					}
	        					if (data.describe == "星期四") {
	        						Vue.set(oldData.thursday,oldData.thursday.length,cources);
	        						window.sessionStorage.removeItem("cources");
	        					}
	        					if (data.describe == "星期五") {
	        						Vue.set(oldData.friday,oldData.friday.length,cources);
	        						window.sessionStorage.removeItem("cources");
	        					}
	        					if (data.describe == "星期六") {
	        						Vue.set(oldData.saturday,oldData.saturday.length,cources);
	        						window.sessionStorage.removeItem("cources");
	        					}
	        					if (data.describe == "星期天") {
	        						Vue.set(oldData.sunday,oldData.sunday.length,cources);
	        						window.sessionStorage.removeItem("cources");
	        					}
							}
	        			}
	        		})
	        	}
	        }
	    });
		
	})