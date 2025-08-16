package players;

import shoe.Card;

public class Dealer extends BlackjackPlayer {

    private Card hiddenCard;

    public Dealer(String name) {
        super(name);
    }

    @Override
    protected void addToTable() {
        table.addDealer(this);
    }

    @Override
    protected void removeFromTable() {
        table.removeDealer(this);
    }

    public void addHiddenCard(Card card) {
        hiddenCard = card;
    }

    public void showHiddenCard() {
        System.out.println("[Dealer] hidden card: " + hiddenCard);
        addToHand(hiddenCard);
        hiddenCard = null;
        updateScore();
    }
}
