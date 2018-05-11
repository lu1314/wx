package com.lulu.weixin.utils;

import com.lulu.weixin.po.MessageBase;
import com.lulu.weixin.po.TextMessage;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MessageUtil {

    public static final String MEESAGE_TEXT = "text";
    public static final String MEESAGE_VOICE = "voice";
    public static final String MEESAGE_VIDEO = "video";
    public static final String MEESAGE_IMAGE = "image";
    public static final String MEESAGE_SHORTVIDEO = "shortvideo";
    public static final String MEESAGE_LOCATION = "location";
    public static final String MEESAGE_LINK = "link";
    public static final String MEESAGE_EVENT = "event";
    public static final String MEESAGE_CLICK = "CLICK";


    /**
     * XML转Map
     * @param request
     * @return
     * @throws IOException
     * @throws DocumentException
     */
    public static Map<String,String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<>(16);
        SAXReader saxReader = new SAXReader();

        InputStream ins = request.getInputStream();

        Document document = saxReader.read(ins);

        Element rootElement = document.getRootElement();

        List<Element> elements = rootElement.elements();

        for (Element element : elements) {
           map.put(element.getName(), element.getStringValue());
        }
        return map;
    }

    public static String textToXml(MessageBase message){
        XStream xStream = new XStream();
        xStream.alias("xml",message.getClass());
        return xStream.toXML(message);
    }
}
