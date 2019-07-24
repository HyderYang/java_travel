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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 杨欢
 * @created: 2019-07-24 20:25
 * @description: 注册页面
 */
@WebServlet("/registUserServlet")
public class RegisterServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("application/json;charset=utf-8");

		String check = req.getParameter("check");
		HttpSession session = req.getSession();
		String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");

		ResultInfo info = new ResultInfo();
		ObjectMapper mapper = new ObjectMapper();
		if (checkcodeServer == null || !checkcodeServer.equalsIgnoreCase(check)){
			info.setFlag(false);
			info.setErrorMsg("验证码错误");
			String s = mapper.writeValueAsString(info);
			resp.getWriter().write(s);
			return;
		}

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
		if( service.register(user)){
			info.setFlag(true);
		} else {
			info.setFlag(false);
			info.setErrorMsg("注册失败");
		}
		String s = mapper.writeValueAsString(info);
		resp.getWriter().write(s);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
