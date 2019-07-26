package com.hyder.travel.web.servlet;

import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;
import com.hyder.travel.service.RouteService;
import com.hyder.travel.service.impl.RouteServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:28
 * @description: 路线相关Servlet
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{

	RouteService service = new RouteServiceImpl();

	/**
	 * 分页查询
	 * @param req
	 * @param resp
	 */
	public void page(HttpServletRequest req, HttpServletResponse resp) {
		String currentPageStr = req.getParameter("currentPage");
		String pageSizeStr = req.getParameter("pageSize");
		String cidStr = req.getParameter("cid");

		int cid = 0;
		int currentPage = 0;
		int pageSize = 0;
		if (cidStr != null && cidStr.length() > 0){
			cid = Integer.parseInt(cidStr);
		}

		if (currentPageStr != null && currentPageStr.length() > 0){
			currentPage = Integer.parseInt(currentPageStr);
		} else {
			currentPage = 1;
		}

		if (pageSizeStr != null && pageSizeStr.length() > 0){
			pageSize = Integer.parseInt(pageSizeStr);
		} else {
			pageSize = 5;
		}

		PageBean<Route> bean = service.pageQuery(cid, currentPage, pageSize);
		this.writeValue(bean, resp);
	}
}
