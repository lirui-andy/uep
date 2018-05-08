function insertAttach(src){
	$(src).before($('<input type=file name=file>'));
}
$(function(){
	$("#eventEditForm").validate();
});