$(document).ready(function() {
	$(".select2_demo_3").select2({
		placeholder: "Select a state",
		allowClear: true
	});

	$(".touchspin1").TouchSpin({
		buttondown_class: 'btn btn-white',
		buttonup_class: 'btn btn-white'
	});

	//function Call
	getAllProductTypes();
	
	
	
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
			debugger;
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/addNewProductTypes",
				data: JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						debugger
						$("#productTypeName").val("");
						$("#error-for-type").show();
						$('#error-for-type').removeClass('alert-danger');
						$("#error-for-type").addClass('alert-success');
						$("#error-for-type p").html(data["result"]);
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
	$("#productTypeName").focus(function(){
		$("#error-for-type").hide();
	});
	
		$("#productCompanyForm").validate({
		rules: {
			productCompanyName: {
				required: true
			}
		},
		messages: {
			productCompanyName: {
				required: "Please fill the product company name"
			}
		},
		submitHandler: function(form, e) {
			e.preventDefault();
			var Formdata = {
				companyName: $("#productCompanyName").val()
			}
			debugger;
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Product/addNewProductCompany",
				data: JSON.stringify(Formdata),
				dataType: 'json',
				success: function(data) {
					if (data["status"] == "OK") {
						debugger
						$("#productCompanyName").val("");
						$("#error-for-company").show();
						$('#error-for-company').removeClass('alert-danger');
						$("#error-for-company").addClass('alert-success');
						$("#error-for-company p").html(data["result"]);

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
	$("#productCompanyName").focus(function(){
		$("#error-for-company").hide();
	});
	
	function getAllProductTypes(){
				debugger;
				$.ajax({
				type: "GET",
				contentType: "application/json",
				url: "/Product/getAllProductTypes",
				data: "",
				dataType: 'json',
				success: function(data) {
					debugger
					if (data["status"] == "OK") {
						debugger
						alert(data);
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


