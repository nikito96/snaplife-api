var $registerButton = $("#registerButton");
$registerButton.click(function () {
	let email = $("#email").val();
	let username = $("#username").val();
	let password = $("#password").val();
	let repeatPassword = $("#repeatPassword").val();
	
	let errorCount = 0;
	
	if (email.length < 10 || email.length > 30) {
		errorCount++;
	}
	
	if (username.length < 6 || username.length > 25) {
		errorCount++;
	}
	
	if (password.length < 8 || password.length > 30) {
		errorCount++;
	}
	
	if (password !== repeatPassword) {
		errorCount++;
	}
	
	if (errorCount == 0) {
		const user = {
			email: email,
			username: username,
			password: password
		};
		
		const data = JSON.stringify(user);
		
		$.ajax({
			url: "/register",
			method: "post",
			contentType: "application/json",
			dataType: "json",
			data: data
		}).done(function (data) {
			console/log(data);
		}).fail(function (data) {
			console/log(data);
		});
	}
});