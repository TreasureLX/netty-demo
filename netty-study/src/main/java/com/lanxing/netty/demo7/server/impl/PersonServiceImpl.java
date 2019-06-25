package com.lanxing.netty.demo7.server.impl;

import com.lanxing.netty.demo7.DataException;
import com.lanxing.netty.demo7.Person;
import com.lanxing.netty.demo7.PersonService;
import org.apache.thrift.TException;

public class PersonServiceImpl implements PersonService.Iface {
    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println(username);
        return new Person().setAge(20).setMarried(false).setName(username);
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("save person:"+person.toString());
    }
}
