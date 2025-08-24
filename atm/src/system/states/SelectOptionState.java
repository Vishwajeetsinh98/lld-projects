package system.states;

import card.Card;
import system.ATM;

public class SelectOptionState implements ATMState {

    private ATM atm;
    public SelectOptionState() {
        atm = ATM.getInstance();
        atm.getScreen().displayLines(new String[]{"Your options: ",
                                                  "w/W: Withdraw cash",
                                                  "b/B: Balance Inquiry",
                                                  "f/F: Fund Transfer",
                                                  "c/C: Cancel transaction"});
    }

    @Override
    public void insertCard(Card card) {
        throw new IllegalArgumentException("Cannot insert card in an active transaction!");
    }

    @Override
    public void authenticatePin() {
        // Do nothing.
        System.out.println("[SelectOptionState] authenticatePin");
    }

    @Override
    public void selectOption() {
        char input = atm.getKeyboard().getNextString().toLowerCase().charAt(0);
        switch(input) {
            case 'w':
                System.out.println("[SelectOptionState] Transferring to withdrawal");
                atm.setState(new CashWithdrawState());
                break;
            case 'b':
                System.out.println("[SelectOptionState] Transferring to balance enquiry");
                atm.setState(new BalanceCheckState());
                break;
            case 'f':
                System.out.println("[SelectOptionState] Transferring to fund transfer");
                atm.setState(new FundTransferState());
                break;
            default:
                throw new IllegalArgumentException("Incorrect option selected");
        }
    }

    @Override
    public void enquireBalance() {
        // Do nothing.
        System.out.println("[SelectOptionState] enquireBalance");
    }

    @Override
    public void withdrawCash() {
        // Do nothing.
        System.out.println("[SelectOptionState] withdrawCash");
    }

    @Override
    public void fundTransfer() {
        // Do nothing.
        System.out.println("[SelectOptionState] fundTransfer");
    }
}
