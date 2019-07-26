package com.hyder.travel.service.impl;

import com.hyder.travel.dao.CategoryDao;
import com.hyder.travel.dao.Impl.CategoryDaoImpl;
import com.hyder.travel.domain.Category;
import com.hyder.travel.service.CategoryService;
import com.hyder.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:29
 * @description: 分类服务接口实现
 */
public class CategoryServiceImpl implements CategoryService {
	private CategoryDao dao = new CategoryDaoImpl();

	@Override
	public List<Category> findAll() {
		Jedis jedis = JedisUtil.getJedis();
		Set<String> set = jedis.zrange("category", 0, -1);
		List<Category> all = null;

		if (set == null || set.size() == 0){
			all = dao.findAll();
			System.out.println("数据库查询");
			for (int i = 0; i < all.size(); i++) {
				jedis.zadd("category", all.get(i).getCid(), all.get(i).getCname());
			}
		} else {
			all = new ArrayList<Category>();
			System.out.println("redis查询");
			for (String name : set){
				Category category = new Category();
				category.setCname(name);
				all.add(category);
			}
		}
		return all;
	}
}
