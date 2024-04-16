package com.zy.seckill.sys.nettySocket.modbus.server;

import com.zy.seckill.common.redis.RedisService;
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
            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            //(3).将线程进行阻塞,使得客户端可以一直请求服务器，一旦服务器出现异常，再进入finally进行关闭Netty释放资源
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
