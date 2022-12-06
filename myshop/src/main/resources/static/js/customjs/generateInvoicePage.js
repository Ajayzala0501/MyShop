var data = JSON.parse(localStorage.getItem("InvoiceDetails"));
$("#invoiceID").html(data[0]["invoiceID"]);
$("#customerName").html(data[0]["customerName"]);
$("#customerAddress").html(data[0]["customerAddress"]);
$("#customerMobileNumber").html(data[0]["customerMobileNumber"]);
$("#invoiceDate").html(data[0]["invoiceDate"]);
$("#invoiceSubTotal").html(data[0]["invoiceSubTotal"]);
$("#invoiceTax").html(data[0]["invoiceTax"]);
$("#invoiceTotal").html(data[0]["invoiceTotal"]);

console.log(data);