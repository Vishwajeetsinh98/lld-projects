import players.BlackjackPlayer;
import players.Dealer;
import players.Player;
import shoe.*;
import table.BlackjackTable;
import table.GameInputController;

public class Main {
    public static void main(String[] args) {
        Deck deck = new Deck();
        Shoe shoe = new Shoe();
        shoe.addDeck(deck);

        BlackjackPlayer dealer = new Dealer("Dealer");
        BlackjackPlayer player = new Player("P1", 5000);
        GameInputController gameInputController = new GameInputController();

        BlackjackTable table = new BlackjackTable();
        player.enterGame(table);
        dealer.enterGame(table);
        table.addShoe(shoe);
        table.addGameInputController(gameInputController);

        table.startRound();

    }
}