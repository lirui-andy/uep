var currentMenu = "";

function queryEvent(){
	$.get("/event/", $("#eventQueryForm").serialize())
	.done(function(data){
		console.log(data);
		
	});
}