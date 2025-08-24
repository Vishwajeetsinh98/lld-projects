package system;

import accounts.Account;
import card.Card;
import card.CardReader;
import dispenser.CashDispenser;
import keyboard.Keyboard;
import network.NetworkInfra;
import printer.Printer;
import screen.Screen;
import system.states.ATMState;
import system.states.IdleState;
import system.states.SelectOptionState;

import java.util.Map;

public class ATM {
    private CardReader cardReader;
    private Screen screen;
    private NetworkInfra networkInfra;
    private CashDispenser dispenser;
    private Keyboard keyboard;
    private Printer printer;
    private ATMState state;
    private Card insertedCard;
    private Account activeAccount;

    private ATM() {
        cardReader = new CardReader();
        screen = new Screen();
        networkInfra = new NetworkInfra();
        dispenser = new CashDispenser();
        keyboard = new Keyboard();
        printer = new Printer();
        insertedCard = null;
        activeAccount = null;
    }

    public void reset() {
        state = new IdleState();
    }

    public void initiateTransaction(Card card) {
        insertedCard = card;
        state.insertCard(card);
    }

    public void authenticatePin() throws Exception {
        state.authenticatePin();
    }

    public void selectOption() {
        state.selectOption();
    }

    public void balanceCheck() {
        state.enquireBalance();
    }

    public void withdraw() {
        state.withdrawCash();
    }

    public void transfer() {
        state.fundTransfer();
    }

    public void completeTransaction(boolean canceled) {
        if (canceled) {
            System.out.println("[ATM] Canceling transaction");
            returnCard();
            state = new IdleState();
        } else {
            screen.displayMessage("Transaction completed. Do you wish to transact more? y/n");
            char input = keyboard.getNextString().toLowerCase().charAt(0);
            if (input != 'y') {
                returnCard();
                state = new IdleState();
            } else {
                state = new SelectOptionState();
            }
        }
    }

    public void cancelTransaction() {
        completeTransaction(true);
    }

    public void returnCard() {
        if (insertedCard != null) {
            System.out.println("[ATM] Returning card");
            insertedCard = null;
            activeAccount = null;
        }
    }

    public CardReader getCardReader() {
        return cardReader;
    }

    public Screen getScreen() {
        return screen;
    }

    public NetworkInfra getNetworkInfra() {
        return networkInfra;
    }

    public CashDispenser getDispenser() {
        return dispenser;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Printer getPrinter() {
        return printer;
    }

    public ATMState getState() {
        return state;
    }

    public void setState(ATMState state) {
        this.state = state;
    }

    public Card getInsertedCard() {
        return insertedCard;
    }

    public Account getActiveAccount() {
        return activeAccount;
    }

    public void setActiveAccount(Account activeAccount) {
        this.activeAccount = activeAccount;
    }

    // Singleton:
    private static final class Holder {
        private static final ATM INSTANCE = new ATM();
    }

    public static ATM getInstance() {
        return Holder.INSTANCE;
    }
}
