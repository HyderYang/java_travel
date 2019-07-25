package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.UserDaoImpl;
import com.hyder.travel.dao.UserDao;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.UserService;
import com.hyder.travel.util.MailUtils;
import com.hyder.travel.util.UuidUtil;

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
		user.setCode(UuidUtil.getUuid());
		user.setStatus("N");
		userDao.save(user);

		String content = "<a href='http://localhost:8989/activeUserServlet?code=" + user.getCode() +"'>点击激活</a>";
		MailUtils.sendMail(user.getEmail(), content, "激活账号");
		return true;
	}

	@Override
	public boolean active(String code) {
		User user = userDao.findByCode(code);
		if (user != null) {
			userDao.updateStatus(user);
			return true;
		}else{
			return false;
		}
	}

	@Override
	public User login(User user) {
		return userDao.findByUsernameAndPassword(user);
	}
}
