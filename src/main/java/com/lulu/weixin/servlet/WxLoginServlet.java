package com.lulu.weixin.servlet;

import com.alibaba.fastjson.JSONObject;
import com.lulu.weixin.utils.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "WxLoginServlet", urlPatterns = "/wxLogin")
public class WxLoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=" + WeixinUtil.APPID +
                "&redirect_uri=" + URLEncoder.encode("http://d058f355.ngrok.io/callback.do", "utf8") +
                "&response_type=code" +
                "&scope=snsapi_userinfo" +
                "&state=STATE#wechat_redirect";
        System.out.println(url);
        response.sendRedirect(url);
    }
}
