package com.lanxing.netty.demo7.client;

import com.lanxing.netty.demo7.Person;
import com.lanxing.netty.demo7.PersonService;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {
    public static void main(String[] args) {
        TTransport tTransport=new TFramedTransport(new TSocket("localhost",9090),600);
        TProtocol protocol=new TCompactProtocol(tTransport);
        PersonService.Client client=new PersonService.Client(protocol);

        try {
            tTransport.open();
            Person person=client.getPersonByUsername("111");
            System.out.println(person);
            client.savePerson(new Person().setAge(24).setMarried(true).setName("test"));
        } catch (TException e) {
            e.printStackTrace();
        }finally {
            tTransport.close();
        }
    }
}
