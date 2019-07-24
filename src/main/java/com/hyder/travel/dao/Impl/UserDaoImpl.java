package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.UserDao;
import com.hyder.travel.domain.User;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:51
 * @description: 用户数据接口实现类
 */
public class UserDaoImpl implements UserDao {

	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public User findByUsername(String username) {
		User user = null;
		try {
			String sql = "select * from tab_user where username = ?";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
		} catch (DataAccessException e) {
			// todo: 处理异常
		}

		return user;
	}

	@Override
	public void save(User user) {
		String sql = "insert into tab_user(username, password, name, birthday, sex, telephone, email, status, code) " + "values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		template.update(
				sql,
				user.getUsername(),
				user.getPassword(),
				user.getName(),
				user.getBirthday(),
				user.getSex(),
				user.getTelephone(),
				user.getEmail(),
				user.getStatus(),
				user.getCode()
		);
	}

	@Override
	public User findByCode(String code) {
		User user = null;
		try {
			String sql = "select * from tab_user where code = ?";
			user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
		}catch (DataAccessException e){
			// todo: 异常情况
		}
		return user;
	}

	@Override
	public void updateStatus(User user) {
		String sql = "update tab_user set status = 'Y' where uid = ?";
		template.update(sql, user.getUid());
	}
}
