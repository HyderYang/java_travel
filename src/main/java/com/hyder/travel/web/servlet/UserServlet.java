package com.hyder.travel.web.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hyder.travel.domain.ResultInfo;
import com.hyder.travel.domain.User;
import com.hyder.travel.service.UserService;
import com.hyder.travel.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author: 杨欢
 * @created: 2019-07-26 00:13
 * @description: 用户Servlet
 */
@WebServlet("/user/*")
public class UserServlet extends BaseServlet{

	private UserService service = new UserServiceImpl();;
	private ResultInfo info = new ResultInfo();;

	/**
	 * 激活
	 * @param req   请求
	 * @param resp  响应
	 */
	public void active(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String code = req.getParameter("code");
		if (code != null){
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

	/**
	 * 登出
	 * @param req   请求
	 * @param resp  响应
	 */
	public void exit(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		req.getSession().invalidate();
		resp.sendRedirect("/login.html");
	}

	/**
	 * 查找用户
	 * @param req   请求
	 * @param resp  响应
	 */
	public void find(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Object user = req.getSession().getAttribute("user");
		resp.setContentType("application/json;charset=utf-8");
		info.clear();
		info.setFlag(true);
		info.setData(user);
		(new ObjectMapper()).writeValue(resp.getOutputStream(), info);
	}

	/**
	 * 登录功能
	 * @param req   请求
	 * @param resp  响应
	 */
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Map<String, String[]> map = req.getParameterMap();
		User user = new User();

		try {
			BeanUtils.populate(user, map);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

		User u = service.login(user);

		info.clear();
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
			(req.getSession()).setAttribute("user", u);
		}
		resp.setContentType("application/json;charset=utf-8");
		(new ObjectMapper()).writeValue(resp.getOutputStream(), info);
	}

	/**
	 * 注册功能
	 * @param req   请求
	 * @param resp  响应
	 */
	public void register(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		resp.setContentType("application/json;charset=utf-8");

		String check = req.getParameter("check");
		HttpSession session = req.getSession();
		String checkcodeServer = (String) session.getAttribute("CHECKCODE_SERVER");
		session.removeAttribute("CHECKCODE_SERVER");

		ObjectMapper mapper = new ObjectMapper();
		info.clear();
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

		if( service.register(user)){
			info.setFlag(true);
		} else {
			info.setFlag(false);
			info.setErrorMsg("注册失败");
		}
		String s = mapper.writeValueAsString(info);
		resp.getWriter().write(s);
	}

}
