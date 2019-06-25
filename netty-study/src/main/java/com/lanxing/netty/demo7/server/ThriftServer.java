package com.lanxing.netty.demo7.server;

import com.lanxing.netty.demo7.PersonService;
import com.lanxing.netty.demo7.server.impl.PersonServiceImpl;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.THsHaServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;

public class ThriftServer {
    public static void main(String[] args) throws Exception {
        //声明socket、arg、processor
        TNonblockingServerSocket socket=new TNonblockingServerSocket(8899);
        THsHaServer.Args arg=new THsHaServer.Args(socket).minWorkerThreads(2).maxWorkerThreads(4);
        PersonService.Processor<PersonServiceImpl> processor=new PersonService.Processor<>(new PersonServiceImpl());
        //设置arg
        arg.protocolFactory(new TCompactProtocol.Factory());
        //以Frame为单位，非阻塞服务中使用
        arg.transportFactory(new TFramedTransport.Factory());
        //
        arg.processorFactory(new TProcessorFactory(processor));
        //声明服务
        TServer server=new THsHaServer(arg);
        //启动服务
        System.out.println("server start");
        server.serve();
    }
}
