package payment;

public class CardPaymentStrategy implements PaymentStrategy {

    private final String cardNumber;
    private final String name;
    private final double amount;

    public CardPaymentStrategy(String cardNumber, String name, double amount) {
        this.cardNumber = cardNumber;
        this.name = name;
        this.amount = amount;
    }

    @Override
    public boolean collect() {
        System.out.println("[CardPaymentStrategy] Collecting " + amount + " from: " + name + " cardNumber: " + cardNumber);
        return true;
    }
}
