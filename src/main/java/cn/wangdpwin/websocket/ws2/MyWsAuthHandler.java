package cn.wangdpwin.websocket.ws2;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-13 11:29
 * @Description
 * @Version 1.0
 */
@Component
public class MyWsAuthHandler extends WsBaseAuthHandler {

    /**
     * 接收消息事件
     *
     * @param session
     * @param message
     * @throws Exception
     */
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        System.out.println("log info: MyWsAuthHandler get message");
        super.handleTextMessage(session, message);
        System.out.println("log info: MyWsAuthHandler handle message success");
    }

}
