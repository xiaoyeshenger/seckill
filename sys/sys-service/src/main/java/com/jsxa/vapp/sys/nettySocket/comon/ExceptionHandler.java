package com.jsxa.vapp.sys.nettySocket.comon;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


/**
 * 服务端异常处理handler
 */
@Slf4j
@ChannelHandler.Sharable
@Service
public class ExceptionHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        if (cause instanceof Exception) {
            //捕捉异常信息
            cause.printStackTrace();
            log.error(cause.getMessage());
            ctx.close();
        } else {
            //捕捉异常信息
            cause.printStackTrace();
            log.error(cause.getMessage());
            ctx.close();
        }
    }

    /**
     * 通道 Read 读取 Complete 完成
     * 做刷新操作 ctx.flush()
     */
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
