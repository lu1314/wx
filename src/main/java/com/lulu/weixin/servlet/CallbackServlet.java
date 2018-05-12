package com.lulu.weixin.servlet;

import com.alibaba.fastjson.JSONObject;
import com.lulu.weixin.utils.WeixinUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/callback.do")
public class CallbackServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String code = req.getParameter("CODE");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + WeixinUtil.APPID +
                "&secret=" + WeixinUtil.APPSECRET +
                "&code=" + code +
                "&grant_type=authorization_code";
        JSONObject object = WeixinUtil.doGetJson(url);
        String token = object.getString("access_token");
        String openid = object.getString("openid");

        String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=" + token +
                "&openid=" + openid +
                "&lang=zh_CN";
        JSONObject jsonObject = WeixinUtil.doGetJson(infoUrl);
        req.setAttribute("info",jsonObject);
        req.getRequestDispatcher("/index.jsp").forward(req,resp);
    }
}
