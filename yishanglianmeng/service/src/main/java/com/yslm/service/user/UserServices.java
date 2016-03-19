package com.yslm.service.user; 

import java.util.List;

import com.yslm.model.user.User;


/**
 * 用户service
 * 
 * @author baixiaozheng
 * @Date 2015年12月17日 下午11:33:49
 */
public interface UserServices {

	/**
	 * 获取所有用户
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:34:15
	 */
	List<User> getAll();
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:34:22
	 */
	User add(User user);
	
	/**
	 * 根据email获取用户
	 * @param email
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:34:29
	 */
	User getUserByEmail(String email);

	/**
	 * 根据手机号码获取用户
	 * @param mobile
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:34:43
	 */
	User getUserByMobile(String mobile);

	/**
	 * 根据昵称查询
	 * @param Nickname
	 * @return
	 * @author baixiaozheng@gmsdtech.com
	 * @Date 2016年3月16日 上午12:13:55
	 */
	User getUserByNickname(String Nickname);
	/**
	 * 根据id获取用户
	 * @param id
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:34:54
	 */
	User getById(int id);
	
	/**
	 * 更新用户
	 * @param user
	 * @return
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:35:05
	 */
	User update(User user);
	
	/**
	 * 更新用户的最后登陆时间和ip
	 * @param user
	 * @param ip
	 * @author baixiaozheng
	 * @Date 2015年12月17日 下午11:35:11
	 */
	void updateUserIpAndLastestLoginTime(User user, String ip);
}
 