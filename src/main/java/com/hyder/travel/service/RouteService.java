package com.hyder.travel.service;

import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:46
 * @description: 路线service接口
 */
public interface RouteService {

	/**
	 * 查询当前分页信息
	 * @param cid           分类ID
	 * @param currentPage   当前页码
	 * @param pageSize      每页条数
	 * @return              page实体类
	 */
	PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);
}
