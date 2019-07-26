package com.hyder.travel.service.impl;

import com.hyder.travel.dao.CategoryDao;
import com.hyder.travel.dao.Impl.CategoryDaoImpl;
import com.hyder.travel.domain.Category;
import com.hyder.travel.service.CategoryService;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:29
 * @description: 分类服务接口实现
 */
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao dao = new CategoryDaoImpl();

	@Override
	public List<Category> findAll() {
		return dao.findAll();
	}
}
