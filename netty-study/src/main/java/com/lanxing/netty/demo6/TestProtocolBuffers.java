package com.lanxing.netty.demo6;

import com.google.protobuf.InvalidProtocolBufferException;

public class TestProtocolBuffers {
    public static void main(String[] args) throws InvalidProtocolBufferException {
        DataInfo.Student student=DataInfo.Student.newBuilder()
                .setName("lanxing").setAddress("shenzhen").setAge(25).build();
        byte[] data=student.toByteArray();
        DataInfo.Student student1=DataInfo.Student.parseFrom(data);
        System.out.println(student1.toByteString());
    }
}
