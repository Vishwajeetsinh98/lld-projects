import accounts.Account;
import card.Card;
import system.ATM;
import system.ATMFacade;


public class Main {
    public static void main(String[] args) throws Exception {
        Account ac1 = new Account(1234567890, 1000.5);
        Account ac2 = new Account(456123, 500);
        Card card = new Card("card", 1234567890, 1234);

        ATM.getInstance().getNetworkInfra().addAccount(ac1);
        ATM.getInstance().getNetworkInfra().addAccount(ac2);

        ATMFacade atm = new ATMFacade();

        atm.initiateTransaction(card);
        atm.authenticatePin();
        atm.selectOption();
        atm.withdraw();
        atm.selectOption();
        atm.balanceCheck();
        atm.selectOption();
        atm.transfer();
    }
}
