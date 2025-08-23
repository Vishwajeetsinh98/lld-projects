package payment;

public class CashPaymentStrategy implements PaymentStrategy {

    private final double amount;
    private final double amountPaid;

    public CashPaymentStrategy(double amount, double amountPaid) {
        this.amount = amount;
        this.amountPaid = amountPaid;
    }

    @Override
    public boolean collect() {
        if (amountPaid < amount) {
            System.out.println("[CashPaymentStrategy] Insufficient cash given: " + amountPaid + ". Amount to pay is: " + amount);
            return false;
        }

        if (amountPaid > amount) {
            System.out.println("[CashPaymentStrategy] Amount returned: " + (amountPaid - amount));
        }
        System.out.println("[CashPaymentStrategy] Payment successful!");
        return true;
    }
}
