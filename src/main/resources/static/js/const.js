function getConstByGroup(constGroup){
	return $.get("/const/"+constGroup);
}

function create_const_select(constGroup, selector){
	var sel = $(selector);
	getConstByGroup(constGroup)
	.done(function(data){
		$.each(data, function(i, d){
			console.log($("<option value='"+d.constCode+"'>"+d.constName+"</option>"));
			if(d.activeFlag == 1)
				sel.append($("<option value='"+d.constCode+"'>"+d.constName+"</option>"));
		});
		
	})
	.fail();
}

/* 自动转换页面上标记为data-constGroup的下拉菜单 */
$(function(){
	$("select[data-constGroup]").each(function(i, e){
		create_const_select($(e).attr("data-constGroup"), e);
	});
});