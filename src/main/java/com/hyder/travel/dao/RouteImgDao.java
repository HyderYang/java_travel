package com.hyder.travel.dao;

import com.hyder.travel.domain.RouteImg;

import java.util.List;

/**
 * @author: 杨欢
 * @created: 2019-07-27 00:01
 * @description: 线路详情配图数据模型接口
 */
public interface RouteImgDao {
	List<RouteImg> findByRid(int rid);
}
