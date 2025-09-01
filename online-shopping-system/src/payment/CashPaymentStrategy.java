package payment;

public class CashPaymentStrategy implements PaymentStrategy {
    @Override
    public void collect(double amount) {
        System.out.println("[CashPaymentStrategy] collected " + amount);
    }
}
