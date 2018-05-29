$(function(){
	
	$('#images').viewer({fullscreen:false});
	$(".image-list li").click(function(e){
		$(this).parents("#images").data("viewer").view($(this).index());
	});
});