package com.hyder.travel.service;

/**
 * @author: 杨欢
 * @created: 2019-07-27 01:11
 * @description: 订阅服务接口
 */
public interface SubscriberService {

	/**
	 * 判断是否收藏
	 * @param rid
	 * @param uid
	 * @return
	 */
	boolean isSubscriber(String rid, int uid);
}
