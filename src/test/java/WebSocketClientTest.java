import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.CountDownLatch;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author: wangdongpeng
 * @Date: 2020-08-07 14:14
 * @Description
 * @Version 1.0
 */
public class WebSocketClientTest extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketClientTest.class);

    private String uri;
    private CountDownLatch countDownLatch;

    public WebSocketClientTest(String uri, CountDownLatch countDownLatch) {
        this.uri = uri;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        getClient(uri);
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取客户端连接实例
     */
    public WebSocketClient getClient(String uri) {
        try {
            //创建客户端连接对象
            WebSocketClient client = new WebSocketClient(new URI(uri)) {
                /**
                 * 建立连接调用
                 * @param serverHandshake
                 */
                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    logger.info(uri + "===建立连接===");
                }

                /**
                 * 收到服务端消息调用
                 * @param s
                 */
                @Override
                public void onMessage(String s) {
                    logger.info(uri + "====收到来自服务端的消息===" + s);
                }

                /**
                 * 断开连接调用
                 * @param i
                 * @param s
                 * @param b
                 */
                @Override
                public void onClose(int i, String s, boolean b) {
                    logger.info("关闭连接:::" + "i = " + i + ":::s = " + s + ":::b = " + b);
                }

                /**
                 * 连接报错调用
                 * @param e
                 */
                @Override
                public void onError(Exception e) {
                    logger.error("====出现错误====" + e.getMessage());
                }
            };
            //请求与服务端建立连接
            client.connect();
            //判断连接状态，0为请求中  1为已建立  其它值都是建立失败
            while (client.getReadyState().ordinal() == 0) {
                try {
                    Thread.sleep(200);
                } catch (Exception e) {
                    logger.warn("延迟操作出现问题，但并不影响功能");
                }
                logger.info("连接中.......");
            }
            //连接状态不再是0请求中，判断建立结果是不是1已建立
            if (client.getReadyState().ordinal() == 1) {
                return client;
            }
            if (!(client.getReadyState().ordinal() == 1 || client.getReadyState().ordinal() == 0)) {
                logger.info(uri + "连接失败.......");
            }
        } catch (URISyntaxException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

}
