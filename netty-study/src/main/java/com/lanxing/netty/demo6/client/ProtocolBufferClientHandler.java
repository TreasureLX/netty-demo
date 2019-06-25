package com.lanxing.netty.demo6.client;

import com.lanxing.netty.demo6.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import java.util.Random;

public class ProtocolBufferClientHandler extends SimpleChannelInboundHandler<DataInfo.MessageType> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MessageType msg) throws Exception {
        System.out.println(msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        int randomTime=new Random().nextInt(2);
        DataInfo.MessageType messageType=null;
        if(randomTime==0){
            messageType=DataInfo.MessageType.newBuilder()
                    .setDataType(DataInfo.MessageType.DataType.student_type)
                    .setStudent(
                            DataInfo.Student.newBuilder()
                                    .setName("lanxing").setAddress("深圳").setAge(25).build()
                    ).build();
        }else if(randomTime==1){
            messageType=DataInfo.MessageType.newBuilder()
                    .setDataType(DataInfo.MessageType.DataType.course_type)
                    .setCourse(
                            DataInfo.Course.newBuilder().setCourseName("测试").build()
                    ).build();
        }
        ctx.writeAndFlush(messageType);
    }
}
