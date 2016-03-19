package com.yslm.dao.user; 

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.yslm.dao.BaseDao;
import com.yslm.model.user.User;



@Repository(value="userDao")
public class UserDao extends BaseDao{

	@Override
	protected Class<?> entityClass() {
		return User.class;
	}

	public List<User> getAll(){
		return super.list();
	}
	
	public User add(User user) {
        return super.add(user);
    }
	
	public User update(User user){
	    return super.update(user);
	}
	
	public User getById(int id) {
		return super.get(id);
	}
	
	/**
	 * @Description: 根据Email获取用户
	 * @param email
	 * @return User
	 * @throws
	 */
	public User getUserByEmail(String email) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from User where email = :email";
		Object object = session.createQuery(hql)
				.setParameter("email", email).uniqueResult();
		return object == null ? null : (User) object;
	}

	/**
	 * @Description: 根据手机号获取用户
	 * @param mobile
	 * @return User
	 * @throws
	 */
	public User getUserByMobile(String mobile) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from User where mobile = :mobile";
		Object object = session.createQuery(hql)
				.setParameter("mobile", mobile).uniqueResult();
		return object == null ? null : (User) object;
	}
	
	public User getUserByNickname(String nickName) {
		Session session = getSessionFactory().getCurrentSession();
		String hql = "from User where nickname = :nickname";
		Object object = session.createQuery(hql)
				.setParameter("nickname", nickName).uniqueResult();
		return object == null ? null : (User) object;
	}
}
 