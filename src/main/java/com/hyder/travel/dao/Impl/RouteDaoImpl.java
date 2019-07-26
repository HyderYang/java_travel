package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.RouteDao;
import com.hyder.travel.domain.Route;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:52
 * @description: 路线数据模型实现类
 */
public class RouteDaoImpl implements RouteDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public int findTotalCount(int cid) {
		String sql = "select count(rid) from tab_route where cid = ?";
		return template.queryForObject(sql, Integer.class, cid);
	}

	@Override
	public List<Route> findByPage(int cid, int start, int pageSize) {
		String sql = "select * from tab_route where cid = ? limit ?, ?";
		return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), cid, start, pageSize);
	}
}
