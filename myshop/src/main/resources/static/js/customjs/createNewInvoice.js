function addRowToTable(id,specValue,company,model,productQuan,productPrice){
	
	alert(id+" "+specValue+" "+company+" "+model+" "+productQuan+" "+productPrice);
	console.log(id+" "+specValue+" "+company+" "+model+" "+productQuan+" "+productPrice);
	}

$(document).ready(function() {
		
	getAllProductTypes();
	function addRowToTable(btn_id){	
		if($('#btn-'+btn_id).prop('click')==true){
				alert(btn_id);		
		}
	}
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
		if ($('#productCompanySelectOption').val() != '' && $('#productCompanySelectOption').val() != null) {
			var formData = {
				"typeId": $('#productTypeSelectOption').val(),
				"companyId": $('#productCompanySelectOption').val()
			};
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/getAllProductModel",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						var opData = '';
						//console.log(data);
						$.each(data["result"], function(i, item) {
							opData += "<option value='" + item + "'>" + item + "</option>";
							//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
						});
						$("#productModelSelectOption").append(opData);
						$("#productModelSelectOption").select2().trigger('change');
						opData = '';
					}
				}
			})
		}
	}

	//For load all the product company from database
	function getAllProductInformationBasedOnModel() {
		if ($('#productModelSelectOption').val() != '' && $('#productModelSelectOption').val() != null) {
			var formData = {
				"typeId": $('#productTypeSelectOption').val(),
				"companyId": $('#productCompanySelectOption').val(),
				"model": $('#productModelSelectOption').val()
			};
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/getAllProductInfoBasedOnModel",
				data: JSON.stringify(formData),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						 destroyCarousel();
						$("#productInfoDiv").empty();
						var cardData = '';
						$.each(data["result"], function(i, item) {
							
							cardData += '<div><div class=ibox><div class="ibox-content product-box"><div class=product-desc><div class=row><div class=col-sm-8 style=height:39px><div class="alert alert-danger"style=padding:7px id=error-for-product><p>Please Enter Atleast 1 Quantity!</div></div><div class=col-sm-4 style=margin-top:-5px><input id=hide-prodId type=hidden value="' + data["result"][i]["prodId"] + '" name=hide-prodId><input id="hide-speci" type="hidden" value="' + data["result"][i]["productSpecification"] + '" name="hide-speci"> <span class=product-price style=top:6px;right:6px>₹ ' + data["result"][i]["productPrice"] + '</span></div></div><p class=product-name>' + $('#productModelSelectOption').val() + '<div class="m-t-xs small"><div class=row>'
							var specData = JSON.parse(data["result"][i]["productSpecification"]);

							$.each(specData, function(i, item) {
								cardData += '<div class="col-lg-6"><strong>' + specData[i]["specification"] + ' : </strong> ' + specData[i]["specification-value"] + '</div>';
							});
							cardData += '<div class="col-lg-6"><strong>Color : </strong> ' + data["result"][i]["productColour"] + '</div></div></div><div class="m-t text-righ"><div class=row><div class=col-sm-9><div class=form-group><input id="inputsm" type=number class="form-control input-sm"min=1 placeholder="Enter Quantity"></div></div><div class=col-sm-2><button type="button" id="btn-add" onclick="addRowToTable(\''+data["result"][i]["prodId"]+'\','+JSON.stringify(specData)+',\''+$('#productCompanySelectOption').find(":selected").text()+'\',\''+$('#productModelSelectOption').val()+'\',\''+$("#inputsm").val()+'\',\''+data["result"][i]["productPrice"]+'\')" class="add-row-btn btn btn-outline btn-primary btn-xs" style=margin-top:4px>Add <i class="fa fa-plus-square"></i></button></div></div></div></div></div></div></div>';
						});

						$("#productInfoDiv").append(cardData);
						slickCall();
					}
				}
			})
		}
	}
	$('#productTypeSelectOption').on('change', function(e) {
		$("#productCompanySelectOption").find('option').remove();
		getAllProductCompany();
	});

	$('#productCompanySelectOption').on('change', function(e) {
		$("#productModelSelectOption").find('option').remove();
		getAllProductModelInformation();
	});

	$('#productModelSelectOption').on('change', function(e) {
		getAllProductInformationBasedOnModel();


	});

	function destroyCarousel() {
		if ($('.slick_demo_2').hasClass('slick-initialized')) {
			$('.slick_demo_2').slick('unslick');
		}
	}

	function slickCall(){
		$('.slick_demo_2').slick({
			infinite: true,
			slidesToShow: 2,
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
		})
	}
	
	
	
	
});
