package system.states;

import card.Card;
import system.ATM;

public class CashWithdrawState implements ATMState {

    private ATM atm;
    public CashWithdrawState() {
        atm = ATM.getInstance();
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalArgumentException("Cannot insert card in an active transaction!");
    }

    @Override
    public void authenticatePin() {
        // Do nothing.
        System.out.println("[CashWithdrawState] authenticatePin");
    }

    @Override
    public void selectOption() {
        // Do nothing.
        System.out.println("[CashWithdrawState] selectOption");
    }

    @Override
    public void enquireBalance() {
        // Do nothing.
        System.out.println("[CashWithdrawState] enquireBalance");
    }

    @Override
    public void withdrawCash() {
        // Skipping savings and current account check
        atm.getScreen().displayMessage("Enter amount (multiples of 100, 200, 500, 2000): ");
        double amount = atm.getKeyboard().getNextDouble();
        if (atm.getNetworkInfra().checkBalance(atm.getActiveAccount(), amount)) {
            if (atm.getDispenser().dispenseMoney(amount)) {
                System.out.println("[CashWithdrawState] amount successfully disbursed");
                atm.getNetworkInfra().decrementBalance(atm.getActiveAccount(), amount);
                atm.completeTransaction(false);
            } else {
                System.out.println("[CashWithdrawState] Unable to dispense amount. Canceling transaction");
                atm.completeTransaction(true);
            }
        } else {
            throw new IllegalArgumentException("Amount entered exceeds balance");
        }
    }

    @Override
    public void fundTransfer() {
        // Do nothing.
        System.out.println("[CashWithdrawState] fundTransfer");
    }
}
