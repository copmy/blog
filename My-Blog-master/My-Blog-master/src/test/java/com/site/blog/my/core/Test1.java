package com.site.blog.my.core;


import com.site.blog.my.core.util.MD5Util;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @Description
 * @Author linhongqi
 * @Data 2023/6/25 9:03
 */
@SpringBootTest
public class Test1 {
    @Test
    public void test1(){
        String url = "http://localhost:8080/admin/dist/img/rand/18.jpg";
        String[] sp = url.split("/");
        String s = "";
        for (int i = 3; i < sp.length; i++) {
            s+="/"+sp[i];
        }
        System.out.println(s);
    }


    @Test
    public void test2(){
        String url = "http://f7ir9f.natappfree.cc/upload/20230625_09532688.png";
        String[] sp = url.split("/");
        String s = "";
        for (int i = 3; i < sp.length; i++) {
            s+="/"+sp[i];
        }
        System.out.println(s);
//        System.out.println(c.concat(sp[sp.length-1]));
    }

    @Test
    public void test3(){
        String passwordMd5 = MD5Util.MD5Encode("123456", "UTF-8");
        System.out.println(passwordMd5);
    }
}
