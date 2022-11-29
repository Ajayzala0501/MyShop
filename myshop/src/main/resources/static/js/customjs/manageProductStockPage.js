
getAllStockDetails();
function getAllStockDetails() {
	$.ajax({
		type: "GET",
		contentType: "application/json",
		url: "/Stock/getAllStockDetails",
		dataType: 'json',
		success: function(data) {
			if (data["status"] == "OK") {
				generateTable(data["result"]);
				//console.log(data["result"]);
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
		//var spec = JSON.parse(data[i]["productSpecification"]);
		raw += '<tr ><td>' + data[i]["compName"] + '</td> <td class="text-center">' + data[i]["prodModel"] + '</td> <td class="text-center">' + data[i]["stockQuantity"] + '</td><td class="text-center">' + data[i]["remainingQuantity"] + '</td><td class="center">' + data[i]["updatedDate"] + '</td><td class="text-center"><form action="/ViewProduct/addNewProductPage" method="GET"><input type="text" name="hide-proid" value="' + data[i]["stockId"] + '" hidden><button type="button" name="submit" value="edit-btn" class="btn btn-primary btn-outline btn-xs btn-open-model" id="btn-open-model" data-toggle="modal">Update</button>&nbsp</form></td></tr>';
	})
	$("#product-Stock-table-body").append(raw);
}
	
$(document).ready(function() {

$(".btn-open-model").click(function(){
	$("#hide-stock-id").val($(this).closest('tr').find('input[name="hide-proid"]').val());
	$("#remainingStock").val($(this).closest('tr').find("td:nth-child(4)").text());
	$('#myModal').modal('show');
	})
	$("#form").validate({
		rules: {
			updateStock: {
				required: true
			}
		},
		messages: {
			updateStock: {
				required: "Please Enter New Stock Quantity",
				min: "Please Enter Atleast 1 Quantiy"
			}
		},
		submitHandler: function(form, e) {
			e.preventDefault();
			var Formdata = {
				stockId: $("#hide-stock-id").val(),
				stockQuantity: $("#updateStock").val()
			}
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Stock/updateStock",
				data:  JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					//$('#myModal').modal('hide');
					if (data["status"] == "OK") {
						
						//$("#product-Stock-table-body").empty();
						//getAllStockDetails();
						window.location.reload();
					} else {
						$("#error").show();
						$('#error').removeClass('alert-success');
						$("#error").addClass('alert-danger');
						$("#error p").html(data["result"]);
					}
				},
				error: function(error) {
					$("#error").show();
					$('#error').removeClass('alert-success');
					$("#error").addClass('alert-danger');
					$("#error p").html(error.responseJSON["result"]);
				}
			})
		}
	});
	$('.footable').footable();
	$('.footable2').footable();
});