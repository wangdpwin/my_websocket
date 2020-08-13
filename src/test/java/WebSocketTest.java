import java.util.concurrent.CountDownLatch;

/**
 * @Author: wangdongpeng
 * @Date: 2020-08-07 14:13
 * @Description 测试websocket连接
 * @Version 1.0
 */
public class WebSocketTest {

    public static void main(String[] args) {
        String urlPre = "ws://localhost:8080/myWS";
        int threadNum = 100;
        CountDownLatch countDownLatch = new CountDownLatch(threadNum);
        for (int i = 0; i < threadNum; i++) {
            String url = urlPre + "?token=" + 1000 + i;
            new WebSocketClientTest(url, countDownLatch).start();
            countDownLatch.countDown();
        }
    }

}
