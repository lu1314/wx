package com.lulu.weixin.servlet;

import com.lulu.weixin.po.ImageMessage;
import com.lulu.weixin.po.MessageBase;
import com.lulu.weixin.po.TextMessage;
import com.lulu.weixin.utils.CheckUtil;
import com.lulu.weixin.utils.MessageUtil;
import org.dom4j.DocumentException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeixinServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PrintWriter out = resp.getWriter();
        String echostr = req.getParameter("echostr");
        if (CheckUtil.checkSignature(req)){
            out.write(echostr);
        }
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("utf-8");
            resp.setCharacterEncoding("utf-8");
            PrintWriter out = null;
        MessageBase message = null;
        try {
            out = resp.getWriter();
            Map<String,String> params = MessageUtil.xmlToMap(req);
            System.out.println(params.toString());
            String toUserName = params.get("ToUserName");
            String fromUserName = params.get("FromUserName");
            String createTime = params.get("CreateTime");
            String msgType = params.get("MsgType");
            String content = params.get("Content");
            String msgId = params.get("MsgId");

            if (MessageUtil.MEESAGE_TEXT.equals(msgType)){
                message = new TextMessage();
                message.setToUserName(fromUserName);
                message.setFromUserName(toUserName);
                message.setCreateTime(createTime);
                message.setMsgType(MessageUtil.MEESAGE_TEXT);
                ((TextMessage)message).setContent("您发送的消息是:" + content);
                ((TextMessage)message).setMsgId(msgId);

            }else if (MessageUtil.MEESAGE_EVENT.equals(msgType)){
                String eventType = params.get("Event");
                if (MessageUtil.MEESAGE_CLICK.equals(eventType)){
                   message = new ImageMessage();
                    String key = params.get("EventKey");
                    if ("ICE".equals(key)){
                        ImageMessage.Image image = new ImageMessage().new Image();
                        image.setMediaId("rRr74o17kQuCK9HSSvWoMEyoeJ9O2UzEq-nmEpurvl3E0M7bajWGWf-RohIpJ9OO");
                        ((ImageMessage)message).setImage(image);
                    }
                    //todo
                   message.setToUserName(fromUserName);
                    message.setFromUserName(toUserName);
                    message.setCreateTime(createTime);
                    message.setMsgType(MessageUtil.MEESAGE_IMAGE);

                }
            }
            String text = MessageUtil.textToXml(message);

            System.out.println(text);
            out.write(text);
        } catch (DocumentException e) {
            e.printStackTrace();
        }finally {
            out.close();
        }
    }
}
