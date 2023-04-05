var mainStockData = '';
getAllTotalCountProductAndStock();
getAllTodayTotalCountProductAndStock();
getAllProductAndQuantityCount();
function getAllTotalCountProductAndStock() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Stock/getTotalProductAndQuantity",
		dataType: 'json',
		success: function(data) {
			if (data["status"] == "OK") {
				$("#totalProduct").html(data["result"][1]);
				$("#totalQuantity").html(data["result"][0] == null ? 0 : data["result"][0]);
			}
		},
		error: function(error) {
		}
	});
}
debugger
function getAllTodayTotalCountProductAndStock() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Stock/getTodayTotalProductAndQuantity",
		dataType: 'json',
		success: function(data) {
			if (data["status"] == "OK") {
				$("#todayTotalProduct").html(data["result"][1]);
				$("#todayTotalQuantity").html(data["result"][0] == null ? 0 : data["result"][0]);
			}
		},
		error: function(error) {
		}
	});
}
function getAllProductAndQuantityCount() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/ManageInvoice/getAllInvoiceDetails",
		dataType: 'json',
		success: function(data) {
			if (data["status"] == "OK") {

				var year = [];
				for (var i = 0; i < data["result"].length; i++) {
					data["result"][i]["createdDate"] = new Date(Date.parse(data["result"][i]["createdDate"])).toDateString();
				}
				mainStockData = data["result"];
				console.log(mainStockData);
				setYearOptionsForGraphOne(mainStockData);
				setTypeOptionsForGraphTwo(mainStockData);


				//var d = data["result"].filter(function(i, ele) {
				//return i["createdDate"];
				//})
				//var d = data["result"];
				//console.log(data["result"]);
				//console.log(data["result"]);	
			}
		},
		error: function(error) {
		}
	});
}

function setYearOptionsForGraphOne(data) {

	$('#year_option_graph_one')
		.find('option')
		.remove();
	var year = [];
	for (var i = 0; i < data.length; i++) {
		if (!year.includes(data[i]["createdDate"].split(' ')[3])) {
			year.push(data[i]["createdDate"].split(' ')[3]);
		}
	}

	var raw = '<option value="-1">Year</option>';
	$(year).each(function(i) {
		raw += '<option value=' + year[i] + '>' + year[i] + '</option>'
	});
	$("#year_option_graph_one").append(raw);
}
$("#year_option_graph_one").change(function() {
	$('#month_option_grap_one')
		.find('option')
		.remove();
	var month = [];
	for (var i = 0; i < mainStockData.length; i++) {
		if (mainStockData[i]["createdDate"].split(' ')[3] == $("#year_option_graph_one").val()) {
			if (!month.includes(mainStockData[i]["createdDate"].split(' ')[1])) {
				month.push(mainStockData[i]["createdDate"].split(' ')[1]);
			}
		}
	}
	var raw = '<option value="-1">Month</option>';
	$(month).each(function(i) {
		raw += '<option value=' + month[i] + '>' + month[i] + '</option>'
	});
	$("#month_option_grap_one").append(raw);
})
$("#month_option_grap_one").change(function() {
	var year = $("#year_option_graph_one").val();
	var month = $("#month_option_grap_one").val();
	var lables = [];
	var productCount = [];
	var quantityCount = [];
	if (year != "-1" && month != "-1") {

		var filterData = mainStockData.filter(function(i, ele) {
			return i["createdDate"].split(' ')[3] == year && i["createdDate"].split(' ')[1] == month;
		})
		//for (var i = 0; i < mainStockData.length; i++) {
		//if (mainStockData[i]["createdDate"].split(' ')[3] == year && mainStockData[i]["createdDate"].split(' ')[1] == month) {
		//if (!lables.includes(mainStockData[i]["createdDate"].split(' ')[2] + " " + mainStockData[i]["createdDate"].split(' ')[1])) {
		//lables.push(mainStockData[i]["createdDate"].split(' ')[2] + " " + mainStockData[i]["createdDate"].split(' ')[1]);
		//}
		//var len =  JSON.parse(mainStockData[i]["customerInvoiceProductDetails"]).lenght;
		//productCount.push(len);
		//}
		//}
		for (var i = 0; i < filterData.length; i++) {
			if (filterData[i]["createdDate"].split(' ')[3] == year && filterData[i]["createdDate"].split(' ')[1] == month) {
				if (!lables.includes(filterData[i]["createdDate"].split(' ')[2] + " " + filterData[i]["createdDate"].split(' ')[1])) {
					lables.push(filterData[i]["createdDate"].split(' ')[2] + " " + filterData[i]["createdDate"].split(' ')[1]);
				}

			}
		}
		for (var j = 0; j < lables.length; j++) {
			var prodCount = 0;
			var quanCount = 0;
			for (var i = 0; i < filterData.length; i++) {
				if (filterData[i]["createdDate"].split(' ')[2] + " " + filterData[i]["createdDate"].split(' ')[1] == lables[j]) {
					//console.log(filterData[i]["customerInvoiceProductDetails"]);
					var d = JSON.parse(filterData[i]["customerInvoiceProductDetails"].replace(/\'/g, "\""));
					//console.log(d.length);
					prodCount += d.length;
					quanCount += Number(filterData[i]["totalQuantity"]);
				}
			}
			productCount.push(prodCount);
			quantityCount.push(quanCount);
		}
	}
	//console.log(lables);
	barGraphShow(lables, productCount, quantityCount)
});
var graphOne;
function barGraphShow(lables, data1, data2) {
	if (graphOne) {
		graphOne.destroy();
	}
	var barData = {
		labels: lables,
		datasets: [
			{
				label: "Product Count",
				backgroundColor: 'rgba(220, 220, 220, 0.5)',
				pointBorderColor: "#fff",
				data: data1
			},
			{
				label: "Quantity Count",
				backgroundColor: 'rgba(26,179,148,0.5)',
				borderColor: "rgba(26,179,148,0.7)",
				pointBackgroundColor: "rgba(26,179,148,1)",
				pointBorderColor: "#fff",
				data: data2
			}
		]
	};
	var barOptions = {
		responsive: true
	};
	var ctx2 = document.getElementById("barChart").getContext("2d");
	graphOne = new Chart(ctx2, { type: 'bar', data: barData, options: barOptions });
}
function setTypeOptionsForGraphTwo(data) {
	$('#type_option_grap_two')
		.find('option')
		.remove();
	var type = [];
	for (var i = 0; i < data.length; i++) {
		var d = JSON.parse(data[i]["customerInvoiceProductDetails"].replace(/\'/g, "\""));
		for (var j = 0; j < d.length; j++) {
			if (!type.includes(d[j]["prodTypes"])) {
				type.push(d[j]["prodTypes"]);
			}
		}
	}
	var raw = '';
	$(type).each(function(i) {
		raw += '<option value=' + type[i] + '>' + type[i] + '</option>'
	});
	$("#type_option_grap_two").append(raw);
}
$('#type_option_grap_two').change(function() {
	$('#year_option_graph_two')
		.find('option')
		.remove();
	var year = [];
	for (var i = 0; i < mainStockData.length; i++) {
		var d = JSON.parse(mainStockData[i]["customerInvoiceProductDetails"].replace(/\'/g, "\""));
		for (var j = 0; j < d.length; j++) {
			if (d[j]["prodTypes"] === $("#type_option_grap_two").val()) {
				if (!year.includes(mainStockData[i]["createdDate"].split(' ')[3])) {
					year.push(mainStockData[i]["createdDate"].split(' ')[3]);
				}
			}
		}
	}
	var raw = '<option value="-1">Year</option>';
	$(year).each(function(i) {
		raw += '<option value=' + year[i] + '>' + year[i] + '</option>'
	});
	$("#year_option_graph_two").append(raw);
});
$("#year_option_graph_two").change(function() {
	$('#month_option_grap_two')
		.find('option')
		.remove();
	var month = [];
	for (var i = 0; i < mainStockData.length; i++) {
		var d = JSON.parse(mainStockData[i]["customerInvoiceProductDetails"].replace(/\'/g, "\""));
		for (var j = 0; j < d.length; j++) {
			if (d[j]["prodTypes"] === $("#type_option_grap_two").val() && mainStockData[i]["createdDate"].split(' ')[3] == $("#year_option_graph_two").val()) {
				if (!month.includes(mainStockData[i]["createdDate"].split(' ')[1])) {
					month.push(mainStockData[i]["createdDate"].split(' ')[1]);
				}
			}
		}
	}
	var raw = '<option value="-1">Month</option>';
	$(month).each(function(i) {
		raw += '<option value=' + month[i] + '>' + month[i] + '</option>'
	});
	$("#month_option_grap_two").append(raw);
});
$("#month_option_grap_two").change(function() {
	$('#day_option_grap_two')
		.find('option')
		.remove();
	var day = [];
	for (var i = 0; i < mainStockData.length; i++) {
		if (mainStockData[i]["createdDate"].split(' ')[1] == $("#month_option_grap_two").val() && mainStockData[i]["createdDate"].split(' ')[3] == $("#year_option_graph_two").val()) {
			if (!day.includes(mainStockData[i]["createdDate"].split(' ')[2])) {
				day.push(mainStockData[i]["createdDate"].split(' ')[2]);
			}
		}
	}
	var raw = '<option value="-1">Day</option>';
	$(day).each(function(i) {
		raw += '<option value=' + day[i] + '>' + day[i] + '</option>'
	});
	$("#day_option_grap_two").append(raw);
});

$("#day_option_grap_two").change(function() {
	$('#model_option_grap_two')
		.find('option')
		.remove();
	var model = [];
	for (var i = 0; i < mainStockData.length; i++) {
		var d = JSON.parse(mainStockData[i]["customerInvoiceProductDetails"].replace(/\'/g, "\""));
		for (var j = 0; j < d.length; j++) {
			if (d[j]["prodTypes"] === $("#type_option_grap_two").val() && mainStockData[i]["createdDate"].split(' ')[2] == $("#day_option_grap_two").val()) {
				if (!model.includes(d[j]["prodModel"])) {
					model.push(d[j]["prodModel"]+'-'+d[j]["prodQuantity"]);					
				}
			}
		}
	}
	var raw = '<option value="-1">Day</option>';
	$(model).each(function(ind) {
		raw += '<option value=\"' + model[ind] + '\">' + model[ind].split('-')[0] + '</option>'
	});
	$("#model_option_grap_two").append(raw);
});

$("#model_option_grap_two").change(function(){
	barGraphShowTwo($("#model_option_grap_two").val().split('-')[0],$("#model_option_grap_two").val().split('-')[1])
});
var graphTwo;
function barGraphShowTwo(lables, data) {
	if (graphTwo) {
		graphTwo.destroy();
	}
	let barData = {
		labels: lables.split(','),
		datasets:[
			{
				label: "Quantity Count",
				backgroundColor: 'rgba(26,179,148,0.5)',
				borderColor: "rgba(26,179,148,0.7)",
				pointBackgroundColor: "rgba(26,179,148,1)",
				pointBorderColor: "#fff",
				data: data.split(',')
			}
		],
		scales: {
        yAxes: [{
            ticks: {
                beginAtZero:true
            }
        }]
    }
	};
	let barOptions = {
		responsive: true
	};
	let ct = $("#graph2");
	graphTwo = new Chart(ct, { type: 'bar', data: barData, options: barOptions });
}
