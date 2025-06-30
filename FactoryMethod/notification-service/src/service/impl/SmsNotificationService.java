package service.impl;

import message.Message;
import message.impl.SMSMessage;
import service.NotificationService;

public class SmsNotificationService extends NotificationService {
    @Override
    public Message createMessage() {
        return new SMSMessage();
    }
}
