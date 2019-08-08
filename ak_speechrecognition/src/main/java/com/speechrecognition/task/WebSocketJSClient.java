package com.speechrecognition.task;

import com.speechrecognition.datamodel.request.Common;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import org.springframework.web.socket.sockjs.frame.Jackson2SockJsMessageCodec;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
@Component
public class WebSocketJSClient {
    private WebSocketStompClient stompClient ;// stomp 客户端
    private SockJsClient sockJsClient;  // socket 客户端
    private StompSession session;      // 连接回话
    private ThreadPoolTaskScheduler task = null;
    @Value("")
    private String url;
    @Value("")
    private static final Logger logger = LoggerFactory.getLogger(WebSocketJSClient.class);
    @Value("")
    private String subscribe;
    @Async
    public void SocketConnect(String meetingId) throws Exception{
        WebSocketJSClient myClient = new WebSocketJSClient();
        myClient.run(myClient,url,subscribe + meetingId,"","");
    }
    public void run(WebSocketJSClient client,String url,String subscribe,String send,String sendMsg) throws Exception{
        //连接到对应的endpoint点上，也就是建立起websocket连接
        ListenableFuture<StompSession> listenableFuture = client.connection(url);
        // 连接会话
        session = listenableFuture.get();
        client.subscribeGreetings(subscribe, session); // 订阅消息
        session.setAutoReceipt(true);
      /*  //绑定发送的的地址send，注意这里使用的字节方式发送数据
        StompSession.Receiptable rec= session.send(send,sendMsg.getBytes("UTF-8"));
        //添加消息发送成功的回调
        rec.addReceiptLostTask(new Runnable() {
            public void run() {
                logger.info("消息发送成功,发送内容为:"+sendMsg);
            }
        });*/
    }
    public ListenableFuture<StompSession> connection(String url){
        Transport transport = new WebSocketTransport(new StandardWebSocketClient());
        List<Transport> transports = Collections.singletonList(transport);
        sockJsClient = new SockJsClient(transports);
        // 设置编码
        sockJsClient.setMessageCodec(new Jackson2SockJsMessageCodec());
        stompClient = new WebSocketStompClient(sockJsClient);
        stompClient.setReceiptTimeLimit(300);
        stompClient.setDefaultHeartbeat(new long[]{10000l,10000l});
        task = new ThreadPoolTaskScheduler();
        task.initialize();
        stompClient.setTaskScheduler(task);
        return  stompClient.connect(url,new MyHandle(),"",8080);

    }
    public void subscribeGreetings(String subscribeUrl,StompSession session){
        session.subscribe(subscribeUrl, new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return byte[].class;//设置订阅到消息用字节方式接收
            }
            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                String recvString=null;
                try {
                    recvString = new String((byte[]) payload,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                logger.info("收到返回的消息" + recvString);
                Common.setSoundCarAndLanguageState(false);
                Common.setSoundCarAndLanguageType(recvString);

            }
        });

    }
    class MyHandle extends StompSessionHandlerAdapter {
        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            super.afterConnected(session, connectedHeaders);
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            super.handleException(session, command, headers, payload, exception);
        }

        @Override
        public void handleTransportError(StompSession session, Throwable exception) {
            super.handleTransportError(session, exception);
        }
    }
}
