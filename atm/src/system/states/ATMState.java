package system.states;

import card.Card;

public interface ATMState {
    public void insertCard(Card card);
    public void authenticatePin() throws Exception;
    public void selectOption();
    public void enquireBalance();
    public void withdrawCash();
    public void fundTransfer();
}
