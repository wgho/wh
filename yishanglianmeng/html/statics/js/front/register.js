function register() {
	common.ajax({
		url: '/web/user', // 跳转到 action  
		type: 'post',
		data: $('#registerForm').serialize(),
		cache: false,
		dataType: 'json',
		success: function(data) {
			if (data = 200) {
				common.alert({
					content: '注册成功，请登录',
					_callback: (function() {
						location.href = "login.html";
					})
				});
			}
		},
		error: function() {
			common.alert({
				content: '系统异常，请重试'
			});
		}
	});
};

function checkParam(type){
	var v = "";
	v = $("#"+type).val();
		common.ajax({
		url: '/web/user/checkParam?type='+type+"&v="+v, // 跳转到 action  
		type: 'get',
		cache: false,
		success: function(data) {
			if (data != 200) {
				common.alert({
					content: data,
				});
				return false;
			}
		},
		error: function() {
			common.alert({
				content: '系统异常，请重试'
			});
		}
	});
}
