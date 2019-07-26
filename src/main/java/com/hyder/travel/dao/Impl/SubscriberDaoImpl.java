package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.SubscriberDao;
import com.hyder.travel.domain.Favorite;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: 杨欢
 * @created: 2019-07-27 01:17
 * @description: 订阅数据模型接口
 */
public class SubscriberDaoImpl implements SubscriberDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public Favorite findByRidAndUid(int rid, int uid) {
		Favorite favorite = null;
		try {
			String sql = "select * from tab_favorite where rid = ? and uid = ?";
			favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
		} catch (DataAccessException e) {

		}
		return favorite;
	}
}
