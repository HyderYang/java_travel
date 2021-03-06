package com.hyder.travel.web.servlet;

import com.hyder.travel.domain.PageBean;
import com.hyder.travel.domain.Route;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.RouteService;
import com.hyder.travel.service.SubscriberService;
import com.hyder.travel.service.impl.RouteServiceImpl;
import com.hyder.travel.service.impl.SubscriberServiceImpl;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:28
 * @description: 路线相关Servlet
 */
@WebServlet("/route/*")
public class RouteServlet extends BaseServlet{

	private RouteService service = new RouteServiceImpl();
	private SubscriberService subscriber = new SubscriberServiceImpl();

	/**
	 * 分页查询
	 * @param req
	 * @param resp
	 */
	public void page(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
		String currentPageStr = req.getParameter("currentPage");
		String pageSizeStr = req.getParameter("pageSize");
		String cidStr = req.getParameter("cid");

		String rname = req.getParameter("rname");
		// 处理乱码问题
		rname = new String(rname.getBytes("iso-8859-1"), "utf-8");

		int cid = 0;
		int currentPage = 0;
		int pageSize = 0;
		if (cidStr != null && cidStr.length() > 0 && "null".equalsIgnoreCase(cidStr)){
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

		PageBean<Route> bean = service.pageQuery(cid, currentPage, pageSize, rname);
		this.writeValue(bean, resp);
	}

	/**
	 * 路线详情
	 * @param req
	 * @param resp
	 */
	public void detail(HttpServletRequest req, HttpServletResponse resp) {
		String rid = req.getParameter("rid");
		Route route = service.findOne(rid);
		this.writeValue(route, resp);
	}

	/**
	 * 是否订阅
	 * @param req
	 * @param resp
	 */
	public void isSubscriber(HttpServletRequest req, HttpServletResponse resp){
		String rid = req.getParameter("rid");

		User user = (User) req.getSession().getAttribute("user");

		int uid;
		if (user == null){
			uid = 0;
		} else {
			uid = user.getUid();
		}

		boolean flag = this.subscriber.isSubscriber(rid, uid);

		this.writeValue(flag, resp);
	}
}
