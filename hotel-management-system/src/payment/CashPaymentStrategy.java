package payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean collect(double amount, double amountPaid, String... details) {
        if (amountPaid < amount)
            return false;
        System.out.println("[CashPaymentStrategy] Returning: " + (amountPaid - amount));
        return true;
    }
}
