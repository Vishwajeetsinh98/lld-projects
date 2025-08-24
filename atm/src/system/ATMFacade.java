package system;

import card.Card;

public class ATMFacade {

    private ATM atm;
    public ATMFacade() {
        atm = ATM.getInstance();
    }

    public void initiateTransaction(Card card) {
        atm.reset();
        atm.initiateTransaction(card);
    }

    public void authenticatePin() throws Exception {
        atm.authenticatePin();
    }

    public void selectOption() {
        atm.selectOption();
    }

    public void balanceCheck() {
        atm.balanceCheck();
    }

    public void withdraw() {
        atm.withdraw();
    }

    public void transfer() {
        atm.transfer();
    }

    public void cancel() {
        atm.completeTransaction(true);
    }
}
