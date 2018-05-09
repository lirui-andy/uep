$(function(){
	
	var showError = function(msg){
		if(msg)
			alert(msg);
	};
	
	$("#changepwdForm").validate({
		submitHandler: function(form) {
			$.post($(form).attr("action"), $(form).serialize())
			.fail(function(){
				showError("操作失败，请重试。");
			})
			.done(function(data){
				if(!data.success){
					showError(data.message);
				} else{
					alert("密码修改成功，请牢记您的新密码！");
					window.location.href="/index";
				}
			});
		}
	});
});
