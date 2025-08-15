package machine;

import snack.Snack;
import state.State;
import state.impl.IdleState;

import java.util.HashMap;
import java.util.Map;

public class VendingMachine implements VendingMachineInterface {
    private double money;
    private final Map<Snack, Integer> snackCount;
    private State state;
    private Snack snackToDispense;

    public VendingMachine() {
        money = 0.0;
        snackCount = new HashMap<>();
        state = new IdleState(this);
        refillMachine();
    }

    public void enterMoney(double money) {
        state.getMoney(money);
    }

    public void selectSnack(String snackName) {
        state.chooseSnack(snackName);
    }

    public void giveSnack() {
        state.dispense();
    }

    public void dispenseSnack() {
        System.out.println("[VendingMachine] Dispensing: " + snackToDispense);
        decrementSnackCount(snackToDispense);
        snackToDispense = null;
    }

    public void refillMachine() {
        Snack[] snacks = {new Snack("Apple", 0.25),
                          new Snack("Chips", 1.2),
                          new Snack("Soda", 0.99)};
        for (int i = 0;i < 1;i++) {
            snackCount.put(snacks[i % 3], snackCount.getOrDefault(snacks[i % 3], 0) + 1);
        }
    }

    public void changeState (State newState) {
        System.out.println("Changing state to: " + newState);
        this.state = newState;
    }

    public void decrementSnackCount(Snack snack) {
        snackCount.put(snack, snackCount.get(snack) - 1);
    }
    public void setMoney(double money) {
        this.money = money;
    }
    public double getMoney() {
        return this.money;
    }

    public Map<Snack, Integer> getSnackCount() {
        return this.snackCount;
    }

    public Snack getSnackToDispense() {
        return snackToDispense;
    }

    public void setSnackToDispense(Snack snackToDispense) {
        this.snackToDispense = snackToDispense;
    }

    public void returnMoney() {
        System.out.println("Returning $" + money);
        money = 0.0;
    }

}
