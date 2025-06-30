package service.impl;

import message.Message;
import message.impl.EmailMessage;
import service.NotificationService;

public class EmailNotificationService extends NotificationService {
    @Override
    public Message createMessage() {
        return new EmailMessage();
    }
}
