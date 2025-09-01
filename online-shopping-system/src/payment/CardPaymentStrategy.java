package payment;

public class CardPaymentStrategy implements PaymentStrategy {

    @Override
    public void collect(double amount) {
        System.out.println("[CardPaymentStrategy] collected " + amount);
    }
}
