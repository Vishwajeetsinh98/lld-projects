package services.payment;

import enums.PaymentMethod;

import java.util.HashMap;
import java.util.Map;

public class PaymentFactory {


    private static long id = 0L;

    public static Payment createPayment(double amount) {
        String paymentId = Long.toString(id++);
        return new Payment(paymentId, amount);
    }
}
