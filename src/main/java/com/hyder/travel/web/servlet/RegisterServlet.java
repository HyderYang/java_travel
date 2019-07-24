package com.hyder.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyder.travel.domain.ResultInfo;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.UserService;
import com.hyder.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:25
 * @description: 注册页面
 */
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, String[]> map = req.getParameterMap();

		User user = new User();
		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		UserService service = new UserServiceImpl();
		ResultInfo info = new ResultInfo();
		if( service.register()){
			info.setFlag(true);
		} else {
			info.setFlag(false);
			info.setErrorMsg("注册失败");
		}
		ObjectMapper mapper = new ObjectMapper();
		String s = mapper.writeValueAsString(mapper);
		resp.setContentType("application/json;charset=utf-8");
		resp.getWriter().write(s);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
