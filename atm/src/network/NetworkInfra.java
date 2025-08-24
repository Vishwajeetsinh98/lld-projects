package network;

import accounts.Account;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NetworkInfra {

    private final Map<Long, Account> accountMap;

    public NetworkInfra() {
        accountMap = new ConcurrentHashMap<>();
    }

    public void addAccount(Account account) {
        if (accountMap.containsKey(account.getNumber())) {
            System.out.println("[ATM] Account already exists! Ignoring..");
            return;
        }
        accountMap.put(account.getNumber(), account);
    }

    public Account getAccount(long accountNumber) {
        if (!accountMap.containsKey(accountNumber))
            throw new IllegalArgumentException("Account number not found!");
        return accountMap.get(accountNumber);
    }

    public double getBalance(Account account) {
        System.out.println("[NetworkInfra] Fetching balance for account: " + account.getNumber());
        return account.getBalance();
    }

    public boolean checkBalance(Account account, double amount) {
        System.out.println("[NetworkInfra] Checking balance for " + amount + " for account: " + account.getNumber());
        return account.getBalance() >= amount;
    }

    public void incrementBalance(Account account, double amount) {
        System.out.println("[NetworkInfra] Incrementing balance by " + amount + " for account: " + account.getNumber());
        account.incrementBalance(amount);
    }

    public void decrementBalance(Account account, double amount) {
        System.out.println("[NetworkInfra] Decrementing balance by " + amount + " for account: " + account.getNumber());
        account.decrementBalance(amount);
    }
}
