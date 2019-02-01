package com.mall.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.mall.common.ServerResponse;
import com.mall.dao.ShippingMapper;
import com.mall.pojo.Shipping;
import com.mall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Cxl on 2018/12/21-15:37
 * Description: Mmall
 */
@Service
public class ShippingServiceImpl implements IShippingService {
    @Autowired
    private ShippingMapper shippingMapper;
    public ServerResponse add(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int rowCount=shippingMapper.insert(shipping);
        if (rowCount>0){
            Map result=Maps.newHashMap();
            result.put("shipingId",shipping.getId());
            return ServerResponse.createBySuccess(result,"新建地址成功");
        }
        return ServerResponse.createByErrorMessage("新建地址失败");
    }
    public ServerResponse<String>del(Integer userId,Integer shippingId){
        int resultCount=shippingMapper.deleteByShippingIdAndUserId(userId,shippingId);
        if (resultCount>0){
            return ServerResponse.createBySuccessMessage("删除地址成功");
        }else {
            return ServerResponse.createByErrorMessage("删除地址失败");
        }
    }
    public ServerResponse update(Integer userId, Shipping shipping){
        shipping.setUserId(userId);
        int rowCount=shippingMapper.updateByShipping(shipping);
        if (rowCount>0){
            Map result=Maps.newHashMap();
            result.put("shipingId",shipping.getId());
            return ServerResponse.createBySuccess(result,"更新地址成功");
        }
        return ServerResponse.createByErrorMessage("更新地址失败");
    }


    public ServerResponse select(Integer userId,Integer shippingId){
        Shipping shipping=shippingMapper.selectByShippingIdUserId(userId,shippingId);
        if (shipping==null){
            return ServerResponse.createByErrorMessage("无法查询到该地址");
        }
        return ServerResponse.createBySuccess(shipping,"更新地址成功");
    }

    public ServerResponse list(Integer userId,Integer pageNum,Integer pageSize){
        PageHelper.startPage(pageNum,pageSize);
        List<Shipping>shippings=shippingMapper.selectByUserId(userId);
        PageInfo pageInfo=new PageInfo(shippings);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
