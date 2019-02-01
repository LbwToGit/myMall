package com.mmall.test;

import com.mall.service.impl.UserServiceImpl;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Cxl on 2018/12/20-14:37
 * Description: Mmall
 */
public class BigDecimalTest {
//    @Test
//    public void test(){
//        System.out.println(0.05+0.01);
//        System.out.println(1.0-0.42);
//        System.out.println(4.015*100);
//        System.out.println(123.3/100);
//    }
//    @Test
//    public void test2(){
//        BigDecimal bigDecimal=new BigDecimal(0.05);
//        BigDecimal bigDecimal2=new BigDecimal(0.01);
//        System.out.println(bigDecimal.add(bigDecimal2));
//    }
//    public void test3(){
//        BigDecimal bigDecimal=new BigDecimal("0.05");
//        BigDecimal bigDecimal2=new BigDecimal("0.01");
//        System.out.println(bigDecimal.add(bigDecimal2));
//    }
    @Test
    public void test4(){
        ApplicationContext applicationContext=new ClassPathXmlApplicationContext("applicationContext.xml");
        UserServiceImpl us=(UserServiceImpl) applicationContext.getBean("iUserService");
        System.out.println("dsd:"+us.login("admin","admin").getData());
    }
}
