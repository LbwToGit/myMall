package com.mall.service;

import com.mall.common.ServerResponse;
import com.mall.pojo.Shipping;

/**
 * Created by Cxl on 2018/12/21-15:37
 * Description: Mmall
 */
public interface IShippingService {
    ServerResponse add(Integer userId, Shipping shipping);

    ServerResponse<String>del(Integer userId,Integer shippingId);

    ServerResponse update(Integer userId, Shipping shipping);

    ServerResponse select(Integer userId, Integer shippingId);

    ServerResponse list(Integer userId,Integer pageNum,Integer pageSize);
}
