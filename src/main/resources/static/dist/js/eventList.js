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
	            'zeroRecords': '未查询到数据',
	            'info': '第 _PAGE_ 页/共 _PAGES_ 页',
	            'infoEmpty': '未查询到数据',
	            'infoFiltered': '(从 _MAX_ 条记录中筛选)',
	            'loadingRecords':'加载中...',
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
						
						e.name = '<a href="/event/'+e.eventId+'" target=_blank>'+e.name+'</a>';
					});
					return json.data;
				}, 
				'data': function(data){
					data.condition = $("#eventQueryForm").serializeObject();
					data.condition.eventType = currentMenu;
					console.log(data);
					return JSON.stringify(data);
				}

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
	var title = '新消息';
	if(currentMenu !=""){
		title = Const.translateByGroupAndCode("EVENT_TYPE", currentMenu);
	}
	$(".list-title").html(title);
}

$(function(){
	_guessPageFromLocation();
	_showPageTitle();
	
	$(".navbar-nav li").each(function(i, e){
		if($(e).attr("data-eventType")== currentMenu){
			$(e).addClass("active");
		}
		else $(e).removeClass("active");
	});
	
	queryEvent();
});