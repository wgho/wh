function loginSubmit() {

	$.ajax({
		url: "/web/login",
		data: $('#loginForm').serialize(),
		type: "post",
		beforeSend: function(xhr) {
			xhr.setRequestHeader("X-Ajax-call", "true");
		},
		success: function(data) {
			
			if (data.code == 200) {
				window.location.href = "/html/admin/index.html";
			} else {
				alert("登录失败");
			}
		}
	});
}
//按键提交登陆
$(document).keyup(function(event) {
	if (event.keyCode == 13) {
		loginSubmit();
	}
});