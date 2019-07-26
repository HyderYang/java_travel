package com.hyder.travel.service.impl;

import com.hyder.travel.dao.Impl.SubscriberDaoImpl;
import com.hyder.travel.dao.SubscriberDao;
import com.hyder.travel.domain.Favorite;
import com.hyder.travel.service.SubscriberService;

/**
 * @author: 杨欢
 * @created: 2019-07-27 01:12
 * @description: 订阅服务接口实现
 */
public class SubscriberServiceImpl implements SubscriberService {
	private SubscriberDao dao = new SubscriberDaoImpl();

	@Override
	public boolean isSubscriber(String rid, int uid) {
		Favorite favorite = dao.findByRidAndUid(Integer.parseInt(rid), uid);
		return favorite != null;
	}
}
