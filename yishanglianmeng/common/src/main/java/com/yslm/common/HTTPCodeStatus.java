package com.yslm.common;

/**
 * HTTP返回状态码
 * @author baixiaozheng
 *
 */
public class HTTPCodeStatus {

	/**
	 * 成功
	 */
	public static final int HTTPCODE_OK = 200;
	public static final String HTTPCODE_OK_MESSAGE = "成功";
	
	/**
	 * 用户未登陆
	 */
	public static final int HTTPCODE_USER_NOTLOGIN = 201;
	public static final String HTTPCODE_USER_NOTLOGIN_MESSAGE = "用户未登陆";
	
	/**
	 * 登陆失败
	 */
	public static final int HTTPCODE_LOGIN_ERROR = 202;
	public static final String HTTPCODE_LOGIN_ERROR_MESSAGE = "登陆失败";
	
	/**
     * 权限不足
     */
    public static final int HTTPCODE_PERMISSION_DENIED = 203;
    public static final String HTTPCODE_PERMISSION_DENIED_MESSAGE = "权限不足";
    
    /**
     * 昵称已存在
     */
    public static final int HTTPCODE_NICKNAME_IS_NOT_NULL = 204;
    public static final String HTTPCODE_NICKNAME_IS_NOT_NULL_MESSAGE = "昵称已存在";
    
    /**
     * 手机号码已存在
     */
    public static final int HTTPCODE_MOBILE_IS_NOT_NULL = 205;
    public static final String HTTPCODE_MOBILE_IS_NOT_NULL_MESSAGE = "手机号码已存在";
    
    /**
     * 邮箱已存在
     */
    public static final int HTTPCODE_EMAIL_IS_NOT_NULL = 206;
    public static final String HTTPCODE_EMAIL_IS_NOT_NULL_MESSAGE = "邮箱已存在";
}
