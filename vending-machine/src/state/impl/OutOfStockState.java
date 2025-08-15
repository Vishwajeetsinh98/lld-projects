package state.impl;

import machine.VendingMachine;
import state.State;

public class OutOfStockState extends State {
    public OutOfStockState(VendingMachine machine) {
        super(machine);
    }

    @Override
    public void getMoney(double money) {
        System.out.println("[OutOfStockState] Machine out of stock!");
    }

    @Override
    public void chooseSnack(String snackName) {
        System.out.println("[OutOfStockState] Machine out of stock!");
    }

    @Override
    public void dispense() {
        System.out.println("[OutOfStockState] Machine out of stock!");
        machine.returnMoney();
    }

    @Override
    public void refill() {
        System.out.println("[OutOfStockState] Refilling machine");
        machine.refillMachine();
        machine.changeState(new IdleState(machine));
    }

    public String toString() {return "OutOfStockState";}

}
