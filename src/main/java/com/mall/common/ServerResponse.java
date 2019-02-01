package com.mall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by Cxl on 2018/12/15-14:50
 * Description: Mmall
 * 这是一个泛型类
 */
//保证序列化json的时候，如果是Null的对象，key也会消失
@JsonSerialize(include =JsonSerialize.Inclusion.NON_NULL)

public class ServerResponse<T> implements Serializable {
    private int status;
    private String msg;
    private T data;

    /**
     * 私有构造器，给当前status赋值
     * @param status
     */
    private ServerResponse(int status) {
        this.status = status;
    }
    /**
     * 私有构造器，给"状态status"与"提示信息msg"赋值
     * @param status
     * @param msg
     */
    private ServerResponse(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
    /**
     *私有构造器,给"状态status"与"data"赋值
     * @param status
     * @param data
     */
    private ServerResponse(int status, T data) {
        this.status = status;
        this.data = data;
    }
    /**
     * 私有构造器，给"status","msg","data" 赋值
     * @param status
     * @param msg
     * @param data
     */
    private ServerResponse(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    //判断是否是一个成功的响应==0,用枚举类来表示
    @JsonIgnore //不在json序列化结果中
    public boolean isSuccess() {
   //return this.status==0;
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus() {
        return this.status;
    }

    public T getData() {
        return this.data;
    }

    public String getMsg() {
        return this.msg;
    }
    /**
     * 泛型方法，返回一个带有"status"值的该泛型类的对象
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T>createBySuccess(){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode());
    }

    /**
     * 泛型方法,返回一个带有"status","msg"值得该泛型类的对象
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> ServerResponse<T>createBySuccessMessage(String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }
    /**
     * 泛型方法,返回一个带有"status","data"值得该泛型类的对象
     * @param data
     * @param <T>
     * @return
     */
    public static <T>ServerResponse<T>createBySuccess(T data){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    /**
     * 泛型方法,返回一个带有"status","data","msg"值得该泛型类的对象
     * @param data
     * @param msg
     * @param <T>
     * @return
     */
    public static <T>ServerResponse<T>createBySuccess(T data,String msg){
        return new ServerResponse<T>(ResponseCode.SUCCESS.getCode(),msg,data);
    }
    //创建失败
    public static <T>ServerResponse<T>createByError(){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }
    public static <T>ServerResponse<T>createByErrorMessage(String errorMessage){
        return new ServerResponse<T>(ResponseCode.ERROR.getCode(),errorMessage);
    }
    public static <T>ServerResponse<T>createByErrorCodeMessage(int errorCode,String errorMessage){
        return new ServerResponse<T>(errorCode,errorMessage);
    }
    }