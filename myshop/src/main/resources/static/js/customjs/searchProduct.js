$(document).ready(function() {
	$('.footable').footable();
	$('.footable2').footable();

	getAllProductDetails();

	function getAllProductDetails() {

		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/Product/getAllProductInfo",
			dataType: 'json',
			success: function(data) {
				if (data["status"] == "OK") {
					console.log(data);
				}
			},
			error: function(error) {

			}
		});
	}
});
