package com.lanxing.netty.demo2.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class ByteServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline=ch.pipeline();
        pipeline.addLast("byteArrayDecoder", new ByteArrayDecoder());
        pipeline.addLast("byteArrayEncoder", new ByteArrayEncoder());
        pipeline.addLast("byteServerHandler", new ByteServerHandler());
    }
}
