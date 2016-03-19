package com.yslm.web.controller.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yslm.common.HTTPCodeStatus;
import com.yslm.model.user.User;
import com.yslm.service.user.UserServices;
import com.yslm.web.controller.BaseController;
import com.yslm.web.entity.ResponseEntity;

@Controller
@RequestMapping("/")
public class UserController extends BaseController {
	
	@Resource(name="userService")
	private UserServices userService;

	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public User get() {
		User user = userService.getById(1);
		return user;
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public ResponseEntity add(User user) {
	    user = userService.add(user);
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, HTTPCodeStatus.HTTPCODE_OK, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value = "/user/checkParam", method = RequestMethod.GET)
	public ResponseEntity checkParam(String type,String v) {
		User user = null;
		if("nickname".equals(type)){
			user = userService.getUserByNickname(v);
			if(user != null){
				return returnSuccess(HTTPCodeStatus.HTTPCODE_NICKNAME_IS_NOT_NULL, HTTPCodeStatus.HTTPCODE_NICKNAME_IS_NOT_NULL_MESSAGE, HTTPCodeStatus.HTTPCODE_NICKNAME_IS_NOT_NULL_MESSAGE);
			}
		}
		if("mobile".equals(type)){
			user = userService.getUserByMobile(v);
			if(user != null){
				return returnSuccess(HTTPCodeStatus.HTTPCODE_MOBILE_IS_NOT_NULL, HTTPCodeStatus.HTTPCODE_MOBILE_IS_NOT_NULL_MESSAGE, HTTPCodeStatus.HTTPCODE_MOBILE_IS_NOT_NULL_MESSAGE);
			}
		}
		if("email".equals(type)){
			user = userService.getUserByEmail(v);
			if(user != null){
				return returnSuccess(HTTPCodeStatus.HTTPCODE_EMAIL_IS_NOT_NULL, HTTPCodeStatus.HTTPCODE_EMAIL_IS_NOT_NULL_MESSAGE, HTTPCodeStatus.HTTPCODE_EMAIL_IS_NOT_NULL_MESSAGE);
			}
		}
		return returnSuccess(HTTPCodeStatus.HTTPCODE_OK, HTTPCodeStatus.HTTPCODE_OK, HTTPCodeStatus.HTTPCODE_OK_MESSAGE);
	}
}
