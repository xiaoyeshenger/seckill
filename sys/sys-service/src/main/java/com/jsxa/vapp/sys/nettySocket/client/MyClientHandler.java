package com.jsxa.vapp.sys.nettySocket.client;

import com.jsxa.vapp.common.utils.StrUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MyClientHandler extends SimpleChannelInboundHandler<Object> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (null == msg || !(msg instanceof byte[])) {
            super.channelRead(ctx, msg);
            return;
        }

        byte[] data = (byte[]) msg;
        int dataLength = data.length;
        String message = StrUtils.byteArrayToStr(data);
        log.info(" ---> 客户端接收到服务端数据,长度:{}，内容:{}",dataLength,message);
    }

    //当连接建立好的时候调用
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        String hello = "你好,服务端！";
        log.info(" ---> 客户端已成功连接上服务器,开始向服务端发送数据:{}",hello);
        byte[] bytes = hello.getBytes();
        //ctx.channel().writeAndFlush(bytes);
        ctx.writeAndFlush(bytes);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
