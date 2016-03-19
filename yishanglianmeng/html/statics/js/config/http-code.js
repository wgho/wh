//http结果状态码
httpCode = {
	/**
	 * http请求结果正常
	 */
	ok: 5000,
	/**
	 * http请求结果失败
	 */
	fatal: 7000,
	/**
	 * http请求结果没有权限
	 */
	no_permission: 403,
	/**
	 * http请求结果未找到
	 */
	no_font_permission: 404,
	/**
	 * http请求结果超时
	 */
	no_font_permission: 3006,
	/**
	 * 无效的请求参数
	 */
	param_invalid: 1002,
	/**
	 * 用户名已存在
	 */
	user_name_exists: 1003,
	/**
	 * 用户未登录
	 */
	user_not_login: 201,
	/**
	 * 存在子菜单
	 */
	delete_exists: 1006,
	/**
	 * http请求地址没有对应服务
	 */
	not_found: 5001,
	/**
	 * 发送验证码成功
	 */
	code_send_ok: 1001,
	/**
	 * 验证码输入错误
	 */
	code_check_error: 4001,
	/**
	 * 系统异常
	 */
	sys_error: 500,
	/**
	 * 内部异常
	 */
	fatal_internal_error: 9000,

	/**
	 * 用户不存在
	 */
	user_not_exists: 1000,
	
	/**
	 * 运行异常
	 */
	runtime_exception: 7002
};

/**
 * 处理请求失败的结果数据。服务器端失败后必须返回错误code和ms
 * @param r 返回结果
 */
common.handleFailedCode = function(r) {
	if (r.code == httpCode.user_not_login) {
		window.location.href = '/login.html';
		return;
	} else if (r.code != httpCode.ok) {
		common.alert({
			content: r.message
		});
	}
}