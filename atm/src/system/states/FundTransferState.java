package system.states;

import accounts.Account;
import card.Card;
import system.ATM;

public class FundTransferState implements ATMState {

    private ATM atm;
    public FundTransferState() {
        atm = ATM.getInstance();
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalArgumentException("Cannot insert card in an active transaction!");
    }

    @Override
    public void authenticatePin() {
        // Do nothing.
        System.out.println("[FundTransferState] authenticatePin");
    }

    @Override
    public void selectOption() {
        // Do nothing.
        System.out.println("[FundTransferState] selectOption");
    }

    @Override
    public void enquireBalance() {
        // Do nothing.
        System.out.println("[FundTransferState] enquireBalance");
    }

    @Override
    public void withdrawCash() {
        // Do nothing.
        System.out.println("[FundTransferState] withdrawCash");
    }

    @Override
    public void fundTransfer() {
        atm.getScreen().displayMessage("Enter receiver account number");
        long receiver = atm.getKeyboard().getNextLong();

        Account receiverAccount = atm.getNetworkInfra().getAccount(receiver);

        atm.getScreen().displayMessage("Enter amount to transfer");
        double amount = atm.getKeyboard().getNextDouble();

        if (atm.getNetworkInfra().checkBalance(atm.getActiveAccount(), amount)) {
            System.out.println("[FundTransferState] Transferring " + amount + " from: " + atm.getActiveAccount().getNumber() + " to: " + receiver);
            atm.getNetworkInfra().decrementBalance(atm.getActiveAccount(), amount);
            atm.getNetworkInfra().incrementBalance(receiverAccount, amount);
            atm.completeTransaction(false);
        } else {
            System.out.println("[FundTransferState] Insufficient funds in balance!");
            atm.completeTransaction(true);
        }
    }
}
