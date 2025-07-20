package services.payment;

public class CardPaymentProcessor implements PaymentProcessor {

    private final String cardId;
    private final String pin;
    private final double amount;

    public CardPaymentProcessor(String cardId, String pin, double amount) {
        this.cardId = cardId;
        this.pin = pin;
        this.amount = amount;
    }

    @Override
    public boolean pay(Payment payment) {

        // [check PIN is correct etc.]

        System.out.println("[CardPaymentProcessor] collecting " + amount + " from: " + cardId);
        return true;
    }
}
