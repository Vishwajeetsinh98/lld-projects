package payment;

public class PaymentService {
    private PaymentStrategy paymentStrategy;
    public PaymentService() {
        this.paymentStrategy = new CardPaymentStrategy();
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void collect(double amount) {
        paymentStrategy.collect(amount);
    }
}
