package payment;

public class CardPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean collect(double amount, double amountPaid, String... details) {
        return true;
    }
}
