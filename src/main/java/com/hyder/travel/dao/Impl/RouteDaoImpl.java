package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.RouteDao;
import com.hyder.travel.domain.Route;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:52
 * @description: 路线数据模型实现类
 */
public class RouteDaoImpl implements RouteDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public int findTotalCount(int cid, String rname) {
//		String sql = "select count(rid) from tab_route where cid = ?";
		String sql = "SELECT count(rid) from tab_route WHERE 1 = 1";
		StringBuilder sb = new StringBuilder(sql);
		List params = new ArrayList();
		if (cid != 0){
			sb.append(" and cid = ?");
			params.add(cid);
		}

		if (rname != null && rname.length() > 0){
			sb.append(" and rname like ?");
			params.add("'%" + rname + "%'");
		}

		sql = sb.toString();

		return template.queryForObject(sql, Integer.class, params.toArray());
	}

	@Override
	public List<Route> findByPage(int cid, int start, int pageSize, String rname) {
//		String sql = "select * from tab_route where cid = ? limit ?, ?";

		String sql = "select * from tab_route WHERE 1 = 1";
		StringBuilder sb = new StringBuilder(sql);
		List params = new ArrayList();
		if (cid != 0){
			sb.append(" and cid = ?");
			params.add(cid);
		}

		if (rname != null && rname.length() > 0){
			sb.append(" and rname like ?");
			params.add("'%" + rname + "%'");
		}
		sb.append(" limit ?, ?");
		params.add(start);
		params.add(pageSize);

		sql = sb.toString();

		return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
	}
}
