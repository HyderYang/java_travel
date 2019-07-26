package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.SellerDao;
import com.hyder.travel.domain.Seller;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author: 杨欢
 * @created: 2019-07-27 00:12
 * @description: 商家数据模型实现类
 */
public class SellerDaoImpl implements SellerDao {
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public Seller findById(int sid) {
		String sql = "select * from tab_seller where sid = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class), sid);
	}
}
