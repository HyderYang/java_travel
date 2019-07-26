package com.hyder.travel.web.servlet;

import com.hyder.travel.domain.Category;
import com.hyder.travel.service.CategoryService;
import com.hyder.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 10:21
 * @description: 分类相关Servlet
 */
@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

	private CategoryService service = new CategoryServiceImpl();

	/**
	 * 查询所有
	 * @param req   请求
	 * @param resp  响应
	 */
	public void all(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		List<Category> categories = service.findAll();
		super.writeValue(categories, resp);
	}
}
