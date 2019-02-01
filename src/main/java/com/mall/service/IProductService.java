package com.mall.service;

import com.github.pagehelper.PageInfo;
import com.mall.common.ServerResponse;
import com.mall.pojo.Product;
import com.mall.vo.ProductDetailVo;

/**
 * Created by Cxl on 2018/12/18-10:17
 * Description: Mmall
 */
public interface IProductService {
    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse setSaleStatus(Integer productId,Integer status);
    ServerResponse<ProductDetailVo> manageProductDetail(Integer productId);
    ServerResponse getProductList(int pageNum,int pageSize);
    ServerResponse searchProduct(String productName,Integer productId,int pageNum,int pageSize);
    ServerResponse getProductDetail(Integer productId);
    ServerResponse<PageInfo>getProductByKeywordCategory(String keyword, Integer categoryId, int pageNum, int pageSize, String orderBy);
}
