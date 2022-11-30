$(document).ready(function() {
	$('.slick_demo_2').slick({
		infinite: true,
		slidesToShow: 3,
		slidesToScroll: 1,
		centerMode: true,
		responsive: [
			{
				breakpoint: 1024,
				settings: {
					slidesToShow: 3,
					slidesToScroll: 3,
					infinite: true,
					dots: true
				}
			},
			{
				breakpoint: 600,
				settings: {
					slidesToShow: 2,
					slidesToScroll: 2
				}
			},
			{
				breakpoint: 480,
				settings: {
					slidesToShow: 1,
					slidesToScroll: 1
				}
			}
		]
	});

	getAllProductTypes();
	// For load all product types from database
	function getAllProductTypes() {
		$.ajax({
			type: "GET",
			contentType: "application/json",
			url: "/Product/getAllProductTypes",
			data: "",
			dataType: 'json',
			success: function(data) {
				if (data["status"] == "OK") {
					var opData = '';
					$.each(data["result"], function(i, item) {
						opData += "<option value='" + item['typeId'] + "'>" + item['typeName'] + "</option>";
						//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
					});
					$("#productTypeSelectOption").append(opData);
					$("#productTypeSelectOptionWithCompany").append(opData);
					$("#productTypeSelectOption").select2().trigger('change');
					opData = '';
				}
			}
		})


	}
	//For load all the product company from database
	function getAllProductCompany() {
			$.ajax({
				type: "GET",
				contentType: "application/json",
				url: "/Product/getAllProductCompany",
				data: {
					"typeId": $("#productTypeSelectOption").val()
				},
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						var opData = '';
						$.each(data["result"], function(i, item) {
							opData += "<option value='" + item['companyId'] + "'>" + item['companyName'] + "</option>";
							//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
						});

						$("#productCompanySelectOption").append(opData);
						$("#productCompanySelectOption").select2().trigger('change');
						opData = '';
					} 
				}
			})
		}
		
		
		//For load all the product company from database
	function getAllProductModelInformation() {
		debugger
		if($('#productCompanySelectOption').val()!='' && $('#productCompanySelectOption').val() != null){
		var formData =  {
					"typeId":$('#productTypeSelectOption').val() ,
					"companyId":$('#productCompanySelectOption').val()
				};
				
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/getAllProductModel",
				data:JSON.stringify(formData),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						var opData = '';
						console.log(data);
						//$.each(data["result"], function(i, item) {
							//opData += "<option value='" + item['companyId'] + "'>" + item['companyName'] + "</option>";
							//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
						//});

						//$("#productCompanySelectOption").append(opData);
						//$("#productCompanySelectOption").select2().trigger('change');
						//opData = '';
					} 
				}
			})
			}
		}
	
	$('#productTypeSelectOption').on('change', function(e) {
		//$('#productCompanySelectOption').val(null).trigger('change');
		$("#productCompanySelectOption").find('option').remove();
		getAllProductCompany();
	});
	
	$('#productCompanySelectOption').on('change', function(e) {
//		$('#productCompanySelectOption').val(null).trigger('change');
	//	$("#productCompanySelectOption").find('option').remove();
		getAllProductModelInformation();
	});
	

});
