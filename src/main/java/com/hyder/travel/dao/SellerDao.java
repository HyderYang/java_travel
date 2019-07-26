package com.hyder.travel.dao;

import com.hyder.travel.domain.Seller;

/**
 * @author: 杨欢
 * @created: 2019-07-27 00:11
 * @description: 商家数据模型接口
 */
public interface SellerDao {
	Seller findById(int sid);
}
