package com.jsxa.vapp.sys.nettySocket.comon;

import com.jsxa.vapp.common.constant.ProtoInstant;
import com.jsxa.vapp.common.utils.CharacterConvert;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class HeartBeatServerHandler extends IdleStateHandler {
    private static final int READ_IDLE_GAP = 150;

    public HeartBeatServerHandler() {
        super(READ_IDLE_GAP, 0, 0, TimeUnit.SECONDS);
    }

    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 判断消息实例
        if (null == msg || !(msg instanceof byte[])) {
            super.channelRead(ctx, msg);
            return;
        }
        byte[] data = (byte[]) msg;
        int dataLength = data.length;
        ByteBuf buf = Unpooled.buffer(dataLength);
        buf.writeBytes(data);
        int type = CharacterConvert.byteToInt(buf.readByte());
        //机器编号
        int deviceId = CharacterConvert.byteToInt(buf.readByte());
        //如果是管理后台登录操作时
        if(type == ProtoInstant.HEART_BEAT){
            int verify = CharacterConvert.byteToInt(buf.readByte());
            int sum = CharacterConvert.sum(ProtoInstant.FIELD_HEAD, dataLength + ProtoInstant.FILED_LEN, type, deviceId);
            if(verify != CharacterConvert.getLow8(sum)){
                log.error("心跳包，校验位错误！机器编码：" + deviceId);
            }else{
                log.info("接收到心跳信息" + deviceId);
                if (ctx.channel().isActive()) {
                    ctx.writeAndFlush(msg);
                }
            }
        }else{
            super.channelRead(ctx, msg);
        }
    }

    @Override
    protected void channelIdle(ChannelHandlerContext ctx, IdleStateEvent evt) throws Exception {
        //log.info(READ_IDLE_GAP + "秒内未读到数据!");
    }
}
