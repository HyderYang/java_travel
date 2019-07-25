package com.hyder.travel.dao;

import com.hyder.travel.domain.User;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:50
 * @description: 用户数据模型接口
 */
public interface UserDao {

	/**
	 * 根据用户名查询用户信息
	 * @param username username
	 * @return User
	 */
	User findByUsername(String username);

	/**
	 * 保存用户信息
	 */
	void save(User user);

	/**
	 * 查找激活Code
	 * @param code code
	 * @return String
	 */
	User findByCode(String code);

	/**
	 * 更新用户激活状态
	 * @param user user
	 */
	void updateStatus(User user);

	/**
	 * 登录信息查找
	 * @param user user
	 * @return User
	 */
	User findByUsernameAndPassword(User user);
}
