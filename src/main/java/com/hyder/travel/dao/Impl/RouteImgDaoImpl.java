package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.RouteImgDao;
import com.hyder.travel.domain.RouteImg;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-27 00:02
 * @description: 线路详情数据配图模型实现
 */
public class RouteImgDaoImpl implements RouteImgDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public List<RouteImg> findByRid(int rid) {
		String sql = "select * from tab_route_img where rid = ?";
		return template.query(sql, new BeanPropertyRowMapper<RouteImg>(RouteImg.class), rid);
	}
}
