package com.yslm.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import com.yslm.model.user.User;
import com.yslm.web.security.util.SecurityUtil;


/**
 * 登陆成功handler
 *
 */
public class CutomerAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {
	
	private static Log logger = LogFactory.getLog(CutomerAuthenticationSuccessHandler.class);
	

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		//获取当前登陆用户
		User user = SecurityUtil.currentLogin();
		if(user == null){
			logger.error("failed get current login user, user had login success, but can not found");
			throw new RuntimeException("failed.found.current.login");
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}
	
}
