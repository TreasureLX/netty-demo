package com.lanxing.netty.server.initializer;

import com.lanxing.netty.server.handler.ServerHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 * @author lanxing
 */
public class ServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        // 添加帧限定符来防止粘包现象
//        pipeline.addLast(new DelimiterBasedFrameDecoder(1024));
//        pipeline.addLast(new LineBasedFrameDecoder(1024));
        // 解码和编码，应和客户端一致
        pipeline.addLast(new StringDecoder());
        pipeline.addLast(new StringEncoder());
        // 业务逻辑实现类
        pipeline.addLast(new ServerHandler());
    }
}
