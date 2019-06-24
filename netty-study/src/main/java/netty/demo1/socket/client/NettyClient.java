package netty.demo1.socket.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class NettyClient {

    public static void main(String[] args) {
        startClient();
    }

    private static void startClient() {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new TcpClientInitializer());
        try {
            Channel channel=bootstrap.connect("127.0.0.1",8585).sync().channel();
            Scanner scanner=new Scanner(System.in);
            String flag="";
            while (!"exit".equals(flag)){
                String str=scanner.nextLine();
                flag=str;
                channel.writeAndFlush(str);
            }
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            workGroup.shutdownGracefully();
        }
    }
}
