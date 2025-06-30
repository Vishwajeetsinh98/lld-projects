package service;

import message.Message;

public abstract class NotificationService {

    public abstract Message createMessage();
    public void sendNotification(String message, String destination) {
        Message msg = createMessage();
        msg.send(message, destination);
    }
}
