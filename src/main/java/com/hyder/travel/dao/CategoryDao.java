package com.hyder.travel.dao;

import com.hyder.travel.domain.Category;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:22
 * @description: 分类数据模型接口
 */
public interface CategoryDao {

	/**
	 * 查询所有
	 * @return List<Category>
	 */
	public List<Category> findAll();
}
