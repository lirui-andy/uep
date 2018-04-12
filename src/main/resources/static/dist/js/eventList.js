var currentMenu = "";
var tableDefaultOptions = {
	      'paging'      : true,
	      'processing'  : true,
	      'serverSide'  : true,
	      "pageLength"  :  20,
	      'lengthChange': false,
	      'searching'   : false,
	      'ordering'    : false,
	      'info'        : true,
	      'autoWidth'   : false,
		  'language': {
	            'lengthMenu': '每页显示 _MENU_ 行',
	            'zeroRecords': '未检索到数据，请去掉部分检索条件后重试，或检索其他类型。',
	            'info': '第 _PAGE_ 页/共 _PAGES_ 页（显示第 _START_ ~ _END_条 / 共检索出 _MAX_ 条）',
	            'infoEmpty': '未检索到数据',
	            'infoFiltered': '(从 _MAX_ 条记录中筛选)',
	            'loadingRecords':'加载中...',
	            'loading':'正在搜索...',
	            'processing':'正在搜索...',
	            'paginate':{
	            	'first':'第一页',
	            	'last':'最后一页',
	            	'next':'下一页',
	            	'previous':'上一页'
	            }
	        }
	    };

function queryEvent(){
	//$("#eventQueryForm").serialize()
	var tableOptions = {
			'ajax':{
				'url':'/event/list',
				'method': 'POST',
				'contentType': 'application/json;charset=UTF-8',
				'dataSrc': function(json){
					json.data.forEach(function(e){
						//e.gender = e.gender == 'M'? '男': (e.gender=='F'?'女':'未知');
						e.genderStr = e.gender;
						e.genderStr = Const.translateByGroupAndCode('GENDER', e.gender);
						e.eventTypeStr = Const.translateByGroupAndCode('EVENT_TYPE', e.eventType);
						if(!e.signed)
							e.eventTypeStr+= '<small class="badge bg-yellow newflag">新</small>'
							;
						
						e.name = '<a href="/event/'+e.eventId+'" target=_blank>'+e.name+'</a>';
						
					});
					return json.data;
				}, 
				'data': function(data){
					data.condition = $("#eventQueryForm").serializeObject();
					data.condition.eventType = currentMenu;
					return JSON.stringify(data);
				}
			},
			'rowCallback': function( row, data, index ) {
				$(row)
					.addClass("newmessage")
					.on("click", function(){
			  		window.open("/event/"+data.eventId);
			  	});
			},
			'columns':[
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
	$('#eventTable').DataTable().destroy();
	$("#eventTable").dataTable($.extend({}, tableOptions, tableDefaultOptions));
}

function _guessPageFromLocation(){
	var loc = location.search.indexOf("t=")
	var v = location.search.substr(loc+2);
	loc = v.indexOf("&");
	if(loc >-1)
		v = v.substr(0, loc);
	currentMenu = v;
	if(currentMenu == "0") currentMenu = "";
}

function _showPageTitle(){
	var title = '<span class="glyphicon glyphicon-bell"></span> 您有<a data-toggle="tooltip" onclick="loadNewMessage();" title="点击显示所有未签收消息" id="newCount" class="badge bg-red" data-original-title="点击显示所有未签收消息">0</a>条新消息';
	if(currentMenu !=""){
		title = Const.translateByGroupAndCode("EVENT_TYPE", currentMenu);
	}
	$(".list-title").html(title);

	$('[data-toggle="tooltip"]').tooltip();
}

//查询新消息列表
function loadNewMessage(){
	currentMenu = "NEW";
	$("#eventQueryForm").get(0).reset();
	queryEvent();
}
//获取新消息数量
function _loadNewMessageCount(){
	if($("#newCount").length == 0) return;
	$.get("/event/newcount", function(data){
		if(data.success){
			$("#newCount").text(data.data);
		}
	});
}

$(function(){
	_guessPageFromLocation();
	_showPageTitle();
	_loadNewMessageCount();
	
	$(".navbar-nav li").each(function(i, e){
		if($(e).attr("data-eventType")== currentMenu){
			$(e).addClass("active");
		}
		else $(e).removeClass("active");
	});
	
	queryEvent();
});