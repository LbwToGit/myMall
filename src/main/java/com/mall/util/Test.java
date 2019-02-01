package com.mall.util;

/**
 * Created by Cxl on 2018/12/17-10:18
 * Description: Mmall
 */
public class Test {
    public static void main(String[] args) {
    String name="沥青";
    char [] c=name.toCharArray();
    for (char ct:c){
        System.out.println(Integer.toBinaryString(ct));
    }
        System.out.println("byte");
    byte [] bt=name.getBytes();
        for (byte b:bt){
            System.out.println(b);
        }
    }
}
