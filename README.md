# my_websocket
websocket sever demo, base on springboot & websocket  
from : https://juejin.im/post/6844903976727494669#heading-13

## ws1
```aspectj
endpoint方式类似于restController，实现简单，符合开发习惯，定义url匹配规则，需要自己实现websocket事件，支持读取token，实现鉴权，暂时没有较好抽象方式
```

## ws2
```
handler intercept方式，可以抽象多个公共ws连接请求，符合aop思想，推荐使用该方式

```

## test
http://coolaf.com/tool/chattest
