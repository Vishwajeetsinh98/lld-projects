package card;

public class CardReader {
    public boolean readCard(Card card) {
        if (card == null) return false;
        System.out.println("[CardReader] Reading card: " + card.getCardNumber());
        return true;
    }
}
