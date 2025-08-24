package system.states;

import card.Card;
import system.ATM;

public class CardEnteredState implements ATMState {

    private ATM atm;
    public CardEnteredState() {
        atm = ATM.getInstance();
        atm.getScreen().displayMessage("Enter PIN: ");
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalArgumentException("Cannot insert card in an active transaction!");
    }

    @Override
    public void authenticatePin() throws Exception {
        int pin = (int) atm.getKeyboard().getNextLong();
        if (!atm.getInsertedCard().validatePin(pin))
            throw new Exception("Incorrect PIN!");
        atm.setState(new SelectOptionState());
    }

    @Override
    public void selectOption() {
        // Do nothing.
        System.out.println("[CardEnteredState] selectOption");
    }

    @Override
    public void enquireBalance() {
        // Do nothing.
        System.out.println("[CardEnteredState] enquireBalance");
    }

    @Override
    public void withdrawCash() {
        // Do nothing.
        System.out.println("[CardEnteredState] withdrawCash");
    }

    @Override
    public void fundTransfer() {
        // Do nothing.
        System.out.println("[CardEnteredState] fundTransfer");
    }
}
