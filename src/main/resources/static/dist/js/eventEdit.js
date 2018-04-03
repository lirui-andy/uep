
function listReceipt(){
	//$("#eventQueryForm").serialize()
	var tableOptions = {
			'ajax':{
				'url':'/event/receipt/1',
				'method': 'POST',
				'contentType': 'application/json;charset=UTF-8',
				'dataSrc': function(json){
					json.data.forEach(function(e){
						//e.gender = e.gender == 'M'? '男': (e.gender=='F'?'女':'未知');
						e.genderStr = e.gender;
						e.genderStr = Const.translateByGroupAndCode('GENDER', e.gender);
						e.eventTypeStr = Const.translateByGroupAndCode('EVENT_TYPE', e.eventType);
						
						e.name = '<a href=#>'+e.name+'</a>';
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