package com.mall.common;

/**
 * Created by Cxl on 2018/12/15-15:12
 * Description: Mmall
 * IllegalArgument 参数错误
 */
public enum ResponseCode {
    //SUCCESS,ERROR等都是这个枚举的对象
    //对象只能放在枚举类的首行
    //枚举类的构造器只能是私有的
    SUCCESS(0,"SUCESS"),
    ERROR(1,"ERROR"),
    NEED_LOGIN(10,"NEED_LOGIN"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");

    private final int code;
    private final String desc;

    ResponseCode(int code,String desc){
        this.code=code;
        this.desc=desc;
    }
    public int getCode(){
        return this.code;
    }
    public String getDesc(){
        return this.desc;
    }
}
