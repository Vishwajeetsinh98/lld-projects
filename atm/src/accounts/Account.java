package accounts;

public class Account {
    private final long number;
    private double balance;
    public Account(long number, double balance) {
        this.number = number;
        this.balance = balance;
    }

    public long getNumber() {
        return number;
    }

    public double getBalance() {
        return balance;
    }

    public void incrementBalance(double amount) {
        balance += amount;
    }

    public void decrementBalance(double amount) {
        balance -= amount;
    }
}
