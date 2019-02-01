package com.mall.controller.portal;

import com.mall.common.Const;
import com.mall.common.ResponseCode;
import com.mall.common.ServerResponse;
import com.mall.pojo.User;
import com.mall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Cxl on 2018/12/15-14:44
 * Description: Mmall
 */
@Controller
@RequestMapping("/user/springsession")
public class UserSpringSessionController {

    @Autowired
    private IUserService iUserService;
    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse login(String username, String password, HttpSession session, HttpServletResponse response,HttpServletRequest request){
        ServerResponse<User> serverResponse=iUserService.login(username,password);
        if (serverResponse.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,serverResponse.getData());
//            CookieUtil.writeLoginToken(response,session.getId());
//            RedisShardedPoolUtil.setEx(session.getId(),JsonUtil.obj2String(serverResponse.getData()),Const.RedisCacheExtime.REDIS_SESSION_EXTIME);
        }
        return serverResponse;
    }
    @RequestMapping(value = "logout.do",method =RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String>logout(HttpSession session,HttpServletResponse response){
//        String loginToken=CookieUtil.readLoginToken(request);
//        CookieUtil.delLoginToken(request,response);
//        RedisShardedPoolUtil.del(loginToken);
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User>get_information(HttpSession session){
        User user=(User) session.getAttribute(Const.CURRENT_USER);
//        String loginToken=CookieUtil.readLoginToken(request);
//        if (loginToken==null){
//            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
//        }
//        String userJsonStr=RedisShardedPoolUtil.get(loginToken);
//        User user=JsonUtil.string2Obj(userJsonStr,User.class);
        if (user==null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"未登录,需要强制登录status=10");
        }
        return iUserService.getInformation(user.getId());
    }
}
