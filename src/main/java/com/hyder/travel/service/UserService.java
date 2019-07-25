package com.hyder.travel.service;

import com.hyder.travel.domain.User;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:28
 * @description: 用户服务接口
 */
public interface UserService {
	public boolean register(User user);

	public boolean active(String code);

	public User login(User user);
}
