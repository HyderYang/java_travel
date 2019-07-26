package com.hyder.travel.dao;

import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:51
 * @description: 路线数据模型接口
 */
public interface RouteDao {

	/**
	 * 根据 分类id 查询总记录数
	 */
	int findTotalCount(int cid, String rname);

	/**
	 * 根据 分类id 当前页码 每页条数 查询当前页的数据集合
	 */
	List<Route> findByPage(int cid, int start, int pageSize, String rname);
}
