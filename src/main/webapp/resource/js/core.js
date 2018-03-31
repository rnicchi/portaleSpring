$(document).ready(function() {
	// validate signup form on keyup and submit
	
	$("form.cmxform").validate({
	});
		// validate login
	$("#login").validate({
		rules: {
			username: {
				required: true,
				minlength: 6
			},
			password: {
				required: true,
				minlength: 6
			}
		},
		messages: {
			username: {
				required: "Campo obbligatorio",
			},
			password: {
				required: "Campo obbligatorio",
				minlength: "Inserire minimo 6 caratteri"
			}			
		}
	});
	
});
