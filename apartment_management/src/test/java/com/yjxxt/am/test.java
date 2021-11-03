package com.yjxxt.am;

import org.junit.Test;

public class test {
    @Test
    public void test(){
        String str = "1234";
        System.out.println(Integer.valueOf(str));
        System.out.println(Integer.valueOf(str) instanceof Integer);
        System.out.println(Integer.parseInt(str));
    }
}
