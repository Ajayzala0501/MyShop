var data = JSON.parse(localStorage.getItem("InvoiceDetails"));
$("#invoiceID").html(data[0]["invoiceID"]);
$("#customerName").html(data[0]["customerName"]);
$("#customerAddress").html(data[0]["customerAddress"]);
$("#customerMobileNumber").html(data[0]["customerMobileNumber"]);
$("#invoiceDate").html(data[0]["invoiceDate"]);
$("#invoiceSubTotal").html(data[0]["invoiceSubTotal"]);
$("#invoiceTax").html(data[0]["invoiceTax"]);
$("#invoiceTotal").html(data[0]["invoiceTotal"]);
var raw = '';
$(data[0]["invoiceDetails"]).each(function(i) {
	raw += '<tr id="rawProIdHide" value="' + data[0]["invoiceDetails"][i]["prodID"] + '"><td id="rawHideSpeci" value=\'' + data[0]["invoiceDetails"][i]["productSpecification"] + '\'>' + (i + 1) + '</td><td class="text-center" id="rawProductId" value="' + data[0]["invoiceDetails"][i]["prodID"] + '">' + data[0]["invoiceDetails"][i]["prodID"] + '</td><td class="text-center" id="rawCompanyName" value="' + data[0]["invoiceDetails"][i]["prodCompany"] + '">' + data[0]["invoiceDetails"][i]["prodCompany"] + '</td><td class="text-center" id="rawModel" value="' + data[0]["invoiceDetails"][i]["prodModel"] + '">' + data[0]["invoiceDetails"][i]["prodModel"] + '</td><td class="text-center" id="rawQuantity" value="' + data[0]["invoiceDetails"][i]["prodQuantity"] + '">' + data[0]["invoiceDetails"][i]["prodQuantity"] + '</td><td class="text-center" class="text-navy" id="rawPrice" value="' + data[0]["invoiceDetails"][i]["prodTotalPrice"] + '">' + data[0]["invoiceDetails"][i]["prodTotalPrice"] + '</td></tr>'
});

console.log(raw);
$("#tbl-generate-invoice-body").append(raw);

$("#btn-invoice-print").click(function() {
	window.print();
});

$("#btn-edit-invoice").click(function() {
	localStorage.setItem("Edit", true);
	window.location.href = 'createNewInvoicePage';
})

var totalQuan=0;
$(data[0]["invoiceDetails"]).each(function(i) {
	totalQuan += Number(data[0]["invoiceDetails"][i]["prodQuantity"]);
});

$("#btn-save-invoice").click(function() {
	var res = JSON.stringify(data[0]["invoiceDetails"]).replaceAll("\"", "'");
	var formData = {
		"customerName": $('#customerName').html(),
		"customerAddess": $('#customerAddress').html(),
		"customerMobileNumber": $('#customerMobileNumber').html(),
		"invoiceID": $('#invoiceID').html(),
		"customerInvoiceProductDetails":res ,
		"totalQuantity": Number(totalQuan),
		"totalAmount": Number($('#invoiceTotal').html()),
		"invoiceDate": $('#invoiceDate').html(),


	};
	$.ajax({
		type: "POST",
		contentType: "application/json",
		url: "/ManageInvoice/addNewCustomerInvoiceDetails",
		data: JSON.stringify(formData),
		dataType: 'json',
		success: function(data) {
			if (data["status"] == "OK") {
				alert("Done")
			}
		}
	})
})



console.log(data);