package com.mall.controller.backend;

import com.google.common.collect.Maps;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.dao.ProductMapper;
import com.mall.pojo.Product;
import com.mall.pojo.User;
import com.mall.service.IFileService;
import com.mall.service.IProductService;
import com.mall.service.IUserService;
import com.mall.util.CookieUtil;
import com.mall.util.JsonUtil;
import com.mall.util.PropertiesUtil;
import com.mall.util.RedisShardedPoolUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by Cxl on 2018/12/18-10:11
 * Description: Mmall
 */
@Controller
@RequestMapping("/manage/product")
public class ProductManageController {
    @Autowired
    private IUserService iUserService;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private IFileService iFileService;
    @RequestMapping("save.do")
    @ResponseBody
    public ServerResponse productAdd(HttpServletRequest request, Product product){
        System.out.println(product.getDetail());
        //判断是否登录
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.saveOrUpdateProduct(product);
//        }else{
//            return ServerResponse.createByErrorMessage("无操作权限");
//        }
    }

    @RequestMapping("set_sale_status.do")
    @ResponseBody
    public ServerResponse setSaleStatus(HttpServletRequest request,Integer productId,Integer status){
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
        String loginToken=CookieUtil.readLoginToken(request);
        if (loginToken==null){
            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
        }
        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
        User user=JsonUtil.string2Obj(userJsonStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }
        if (iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.setSaleStatus(productId,status);
        }else{
            return ServerResponse.createByErrorMessage("无操作权限");
        }
    }
    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse getDetail(HttpServletRequest request,Integer productId,Integer status){
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
            return iProductService.manageProductDetail(productId);
//        }else{
//            return ServerResponse.createByErrorMessage("无操作权限");
//        }
    }
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse getList(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
            return iProductService.getProductList(pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无操作权限");
//        }
    }
    @RequestMapping("search.do")
    @ResponseBody
    public ServerResponse productSearch(HttpServletRequest request, @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,String productName,Integer productId){
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
            return iProductService.searchProduct(productName,productId,pageNum,pageSize);
//        }else{
//            return ServerResponse.createByErrorMessage("无操作权限");
//        }
    }
    //图片上传
    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(@RequestParam(value = "upload_file",required = false) MultipartFile multipartFile, HttpServletRequest request){
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorMessage("用户未登陆，无法获取当前用户的信息");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
//        }
//        if (iUserService.checkAdminRole(user).isSuccess()){
            //填充业务
            String path=request.getSession().getServletContext().getRealPath("upload");
            String targetFileName=iFileService.upload(multipartFile,path);
            String url=PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
            Map fileMap=Maps.newHashMap();
            fileMap.put("uri",targetFileName);
            fileMap.put("url",url);
            return ServerResponse.createBySuccess(fileMap);
//        }else{
//            return ServerResponse.createByErrorMessage("无操作权限");
//        }
    }

    //富文本上传
    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImgUpload(HttpServletResponse response,@RequestParam(value = "upload_file",required = false) MultipartFile multipartFile, HttpServletRequest request){
        Map resultMap=Maps.newHashMap();
//        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
//        if (user==null){
//            resultMap.put("success",false);
//            resultMap.put("msg","请登录管理员");
//            return resultMap;
//        }
        /*
            富文本中对于返回值有自己的要求，我们使用的是simditor所以按照simditor的要求进行返回
            "success":true/false
                "msg":"error message",#optional
                "file_path":"[real file path]"
         */
//        if (iUserService.checkAdminRole(user).isSuccess()){
//            //填充业务
//
//        }else{
//            resultMap.put("success",false);
//            resultMap.put("msg","无权限操作");
//            return resultMap;
//        }
        String path=request.getSession().getServletContext().getRealPath("upload");
        String targetFileName=iFileService.upload(multipartFile,path);
        if (StringUtils.isEmpty(targetFileName)) {
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
            return resultMap;
        }
        String url=PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;
        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file_path",targetFileName);
        response.addHeader("Access-Control-Allow-Headers","X-File-Name");
        return resultMap;

    }


}
