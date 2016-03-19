package com.yslm.web.security.entrypoint;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;


/**
 * 自定义表单登录配置
 * 
 * 
 */
public class CustomerAuthenticationEntryPoint extends
		LoginUrlAuthenticationEntryPoint {

	@Deprecated
	public CustomerAuthenticationEntryPoint() {
	}

	public CustomerAuthenticationEntryPoint(String loginFormUrl) {
		super(loginFormUrl);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * LoginUrlAuthenticationEntryPoint
	 * #commence(javax.servlet.http.HttpServletRequest,
	 * javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException, ServletException {
		super.commence(request, response, authException);
	}

	@Override
	protected String buildRedirectUrlToLoginPage(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException) {
		String redirectUri = super.buildRedirectUrlToLoginPage(request,
				response, authException);
		// 当前转向的url携带的src参数
		String srcuri = request.getParameter("src");
		if(srcuri != null && !"".equals(srcuri)){
			return redirectUri += "?src=" + new String(Base64.encodeBase64(srcuri.getBytes())); 
		}
		//System.out.println(Base64.class.getProtectionDomain().getCodeSource().getLocation());
		String uri = request.getRequestURI();
		String value = "true";
		if (value != null && Boolean.valueOf(value).booleanValue()) {
			uri = new String(Base64.encodeBase64(uri.getBytes()));
			redirectUri += "?src=" + uri; 
		}
//		request.getSession().setAttribute("requestURI", request.getHeader("referer"));
		return redirectUri;

	}

	@Override
	protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request)
			throws IOException, ServletException {

		String httpsUri = super.buildHttpsRedirectUrlForRequest(request);
		if (httpsUri != null) {
			String srcuri = request.getParameter("src");
			if(srcuri != null && !"".equals(srcuri)){
				return httpsUri += "?src=" + new String(Base64.encodeBase64(srcuri.getBytes()));
			}
			String uri = request.getRequestURI();
			String value = "true";
			if (value != null && Boolean.valueOf(value).booleanValue()) {
				uri = new String(Base64.encodeBase64(uri.getBytes()));
				httpsUri += "?src=" + uri;
			}
		}
		return httpsUri;

	}
}
