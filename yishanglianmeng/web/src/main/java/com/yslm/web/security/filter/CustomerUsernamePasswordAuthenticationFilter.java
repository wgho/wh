package com.yslm.web.security.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.yslm.common.Conf;


/**
 * 自定义登录拦截器，可以存在验证码校验
 * 
 */
public class CustomerUsernamePasswordAuthenticationFilter extends
		UsernamePasswordAuthenticationFilter {


	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * UsernamePasswordAuthenticationFilter
	 * #attemptAuthentication(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse)
	 */
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request,
			HttpServletResponse response) throws AuthenticationException {
		/**if user already login throw exception
		if(null != SecurityUtil.currentLogin()){
			throw new AuthenticationOperationException("user.already.login");
		}**/
		// validate code
		String sessionId = request.getSession().getId();
		int counter = request.getSession().getAttribute(sessionId) == null ? 0 : Integer.valueOf(request.getSession().getAttribute(sessionId).toString()).intValue();
		if(counter > 2){
			//校验码
			Object so = request.getSession().getAttribute(Conf.LONGIN_RANDOM_CODE);
			String validateCode = request.getParameter("validatecode");

			if ((null == so || "".equals(so.toString().trim())
					|| null == validateCode || "".equals(validateCode.trim()))
					|| !validateCode.equals(so.toString())) {
				request.setAttribute("error_msg", "请输入正确的验证码");
				try {
					//clear securityContextHolder
					SecurityContextHolder.clearContext();
					String path = request.getContextPath();
					path = Conf.LOGIN_URI.substring(Conf.LOGIN_URI.indexOf(path)+path.length());
					request.getRequestDispatcher(path).forward(request,
							response);
					return null;
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return super.attemptAuthentication(request, response);
	}

}
