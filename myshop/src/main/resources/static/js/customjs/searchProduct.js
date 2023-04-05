
	getAllProductDetails();
	function getAllProductDetails() {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/Product/getAllProductInfo",
			dataType: 'json',
			success: function(data) {
				if (data["status"] == "OK") {
					generateTable(data["result"]);
				}
			},
			error: function(error) {

			}
		});
	}

	function generateTable(data) {
		var raw = '';
		//data = JSON.parse(data2);

		$(data).each(function(i) {
			var specInfo = '';
			var spec = JSON.parse(data[i]["productSpecification"]);
			$(spec).each(function(index) {
				specInfo += spec[index]["specification"] + " : " + spec[index]["specification-value"] + " | ";
			});
			raw += '<tr ><td>' + data[i]["prodId"] + '</td><td class="text-center">' + data[i]["typeId"] + '</td> <td class="text-center">' + data[i]["companyId"] + '</td> <td class="text-center">' + data[i]["productModel"] + '</td><td class="text-center">' + data[i]["productColour"] + '</td><td class="center">' + data[i]["productPrice"] + '</td><td class="center">' + specInfo.slice(0, -2) + '</td><td class="center">' + data[i]["updatedDate"] + '</td><td class="text-center"><form action="/ViewProduct/addNewProductPage" method="GET"><input type="text" name="hide-proid" value="' + data[i]["prodId"] + '" hidden><button type="submit" name="submit" value="edit-btn" class="btn btn-primary btn-outline btn-xs">Edit</button>&nbsp;&nbsp;<button type="submit" name="submit" value="edit-delete" class="btn btn-danger btn-outline btn-xs">Delete</button></form></td></tr>';

		})
		$("#product-Info-table-body").append(raw);

	}

$(document).ready(function() {

	$('.footable').footable();
	$('.footable2').footable();
});
