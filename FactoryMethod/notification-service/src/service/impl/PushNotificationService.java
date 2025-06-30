package service.impl;

import message.Message;
import message.impl.PushMessage;
import service.NotificationService;

public class PushNotificationService extends NotificationService {
    @Override
    public Message createMessage() {
        return new PushMessage();
    }
}
