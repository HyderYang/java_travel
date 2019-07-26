package com.hyder.travel.service;

import com.hyder.travel.domain.Category;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:27
 * @description: 分类数据处理服务类
 */
public interface CategoryService {

	public List<Category> findAll();
}
