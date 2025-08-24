package system.states;


import card.Card;
import system.ATM;

public class IdleState implements ATMState {

    private final ATM atm;

    public IdleState() {
        atm = ATM.getInstance();
        atm.getScreen().displayMessage("[IdleState] ATM Ready...");
    }

    @Override
    public void insertCard(Card card) {
        if (!atm.getCardReader().readCard(card))
            return;
        atm.setActiveAccount(atm.getNetworkInfra().getAccount(card.getAccountNumber()));
        atm.setState(new CardEnteredState());
    }

    @Override
    public void authenticatePin() {
        // Do nothing.
        System.out.println("[IdleState] authenticatePin");
    }

    @Override
    public void selectOption() {
        // Do nothing.
        System.out.println("[IdleState] selectOption");
    }

    @Override
    public void enquireBalance() {
        // Do nothing.
        System.out.println("[IdleState] enquireBalance");
    }

    @Override
    public void withdrawCash() {
        // Do nothing.
        System.out.println("[IdleState] withdrawCash");
    }

    @Override
    public void fundTransfer() {
        // Do nothing.
        System.out.println("[IdleState] fundTransfer");
    }
}
