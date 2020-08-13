package cn.wangdpwin.websocket.ws1;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import org.springframework.stereotype.Component;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-07 12:23
 * @Description
 * @Version 1.0
 */
@ServerEndpoint("/myWS1")
@Component
public class WsServerEndpoint {

    /**
     * 连接成功
     *
     * @param session
     */
    @OnOpen
    public void onOpen(Session session) {
        String token = null;
        //请求uri中参数，类似 @PathVariable
        //Map<String, String> parameters = session.getPathParameters();
        //获取token
        List<String> tokens = session.getRequestParameterMap().getOrDefault("token", Collections.singletonList(""));
        token = tokens.get(0);
        //获取token方式2
//        String queryString = session.getQueryString();
//        String[] split = queryString.split("&");
//        for (String str : split) {
//            if(str.startsWith("token")) {
//                token = str.split("=")[1];
//            }
//        }
        System.out.println("token: " + token);
        System.out.println("连接成功 session");
    }

    /**
     * 连接关闭dd
     *
     * @param session
     */
    @OnClose
    public void onClose(Session session) {
        System.out.println("连接关闭 session");
    }

    /**
     * 接收到消息
     *
     * @param text
     */
    @OnMessage
    public String onMsg(String text) throws IOException {
        return "servet 发送：" + text;
    }
}