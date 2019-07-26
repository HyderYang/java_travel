package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.RouteDaoImpl;
import com.hyder.travel.dao.RouteDao;
import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;
import com.hyder.travel.service.RouteService;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:49
 * @description: 路线service实现类
 */
public class RouteServiceImpl implements RouteService {
	RouteDao dao = new RouteDaoImpl();

	@Override
	public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
		PageBean<Route> bean = new PageBean<Route>();
		bean.setCurrentPage(currentPage);
		bean.setPageSize(pageSize);

		int totalCount = dao.findTotalCount(cid, rname);
		bean.setTotalCount(totalCount);

		int start = (currentPage - 1) * pageSize;
		List<Route> list = dao.findByPage(cid, start,pageSize, rname);
		bean.setList(list);

		int countPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
		bean.setTotalPage(countPage);
		return bean;
	}
}
