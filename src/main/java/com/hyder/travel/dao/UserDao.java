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
	 * @param username
	 * @return User
	 */
	public User findByUsername(String username);

	/**
	 * 保存用户信息
	 */
	public void save(User user);

	/**
	 * 查找激活Code
	 * @param code
	 * @return
	 */
	public User findByCode(String code);

	/**
	 * 更新用户激活状态
	 * @param user
	 */
	public void updateStatus(User user);
}
