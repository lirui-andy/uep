function insertAttach(src){
	console.log($(src));
	$(src).before($('<input type=file name=file>'));
}