package state;

import machine.VendingMachine;

public abstract class State {

    protected VendingMachine machine;
    public State(VendingMachine machine) {
        this.machine = machine;
    }

    public abstract void getMoney(double money);
    public abstract void chooseSnack(String snackName);
    public abstract void dispense();
    public abstract void refill();

}
