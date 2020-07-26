package com.housing.authority.Tuples.Message;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

//@Component
public class Receiver {
    @JmsListener(destination = "mailbox", containerFactory = "factory")
    public void receiveMessage(Email email){
        System.out.println("Received <" +email + ">");
    }
}
