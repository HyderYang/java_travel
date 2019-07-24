package com.hyder.travel.web.servlet;

/**
 * @author: 杨欢
 * @created: 2019-07-24 22:37
 * @description: 激活验用户
 */

import com.hyder.travel.service.UserService;
import com.hyder.travel.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/activeUserServlet")
public class ActiveUserServlet extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		if (code != null){
			UserService service = new UserServiceImpl();
			boolean b = service.active(code);

			String msg = null;
			if (b) {
				msg = "激活成功, <a href='/'>点我</a>回到首页";
			} else {
				msg = "激活失败";
			}
			resp.setContentType("text/html;charset=utf-8");
			resp.getWriter().write(msg);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
