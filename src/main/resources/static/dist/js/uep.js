function logout(){
	
}

$.fn.serializeObject = function()
{
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
		if (o[this.name] !== undefined) {
			if (!o[this.name].push) {
				o[this.name] = [o[this.name]];
			}
			o[this.name].push(this.value || '');
		} else {
			o[this.name] = this.value || '';
		}
	});
	return o;
};
var datepickerConfig = {
    "locale": {
        "format": "YYYY-MM-DD",    
        "separator": " 至 ",
        "applyLabel": "确定",
        "cancelLabel": "取消",
        "fromLabel": "从",
        "toLabel": "至",
        "daysOfWeek": ["日", "一", "二", "三", "四", "五", "六"],
        "monthNames": ["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
        "firstDay": 1
    },
    "autoApply":true,
    "showDropdowns": true,
};


$(function(){
	$.ajaxSetup({
		'beforeSend':function(jqxhr){
			var token = $("meta[name='_csrf']").attr("content");
			var header = $("meta[name='_csrf_header']").attr("content");
			if(token && header){
				jqxhr.setRequestHeader(header, token);
			}
		}
	});
	$(":radio,:checkbox").iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass   : 'iradio_flat-blue',
	}).on('ifChanged', function(event){
		$(event.target).trigger("change");
//		var srcEvent = $(event.target).attr("onchange");
//		if (srcEvent)
//			eval(srcEvent);
	});
	$('[data-datepicker]').daterangepicker($.extend({},
			datepickerConfig,
			{"singleDatePicker": true,"timePicker": true,"timePicker24Hour": true, 'locale':{'format':'YYYY-MM-DD HH:mm'}}));
	$('[data-daterangepicker]').daterangepicker(datepickerConfig);
	$('[data-daterangepicker]').each(function(i, e){
		var v = $(e).attr("data-default");
		$(e).val(v);
	});
	$('[data-toggle="tooltip"]').tooltip();
});