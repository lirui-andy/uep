
function drawRootCauseChartAt(element, level){
	var datas = [];
	console.log(pdeUtil.datas);
	if(typeof(level) == "undefined"){
		pdeUtil.datas.forEach(function(lvlData){
			datas = datas.concat(lvlData.workItem);
		});
	}
	else if(level == 1 || level == 2 || level == 3){
		datas = pdeUtil.datas[level-1].workItem;
	}
	else{
		return;
	}
	console.log(datas.length);
	
	var openedDataSet = new Array();
	var closedDataSet = new Array();
	var labels = [];
	
	datas.forEach(function(dt){
		var rootCause = dt.rootCause;
		if(labels.indexOf(rootCause)<0){
			labels.push(rootCause)
		}
		if(dt.status == "closed"){
			if(!closedDataSet[rootCause])
				closedDataSet[rootCause] = 1;
			else
				closedDataSet[rootCause] += 1;
		}else{
			if(!openedDataSet[rootCause])
				openedDataSet[rootCause] = 1;
			else
				openedDataSet[rootCause] += 1;
		}
	});
	
	var openedValues = [];
	var closedValues = [];
	labels.forEach(function(label){
		var openV = openedDataSet[label];
		if(!openV) openV = 0;
		var closeV = closedDataSet[label];
		if(!closeV) closeV = 0;

		openedValues.push({x:openV, y:label});
		closedValues.push({x:closeV, y:label});
	});
//	
//	for(i in openedDataSet){
//		openedValues.push({x:openedDataSet[i], y:i});
//	}
//	for(i in closedDataSet){
//		closedValues.push({x:closedDataSet[i], y:i});
//	}
	console.log(labels);
	console.log("Opened:")
	console.log(openedValues);
	console.log("Closed:")
	console.log(closedValues);
	
	if(window.myBarChart){
		window.myBarChart.data.labels = labels;
		window.myBarChart.data.datasets[0].data = openedValues;
		window.myBarChart.data.datasets[1].data = closedValues;
		window.myBarChart.update();
		return;
	}
	
    var barChartCanvas = $(element).get(0).getContext('2d');
    window.myBarChart = new Chart(barChartCanvas, {
        type: 'horizontalBar',
        data: {
            labels: labels,
            datasets: [{
                label: 'Open',
                data: openedValues,
                borderWidth: 1,
                backgroundColor: 'rgba(243,156,18, 0.6)',
                borderColor: 'rgba(243,156,18, 1)'
                
            },{
                label: 'Closed',
                data: closedValues,
                borderWidth: 1
                
            }]
        },
        options: {
            scales: {
                xAxes: [{
                    ticks: {
                        beginAtZero:true
                    },
                    //stacked: true
                }]
            },
            onClick: function(e){
            	console.log("click");
            	var element = this.getElementAtEvent(e);
            	console.log(element);
            }
        }
    });
}

function drawCodeErrorChartAt(element, data){
	if(!data) return;
	else data = [12, 19, 3, 5, 2, 3];
	var colors = ['#3c8dbc', '#00c0ef', '#00a65a', '#f39c12', '#f56954', '#001F3F', '#39CCCC', '#605ca8', '#ff851b', '#D81B60'];
	
	if(window.myPieChart){
		data = [];
		for(var i = 0; i < 6; i++){
			data.push(parseInt(Math.random() * 10));
		}
		window.myPieChart.data.datasets[0].data = data;
		window.myPieChart.update();
		return;
	}
	
    var barChartCanvas = $(element).get(0).getContext('2d');
    window.myPieChart = new Chart(barChartCanvas, {
        type: 'pie',
        data: {
            labels: ['XCreditCardPaymentSessionCmdImpl.java', 'DPLCheckCmdImpl.java', 
            	'XPurchaseOrderPaymentCmdImpl.java', 'XConfigOrderItemDataBean.java', 
            	'XOrderItemAddCmdImpl.java', 'MarketplaceAPIHelperBean.java'],
            datasets: [{
                label: 'New',
                data: data,
                backgroundColor: colors,
                
            }]
        },
        options: {
            responsive: true
        }
    });
}