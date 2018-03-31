$(document).ready(function() {
	
	var now = moment();
	pdeUtil.refreshData(now,now);
	$("#nunmberForSEV1").parent().click(function(){drawRootCauseChartAt($("#barChart"), 1); drawCodeErrorChartAt('#pieChart', true);});
	$("#nunmberForSEV2").parent().click(function(){drawRootCauseChartAt($("#barChart"), 2); drawCodeErrorChartAt('#pieChart', true);});
	$("#nunmberForSEV3").parent().click(function(){drawRootCauseChartAt($("#barChart"), 3); drawCodeErrorChartAt('#pieChart', true);});
	$("#nunmberForALL").parent().click(function(){drawRootCauseChartAt($("#barChart")); drawCodeErrorChartAt('#pieChart', true);});
});

pdeUtil = {};
pdeUtil.datas = [];

pdeUtil.refreshData = function(start, end){
	console.log("fetch data >>>")
	$(".overlay").show();
	$.ajax({
		type : "GET",
		url : "/allWorkItems?timeType=month",
		// data: "name=garfield&age=18",
		async : true,
		success : function(res) {
			
			pdeUtil.datas = res;
			
			displayCard();
			displayBarChart();
			displayPieChart();
			/*
			$("#nunmberForSEV1").html(res[0].count);
			$("#nunmberForSEV2").html(res[1].count);
			$("#nunmberForSEV3").html(res[2].count);
			*/
		}
	});
}

function displayBarChart(){
	$(".overlay", $("#barChartBox")).hide();
	drawRootCauseChartAt($("#barChart"));
}
function displayPieChart(){
	$(".overlay", $("#pieChartBox")).hide();
	drawCodeErrorChartAt($("#pieChart"), true);
}


displayCard = function(){
	$(".small-box .overlay").hide();
	if(pdeUtil.datas){
		$("#nunmberForSEV1").html(pdeUtil.datas[0].count);
		$("#nunmberForSEV2").html(pdeUtil.datas[1].count);
		$("#nunmberForSEV3").html(pdeUtil.datas[2].count);
		$("#nunmberForALL").html(pdeUtil.datas[0].count + pdeUtil.datas[1].count + pdeUtil.datas[2].count);
		
	}
}

displayTable = function(sevN){
	var n = sevN;
	$(".small-box .overlay").hide();
	if(pdeUtil.datas){
		var str="";
		$.each(pdeUtil.datas[n].workItem, function(inx, ele){
			str += 
					" <tr>  " +
					" <td class='id'>"+ ele.id +"</td>  " +
					" <td class='title'> <a href='javascript:' data-toggle='modal' data-target='#modal-default'>"+ ele.title +"</a></td>  " +
					" <td class='rootCause'>"+ ele.rootCause +"</td>  " +
					" <td class='ownedBy'>"+ ele.ownedBy +"</td>  " +
					" <td class='createDate'>"+ ele.createDate +"</td>  </tr>";
		});
		
		$("#dataTable").html(str);
	}
}

$("#clickSEV1").click(function(){
	displayTable(0);
});
$("#clickSEV2").click(function(){
	displayTable(1);
});
$("#clickSEV3").click(function(){
	displayTable(2);
});

