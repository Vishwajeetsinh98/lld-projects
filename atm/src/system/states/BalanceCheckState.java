package system.states;

import card.Card;
import network.NetworkInfra;
import printer.Receipt;
import system.ATM;

public class BalanceCheckState implements ATMState {

    private ATM atm;
    public BalanceCheckState() {
        atm = ATM.getInstance();
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalArgumentException("Cannot insert card in an active transaction!");
    }

    @Override
    public void authenticatePin() {
        // Do nothing.
        System.out.println("[BalanceCheckState] authenticatePin");
    }

    @Override
    public void selectOption() {
        // Do nothing.
        System.out.println("[BalanceCheckState] selectOption");
    }

    @Override
    public void enquireBalance() {
        NetworkInfra networkInfra = atm.getNetworkInfra();
        double amount = networkInfra.getBalance(atm.getActiveAccount());
        System.out.println("[BalanceCheckState] balance in account: " + amount);
        Receipt receipt = new Receipt(atm.getActiveAccount().getNumber(), amount, "");
        atm.getPrinter().print(receipt);
        atm.completeTransaction(false);
    }

    @Override
    public void withdrawCash() {
        // Do nothing.
        System.out.println("[BalanceCheckState] withdrawCash");
    }

    @Override
    public void fundTransfer() {
        // Do nothing.
        System.out.println("[BalanceCheckState] fundTransfer");
    }
}
