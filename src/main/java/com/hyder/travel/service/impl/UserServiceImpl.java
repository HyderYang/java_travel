package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.UserDaoImpl;
import com.hyder.travel.dao.UserDao;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.UserService;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:39
 * @description: 用户服务实现类
 */
public class UserServiceImpl implements UserService {
	private UserDao userDao = new UserDaoImpl();

	@Override
	public boolean register(User user) {
		User u = userDao.findByUsername(user.getUsername());
		if (u != null) {
			return false;
		}
		userDao.save(user);

		return true;
	}
}
