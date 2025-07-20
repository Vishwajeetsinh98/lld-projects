package services.payment;

import enums.PaymentMethod;
import enums.PaymentStatus;

public class Payment {
    private final String id;
    private final double amount;
    private PaymentMethod paymentMethod;
    private PaymentStatus paymentStatus;

    public Payment(String id, double amount) {
        this.id = id;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.UNPAID;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(PaymentStatus paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
