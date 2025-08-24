package card;

public class Card {
    private final String cardNumber;
    private final long accountNumber;
    private final int pin;

    public Card(String cardNumber, long accountNumber, int pin) {
        this.cardNumber = cardNumber;
        this.accountNumber = accountNumber;
        this.pin = pin;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public boolean validatePin(int entered) {
        return pin == entered;
    }
}
