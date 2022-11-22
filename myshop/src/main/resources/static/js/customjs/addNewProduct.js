$(document).ready(function() {
	$(".select2_demo_3").select2({
		placeholder: "Select a state",
		allowClear: true
	});

	$(".touchspin1").TouchSpin({
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});


	// Defining the array for loading the information.
	
	// Array creating for product size
	var productSizeArray = ["24 inches","32 inches","43 inches","55 inches","65 inches","75 inches","85 inches"];

	// Array creating for product capacity
	var productCapacityArray = ["121-130 litres","240-350 litres","231-500 litres","500 litres"];

	// Array creating for product ram
	var productRAMArray = ["1 GB","2 GB","4 GB","6 GB","8 GB","12 GB","16 GB","32 GB"];

	// Array creating for product rom
	var productROMArray = ["1 GB","2 GB","4 GB","8 GB","16 GB","32 GB","64 GB","128 GB","256 GB","512 GB","1 TB"];

	// Array creating for tv type
	var productTVTypeArray = ["LED","CURVED","OLED"];
	
	// Array creating for Refrigerator type
	var productRefrigeratorTypeArray = ["Single Door","Double Door","Tripal Door","Side By Side"];

	//function Call
	getAllProductTypes(true, "");


	// For add new product types 
	$("#productTypeForm").validate({
		rules: {
			productTypeName: {
				required: true
			}
		},
		messages: {
			productTypeName: {
				required: "Please fill the product type name"
			}
		},
		submitHandler: function(form, e) {
			e.preventDefault();
			var Formdata = {
				typeName: $("#productTypeName").val()
			}
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/addNewProductTypes",
				data: JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						$("#productTypeName").val("");
						$("#error-for-type").show();
						$('#error-for-type').removeClass('alert-danger');
						$("#error-for-type").addClass('alert-success');
						$("#error-for-type p").html("New Type Added Successfully");
						var prepairedData = "<option value='" + data['result']['typeId'] + "'>" + data['result']['typeName'] + "</option>"
						getAllProductTypes(false, prepairedData);
					} else {
						$("#error-for-type").show();
						$('#error-for-type').removeClass('alert-success');
						$("#error-for-type").addClass('alert-danger');
						$("#error-for-type p").html(data["result"]);
					}
				},
				error: function(error) {
					$("#error-for-type").show();
					$('#error-for-type').removeClass('alert-success');
					$("#error-for-type").addClass('alert-danger');

				}
			})
		}
	});
	$("#productTypeName").focus(function() {
		$("#error-for-type").hide();
	});


	//For add new product company
	$("#productCompanyForm").validate({
		rules: {
			productCompanyName: {
				required: true
			},
			productTypeSelectOptionWithCompany: {
				required: true
			}
		},
		messages: {
			productCompanyName: {
				required: "Please fill the product company name"
			},
			productTypeSelectOptionWithCompany: {
				required: "Please add the product types"
			}
		},
		submitHandler: function(form, e) {
			e.preventDefault();
			var Formdata = {
				companyName: $("#productCompanyName").val(),
				productTypeId: $("#productTypeSelectOptionWithCompany").val()
			}
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/addNewProductCompany",
				data: JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						$("#productCompanyName").val("");
						$("#error-for-company").show();
						$('#error-for-company').removeClass('alert-danger');
						$("#error-for-company").addClass('alert-success');
						$("#error-for-company p").html("New Company Added Successfully");
						if ($("#productTypeSelectOptionWithCompany").val() == $("#productTypeSelectOption").val()) {
							var prepairedData = "<option value='" + data['result']['companyId'] + "'>" + data['result']['companyName'] + "</option>"
							getAllProductCompany(false, prepairedData);
						}

					} else {
						$("#error-for-company").show();
						$('#error-for-company').removeClass('alert-success');
						$("#error-for-company").addClass('alert-danger');
						$("#error-for-company p").html(data["result"]);
					}
				},
				error: function(error) {
					$("#error-for-company").show();
					$('#error-for-company').removeClass('alert-success');
					$("#error-for-company").addClass('alert-danger');

				}
			})
		}
	});
	$("#productCompanyName").focus(function() {
		$("#error-for-company").hide();
	});


	// For load all product types from database
	function getAllProductTypes(checkAlreadyNotPresentData, OptionData) {
		if (checkAlreadyNotPresentData) {
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
					} else {
						$("#error-for-type").show();
						$('#error-for-type').removeClass('alert-success');
						$("#error-for-type").addClass('alert-danger');
						$("#error-for-type p").html(data["result"]);
					}
				},
				error: function(error) {
					$("#error-for-type").show();
					$('#error-for-type').removeClass('alert-success');
					$("#error-for-type").addClass('alert-danger');

				}
			})
		} else {
			$("#productTypeSelectOption").append(OptionData);
			$("#productTypeSelectOptionWithCompany").append(OptionData);
			$("#productTypeSelectOption").select2().trigger('change');
			$("#productTypeSelectOptionWithCompany").select2().trigger('change');
		}

	}

	//For load all the product company from database
	function getAllProductCompany(checkAlreadyNotPresentData, OptionData) {
		debugger;
		if (checkAlreadyNotPresentData) {
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
						debugger
						$("#productCompanySelectOption").append(opData);
						$("#productCompanySelectOption").select2().trigger('change');
						opData = '';
					} else {
						$("#error-for-company").show();
						$('#error-for-company').removeClass('alert-success');
						$("#error-for-company").addClass('alert-danger');
						$("#error-for-company p").html(data["result"]);
					}
				},
				error: function(error) {
					$("#error-for-company").show();
					$('#error-for-company').removeClass('alert-success');
					$("#error-for-company").addClass('alert-danger');

				}
			})
		} else {
			$("#productCompanySelectOption").append(OptionData);
			$("#productCompanySelectOption").select2().trigger('change');
		}
	}
	$('#productTypeSelectOption').on('change', function(e) {
	$('#productCompanySelectOption').val(null).trigger('change');
		$("#productCompanySelectOption").find('option').remove();
		getAllProductCompany(true, "");
		loadDataBasedOnProductTypes($("#productTypeSelectOption").text());
	});

	// For add new product information.	
	$("#productInfoForm").validate({
		rules: {
			productTypeSelectOption: {
				required: true
			},
			productCompanySelectOption: {
				required: true
			},
			productModel: {
				required: true
			},
			productColor: {
				required: true
			},
			productSize: {
				required: true
			},
			productRAM: {
				required: true
			},
			productROM: {
				required: true
			},
			productTvType: {
				required: true
			},
			productQuantity: {
				required: true
			},
			productPrice: {
				required: true
			}

		},
		messages: {
			productTypeSelectOption: {
				required: "Please select the product type"
			},
			productCompanySelectOption: {
				required: "Please select the product company"
			},
			productModel: {
				required: "Please enter the product model"
			},
			productColor: {
				required: "Please chooes the product color"
			},
			productSize: {
				required: "Please select the product size"
			},
			productRAM: {
				required: "Please select the product RAM"
			},
			productROM: {
				required: "Please select the product ROM"
			},
			productTvType: {
				required: "Please select the product TV Type"
			},
			productQuantity: {
				required: "Please enter the product quantity ",
				min: "Please enter atleast one product quantity"
			},
			productPrice: {
				required: "Please enter the product price",
				min: "Please enter valid product price"
			}
		},
		submitHandler: function(form, e) {
			$("#productTvType").html('');
			e.preventDefault();
			var Formdata = {
				//email: $("#email").val()
			}
			$("#error-for-product").show();
			$('#error-for-product').removeClass('alert-danger');
			$("#").addClass('alert-success');
			$("#error-for-product p").html("Please wait.. while generating token!!!");
			$.ajax({
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					/** 
					if (data["status"] == "OK") {
						$("#error-for-product").show();
						$('#error-for-product').removeClass('alert-danger');
						$("#error-for-product").addClass('alert-success');
						$("#error-for-product p").html("Token is send to registered email, Please Check!!");
						$("#submit").hide();
					}*/
					alert("done");
				},
				error: function(error) {
					$("#error-for-product").show();
					$('#error-for-product').removeClass('alert-success');
					$("#error-for-product").addClass('alert-danger');
					$("#error-for-product p").html(error.responseJSON["result"]);
				}
			})
		}
	});


// Function defining for loading the data on product type change

function loadDataBasedOnProductTypes(productType){
	
	alert(productType);
}

});


