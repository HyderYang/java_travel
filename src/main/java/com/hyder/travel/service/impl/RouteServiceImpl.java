package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.RouteDaoImpl;
import com.hyder.travel.dao.RouteDao;
import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;
import com.hyder.travel.service.RouteService;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:49
 * @description: 路线service实现类
 */
public class RouteServiceImpl implements RouteService {
	RouteDao dao = new RouteDaoImpl();

	@Override
	public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize) {
		return null;
	}
}
