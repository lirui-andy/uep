var Const = {
		cacheAllConst: function(){
			$.ajax("/const",{
				async:false
			})
			.done(function(data){
				sessionStorage.setItem("consts", JSON.stringify(data));
			});
		}
		
		,getListByGroup: function(constGroup){
			var consts = JSON.parse(sessionStorage.getItem("consts"));
			var target = [];
			consts.forEach(function(d){
				if(d.constGroup == constGroup)
					target.push(d);
			});
			return target;
		}
		
		,translateByGroupAndCode(group, code){
			var consts = JSON.parse(sessionStorage.getItem("consts"));
			var target = {constName:'', constCode:''};
			consts.forEach(function(d){
				if(d.constGroup == group && d.constCode == code)
					target = d;
			});
			return target.constName;
		}
}


function create_const_select(constGroup, selector){
	var sel = $(selector);
	var data = Const.getListByGroup(constGroup);
		$.each(data, function(i, d){
			if(d.activeFlag == 1)
				sel.append($("<option value='"+d.constCode+"'>"+d.constName+"</option>"));
		});
		
}

/* 自动转换页面上标记为data-constGroup的下拉菜单 */
$(function(){
	Const.cacheAllConst();
	$("select[data-constGroup]").each(function(i, e){
		create_const_select($(e).attr("data-constGroup"), e);
	});
});