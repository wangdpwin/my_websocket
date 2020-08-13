package cn.wangdpwin.websocket.ws2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-11 15:07
 * @Description
 * @Version 1.0
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WsBaseAuthHandler wsBaseAuthHandler;
    @Autowired
    private MyWsAuthHandler myWsAuthHandler;
    @Autowired
    private WsBaseInterceptor wsBaseInterceptor;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        System.out.println("注册过滤器 拦截器");
        System.out.println(wsBaseAuthHandler.toString());
        System.out.println(wsBaseInterceptor.toString());
        registry
                .addHandler(wsBaseAuthHandler, "baseWS")
                .addHandler(myWsAuthHandler, "myWS")
                .addInterceptors(wsBaseInterceptor)
                .setAllowedOrigins("*");
    }

    @Bean
    public ServerEndpointExporter serverEndpoint() {
        return new ServerEndpointExporter();
    }

}