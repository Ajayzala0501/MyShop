//Take i variable for increment raw count



var i = 0;
function addRowToTable(id) {
	var divId = '#' + id + '';
	//alert($(divId).find('#inputsm').val());
	//alert($(divId).find('#hide-speci').val());
	if ($(divId).find('#inputsm').val() != '' && $(divId).find('#inputsm').val() > 0) {
		//alert("ok");
		var prodId = id;
		var specification = $(divId).find('#hide-speci').val();
		var productType = $('#productTypeSelectOption option:selected').text();
		var companyName = $('#productCompanySelectOption option:selected').text();
		
		var model = $('#productModelSelectOption').val();
		var quan = $(divId).find('#inputsm').val();

		var price = Number($(divId).find('#pd-price').text().slice(2)) * Number(quan);
		//alert(prodId+" "+specification+" "+companyName+" "+model+" "+quan+" "+price);
		var raw = '<tr id="rawProIdHide" value="' + prodId + '"><td id="rawHideSpeci" value=\'' + specification + '\'>' + (++i) + '</td><td class="text-center" id="rawProductId" value="' + prodId + '">' + prodId + '</td><td class="text-center" id="rawCompanyName" value="' + companyName + '">' + companyName + '</td><td class="text-center" id="rawModel" value="' + model + '">' + model + '</td><td class="text-center" id="rawQuantity" value="' + quan + '">' + quan + '</td><td class="text-center" class="text-navy" id="rawPrice" value="' + price + '">' + price + '</td><td class="text-center" class="text-navy" id="rawProductType" hidden value="' + productType + '">' + productType + '</td><td class="text-navy text-center"><a href="#tbl-invoice"><i style="color:red;" id="btn-delete" class="btn-delete fa fa-trash-o fa-lg"></i></a></td></tr>'
		addToTableBody(raw, prodId, quan, price);
		$(divId).find('#error-for-product').hide();
	}
	else {
		$(divId).find('#error-for-product').show();
		$(divId).find("#inputsm").focus();
		//		alert("fail");	
	}
	//alert(id);
	//console.log(id);
}

function addToTableBody(data, id, quan, price) {
	var checkIfExits = true;
	$("#tbl-invoice-body tr").each(function() {
		// console.log($(this).attr("value"));
		if ($(this).attr("value") == id) {
			var tempQuan = $(this).find("td:nth-child(5)").text();
			var tempPrice = $(this).find("td:nth-child(6)").text();
			$(this).find("td:nth-child(5)").text(Number(tempQuan) + Number(quan));
			$(this).find("td:nth-child(5)").attr("id", Number(tempQuan) + Number(quan));
			$(this).find("td:nth-child(6)").text(Number(tempPrice) + Number(price));
			$(this).find("td:nth-child(6)").attr("id", Number(tempPrice) + Number(tempPrice));
			getSubTotal();
			checkIfExits = false;
		}

	});
	if (checkIfExits) {
		$("#tbl-invoice-body").append(data);
		getSubTotal();
		setRowCount();
	}
}

function getSubTotal() {
	var subTotal = 0;
	$("#tbl-invoice-body tr").each(function() {
		// console.log($(this).attr("value"));
		subTotal = subTotal + Number($(this).find("td:nth-child(6)").text());
	});

	$("#subTotal").html(subTotal);
	$("#perValue").html((Number(12 / 100) * Number(subTotal)).toFixed(2));
	$("#Total").html(Number(subTotal) + Number(12 / 100) * Number(subTotal));
}
function setRowCount() {
	$("#tbl-invoice-body tr").each(function(ind, v) {
		//$(this).attr("id", i + 1);

		$(this).find("td:nth-child(1)").text(ind + 1);
		//$(this).find("td:nth-child(1)").attr("id", i + 1);
	});
}
$("#tbl-invoice").on("click", ".btn-delete", function() {
	$(this).closest("tr").remove();
	i--;
	setRowCount();
	getSubTotal();
});

$("#btn-generateInvoice").click(function() {
	$("#createInvoiceForm").validate({
		rules: {
			customerName: {
				required: true
			},
			customerMobileNumber: {
				required: true,
				digits: true
			},
			invoiceDate: {
				required: true
			},
			customerAddress: {
				required: true
			}
		},
		messages: {
			customerName: {
				required: "Please fill the customer name"
			},
			customerMobileNumber: {
				required: "Please fill the Customer MobileNumber",
				minlength: "Please enter valid mobile number"
			},
			productModel: {
				required: "Please enter the product model"
			},
			invoiceDate: {
				required: "Please select invoice date"
			},
			customerAddress: {
				required: "Please fill the customer address "
			}
		},
		submitHandler: function(form, e) {
			e.preventDefault();
			if ($('#tbl-invoice-body tr').length > 0) {
				var data = createJSON();
				localStorage.clear();
				localStorage.setItem("InvoiceDetails", JSON.stringify(data));
				window.location.href = 'generateInvoicePage';
			} else {
				$("#invoice-fieldSet").removeClass("scheduler-border");
				$("#invoice-legend").removeClass("scheduler-border");
				$("#invoice-fieldSet").addClass("fieldset-scheduler-border-error");
				$("#invoice-legend").addClass("legend-scheduler-border-error");
				$("#invoice-legend").html("Please Enter Atleast 1 Product");
			}
		}
	});
});


function createJSON() {
	jsonMain = [];

	jsonObj = [];
	$("#tbl-invoice-body tr").each(function(i) {

		var subItem = {};	
		subItem["prodID"] = $(this).children().eq(1).attr("value");
		subItem["prodCompany"] = $.trim($(this).children().eq(2).attr("value"));
		subItem["prodModel"] = $(this).children().eq(3).attr("value");
		subItem["prodQuantity"] = $(this).children().eq(4).attr("value");
		subItem["prodTotalPrice"] = $(this).children().eq(5).attr("value");
		subItem["prodTypes"] = $(this).children().eq(6).attr("value");
		subItem["productSpecification"] = JSON.parse($(this).children().eq(0).attr("value"));
		jsonObj.push(subItem);

	});
	item = {}

	item["customerName"] = $("#customerName").val();
	item["customerMobileNumber"] = $("#customerMobileNumber").val();
	item["customerAddress"] = $("#customerAddress").val();
	item["invoiceID"] = $("#setInvoiceID").html();
	item["invoiceDate"] = $("#invoiceDate").val();
	item["invoiceDetails"] = jsonObj;
	item["invoiceSubTotal"] = $("#subTotal").html();
	item["invoiceTax"] = $("#perValue").html();
	item["invoiceTotal"] = $("#Total").html();
	jsonMain.push(item);

	return jsonMain;
}


function generateId(length) {
	var result = '';
	if (length == 8) {
		var characters = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789';
		var charactersLength = characters.length;
		for (var i = 0; i < length; i++) {
			result += characters.charAt(Math.floor(Math.random() * charactersLength));
		}
	} else {
		var characters = '0123456789';
		var charactersLength = characters.length;
		for (var i = 0; i < length; i++) {
			result += characters.charAt(Math.floor(Math.random() * charactersLength));
		}
	}

	return result;
}

$("#setInvoiceID").html("INV-" + generateId(8) + "-" + generateId(2));
$(document).ready(function() {

	getAllProductTypes();
	function addRowToTable(btn_id) {
		if ($('#btn-' + btn_id).prop('click') == true) {
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

							cardData += '<div id=' + data["result"][i]["prodId"] + '><div class=ibox><div class="ibox-content product-box"><div class=product-desc><div class=row><div class=col-sm-8 style=height:39px><div hidden class="alert alert-danger"style=padding:7px id="error-for-product"><p>Please Enter Atleast 1 Quantity</div></div><div class=col-sm-4 style=margin-top:-5px><input id="hide-prodId" type="hidden" value="' + data["result"][i]["prodId"] + '" name="hide-prodId"><input id="hide-speci" type="hidden" value=\'' + data["result"][i]["productSpecification"] + '\'" name="hide-speci"> <span class="product-price" id="pd-price"style=top:6px;right:6px>₹ ' + data["result"][i]["productPrice"] + '</span></div></div><p class=product-name>' + $('#productModelSelectOption').val() + '</p><small class="text-muted">' + data["result"][i]["prodId"] + '</small><div class="m-t-xs small"><div class=row>'
							var specData = JSON.parse(data["result"][i]["productSpecification"]);

							$.each(specData, function(i, item) {
								cardData += '<div class="col-lg-6"><strong>' + specData[i]["specification"] + ' : </strong> ' + specData[i]["specification-value"] + '</div>';
							});
							var specData2 = JSON.stringify(data["result"][i]["productSpecification"]);
							cardData += '<div class="col-lg-6" id="productColor"><strong>Color : </strong> ' + data["result"][i]["productColour"] + '</div></div></div><div class="m-t text-righ"><div class=row><div class=col-sm-9><div class=form-group><input id="inputsm" type="number" class="inputsm form-control input-sm"min="1" placeholder="Enter Quantity"></div></div><div class="col-sm-2"><button type="button" onclick="addRowToTable(\'' + data["result"][i]["prodId"] + '\')" class="add-row-btn btn btn-outline btn-primary btn-xs" style="margin-top:4px">Add <i class="fa fa-plus-square"></i></button></div></div></div></div></div></div></div>';
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

	function slickCall() {
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

	$(".inputsm").on("change paste keyup", function() {
		alert($(this).val());
	});
});

if (localStorage.getItem("Edit")) {
	var data = JSON.parse(localStorage.getItem("InvoiceDetails"));

	$("#setInvoiceID").html(data[0]["invoiceID"]);
	$("#customerName").val(data[0]["customerName"]);
	$("#customerAddress").val(data[0]["customerAddress"]);
	$("#customerMobileNumber").val(data[0]["customerMobileNumber"]);
	$("#invoiceDate").val(data[0]["invoiceDate"]);
	$("#subTotal").html(data[0]["invoiceSubTotal"]);
	$("#perValue").html(data[0]["invoiceTax"]);
	$("#Total").html(data[0]["invoiceTotal"]);

	var raw = '';
	$(data[0]["invoiceDetails"]).each(function(i) {
		raw += '<tr id="rawProIdHide" value="' + data[0]["invoiceDetails"][i]["prodID"] + '"><td id="rawHideSpeci" value=\'' + data[0]["invoiceDetails"][i]["productSpecification"] + '\'>' + (i + 1) + '</td><td class="text-center" id="rawProductId" value="' + data[0]["invoiceDetails"][i]["prodID"] + '">' + data[0]["invoiceDetails"][i]["prodID"] + '</td><td class="text-center" id="rawCompanyName" value="' + data[0]["invoiceDetails"][i]["prodCompany"] + '">' + data[0]["invoiceDetails"][i]["prodCompany"] + '</td><td class="text-center" id="rawModel" value="' + data[0]["invoiceDetails"][i]["prodModel"] + '">' + data[0]["invoiceDetails"][i]["prodModel"] + '</td><td class="text-center" id="rawQuantity" value="' + data[0]["invoiceDetails"][i]["prodQuantity"] + '">' + data[0]["invoiceDetails"][i]["prodQuantity"] + '</td><td class="text-center" class="text-navy" id="rawPrice" value="' + data[0]["invoiceDetails"][i]["prodTotalPrice"] + '">' + data[0]["invoiceDetails"][i]["prodTotalPrice"] + '</td><td class="text-navy text-center"><a href="#tbl-invoice"><i style="color:red;" id="btn-delete" class="btn-delete fa fa-trash-o fa-lg"></i></a></td></tr>'
	});

	console.log(raw);
	$("#tbl-invoice-body").append(raw);
	localStorage.removeItem("Edit");
	//localStorage.setItem("Edit",false);
}

