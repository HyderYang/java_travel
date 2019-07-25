package com.hyder.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyder.travel.domain.ResultInfo;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.UserService;
import com.hyder.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 杨欢
 * @created: 2019-07-25 22:09
 * @description: 用户登录功能
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		User u = service.login(user);

		ResultInfo info = new ResultInfo();

		if (u == null){
			info.setFlag(false);
			info.setErrorMsg("用户名或密码错误");
		}

		if (u != null && !"Y".equals(u.getStatus())){
			info.setFlag(false);
			info.setErrorMsg("用户未激活");
		}

		if (u != null && "Y".equals(u.getStatus())){
			info.setFlag(true);
		}
		resp.setContentType("application/json;charset=utf-8");
		(new ObjectMapper()).writeValue(resp.getOutputStream(), info);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doPost(req,resp);
	}
}
