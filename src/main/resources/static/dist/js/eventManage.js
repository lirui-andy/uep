(function(){

		function queryEvent(){
			//$("#eventQueryForm").serialize()
			var tableOptions = {
					'ajax':{
						'url':'/event/list',
						'method': 'POST',
						'contentType': 'application/json;charset=UTF-8',
						'dataSrc': function(json){
							json.data.forEach(function(e){
								e.genderStr = e.gender;
								e.genderStr = Const.translateByGroupAndCode('GENDER', e.gender);
								e.eventTypeStr = Const.translateByGroupAndCode('EVENT_TYPE', e.eventType);
								if(!e.signed)
									e.eventTypeStr+= '<small class="badge bg-yellow newflag">新</small>'
										;
								if(!e.name || e.name == "") e.name = "无";
								e.choose = "<input type='checkbox' name='eventIds' value='"+e.eventId+"'>";
							});
							return json.data;
						}, 
						'data': function(data){
							data.condition = $("#eventQueryForm").serializeObject();
							return JSON.stringify(data);
						}
					},
					'rowCallback': function( row, data, index ) {
						$(row).addClass(" event-type-"+data.eventType);
						if(!data.active){
							$(row).addClass(" event-inactive");
						}
						$(row).find("td:gt(0)").on("click", function(){
							window.open("/event/"+data.eventId, "eventdetail");
						});
					},
					'columns':[
						{'data':'choose'},
						{'data':'eventTypeStr'},
						{'data':'name'},
						{'data':'genderStr'},
						{'data':'idNum'},
						{'data':'eventTime'},
						{'data':'inputTime'},
						{'data':'inputRealName'},
						{'data':'inputOrgName'}
						]
			};
			$('#eventManageTable').DataTable().destroy();
			$("#eventManageTable").dataTable($.extend({}, tableOptions, tableDefaultOptions));
		}

		function deleteEvent(){
			var choosed = $(":checkbox[name=eventIds]:checked").map(function(i,e){return $(e).val();}).get();
			if(choosed.length && confirm("确定删除"+choosed.length+"条数据吗？")){
				$.post("/manage/delete",{"eventIds":choosed})
				.done(function(data){
					console.log(data);
				});
			}
		}
		
		$(function(){
			queryEvent();
			$("#manage-query-btn").on("click", queryEvent);
			$("#manage-delete-btn").on("click", deleteEvent);
		});
		
		
})();