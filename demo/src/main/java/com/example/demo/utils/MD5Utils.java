package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
MD5加密工具类
 */
public class MD5Utils {
    private MD5Utils(){}
    public  static String MD5(String s){
        byte[] md5s ;
        try {
            md5s = MessageDigest.getInstance("MD5").digest(s.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            //md5
            throw  new  RuntimeException("MD5",e);
        } catch (UnsupportedEncodingException e) {
            throw  new  RuntimeException("UTF-8",e);
            //UTF-8
        }
        StringBuilder str = new StringBuilder(md5s.length*2);
        for (byte md5 : md5s) {
            if((md5&0xff)<0x10)str.append(0);//小于两位加0
            str.append(Integer.toHexString(md5&0xff));//去除负数ffff
        }
        return str.toString().toUpperCase();
    }
    public static void main(String[] args){
        System.out.println(MD5("123456"));
    }
}
