package com.hyder.travel.dao.Impl;

import com.hyder.travel.dao.CategoryDao;
import com.hyder.travel.domain.Category;
import com.hyder.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:24
 * @description: 分类数据模型接口实现
 */
public class CategoryDaoImpl implements CategoryDao {

	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public List<Category> findAll() {
		String sql = "select * from tab_category";
		return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));
	}
}
