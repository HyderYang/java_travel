package com.hyder.travel.dao;

import com.hyder.travel.domain.Favorite;

/**
 * @author: 杨欢
 * @created: 2019-07-27 01:17
 * @description: 订阅数据模型接口
 */
public interface SubscriberDao {

	/**
	 * 根据用户id和线路id查询收藏信息
	 * @param rid
	 * @param uid
	 * @return
	 */
	Favorite findByRidAndUid(int rid, int uid);
}
