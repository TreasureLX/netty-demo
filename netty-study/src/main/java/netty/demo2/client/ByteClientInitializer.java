package netty.demo2.client;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.bytes.ByteArrayDecoder;
import io.netty.handler.codec.bytes.ByteArrayEncoder;

public class ByteClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast("byteArrayDecoder", new ByteArrayDecoder());
        pipeline.addLast("byteArrayEncoder", new ByteArrayEncoder());
        pipeline.addLast("byteClientHandler", new ByteClientHandler());
    }
}
