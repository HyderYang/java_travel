package com.hyder.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyder.travel.domain.ResultInfo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: 杨欢
 * @created: 2019-07-25 22:55
 * @description: 查询用户信息
 */
@WebServlet("/findUserServlet")
public class FindUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Object user = req.getSession().getAttribute("user");
		resp.setContentType("application/json;charset=utf-8");
		ResultInfo info = new ResultInfo();
		info.setFlag(true);
		info.setData(user);
		(new ObjectMapper()).writeValue(resp.getOutputStream(), info);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req, resp);
	}
}
