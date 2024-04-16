package com.zy.seckill.sys.nettySocket.client;

import com.zy.seckill.sys.nettySocket.comon.ExceptionHandler;
import com.zy.seckill.sys.nettySocket.comon.HeartBeatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline pipeline = ch.pipeline();
        /*//解码器
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        //编码器
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyClientHandler());*/

        pipeline.addLast("bdeCoder", new ByteArrayDecoder());
        pipeline.addLast("benCoder", new ByteArrayEncoder());
        //客户端处理器
        //pipeline.addLast("myRequestHandler", myRequestHandler);
        pipeline.addLast("myClientHandler", new MyClientHandler());
        //心跳检测
        pipeline.addLast("heartBeat", new HeartBeatServerHandler());
        //异常处理
        //pipeline.addLast("exception", exceptionHandler);
        pipeline.addLast("exception",  new ExceptionHandler());

    }
}
