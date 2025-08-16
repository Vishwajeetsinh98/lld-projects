package shoe;

import java.util.ArrayList;
import java.util.List;

public class Deck {
    private final List<Card> cards;
    public Deck() {
        cards = new ArrayList<>();
        for (CardShape cardShape : CardShape.values()) {
            for (CardName cardName : CardName.values()) {
                cards.add(new Card(cardName, cardShape));
            }
        }
    }
    public List<Card> getCards() {
        return cards;
    }
}