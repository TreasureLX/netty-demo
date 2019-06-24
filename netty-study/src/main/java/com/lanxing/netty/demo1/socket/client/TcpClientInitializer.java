package com.lanxing.netty.demo1.socket.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TcpClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("stringEncoder", new StringEncoder());
        pipeline.addLast("stringDecoder", new StringDecoder());
        pipeline.addLast("tcpClientHandler", new TcpClientHandler());
    }
}
