$(document).ready(function () {
			$(".select2_demo_3").select2({
				placeholder: "Select a state",
				allowClear: true
			});

			$(".touchspin1").TouchSpin({
				buttondown_class: 'btn btn-white',
				buttonup_class: 'btn btn-white'
			});
	
			$("#productTypeForm").validate({
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
				submitHandler: function (form, e) {
					e.preventDefault();
					var Formdata = {	
						typeName: $("#productCompanyName").html()
					}
					$.ajax({
						type: "POST",
						contentType: "application/json",
						url: "/Product/addNewProductTypes",
						data: JSON.stringify(Formdata),
						dataType: 'json',
						success: function (data) {
							if (data["status"] == "OK") {
								window.location.href = "/login";
							} else {
								$("#error").show();
								$('#error').removeClass('alert-success');
								$("#error").addClass('alert-danger');
								$("#error p").html("Password Not Reset Please Try Again!");
							}
						},
						error: function (error) {
							$("#error").show();
							$('#error').removeClass('alert-success');
							$("#error").addClass('alert-danger');
							$("#error p").html(error.responseJSON["result"]);
						}
					})
				}
			});
		});
	
		
		