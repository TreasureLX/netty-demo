package com.lanxing.netty.demo1.http.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

public class TcpServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        pipeline.addLast("stringEncoder",new StringEncoder());
        pipeline.addLast("stringDecoder",new StringDecoder());
        pipeline.addLast("tcpServerHandler",new TcpServerHandler());
    }
}
