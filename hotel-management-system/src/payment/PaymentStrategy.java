package payment;

public interface PaymentStrategy {
    public boolean collect(double amount, double amountPaid, String... details);
}
