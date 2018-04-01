

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
	$(":radio,:checkbox").iCheck({
	      checkboxClass: 'icheckbox_square-blue',
	      radioClass   : 'iradio_flat-blue'
	});
	$('[data-datepicker]').daterangepicker($.extend({},datepickerConfig,{"singleDatePicker": true}));
	$('[data-daterangepicker]').daterangepicker(datepickerConfig);
	$("[data-inputmask]").inputmask();
});