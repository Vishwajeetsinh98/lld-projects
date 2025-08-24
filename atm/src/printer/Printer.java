package printer;

public class Printer {
    public void print(Receipt receipt) {
        System.out.println("[Printer] Printing receipt: ");
        System.out.println("Account: " + receipt.accountNumber());
        System.out.println("Balance: " + receipt.balance());
        System.out.println("Message: " + receipt.message());
    }
}
