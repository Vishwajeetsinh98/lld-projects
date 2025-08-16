package players;

import shoe.Card;
import shoe.CardName;
import table.BlackjackTable;

import java.util.ArrayList;
import java.util.List;

public abstract class BlackjackPlayer {
    protected final String name;
    protected double money;
    protected final List<Card> hand;
    protected PlayerState playerState;
    protected BlackjackTable table;
    protected int score;

    public BlackjackPlayer(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
        this.playerState = PlayerState.NOT_PLAYING;
        this.table = null;
        this.score = 0;
    }

    public BlackjackPlayer(String name, double money) {
        this(name);
        this.money = money;
    }

    protected void addToTable() {
        table.addPlayer(this);
    }

    public void enterGame(BlackjackTable table) {
        this.table = table;
        addToTable();
        playerState = PlayerState.PLAYING;
    }

    public void watchRound() {
        playerState = PlayerState.NOT_PLAYING;
    }

    public boolean bet(double amount) {
        if (amount <= money) {
            System.out.println("[BlackjackPlayer] " + name + " bet: " + amount);
            money -= amount;
            return true;
        } else {
            System.out.println("[BlackjackPlayer]" + name + " insufficient money, removing from this round!");
            playerState = PlayerState.NOT_PLAYING;
        }
        return false;
    }

    protected void removeFromTable() {
        table.removePlayer(this);
    }

    public void leaveGame() {
        if (table == null) return;
        removeFromTable();
        playerState = PlayerState.NOT_PLAYING;
    }

    public void showPlayerHand() {
        System.out.println("[BlackjackPlayer] " + name + " Hand: ");
        for (Card card : hand) {
            System.out.println(card);
        }
        System.out.println("[BlackjackPlayer] " + name + " score: " + score);
    }

    public void hit() {
        if (playerState == PlayerState.PLAYING) {
            System.out.println("[BlackjackPlayer] " + name + " hit.");
            Card nextCard = table.getNextCard();
            addToHand(nextCard);
            System.out.println("[BlackjackPlayer] " + name + " next card: " + nextCard);
            updateScore();
        }
    }

    public void stand() {
        System.out.println("[BlackjackPlayer] " + name + " stands.");
        playerState = PlayerState.STAND;
    }

    public void win() {
        System.out.println("[BlackjackPlayer] " + name + " wins!");
        playerState = PlayerState.WIN;
    }

    public void lose() {
        System.out.println("[BlackjackPlayer] " + name + " loses!");
        playerState = PlayerState.LOSE;
    }

    public void tie() {
        System.out.println("[BlackjackPlayer] " + name + " tied!");
        playerState = PlayerState.TIE;
    }

    public boolean checkBlackjack() {
        boolean isAce = false;
        boolean isFace = false;
        for (Card card : hand) {
            CardName cardName = card.getCardName();
            if (cardName == CardName.ACE)
                isAce = true;
            else if (cardName == CardName.KING ||
                        cardName == CardName.JACK ||
                        cardName == CardName.QUEEN)
                isFace = true;
        }
        return isAce && isFace;
    }

    public void updateScore() {

        if (checkBlackjack()) {
            System.out.println("[BlackjackPlayer] " + name + " BlackJack!");
            playerState = PlayerState.BLACKJACK;
            return;
        }

        int handTotal = 0;
        int aceCount = 0;
        for (Card card : hand) {
            handTotal += card.getCardValue();
            if (card.getCardName() == CardName.ACE)
                aceCount++;
        }
        while (handTotal > 21 && aceCount > 0) {
            handTotal -= 10;
            aceCount--;
        }

        score = handTotal;
        if (handTotal > 21) {
            System.out.println("[BlackjackPlayer] " + name + " bust!");
            playerState = PlayerState.BUST;
            return;
        }
        System.out.println("[BlackjackPlayer] " + name + " score updated: " + score);
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void addToHand(Card card) {
        hand.add(card);
        updateScore();
    }

    public int getScore() {
        return score;
    }

    public PlayerState getPlayerState() {
        return playerState;
    }

    public String getName() {
        return name;
    }

    public void reset() {
        score = 0;
        hand.clear();
        playerState = PlayerState.PLAYING;
    }
}
