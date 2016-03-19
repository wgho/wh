package com.yslm.web.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

/**
 * 登录失败的hander
 *
 */
public class CustomerAuthenticationFailedHandler extends
		SimpleUrlAuthenticationFailureHandler {
	
	/**
	 * 控制器，校验码的控制器
	 */
	@Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException exception) throws IOException, ServletException {

        String sessionId = request.getSession().getId();
        if (null == request.getSession().getAttribute(sessionId)) {
            request.getSession().setAttribute(sessionId, 1);
        } else {
            int counter = (Integer) request.getSession().getAttribute(sessionId);
            counter++;
            request.getSession().setAttribute(sessionId, counter);
        }

        if (exception.getMessage().equals("User is disabled")) {
            request.getSession().setAttribute("isEnable", "false");
        }

        super.onAuthenticationFailure(request, response, exception);

    }

}
