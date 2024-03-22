package com.jsxa.vapp.sys.nettySocket.modbus.server;

import com.jsxa.vapp.common.redis.RedisService;
import com.jsxa.vapp.sys.nettySocket.comon.ExceptionHandler;
import com.jsxa.vapp.sys.nettySocket.comon.HeartBeatServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import org.springframework.stereotype.Service;


/**
 * 自定义 Socket的初始化器，包括编码解码器、心跳处理器、异常处理器、自定义的数据处理器等
 */
@Service
public class ModbusServerInitializer extends ChannelInitializer<SocketChannel> {


    private RedisService redisService;


    public ModbusServerInitializer(RedisService redisService) {
        this.redisService = redisService;
    }


    @Override
    protected void initChannel(SocketChannel ch) {

        //1.ChannelPipeline类是ChannelHandler实例对象的链表，用于处理或截获通道的接收和发送数据
        ChannelPipeline pipeline = ch.pipeline();

        //2.添加编码器和解码器
        //以下是一些限定和编码解码器
        /*pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4, 0, 4));
        pipeline.addLast(new LengthFieldPrepender(4));
        pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
        pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
        pipeline.addLast(new MyServerHandler());*/
        pipeline.addLast("bdeCoder", new ByteArrayDecoder());
        pipeline.addLast("benCoder", new ByteArrayEncoder());

        //3.添加自定义的数据处理器
        pipeline.addLast("modbusServerHandler", new ModbusServerHandler(redisService));

        //4.添加心跳检测器
        pipeline.addLast("heartBeat", new HeartBeatServerHandler());

        //5.添加异常处理器
        pipeline.addLast("exception",  new ExceptionHandler());
    }
}
