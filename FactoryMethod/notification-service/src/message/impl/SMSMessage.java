package message.impl;

import message.Message;

public class SMSMessage implements Message {
    @Override
    public void send(String message, String destination) {
        System.out.println("Sending SMS Message to: " + destination + "\n" + message);
        System.out.println("-------------------------");
    }
}
