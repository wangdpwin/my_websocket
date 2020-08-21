package cn.wangdpwin.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-07 14:04
 * @Description
 * @Version 1.0
 */
@SpringBootApplication
public class MyWebsocketApp {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(MyWebsocketApp.class);
        application.run();
    }

}
