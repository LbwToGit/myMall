package com.mall.util;

import com.github.pagehelper.StringUtil;
import com.mall.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.type.JavaType;
import org.codehaus.jackson.type.TypeReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Cxl on 2019/1/6-4:56
 * Description: Mmall
 */
@Slf4j
public class JsonUtil {
    private static ObjectMapper objectMapper=new ObjectMapper();
    static {
        //对象的所有字段全部列入
        objectMapper.setSerializationInclusion(JsonSerialize.Inclusion.ALWAYS);

        //取消默认转换timestamps形式
        objectMapper.configure(SerializationConfig.Feature.WRITE_DATE_KEYS_AS_TIMESTAMPS,false);

        //忽略空bean转json的错误
        objectMapper.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS,false);

        //所有日期格式都统一为以下格式，yyyy-MM-dd HH:mm:ss
        objectMapper.setDateFormat(new SimpleDateFormat(DateTimeUtil.STANDARD_FORMAT));

        //忽略在json字符串中存在,但是在java对象中不存在对应属性的情况，防止错误
        objectMapper.configure(DeserializationConfig.Feature.FAIL_ON_NULL_FOR_PRIMITIVES,false);
    }

  public static <T> String obj2String(T obj){
        if (obj==null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj:objectMapper.writeValueAsString(obj);
        }catch (Exception e){
            log.warn("parse Object to String Error",e);
            return null;
        }
  }
    public static <T> String obj2StringPretty(T obj){
        if (obj==null){
            return null;
        }
        try {
            return obj instanceof String ? (String)obj:objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        }catch (Exception e){
            log.warn("Parse Object to String Error",e);
            return null;
        }
    }
    public static <T> T string2Obj(String str,Class<T>clazz){
        if (StringUtil.isEmpty(str)||clazz==null){
            return null;
        }
        try {
            return clazz.equals(String.class)?(T)str:objectMapper.readValue(str,clazz);
        }catch (Exception e){
            log.warn("Parse String to Object Error",e);
            return null;
        }
    }
    public static <T> T string2Obj(String str, TypeReference<T>typeReference){
        if (StringUtil.isEmpty(str)||typeReference==null){
            return null;
        }
        try {
            return (T)(typeReference.getType().equals(String.class)?str:objectMapper.readValue(str,typeReference));
        }catch (Exception e){
            log.warn("Parse String to Object Error",e);
            return null;
        }
    }
    public static <T> T string2Obj(String str,Class<?>collectionClass,Class<?>...elementClasses){
        JavaType javaType=objectMapper.getTypeFactory().constructParametricType(collectionClass,elementClasses);
        try {
            return objectMapper.readValue(str,javaType);
        }catch (Exception e){
            log.warn("Parse String to Object Error",e);
            return null;
        }
    }

    public static void main(String[] args) {
        User user=new User();
        user.setUsername("cxl");
        user.setEmail("2413865026@qq.com");

        User user3=new User();
        user.setUsername("cxl2");
        user.setEmail("csdn_dawn@163.com");

        List<User>list=new ArrayList<User>();
        list.add(user);
        list.add(user3);

        String listJson=obj2StringPretty(list);
        log.info("UserList：{}",listJson);

        List<User>list1=new ArrayList<User>();
        list1=string2Obj(listJson, new TypeReference<List<User>>() {});
        log.info("list1:{}",list1.get(0).getUsername());
        String user1Json=obj2String(user);
        String user2Json=obj2StringPretty(user);

        log.info("user1Josn:{}",user1Json);
        log.info("user2Json:{}",user2Json);

        User user2=string2Obj(user1Json,User.class);
        log.info("user2:{}",user2);
    }
}
