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
			dataType:'json',
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
		if($('#productModelSelectOption').val()!='' && $('#productModelSelectOption').val() != null){
		var formData =  {
					"typeId":$('#productTypeSelectOption').val() ,
					"companyId":$('#productCompanySelectOption').val(),
					"model":$('#productModelSelectOption').val()
				};			
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/getAllProductInfoBasedOnModel",
				data:JSON.stringify(formData),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						//$("#productInfoDiv").append('');
						//$("#productInfoDiv").addClass("slick-initialized slick-slider");
						$(".slick-track").empty();
						var cardData = '';
						// cardData = '<div aria-live="polite" class="slick-list draggable" style="padding: 0px 50px;"><div class="slick-track" style="opacity: 1; width: 334px; transform: translate3d(0px, 0px, 0px);" role="listbox">';
						console.log(data["result"]);
						$.each(data["result"], function(i, item) {
						cardData += '<div class="slick-slide slick-current slick-center" data-slick-index="-'+(i)+'" aria-hidden="true" style="width: 334px;" tabindex="-1" role="option" aria-describedby="slick-slide0'+i+'"><div class="ibox"><div class="ibox-content product-box"><div class="product-desc"><div class="row"><div class="col-sm-8" style="height: 39px;"><div id="error-for-product" style=" padding: 7px;" class="alert alert-danger"><p>Stock Does Not Exits</p></div></div><div class="col-sm-4" style="margin-top: -5px;"><input type="hidden" name="hide-prodId" value="'+data["result"][i]["prodId"]+'" id="hide-prodId" tabindex="-1"><span class="product-price" style="top: 6px; right: 6px; ">₹ '+data["result"][i]["productPrice"]+'</span></div></div><p class="product-name"> '+ $('#productModelSelectOption').val()+' </p><div class="small m-t-xs"><div class="row">';	
						//cardData += '<div class="slick-slide slick-current slick-center" data-slick-index="'+i+'" aria-hidden="true" style="width: 334px;" tabindex="-1" role="option" aria-describedby="slick-slide0'+i+'"><div class="ibox"><div class="ibox-content product-box"><div class="product-desc"><div class="row"><div class="col-sm-8" style="height: 39px;"><div id="error-for-product" style=" padding: 7px;" class="alert alert-danger"><p>Stock Does Not Exits</p></div></div><div class="col-sm-4" style="margin-top: -5px;"><input type="hidden" name="hide-prodId" value="'+data["result"][i]["prodId"]+'" id="hide-prodId" tabindex="-1"><span class="product-price" style="top: 6px; right: 6px; ">₹ '+data["result"][i]["productPrice"]+'</span></div></div><p class="product-name"> '+ $('#productModelSelectOption').val()+'</p><div class="small m-t-xs"><div class="row">';
						var specData = JSON.parse(data["result"][i]["productSpecification"]);
						
						$.each(specData,function(i,item){
							cardData +='<div class="col-lg-6"><strong>'+specData[i]["specification"] +' : </strong> '+specData[i]["specification-value"] +'</div>';
						});
						
						cardData +='<div class="col-lg-6"><strong>Color : </strong> '+data["result"][i]["productColour"] +'</div></div></div><div class="m-t text-righ"><div class="row"><div class="col-sm-9"><div class="form-group"><input class="form-control input-sm" id="inputsm" placeholder="Enter Quantity" type="number" min="1" tabindex="-1"></div></div><div class="col-sm-2"><a href="#" style="margin-top: 4px;" class="btn btn-xs btn-outline btn-primary" tabindex="-1">Add<i class="fa fa-plus-square"></i></a></div></div></div></div></div></div></div>';
						//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
						});
						$.each(data["result"], function(i, item) {
						cardData += '<div class="slick-slide slick-current slick-center" data-slick-index="'+i+'" aria-hidden="true" style="width: 334px;" tabindex="-1" role="option" aria-describedby="slick-slide0'+i+'"><div class="ibox"><div class="ibox-content product-box"><div class="product-desc"><div class="row"><div class="col-sm-8" style="height: 39px;"><div id="error-for-product" style=" padding: 7px;" class="alert alert-danger"><p>Stock Does Not Exits</p></div></div><div class="col-sm-4" style="margin-top: -5px;"><input type="hidden" name="hide-prodId" value="'+data["result"][i]["prodId"]+'" id="hide-prodId" tabindex="-1"><span class="product-price" style="top: 6px; right: 6px; ">₹ '+data["result"][i]["productPrice"]+'</span></div></div><p class="product-name"> '+ $('#productModelSelectOption').val()+' </p><div class="small m-t-xs"><div class="row">';	
						//cardData += '<div class="slick-slide slick-current slick-center" data-slick-index="'+i+'" aria-hidden="true" style="width: 334px;" tabindex="-1" role="option" aria-describedby="slick-slide0'+i+'"><div class="ibox"><div class="ibox-content product-box"><div class="product-desc"><div class="row"><div class="col-sm-8" style="height: 39px;"><div id="error-for-product" style=" padding: 7px;" class="alert alert-danger"><p>Stock Does Not Exits</p></div></div><div class="col-sm-4" style="margin-top: -5px;"><input type="hidden" name="hide-prodId" value="'+data["result"][i]["prodId"]+'" id="hide-prodId" tabindex="-1"><span class="product-price" style="top: 6px; right: 6px; ">₹ '+data["result"][i]["productPrice"]+'</span></div></div><p class="product-name"> '+ $('#productModelSelectOption').val()+'</p><div class="small m-t-xs"><div class="row">';
						var specData = JSON.parse(data["result"][i]["productSpecification"]);
						$.each(specData,function(i,item){
							cardData +='<div class="col-lg-6"><strong>'+specData[i]["specification"] +' : </strong> '+specData[i]["specification-value"] +'</div>';
						});
						
						cardData +='<div class="col-lg-6"><strong>Color : </strong> '+data["result"][i]["productColour"] +'</div></div></div><div class="m-t text-righ"><div class="row"><div class="col-sm-9"><div class="form-group"><input class="form-control input-sm" id="inputsm" placeholder="Enter Quantity" type="number" min="1" tabindex="-1"></div></div><div class="col-sm-2"><a href="#" style="margin-top: 4px;" class="btn btn-xs btn-outline btn-primary" tabindex="-1">Add<i class="fa fa-plus-square"></i></a></div></div></div></div></div></div></div>';
						//alert(item['id']+" "+item['name']+" "+item['department']+" "+item['sem']);
						});
						//cardData+='</div></div>';
						
						
						//$("#productInfoDiv").append(cardData);
						//slick-track
						
						$('.slick-track').prepend(cardData);
						//$("#productModelSelectOption").append(opData);
						//$("#productModelSelectOption").select2().trigger('change');
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
	//$('#productCompanySelectOption').val(null).trigger('change');
		$("#productModelSelectOption").find('option').remove();
		getAllProductModelInformation();
	});
	
	$('#productModelSelectOption').on('change', function(e) {
	//$('#productCompanySelectOption').val(null).trigger('change');
		//$("#productModelSelectOption").find('option').remove();
		//$('.slick-track').prepend('');
		getAllProductInformationBasedOnModel();
	});
	
});
