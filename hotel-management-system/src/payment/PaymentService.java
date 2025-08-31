package payment;

public class PaymentService {
    private PaymentStrategy paymentStrategy;

    public PaymentService() {
        paymentStrategy = new CardPaymentStrategy();
    }

    public boolean collect(double amount, double amountPaid, String... details) {
        return paymentStrategy.collect(amount, amountPaid, details);
    }

    public boolean collect(double amount, String... details) {
        return collect(amount, 0.0, details);
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        paymentStrategy = strategy;
    }
}
