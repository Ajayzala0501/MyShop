
$(document).ready(function() {
	$(".select2_demo_3").select2({
		placeholder: "Select a state",
		allowClear: true
	});

	$(".touchspin1").TouchSpin({
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});

	$('.typeahead_1').typeahead({
		source: ["RAM", "ROM", "INCH", "TV TYPE", "LITERS", "WATT", "REFRIGERATOR TYPES", "CATEGORY"]
	});

	$("#specification-table-hide-show").hide();
	$("#btn-add-specification").click(function() {
		$("#specification-table-hide-show").toggle(500);
	});


	if($("#prodSpecification").html()!= ''){
		$("#add-quantity-div").hide();
		$("#update-header-1").html("Update Product");
		$("#update-header-2").html("Update Product");
		$("#update-header-3").html("Update Existing Product Information");
		$("#update-btn-text").html("Update Product Information");
		$("#specification-table-hide-show").show();
		var data = JSON.parse($("#prodSpecification").html());
		
		var tblRaw = '';
		$(data).each(function(i){
		tblRaw +=`<tr class='text-center'class="main-tr" id="${++i}">
		<td id='${i}' class='first'>${i}</td>
		<td><input type="text" style="height:25px" id="spec-input" name="spec-input" value='${data[i-1]["specification"]}' class="spec-input typeahead_1 form-control"></td>
		<td><input type="text" style="height:25px" id="spec-value" name="spec-value" value='${data[i-1]["specification-value"]}' class="spec-input form-control"></td>
		<td class="text-navy text-center"> <a href="#"><i style='color:red;' id='btn-delete' class="btn-delete fa fa-trash-o fa-lg"></i></a></td>											
        </tr>`	
					
		});
		$('#tbl-body-specification-input').append(tblRaw);
		}
		else{
		$("#update-header-1").html("Add New Product");
		$("#update-header-2").html("Add New Product");
		$("#update-header-3").html("Add New Product Information");
		$("#update-btn-text").html("Add Product Information");
			$("#add-quantity-div").show();
		
	}
	

	/*
	//Function Uses Name That Color
	
	let result = ntc.name('#6195ed');
	
	let rgb_value = result[0];      // #6495ed         : RGB value of closest match
	let specific_name = result[1];  // Cornflower Blue : Color name of closest match
	let shade_value = result[2];    // #0000ff         : RGB value of shade of closest match
	let shade_name = result[3];     // Blue            : Color name of shade of closest match
	let is_exact_match = result[4]; // false           : True if exact color match
	
	*/
	$("#tbl-body-specification-input").on('keydown', function() {
		$(".typeahead_1").typeahead({
			source: ["RAM", "ROM", "INCH", "TV TYPE", "LITERS", "WATT", "REFRIGERATOR TYPES", "CATEGORY"]
		});
	});




	$("#btn-add-row").click(function() {
		var rowCount = $("#tbl-body-specification-input tr").length;
		$('#tbl-body-specification-input').append(`<tr class='text-center'class="main-tr" id="${++rowCount}">
		<td id='${rowCount}' class='first'>${rowCount}</td>
		<td><input type="text" style="height:25px" id="spec-input" name="spec-input" class="spec-input typeahead_1 form-control"></td>
		<td><input type="text" style="height:25px" id="spec-value" name="spec-value" class="spec-input form-control"></td>
		<td class="text-navy text-center"> <a href="#"><i style='color:red;' id='btn-delete' class="btn-delete fa fa-trash-o fa-lg"></i></a></td>											
        </tr>`);
	});


	// Defining the array for loading the information.

	// Array creating for product size
	var productSizeArray = ["24 inches", "32 inches", "43 inches", "55 inches", "65 inches", "75 inches", "85 inches"];

	// Array creating for product capacity
	var productCapacityArray = ["121-130 litres", "240-350 litres", "231-500 litres", "500 litres"];

	// Array creating for product ram
	var productRAMArray = ["1 GB", "2 GB", "4 GB", "6 GB", "8 GB", "12 GB", "16 GB", "32 GB"];

	// Array creating for product rom
	var productROMArray = ["1 GB", "2 GB", "4 GB", "8 GB", "16 GB", "32 GB", "64 GB", "128 GB", "256 GB", "512 GB", "1 TB"];

	// Array creating for tv type
	var productTVTypeArray = ["LED", "CURVED", "OLED"];

	// Array creating for Refrigerator type
	var productRefrigeratorTypeArray = ["Single Door", "Double Door", "Tripal Door", "Side By Side"];

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
	});
	
	$("#productColor").change(function(event){
		let result = ntc.name(event.target.value);
		$("#productColorName").val(result[1]);			
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
			productColorName: {
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
			productColorName: {
				required: "Please chooes the product color"
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
		var url = $("#urlTag").html();
		var JSONdata=createJSON();
			e.preventDefault();
			debugger
			var Formdata = {
			 typeId:$("#productTypeSelectOption").val(),
			 companyId:$("#productCompanySelectOption").val(),
			 productModel:$("#productModel").val(),
			 productColour:$("#productColorName").val(),
			 productQuantity:$("#productQuantity").val(),
			 productPrice:$("#productPrice").val(),
			 productSpecification:JSONdata			
			}
			
			$("#error-for-product").show();
			$('#error-for-product').removeClass('alert-danger');
			$("#error-for-product").addClass('alert-success');
			$("#error-for-product p").html("Please wait.. !!!");
			debugger
			$.ajax({
				type: "POST",
				contentType: "application/json",
				data: JSON.stringify(Formdata),
				url: url,
				dataType: 'json',
				success: function(data) {				
					if (data["status"] == "OK") {
						$("#error-for-product").show();
						$('#error-for-product').removeClass('alert-danger');
						$("#error-for-product").addClass('alert-success');
						$("#error-for-product p").html(data["result"]);	
						$("#productInfoForm").trigger("reset");		
					}
					
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


	function createJSON() {
		jsonObj = [];
		$("#tbl-body-specification-input tr").each(function() {
			var id = $(this).find("td:nth-child(1)").html();
			var spec = $(this).find("td:nth-child(2) input[name=spec-input]").val();
			var specvalue = $(this).find("td:nth-child(3) input[name=spec-value]").val();
			item = {}
			item["id"] = id;
			item["specification"] = spec;
			item["specification-value"] = specvalue;
			jsonObj.push(item);
		});
	 return	JSON.stringify(jsonObj);		
	}
	function setRowCount() {
		$("#tbl-body-specification-input tr").each(function(i, v) {
			$(this).attr("id", i + 1);
			$(this).find("td:nth-child(1)").text(i + 1);
			$(this).find("td:nth-child(1)").attr("id", i + 1);
		});
	}
	$("#tbl-specification-input").on("click", ".btn-delete", function() {
		$(this).closest("tr").remove();
		setRowCount();
	});
});


