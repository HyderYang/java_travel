package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.RouteDaoImpl;
import com.hyder.travel.dao.Impl.RouteImgDaoImpl;
import com.hyder.travel.dao.Impl.SellerDaoImpl;
import com.hyder.travel.dao.RouteDao;
import com.hyder.travel.dao.RouteImgDao;
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
	private RouteDao dao = new RouteDaoImpl();
	private RouteImgDao imgDao = new RouteImgDaoImpl();
	private SellerDaoImpl sellerDao = new SellerDaoImpl();

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

	@Override
	public Route findOne(String rid) {
		Route one = dao.findOne(Integer.parseInt(rid));
		// 根据rid 查询图片集合信息
		one.setRouteImgList(imgDao.findByRid(one.getRid()));
		// 根据 sid 查询商家信息
		one.setSeller(sellerDao.findById(one.getSid()));
		return one;
	}
}
