package com.hyder.travel.domain;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-26 16:20
 * @description: 分类页面实体类
 */
public class PageBean {
	private int totalPage;
	private int totalCount;
	private int currentPage;
	private int pageSize;

	private List list;//每页显示的数据集合

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List getList(){
		return this.list;
	}

	public void setList(List list) {
		this.list = list;
	}
}
