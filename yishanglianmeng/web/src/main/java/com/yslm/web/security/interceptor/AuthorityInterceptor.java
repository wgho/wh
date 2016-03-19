package com.yslm.web.security.interceptor;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.yslm.common.HTTPCodeStatus;
import com.yslm.model.user.User;
import com.yslm.model.user.UserRole;
import com.yslm.web.entity.ResponseEntity;
import com.yslm.web.security.annotation.Authority;
import com.yslm.web.security.common.AuthorityType;
import com.yslm.web.security.util.SecurityUtil;

/**
 * 权限控制类
 *
 * @date 2014年6月28日
 */
public class AuthorityInterceptor extends HandlerInterceptorAdapter {

	private static final Log LOG = LogFactory.getLog(AuthorityInterceptor.class);

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#
	 * preHandle (javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 访问静态资源
		String uri = request.getRequestURI();
		if (uri.contains("/statics/") || uri.contains("/static/") || uri.contains("logout") || uri.contains("/download")
				|| uri.contains("/html") || uri.contains("/index.html") || uri.contains("/login")) {
			return true;
		}
		if (!(handler instanceof HandlerMethod)) {
            return true;
        }
		HandlerMethod mHandler = (HandlerMethod) handler;
		Authority authority = mHandler.getMethodAnnotation(Authority.class);
		// not found annotation, allow every one
		if (null == authority) {
			return true;
		}
		// author type
		AuthorityType type = authority.type();
		if (AuthorityType.ANYMOUS.equals(type)) {
			return true;
		}
		User user = SecurityUtil.currentLogin();
		if (null == user) {
			return _reponseAuthenticationError(response);
		}
		Set<UserRole> roles = user.getRoles();
		if(roles.contains(UserRole.NONMEMBER) && AuthorityType.MEMBER.equals(type)){
		    return _reponseAuthenticationDenied(response);
		}
		
		return true;
	}

	/**
	 * Print authentication error message
	 *
	 * @param response
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unused" })
	private boolean _reponseAuthenticationError(HttpServletResponse response) {
		int errorCode = HTTPCodeStatus.HTTPCODE_USER_NOTLOGIN;
		String httpResponseMessage = HTTPCodeStatus.HTTPCODE_USER_NOTLOGIN_MESSAGE;
		LOG.info("response msg: " + httpResponseMessage);
		try {
			ResponseEntity re = new ResponseEntity(errorCode, null, httpResponseMessage);
			response.addHeader(errorCode + "", URLEncoder.encode(httpResponseMessage, "UTF-8"));
			response.addHeader("Content-Type", "application/json; charset=utf-8");
			String code = "\"code\" : " + "\"" + errorCode + "\"";
			String msg = "\"message\" : \"" + httpResponseMessage + "\"";
			String result = "{" + code + "," + msg + "}";
			LOG.info("result: " + result);
			response.getWriter().write(result);
			response.getWriter().flush();
		} catch (UnsupportedEncodingException e) {
			response.addHeader(errorCode + "", "security.user.error");
			LOG.error("response authentication error response exception ", e);
		} catch (IOException e) {
			LOG.error("response authentication error response exception ", e);
		}
		return false;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
    private boolean _reponseAuthenticationDenied(HttpServletResponse response) {
        int errorCode = HTTPCodeStatus.HTTPCODE_PERMISSION_DENIED;
        String httpResponseMessage = HTTPCodeStatus.HTTPCODE_PERMISSION_DENIED_MESSAGE;
        LOG.info("response msg: " + httpResponseMessage);
        try {
            ResponseEntity re = new ResponseEntity(errorCode, null, httpResponseMessage);
            response.addHeader(errorCode + "", URLEncoder.encode(httpResponseMessage, "UTF-8"));
            response.addHeader("Content-Type", "application/json; charset=utf-8");
            String code = "\"code\" : " + "\"" + errorCode + "\"";
            String msg = "\"message\" : \"" + httpResponseMessage + "\"";
            String result = "{" + code + "," + msg + "}";
            LOG.info("result: " + result);
            response.getWriter().write(result);
            response.getWriter().flush();
        } catch (UnsupportedEncodingException e) {
            response.addHeader(errorCode + "", "security.user.error");
            LOG.error("response authentication error response exception ", e);
        } catch (IOException e) {
            LOG.error("response authentication error response exception ", e);
        }
        return false;
    }

}
