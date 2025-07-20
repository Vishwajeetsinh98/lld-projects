package services.payment;

public class CashPaymentProcessor implements PaymentProcessor {

    private final double amount;
    private final double amountPaid;
    public CashPaymentProcessor(double amount, double amountPaid) {
        this.amount = amount;
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean pay(Payment payment) {
        if (amount > amountPaid) {
            System.out.println("[CashPaymentProcessor]: insufficient amount!");
            return false;
        }
        System.out.println("[CashPaymentProcessor]: received " + amountPaid + " returning " + (amountPaid - amount) + " after deducting " + amount);
        return true;
    }
}
