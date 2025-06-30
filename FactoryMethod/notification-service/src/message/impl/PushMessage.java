package message.impl;

import message.Message;

public class PushMessage implements Message {
    @Override
    public void send(String message, String destination) {
        System.out.println("Sending Push Message to: " + destination + "\n" + message);
        System.out.println("-------------------------");
    }
}
