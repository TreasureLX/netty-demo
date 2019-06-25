package com.lanxing.netty.demo6.server;

import com.lanxing.netty.demo6.DataInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtocolBufferServerHandler extends SimpleChannelInboundHandler<DataInfo.MessageType> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.MessageType msg) throws Exception {
        DataInfo.MessageType.DataType dateType=msg.getDataType();
        if(dateType==DataInfo.MessageType.DataType.student_type){
            DataInfo.Student student=msg.getStudent();
            System.out.println(student.getAddress());
        }else if(dateType==DataInfo.MessageType.DataType.course_type){
            DataInfo.Course course=msg.getCourse();
            System.out.println(course.getCourseName());
        }
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("hello "+ctx.channel().id());
        super.channelActive(ctx);
    }
}
