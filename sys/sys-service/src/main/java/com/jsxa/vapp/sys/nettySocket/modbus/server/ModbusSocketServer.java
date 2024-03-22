package com.jsxa.vapp.sys.nettySocket.modbus.server;

import com.jsxa.vapp.common.redis.RedisService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ModbusSocketServer {

    @Resource
    private RedisService redisService;


    public void start(Integer port) {
        System.out.println("netty Socket(tcp) 服务器启动,端口:"+port);

        //1.负责连接请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();

        //2.负责事件响应
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        //3.开启服务
        try {
            //(1).初始化服务器启动项
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ModbusServerInitializer(redisService));
            //(2).绑定指定端口并启动
            //该方法进行阻塞,等待服务端链路关闭之后继续执行。这种模式一般都是使用Netty模块主动向服务端发送请求，然后最后结束才使用
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
