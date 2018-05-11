package com.lulu.weixin.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class WeixinUtil {

    public static final String APPID =  "wxf646a09f953a22b6";

    public static final String APPSECRET = "2a962d811832fbf2c5fe47f8ae74815b";


    public static void main(String[] args) throws UnsupportedEncodingException {
        System.out.println(URLEncoder.encode("http://53f67f6b.ngrok.io/wechat/login.do", "utf8"));
    }
}
