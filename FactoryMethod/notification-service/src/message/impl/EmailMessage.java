package message.impl;

import message.Message;

public class EmailMessage implements Message {
    @Override
    public void send(String message, String destination) {
        System.out.println("Sending Email to: " + destination + "\n" + message);
        System.out.println("-------------------------");
    }
}
