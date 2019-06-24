package com.lanxing.netty.client;

import com.lanxing.netty.client.handler.ClientHandler;
import com.lanxing.netty.client.initializer.ClientInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author lanxing
 */
public class NettyClient {

    public static void main(String[] args) throws IOException {
        startClient();
    }

    private static void startClient() throws IOException {
        EventLoopGroup boosGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap
                    .group(workGroup)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientInitializer());
            try {
                Channel f = bootstrap.connect("127.0.0.1", 9999).sync().channel();
                System.out.println("Client start ......");
                BufferedReader in = new BufferedReader(new
                        InputStreamReader(System.in));
                String flag="";
                while (!"exit".equals(flag)){
                    String str=in.readLine();
                    flag=str;
                    f.writeAndFlush(str+"\r\n");
                }
                f.closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            boosGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }


    }
}
