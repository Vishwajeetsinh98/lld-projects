package table;

import players.BlackjackPlayer;
import players.Dealer;
import players.PlayerState;
import shoe.Card;
import shoe.Shoe;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BlackjackTable {
    private Shoe shoe;
    private final List<BlackjackPlayer> players;
    private Map<BlackjackPlayer, Double> bets;
    private Dealer dealer;
    private GameInputController gameInputController;

    public BlackjackTable() {
        players = new ArrayList<>();
        bets = new ConcurrentHashMap<>();
    }

    public void startRound() {
        for (BlackjackPlayer player : players) {
            player.reset();
            System.out.println(player.getName() + " in? y/n: ");
            char input = gameInputController.getNextInput();
            if (input == 'y') continue;
            else {
                player.watchRound();
            }
        }
        dealer.reset();
        takeBets();
        dealInitialCards();
        proceed();
    }

    private void giveCardToPlayer(boolean hidden,
                                 BlackjackPlayer player) {
        Card card = shoe.getNextCard();
        if (hidden) {
            ((Dealer) player).addHiddenCard(card);
            return;
        }
        player.addToHand(card);
    }

    private void takeBets() {
        bets.clear();
        for (BlackjackPlayer player : players) {
            if (player.getPlayerState() == PlayerState.PLAYING) {
                System.out.println(player.getName() + " enter bet: ");
                double amt = gameInputController.getNextNumber();
                if (player.bet(amt)) {
                    bets.put(player, amt);
                }
            }
        }
    }

    private void dealInitialCards() {
        for (BlackjackPlayer player : players) {
            if (player.getPlayerState() == PlayerState.PLAYING) {
                giveCardToPlayer(false, player);
                giveCardToPlayer(false, player);
            }
        }
        giveCardToPlayer(true, dealer);
        giveCardToPlayer(false, dealer);
    }

    private void showAllPlayerHands() {
        for (BlackjackPlayer player : players)
            player.showPlayerHand();
        dealer.showPlayerHand();
    }

    private void proceed() {
        showAllPlayerHands();
        if (dealer.getPlayerState() == PlayerState.BUST) {
            dealerBust();
        }
        for (BlackjackPlayer player : players) {
            if (player.getPlayerState() == PlayerState.PLAYING) {
                System.out.println(player.getName() + " (h)it or (s)tand?");
                char input = gameInputController.getNextInput();
                if (input == 'h') player.hit();
                else player.stand();
            }
        }

        if (anyPlayersLeft()) proceed();
        else completeDealerRound();
    }

    private void dealerBust() {
        System.out.println("Dealer bust! Everyone wins!");
        for (BlackjackPlayer player : players) {
            if (player.getPlayerState() == PlayerState.PLAYING || player.getPlayerState() == PlayerState.STAND)
                player.win();
        }
        payoutPlayers();
    }

    private void completeDealerRound() {
        dealer.showHiddenCard();
        while(dealer.getScore() < 17) {
            dealer.hit();
        }

        if (dealer.getPlayerState() == PlayerState.BUST) {
            dealerBust();
            return;
        }

        // Compare dealer score with standing players.
        for (BlackjackPlayer player : players) {
            if (player.getPlayerState() == PlayerState.STAND) {
                if (player.getScore() < dealer.getScore())
                    player.lose();
                else if (player.getScore() == dealer.getScore())
                    player.tie();
                else player.win();
            }
        }

        payoutPlayers();

    }

    private boolean anyPlayersLeft() {
        boolean ret = true;
        for (BlackjackPlayer player : players) {
            ret = ret & player.getPlayerState() == PlayerState.PLAYING;
        }
        return ret;
    }

    private void payoutPlayers() {
        System.out.println("Paying out players....");
        for (BlackjackPlayer player : players) {
            double amount = bets.get(player);
            if (player.getPlayerState() == PlayerState.BLACKJACK ||
                    player.getPlayerState() == PlayerState.WIN ||
                    player.getPlayerState() == PlayerState.TIE) {
                amount *= switch(player.getPlayerState()) {
                    case BLACKJACK -> 2.5;
                    case WIN -> 2.0;
                    default -> 1.0;
                };
                System.out.println("Player " + player.getName() + " winnings: " + amount);
                player.addMoney(amount);
            }
        }
    }

    public Card getNextCard() {
        return shoe.getNextCard();
    }

    public void addGameInputController(GameInputController gameInputController) {
        this.gameInputController = gameInputController;
    }

    public void addShoe(Shoe shoe) {
        this.shoe = shoe;
    }

    public void addDealer(Dealer dealer) {
        this.dealer = dealer;
    }

    public void addPlayer(BlackjackPlayer player) {
        players.add(player);
    }

    public void removePlayer(BlackjackPlayer player) {
        players.remove(player);
    }

    public void removeDealer(BlackjackPlayer dealer) {
        if (dealer.equals(this.dealer))
            dealer = null;
    }
}
