package com.hyder.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author: 杨欢
 * @created: 2019-07-26 00:13
 * @description: Servlet父类
 */
public class BaseServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String uri = req.getRequestURI();
		String methodName = uri.substring(uri.lastIndexOf('/') + 1);
		try {
			Method method = this.getClass().getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
			method.invoke(this, req, resp);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 直接将传入的对象序列化为JSON 并且写会客户端
	 * @param obj
	 */
	public void writeValue(Object obj, HttpServletResponse resp){
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=utf-8");
		try {
			mapper.writeValue(resp.getOutputStream(), obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String writeValueAsString(Object obj, HttpServletResponse resp) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(obj);
	}
}
