package com.lanxing.netty.demo2.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ByteClient {
    public static void main(String[] args) {
        startByteClient();
    }

    private static void startByteClient() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new ByteClientInitializer());
        try {
            Channel channel = bootstrap.connect("127.0.0.1", 8585).sync().channel();
            Scanner scanner = new Scanner(System.in);
            String flag = "";
            while (!"exit".equals(flag)) {
                String str = scanner.nextLine();
                flag = str;
                List<String> list = Arrays.asList(str.split(","));
                byte[] data = new byte[list.size()];
                for (int i = 0; i < list.size(); i++) {
                    data[i]=Byte.parseByte(list.get(i));
                }
                channel.writeAndFlush(data);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            workGroup.shutdownGracefully();
        }
    }
}
