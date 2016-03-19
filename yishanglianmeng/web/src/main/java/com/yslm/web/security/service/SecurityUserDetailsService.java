package com.yslm.web.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.yslm.model.user.User;
import com.yslm.service.user.UserServices;
import com.yslm.util.validate.ValidateUtil;
import com.yslm.web.security.service.entity.SecurityUser;


public class SecurityUserDetailsService implements UserDetailsService {

	@Autowired
	private UserServices userService;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if (null == username || "".equals(username)) {
			throw new RuntimeException("登录名称和密码不可为空");
		}
		//判断是否属于邮箱还是手机
		User user = null;
		boolean isEmail = false;
		if(ValidateUtil.isMail(username)){
			user = userService.getUserByEmail(username);
			isEmail = true;
		}else{
			user = userService.getUserByMobile(username);
		}
		if (null == user) {
			throw new RuntimeException("请输入正确的用户名称和密码");
		}

		return new SecurityUser(user, isEmail);

	}

}
