package shoe;

public class Card {
    private final CardName cardName;
    private final CardShape cardShape;
    private final int cardValue;

    public Card(CardName cardName, CardShape cardShape) {
        this.cardName = cardName;
        this.cardShape = cardShape;
        this.cardValue = calculateCardValue();
    }

    public CardName getCardName() {
        return cardName;
    }

    public CardShape getCardShape() {
        return cardShape;
    }

    public int getCardValue() {
        return cardValue;
    }

    public String toString() {
        return cardName + " of " + cardShape;
    }

    private int calculateCardValue() {
        return switch (cardName) {
            case ACE -> 11;
            case JACK, QUEEN, KING -> 10;
            default -> cardName.ordinal() + 1;
        };
    }
}
