package shoe;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Shoe {
    private final List<Card> cards;

    public Shoe() {
        cards = new LinkedList<>();
    }

    public void addDeck(Deck deck) {
        addCardsFromDeck(deck);
        shuffleCards();
    }

    public void shuffleCards() {
        Collections.shuffle(cards);
    }

    public int getRemainingCards() {
        return cards.size();
    }

    public Card getNextCard() {
        if (cards.isEmpty())
            throw new RuntimeException("Cards over!");
        return cards.removeFirst();
    }

    private void addCardsFromDeck(Deck deck) {
        cards.addAll(deck.getCards());
    }
}
